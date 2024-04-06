package com.bitirmeodev.halisahambe.library.security;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserService;
import com.bitirmeodev.halisahambe.domain.auth.user.impl.User;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String JWT_BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    private static final String VERIFICATION = "/users/send-verification";

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        final String token;
        final String username;

        if (authHeader == null || !authHeader.startsWith(JWT_BEARER)){
            filterChain.doFilter(request,response);
            return;
        }

        token = authHeader.substring(7);
        username = jwtUtil.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        if (request.getMethod().equals("POST") && request.getRequestURI().equals(VERIFICATION)){
            filterChain.doFilter(request,response);
            return;
        }

        CustomUserDetails customUserDetails = userDetailsService.loadUserByUsername(username);
        User user = customUserDetails.getUser();

        if (user != null && !user.getIsVerified()){
            throw new BaseException(MessageCodes.UNAUTHORIZED);
        }

        filterChain.doFilter(request,response);

    }
}

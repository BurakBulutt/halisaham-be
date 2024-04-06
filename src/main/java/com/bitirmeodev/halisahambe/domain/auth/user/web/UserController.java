package com.bitirmeodev.halisahambe.domain.auth.user.web;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserMapper;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserService;
import com.bitirmeodev.halisahambe.library.exception.MetaResponse;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private static final String VERIFY = "HESAP ONAYLANDI";
    private final UserService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<DataResponse<UserResponse>> getAll(){
        return response(UserMapper.toDataResponse(service.getAll()));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<UserResponse> getById(@PathVariable String id){
        return response(UserMapper.toResponse(service.getById(id)));
    }

    @GetMapping("email/{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<UserResponse> getByEmail(@PathVariable String email){
        return response(UserMapper.toResponse(service.getByEmail(email)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<UserResponse> save(@RequestBody UserRequest request){
        return response(UserMapper.toResponse(service.save(UserMapper.toDto(request))));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<UserResponse> update(@PathVariable String id, @RequestBody UserRequest request){
        return response(UserMapper.toResponse(service.update(id,UserMapper.toDto(request))));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<Void> delete(@PathVariable String id){
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

    @GetMapping("verificate")
    public ResponseEntity<String> verificateUser(@RequestParam(name = "code") String code){
        service.verificateUser(code);
        return ResponseEntity.ok(VERIFY);
    }

    @PostMapping("send-verification")
    public Response<Void> sendVerificationCode(){
        service.sendVerificationMail();
        return new Response<>(MetaResponse.success());
    }
}

package com.bitirmeodev.halisahambe.domain.auth.user.web;

import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController extends BaseController {

    @GetMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<String> sayHi(){
        return response("Merhaba");
    }
}

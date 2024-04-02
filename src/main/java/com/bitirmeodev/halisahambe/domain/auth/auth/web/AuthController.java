package com.bitirmeodev.halisahambe.domain.auth.auth.web;

import com.bitirmeodev.halisahambe.domain.auth.auth.impl.AuthServiceImpl;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthServiceImpl authService;


}

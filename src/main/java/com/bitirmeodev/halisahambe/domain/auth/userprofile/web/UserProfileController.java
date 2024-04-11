package com.bitirmeodev.halisahambe.domain.auth.userprofile.web;

import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileMapper;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileService;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-profiles")
@RequiredArgsConstructor
public class UserProfileController extends BaseController {
    private final UserProfileService service;

    @GetMapping("/find-user-id/{id}")
    public Response<UserProfileResponse> getByUserId(@PathVariable String id){
        return response(UserProfileMapper.toResponse(service.getByUserId(id)));
    }

    @GetMapping("{id}")
    public Response<UserProfileResponse> getById(@PathVariable String id){
        return response(UserProfileMapper.toResponse(service.getByUserId(id)));
    }

    @PutMapping("{id}")
    public Response<UserProfileResponse> update(@PathVariable String id,@RequestBody UserProfileRequest request){
        return response(UserProfileMapper.toResponse(service.update(id,UserProfileMapper.toDto(request))));
    }
}

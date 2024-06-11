package com.bitirmeodev.halisahambe.domain.auth.userprofile.web;

import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileMapper;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileService;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-profiles")
@RequiredArgsConstructor
public class UserProfileController extends BaseController {
    private final UserProfileService service;

    @PostMapping("get-profiles-bulk")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<DataResponse<UserProfileResponse>> getAllByUserId(@RequestBody UserProfileBulkRequest request){
        return response(UserProfileMapper.toDataResponse(service.getAllByUserIdIn(request.userIds())));
    }

    @GetMapping("/find-user-id/{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<UserProfileResponse> getByUserId(@PathVariable String id){
        return response(UserProfileMapper.toResponse(service.getByUserId(id)));
    }

    @GetMapping("find-user")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<UserProfileResponse> getUser(@RequestParam String token){
        return response(UserProfileMapper.toResponse(service.getProfileUser(token)));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<UserProfileResponse> getById(@PathVariable String id){
        return response(UserProfileMapper.toResponse(service.getByUserId(id)));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<UserProfileResponse> update(@PathVariable String id,@RequestBody UserProfileRequest request){
        return response(UserProfileMapper.toResponse(service.update(id,UserProfileMapper.toDto(request))));
    }

}

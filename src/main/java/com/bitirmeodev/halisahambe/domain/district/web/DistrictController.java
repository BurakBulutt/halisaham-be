package com.bitirmeodev.halisahambe.domain.district.web;

import com.bitirmeodev.halisahambe.domain.district.api.DistrictMapper;
import com.bitirmeodev.halisahambe.domain.district.api.DistrictService;
import com.bitirmeodev.halisahambe.library.exception.MetaResponse;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("districts")
@RequiredArgsConstructor
public class DistrictController extends BaseController {
    private final DistrictService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<DataResponse<DistrictResponse>> getAll(){
        return response(DistrictMapper.toDataResponse(service.getAll()));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<DistrictResponse> getById(@PathVariable String id){
        return response(DistrictMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<DistrictResponse> save(@RequestBody DistrictRequest request){
        return response(DistrictMapper.toResponse(service.save(DistrictMapper.toDto(request))));
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<DistrictResponse> update(@PathVariable String id,@RequestBody DistrictRequest request){
        return response(DistrictMapper.toResponse(service.update(id,DistrictMapper.toDto(request))));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<Void> delete(@PathVariable String id){
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}

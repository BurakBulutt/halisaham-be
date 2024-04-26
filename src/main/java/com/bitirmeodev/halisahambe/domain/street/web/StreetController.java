package com.bitirmeodev.halisahambe.domain.street.web;

import com.bitirmeodev.halisahambe.domain.district.api.DistrictMapper;
import com.bitirmeodev.halisahambe.domain.district.api.DistrictService;
import com.bitirmeodev.halisahambe.domain.district.web.DistrictRequest;
import com.bitirmeodev.halisahambe.domain.district.web.DistrictResponse;
import com.bitirmeodev.halisahambe.domain.street.api.StreetMapper;
import com.bitirmeodev.halisahambe.domain.street.api.StreetService;
import com.bitirmeodev.halisahambe.library.exception.MetaResponse;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("streets")
@RequiredArgsConstructor
public class StreetController extends BaseController {
    private final StreetService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<DataResponse<StreetResponse>> getAll(){
        return response(StreetMapper.toDataResponse(service.getAll()));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<StreetResponse> getById(@PathVariable String id){
        return response(StreetMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<StreetResponse> save(@RequestBody StreetRequest request){
        return response(StreetMapper.toResponse(service.save(StreetMapper.toDto(request))));
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<StreetResponse> update(@PathVariable String id,@RequestBody StreetRequest request){
        return response(StreetMapper.toResponse(service.update(id,StreetMapper.toDto(request))));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<Void> delete(@PathVariable String id){
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}

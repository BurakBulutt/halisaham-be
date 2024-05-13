package com.bitirmeodev.halisahambe.domain.city.web;

import com.bitirmeodev.halisahambe.domain.city.api.CityMapper;
import com.bitirmeodev.halisahambe.domain.city.api.CityService;
import com.bitirmeodev.halisahambe.library.exception.MetaResponse;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class CityController extends BaseController {
    private final CityService service;

    @GetMapping("get-all")
    @PreAuthorize("hasAnyRole('admin','user')")
    public Response<DataResponse<CityResponse>> getAll(){
        return response(CityMapper.toDataResponse(service.getAll()));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<CityResponse> getById(@PathVariable String id){
        return response(CityMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<CityResponse> save(@RequestBody CityRequest request){
        return response(CityMapper.toResponse(service.save(CityMapper.toDto(request))));
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<CityResponse> update(@PathVariable String id,@RequestBody CityRequest request){
        return response(CityMapper.toResponse(service.update(id,CityMapper.toDto(request))));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<Void> delete(@PathVariable String id){
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}

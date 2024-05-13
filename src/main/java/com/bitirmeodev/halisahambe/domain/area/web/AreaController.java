package com.bitirmeodev.halisahambe.domain.area.web;

import com.bitirmeodev.halisahambe.domain.area.api.AreaMapper;
import com.bitirmeodev.halisahambe.domain.area.api.AreaService;
import com.bitirmeodev.halisahambe.library.exception.MetaResponse;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("areas")
@RequiredArgsConstructor
public class AreaController extends BaseController {
    private final AreaService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<DataResponse<AreaResponse>> getAll(){
        return response(AreaMapper.toDataResponse(service.getAll()));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<AreaResponse> getById(@PathVariable String id){
        return response(AreaMapper.toResponse(service.getById(id)));
    }

    @GetMapping("find-district-and-street")
    @PreAuthorize("hasAnyRole('admin','user')")
    public Response<DataResponse<AreaResponse>> getById(@RequestParam String districtId, @RequestParam String streetId){
        return response(AreaMapper.toDataResponse(service.getByDistrictIdAndStreetId(districtId,streetId)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<AreaResponse> save(@RequestBody AreaRequest request) throws IOException {
        return response(AreaMapper.toResponse(service.save(AreaMapper.toDto(request))));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<AreaResponse> update(@PathVariable String id,@RequestBody AreaRequest request) throws IOException{
        return response(AreaMapper.toResponse(service.update(id,AreaMapper.toDto(request))));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<Void> delete(@PathVariable String id){
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

}

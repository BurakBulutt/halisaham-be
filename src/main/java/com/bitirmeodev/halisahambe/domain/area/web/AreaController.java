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
      //  return response(AreaMapper.toResponse(service.getById(id)));
        return null;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin')")
    public Response<AreaResponse> save(@RequestParam("request") String areaRequest,@RequestParam("image") MultipartFile multipartFile) throws IOException {
        AreaRequest request = new ObjectMapper().readValue(areaRequest, AreaRequest.class);
        return response(AreaMapper.toResponse(service.save(AreaMapper.toDto(request,multipartFile))));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<AreaResponse> update(@PathVariable String id,@RequestParam("request") String areaRequest,@RequestParam("image") MultipartFile multipartFile) throws IOException{
        AreaRequest request = new ObjectMapper().readValue(areaRequest, AreaRequest.class);
        return response(AreaMapper.toResponse(service.update(id,AreaMapper.toDto(request,multipartFile))));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public Response<Void> delete(@PathVariable String id){
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

}

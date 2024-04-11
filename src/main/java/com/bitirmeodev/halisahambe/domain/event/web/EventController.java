package com.bitirmeodev.halisahambe.domain.event.web;

import com.bitirmeodev.halisahambe.domain.event.api.EventMapper;
import com.bitirmeodev.halisahambe.domain.event.api.EventService;
import com.bitirmeodev.halisahambe.library.exception.MetaResponse;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("events")
@RequiredArgsConstructor
public class EventController extends BaseController {
    private final EventService service;

    @GetMapping("find-all-by-user-id/{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<DataResponse<EventResponse>> getAllByUserId(@PathVariable String id){
        return response(EventMapper.toDataResponse(service.getAllByUserId(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<EventResponse> save(@RequestBody EventRequest request){
        return response(EventMapper.toResponse(service.save(EventMapper.toDto(request))));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<EventResponse> update(@PathVariable String id,@RequestBody EventRequest request){
        return response(EventMapper.toResponse(service.update(id,EventMapper.toDto(request))));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<Void> delete(@PathVariable String id){
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}

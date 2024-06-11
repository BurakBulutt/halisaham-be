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

    @GetMapping
    public Response<DataResponse<EventResponse>> getAllEvents() {
        return response(EventMapper.toDataResponse(service.getAll()));
    }

    @GetMapping("user-events")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<DataResponse<EventResponse>> getUserEvents() {
        return response(EventMapper.toDataResponse(service.getUserEvents()));
    }

    @GetMapping("find-all-param")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<DataResponse<EventResponse>> getByCityAndDistrictAndStreetAndArea(@RequestParam String cityId,@RequestParam String districtId,@RequestParam String streetId,@RequestParam String areaId) {
        return response(EventMapper.toDataResponse(service.getByCityAndDistrictAndStreetAndArea(cityId,districtId,streetId,areaId)));
    }

    @GetMapping("find-without-area")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<DataResponse<EventResponse>> getByCityAndDistrictAndStreet(@RequestParam String cityId,@RequestParam String districtId,@RequestParam String streetId) {
        return response(EventMapper.toDataResponse(service.getByCityAndDistrictAndStreet(cityId,districtId,streetId)));
    }

    @PostMapping("join-event")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<EventResponse> joinEvent(@RequestParam String eventId) {
        return response(EventMapper.toResponse(service.joinEvent(eventId)));
    }

    @PutMapping("delete-user-event/{eventId}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<Void> deleteUserOnEvent(@PathVariable String eventId, @RequestParam String userId) {
        service.deleteUserOnEvent(eventId, userId);
        return new Response<>(MetaResponse.success());
    }

    @DeleteMapping("exit-event/{eventId}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<Void> exitEvent(@PathVariable String eventId) {
        service.exitOnEvent(eventId);
        return new Response<>(MetaResponse.success());
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<EventResponse> save(@RequestBody EventRequest request) {
        return response(EventMapper.toResponse(service.save(EventMapper.toDto(request))));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<EventResponse> update(@PathVariable String id, @RequestBody EventRequest request) {
        return response(EventMapper.toResponse(service.update(id, EventMapper.toDto(request))));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

    @GetMapping("check-event-authority/{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<Boolean> checkAdmin(@PathVariable String id){
        return response(service.checkEventAuthority(id));
    }
}

package com.company.hoteladministrator.controller;

import com.company.hoteladministrator.api.service.IGuestService;
import com.company.hoteladministrator.model.dto.AccommodateGuestDto;
import com.company.hoteladministrator.model.dto.GuestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GuestController {

    private final IGuestService guestService;

    @Autowired
    private GuestController(IGuestService guestService) {
        this.guestService = guestService;
    }


    @PostMapping(value = "/admin/guest")
    @ResponseBody
    public GuestDto addGuest(@RequestBody GuestDto guestDto) {
        return guestService.addGuest(guestDto);
    }

    @PutMapping(value = "/user/accommodate-guest")
    public void accommodateGuestToRoom(@RequestBody AccommodateGuestDto accommodateGuestDto) {
        guestService.accommodateToRoom(accommodateGuestDto);
    }

    @PutMapping(value = "/user/evict-guest/{guestId}")
    public void evictGuestFromRoom(@PathVariable Long guestId) {
        guestService.evictFromRoom(guestId);
    }

    @PutMapping(value = "/user/maintenance/{guestId}/{maintenanceId}")
    public void orderMaintenance(@PathVariable Long guestId, @PathVariable Long maintenanceId) {
        guestService.orderMaintenance(guestId, maintenanceId);
    }

    @PutMapping(value = "/admin/guest")
    @ResponseBody
    public GuestDto changeGuestInfo(@RequestBody GuestDto guestDto) {
        return guestService.changeGuestInfo(guestDto);
    }

    @GetMapping(value = "/user/amount-of-payment/{guestId}")
    @ResponseBody
    public Double getAmountOfPaymentForTheRoom(@PathVariable Long guestId) {
        return guestService.getAmountOfPaymentForTheRoom(guestId);
    }

    @GetMapping(value = "/user/last-guests-of-room/{roomId}")
    public List<GuestDto> getLastGuestsOfRoom(@PathVariable Long roomId) {
        return guestService.getLastGuests(roomId);
    }

    @GetMapping(value = "/user/guests")
    public List<GuestDto> getAllGuests() {
        return guestService.getAllGuests();
    }

    @GetMapping(value = "/user/number-of-guests")
    public Integer getNumberOfGuests() {
        return guestService.getNumberOfGuests();
    }

    @GetMapping(value = "/user/sorted-guests/{paramToSort}")
    public List<GuestDto> getSortedGuests(@PathVariable String paramToSort) {
        return guestService.getSortedGuests(paramToSort);
    }

    @GetMapping(value = "/user/guests/{guestId}")
    public GuestDto getGuestById(@PathVariable Long guestId) {
        return guestService.getGuestById(guestId);
    }

    @DeleteMapping(value = "/admin/guest/{guestId}")
    public void deleteGuest(@PathVariable Long guestId) {
        guestService.deleteGuest(guestId);
    }
}

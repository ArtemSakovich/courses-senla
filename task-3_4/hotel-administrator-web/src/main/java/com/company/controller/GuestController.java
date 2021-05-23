package com.company.controller;

import com.company.hoteladministrator.api.service.IGuestService;
import com.company.hoteladministrator.model.dto.AccommodateGuestDto;
import com.company.hoteladministrator.model.dto.GuestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GuestController {

    IGuestService guestService;

    @Autowired
    private GuestController(IGuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping(value = "/guest")
    @ResponseBody
    public GuestDto addGuest(@RequestBody GuestDto guestDto) {
        return guestService.addGuest(guestDto);
    }

    @PutMapping(value = "/accommodate-guest")
    public void accommodateGuestToRoom(@RequestBody AccommodateGuestDto accommodateGuestDto) {
        guestService.accommodateToRoom(accommodateGuestDto);
    }

    @PutMapping(value = "evict-guest/{guestId}")
    public void evictGuestFromRoom(@PathVariable Long guestId) {
        guestService.evictFromRoom(guestId);
    }

    @PutMapping(value = "/maintenance/{guestId}/{maintenanceId}")
    public void orderMaintenance(@PathVariable Long guestId, @PathVariable Long maintenanceId) {
        guestService.orderMaintenance(guestId, maintenanceId);
    }

    @PutMapping(value = "/guest")
    @ResponseBody
    public GuestDto changeGuestInfo(@RequestBody GuestDto guestDto) {
        return guestService.changeGuestInfo(guestDto);
    }

    @GetMapping(value = "/amount-of-payment/{guestId}")
    @ResponseBody
    public Double getAmountOfPaymentForTheRoom(@PathVariable Long guestId) {
        return guestService.getAmountOfPaymentForTheRoom(guestId);
    }

    @GetMapping(value = "/last-guests-of-room/{roomId}")
    public List<GuestDto> getLastGuestsOfRoom(@PathVariable Long roomId) {
        return guestService.getLastGuests(roomId);
    }

    @GetMapping(value = "/guests")
    public List<GuestDto> getAllGuests() {
        return guestService.getAllGuests();
    }

    @GetMapping(value = "/number-of-guests")
    public Integer getNumberOfGuests() {
        return guestService.getNumberOfGuests();
    }

    @GetMapping(value = "/sorted-guests/{paramToSort}")
    public List<GuestDto> getSortedGuests(@PathVariable String paramToSort) {
        return guestService.getSortedGuests(paramToSort);
    }

    @GetMapping(value = "/guests/{guestId}")
    public GuestDto getGuestById(@PathVariable Long guestId) {
        return guestService.getGuestById(guestId);
    }

    @DeleteMapping(value = "/guest/{guestId}")
    public void deleteGuest(@PathVariable Long guestId) {
        guestService.deleteGuest(guestId);
    }
}

import http from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/http-common.js";

class GuestDataService {
    addGuest(data) {
        return http.post("/guest", data);
    }

    get(id) {
        return http.get(`/guests/${id}`);
    }

    getAmountOfPaymentForTheRoom(guestId) {
        return http.get(`/amount-of-payment/${guestId}`);
    }

    getLastGuestsOfRoom(roomId) {
        return http.get(`/last-guests-of-room/${roomId}`);
    }

    getAllGuests() {
        return http.get("/guests")
    }

    getNumberOfGuests() {
        return http.get("/number-of-guests")
    }

    getSortedGuests(paramToSort) {
        return http.get(`/sorted-guests/${paramToSort}`)
    }

    evictGuest(guestId) {
        return http.put(`evict-guest/${guestId}`);
    }

    accommodateGuest(data) {
        return http.put("/accommodate-guest", data);
    }

    orderMaintenance(guestId, maintenanceId) {
        return http.put(`/maintenance/${guestId}/${maintenanceId}`);
    }

    changeGuestInfo(data) {
        return http.put("/guest", data);
    }

    deleteGuest(guestId) {
        return http.delete(`/guest/${guestId}`)
    }
}

export default new GuestDataService();
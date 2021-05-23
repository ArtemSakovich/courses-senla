import http from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/http-common.js";

class RoomDataService {
    addRoom(data) {
        return http.post("/room", data);
    }

    get(id) {
        return http.get(`/rooms/${id}`);
    }

    getFreeSortedRooms(paramToSort) {
        return http.get(`/free-sorted-rooms/${paramToSort}`);
    }

    getRoomByGuestId(guestId) {
        return http.get(`/room/${guestId}`);
    }

    getAllRooms() {
        return http.get("/rooms");
    }

    getAllFreeRooms() {
        return http.get("/free-rooms");
    }

    getNumberOfFreeRooms() {
        return http.get("/number-of-free-rooms");
    }

    getSortedRooms(paramToSort) {
        return http.get(`/sorted-rooms/${paramToSort}`);
    }

    changeRoomInfo(data) {
        return http.put("/room", data);
    }

    deleteRoom(roomId) {
        return http.delete(`/room/${roomId}`);
    }
}

export default new RoomDataService();
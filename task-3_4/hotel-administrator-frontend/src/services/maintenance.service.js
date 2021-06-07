import http from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/http-common.js";

class MaintenanceDataService {
    addMaintenance(data) {
        return http.post("/maintenance", data);
    }

    get(id) {
        return http.get(`/maintenances/${id}`);
    }

    getAllMaintenances() {
        return http.get("/maintenances");
    }

    getAllOrderedMaintenancesOfCertainGuest(guestId) {
        return http.get(`/ordered-maintenances/${guestId}`);
    }

    getAllSortedOrderedMaintenancesOfCertainGuest(guestId, paramToSort) {
        return http.get(`/sorted-ordered-maintenances/${guestId}/${paramToSort}`);
    }

    getAllSortedMaintenances(paramToSort) {
        return http.get(`/sorted-maintenances/${paramToSort}`);
    }

    getAllOrderedMaintenances() {
        return http.get("/ordered-maintenances");
    }

    changeMaintenanceInfo(data) {
        return http.put("/maintenance", data);
    }

    deleteMaintenance(maintenanceId) {
        return http.delete(`/maintenance/${maintenanceId}`);
    }
}

export default new MaintenanceDataService();
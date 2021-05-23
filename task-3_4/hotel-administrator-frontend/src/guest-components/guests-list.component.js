import React, { Component } from "react";
import GuestService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/guest.service.js"
import MaintenanceService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/maintenance.service.js"
import RoomService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/room.service.js"
import {Link, Route, Switch} from "react-router-dom";

export default class GuestsList extends Component {
    constructor(props) {
        super(props);
        this.retrieveGuests = this.retrieveGuests.bind(this);
        this.setActiveGuest = this.setActiveGuest.bind(this);
        this.evictGuestFrom = this.evictGuestFrom.bind(this);

        this.state = {
            guests: [],
            orderedMaintenances: [],
            currentGuest: null,
            currentIndex: -1,
            amount: 0.0,
            guestRoom: { roomNumber: "" }
        }
        ;
    }

    componentDidMount() {
        this.retrieveGuests();
    }

    retrieveGuests() {
        GuestService.getAllGuests()
            .then(response => {
                this.setState({
                    guests: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    setActiveGuest(guest, index) {
        MaintenanceService.getAllOrderedMaintenancesOfCertainGuest(guest.id)
            .then(response => {
                this.setState({
                    orderedMaintenances: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        RoomService.getRoomByGuestId(guest.id)
            .then(response => {
                this.setState({
                    guestRoom: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        GuestService.getAmountOfPaymentForTheRoom(guest.id)
            .then(response => {
                debugger
                this.setState({
                    currentGuest: guest,
                    amount: response.data.value,
                    currentIndex: index
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    evictGuestFrom() {
        GuestService.evictGuest(this.state.currentGuest.id)
            .then(response => {
                console.log(response.data);
                this.props.history.push('/guests')
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { guests, currentGuest, currentIndex, amount, orderedMaintenances, guestRoom } = this.state;

        return (
            <div className="list row">
                <div className="col-md-6">
                    <h4>Total number of guests: {guests.length}</h4><br/>
                    <ul className="list-group">
                        {guests &&
                        guests.map((guest, index) => (
                            <li
                                className={
                                    "list-group-item " +
                                    (index === currentIndex ? "active" : "")
                                }
                                onClick={() => this.setActiveGuest(guest, index)}
                                key={index}
                            >
                                {guest.name} {guest.surname}
                                </li>
                        ))}
                    </ul>
                </div>
                <div className="col-md-6">
                    {currentGuest ? (
                        <div>
                            <h4>Guest</h4><br/>
                            <div>
                                <label>
                                    <strong>Name:</strong>
                                </label>{" "}
                                {currentGuest.name}
                            </div>
                            <div>
                                <label>
                                    <strong>Surname:</strong>
                                </label>{" "}
                                {currentGuest.surname}
                            </div>
                            <div>
                                <label>
                                    <strong>Age:</strong>
                                </label>{" "}
                                {currentGuest.age}
                            </div>
                            <div>
                                <label>
                                    <strong>Lives in a hotel:</strong>
                                </label>{" "}
                                {currentGuest.hasActiveRoomAssignment ? "Yes" : "No"}
                            </div>
                            {currentGuest.hasActiveRoomAssignment ? <div>
                                <label>
                                    <strong>Room:</strong>
                                </label>{" "}
                                #{guestRoom.roomNumber}
                            </div> : ""}<br/>
                            {currentGuest.hasActiveRoomAssignment ? <div>
                                <label>
                                    <strong>Amount of payment:</strong>
                                </label>{" "}
                                ${amount}
                            </div> : ""}<br/>
                            {currentGuest.hasActiveRoomAssignment ? <div>
                                <strong>Ordered maintenances:</strong>
                                <ul className="list-group">
                                    {orderedMaintenances &&
                                    orderedMaintenances.map((orderedMaintenance, index) => (
                                        <li>
                                            {orderedMaintenance.maintenanceName}: ${orderedMaintenance.maintenancePrice}
                                        </li>
                                    ))}
                                </ul>
                            </div> : ""}<br/>
                            <Link
                                to={"/guests/" + currentGuest.id}
                                className="orangeButtons"
                            >
                                Edit
                            </Link>
                            {currentGuest.hasActiveRoomAssignment ? <button
                                className="redButtons"
                                onClick={this.evictGuestFrom}
                            >
                                Evict from room
                            </button> : ""}
                            {currentGuest.hasActiveRoomAssignment ? "" : <Link
                                to={"/accommodate-guest/" + currentGuest.id}
                                className="greenButtons"
                            >
                                Accommodate to room
                            </Link>}
                            {currentGuest.hasActiveRoomAssignment ? <Link
                                to={"/order-maintenance/" + currentGuest.id}
                                className="blueButtons"
                            >
                                Order maintenance
                            </Link> : ""}
                        </div>

                    ) : (
                        <div>
                            <br/>
                            <br/>
                            <p>Please click on a guest</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}


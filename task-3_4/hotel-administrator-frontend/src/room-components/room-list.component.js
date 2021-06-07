import React, { Component } from "react";
import {Link} from "react-router-dom";
import RoomService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/room.service.js"
import GuestService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/guest.service.js"

export default class RoomList extends Component {
    constructor(props) {
        super(props);
        this.retrieveRooms = this.retrieveRooms.bind(this);
        this.setActiveRoom = this.setActiveRoom.bind(this);

        this.state = {
            rooms: [],
            lastGuests: [],
            currentRoom: null,
            currentIndex: -1,
        };
    }

    componentDidMount() {
        this.retrieveRooms();
    }

    retrieveRooms() {
        RoomService.getAllRooms()
            .then(response => {
                this.setState({
                    rooms: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    setActiveRoom(room, index) {
        GuestService.getLastGuestsOfRoom(room.id)
            .then(response => {
                this.setState({
                    lastGuests: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        this.setState({
            currentRoom: room,
            currentIndex: index
        });
    }

    render() {
        const { rooms, currentRoom, currentIndex, lastGuests } = this.state;

        return (
            <div className="list row">
                <div className="col-md-6">
                    <h4>All rooms</h4><br/>

                    <ul className="list-group">
                        {rooms &&
                        rooms.map((room, index) => (
                            <li
                                className={
                                    "list-group-item " +
                                    (index === currentIndex ? "active" : "")
                                }
                                onClick={() => this.setActiveRoom(room, index)}
                                key={index}
                            >
                                Room #{room.roomNumber}; Capacity: {room.numberOfBeds}; Number of guests: {room.numberOfTenants}; Status: {room.roomStatus}
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="col-md-6">
                    {currentRoom ? (
                        <div>
                            <h4>Room</h4><br/>
                            <div>
                                <label>
                                    <strong>Number: </strong>
                                </label>{" "}
                                {currentRoom.roomNumber}
                            </div>
                            <div>
                                <label>
                                    <strong>Price: </strong>
                                </label>{" "}
                                ${currentRoom.roomPrice}
                            </div>
                            <div>
                                <label>
                                    <strong>Number of beds: </strong>
                                </label>{" "}
                                {currentRoom.numberOfBeds}
                            </div>
                            <div>
                                <label>
                                    <strong>Status:</strong>
                                </label>{" "}
                                {currentRoom.roomStatus}
                            </div>
                            <div>
                                <label>
                                    <strong>Rating: </strong>
                                </label>{" "}
                                {currentRoom.numberOfStars} stars
                            </div>
                            <div>
                                <label>
                                    <strong>Number of guests: </strong>
                                </label>{" "}
                                {currentRoom.numberOfTenants}
                            </div><br/>
                            <div>
                                <strong>Last guests:</strong>
                                <ul className="list-group">
                                    {lastGuests &&
                                    lastGuests.map((lastGuest, index) => (
                                        <li>
                                            {lastGuest.name} {lastGuest.surname}
                                        </li>
                                    ))}
                                </ul>
                            </div><br/>
                            <Link
                                to={"/rooms/" + currentRoom.id}
                                className="orangeButtons"
                            >
                                Edit
                            </Link>
                        </div>

                    ) : (
                        <div>
                            <br />
                            <p>Please click on a room</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}


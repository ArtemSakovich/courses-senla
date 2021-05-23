import React, { Component } from "react";
import RoomService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/room.service.js"
import GuestService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/guest.service.js"

export default class AccommodateGuest extends Component {
        constructor(props) {
                super(props);
                this.retrieveRooms = this.retrieveRooms.bind(this);
                this.setActiveRoom = this.setActiveRoom.bind(this);
                this.accommodateRoom = this.accommodateRoom.bind(this);
                this.onChangeNumberOfDays = this.onChangeNumberOfDays.bind(this);

                this.state = {
                        rooms: [],
                        currentRoom: null,
                        currentGuest: null,
                        currentRoomIndex: null,
                        numberOfDays: 0,

                    message: ""
                };
        }

        componentDidMount() {
                this.retrieveRooms();
        }

        retrieveRooms() {
                RoomService.getAllFreeRooms()
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
                this.setState({
                        currentRoom: room,
                        currentRoomIndex: index
                });
        }

        accommodateRoom() {
            var dto = {};
                dto.guestId = this.props.match.params.id
            dto.roomId = this.state.currentRoom.id
            dto.numberOfDays = this.state.numberOfDays
                GuestService.accommodateGuest(dto)
                    .then(response => {
                            console.log(response.data);
                        this.setState({
                            message: "The guest was accommodated successfully!"
                        });
                    })
                    .catch(e => {
                            console.log(e);
                    });
        }

        onChangeNumberOfDays(e) {
                const numberOfDays = e.target.value;

            this.setState({
                numberOfDays: numberOfDays
            });
        }

        render() {
                const {rooms, currentGuest, currentRoom, currentRoomIndex} = this.state;
                return (
                    <div className="accommodate-form">
                            <h4>Accommodate guest</h4>
                        <ul className="list-group">
                                    {rooms &&
                                    rooms.map((room, index) => (
                                        <li
                                            className={
                                                "list-group-item " +
                                                (index === currentRoomIndex ? "active" : "")
                                            }
                                            onClick={() => this.setActiveRoom(room, index)}
                                            key={index}
                                        >
                                            Room number: {room.roomNumber}; Capacity: {room.numberOfBeds}; Number of guests: {room.numberOfTenants}; Status: {room.roomStatus}
                                        </li>
                                    ))}
                                </ul>
                        <div className="form-group">
                            <form>
                                    <div className="form-group">
                                            <label htmlFor="numberOfDays">Number of days</label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                id="numberOfDays"
                                                onChange={this.onChangeNumberOfDays}
                                            />
                                    </div>
                            </form>
                        </div>
                            <button
                                className="badge badge-danger mr-2"
                                onClick={this.accommodateRoom}
                            >
                                    Accommodate
                            </button>

                            <button
                                type="submit"
                                className="badge badge-success"
                                onClick={this.cancelClicked}
                            >
                                    Cancel
                            </button>
                            <p>{this.state.message}</p>
                    </div>
                );
        }
}
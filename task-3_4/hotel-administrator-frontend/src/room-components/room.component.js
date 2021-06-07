import React, { Component } from "react";
import RoomService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/room.service.js"

export default class Room extends Component {
    constructor(props) {
        super(props);
        this.onChangeRoomNumber = this.onChangeRoomNumber.bind(this);
        this.onChangeRoomStatus = this.onChangeRoomStatus.bind(this);
        this.onChangeNumberOfBeds = this.onChangeNumberOfBeds.bind(this);
        this.onChangeNumberOfStars = this.onChangeNumberOfStars.bind(this);
        this.onChangeRoomPrice = this.onChangeRoomPrice.bind(this);
        this.getRoom = this.getRoom.bind(this);
        this.updateRoom = this.updateRoom.bind(this);
        this.deleteRoom = this.deleteRoom.bind(this);

        this.state = {
            currentRoom: {
                id: null,
                roomNumber: 0,
                numberOfBeds: 0,
                numberOfStars: 0,
                numberOfTenants: 0,
                roomStatus: "",
                roomPrice: 0.0
            },
            message: ""
        };
    }

    componentDidMount() {
        this.getRoom(this.props.match.params.id);
    }

    onChangeRoomNumber(e) {
        const roomNumber = e.target.value;

        this.setState(function(prevState) {
            return {
                currentRoom: {
                    ...prevState.currentRoom,
                    roomNumber: roomNumber
                }
            };
        });
    }

    onChangeRoomStatus(e) {
        const roomStatus = e.target.value;

        this.setState(prevState => ({
            currentRoom: {
                ...prevState.currentRoom,
                roomStatus: roomStatus
            }
        }));
    }

    onChangeNumberOfBeds(e) {
        const numberOfBeds = e.target.value;

        this.setState(prevState => ({
            currentRoom: {
                ...prevState.currentRoom,
                numberOfBeds: numberOfBeds
            }
        }));
    }

    onChangeNumberOfStars(e) {
        const numberOfStars = e.target.value;

        this.setState(prevState => ({
            currentRoom: {
                ...prevState.currentRoom,
                numberOfStars: numberOfStars
            }
        }));
    }

    onChangeRoomPrice(e) {
        const roomPrice = e.target.value;

        this.setState(prevState => ({
            currentRoom: {
                ...prevState.currentRoom,
                roomPrice: roomPrice
            }
        }));
    }

    getRoom(id) {
        RoomService.get(id)
            .then(response => {
                this.setState({
                    currentRoom: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateRoom() {
        RoomService.changeRoomInfo(
            this.state.currentRoom
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The room was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteRoom() {
        RoomService.deleteRoom(this.state.currentRoom.id)
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The room was deleted successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { currentRoom } = this.state;

        return (
            <div>
                {currentRoom ? (
                    <div className="edit-form">
                        <h4>Room #{currentRoom.roomNumber}</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Room number:</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="title"
                                    value={currentRoom.roomNumber}
                                    onChange={this.onChangeRoomNumber}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Price:</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentRoom.roomPrice}
                                    onChange={this.onChangeRoomPrice}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Number of beds: </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentRoom.numberOfBeds}
                                    onChange={this.onChangeNumberOfBeds}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Status: </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentRoom.roomStatus}
                                    onChange={this.onChangeRoomStatus}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Number of stars: </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentRoom.numberOfStars}
                                    onChange={this.onChangeNumberOfStars}
                                />
                            </div>
                            <div className="form-group">
                                <label>
                                    <strong>Amount of guests: </strong>
                                </label>
                                { currentRoom.numberOfTenants}
                            </div>
                        </form>

                        <button
                            className="redButtons"
                            onClick={this.deleteRoom}
                        >
                            Delete
                        </button>
                        <button
                            type="submit"
                            className="updateButton"
                            onClick={this.updateRoom}
                        >
                            Update
                        </button>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on a Room...</p>
                    </div>
                )}
            </div>
        );
    }
}
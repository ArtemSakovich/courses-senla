import React, { Component } from "react";
import RoomService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/room.service.js"

export default class AddRoom extends Component {
    constructor(props) {
        super(props);
        this.newRoom = this.newRoom.bind(this);
        this.onChangeRoomNumber = this.onChangeRoomNumber.bind(this);
        this.onChangeNumberOfBeds = this.onChangeNumberOfBeds.bind(this);
        this.onChangeNumberOfStars = this.onChangeNumberOfStars.bind(this);
        this.onChangeRoomStatus = this.onChangeRoomStatus.bind(this);
        this.onChangeRoomPrice = this.onChangeRoomPrice.bind(this);
        this.saveRoom = this.saveRoom.bind(this);
        this.newRoom = this.newRoom.bind(this);

        this.state = {
            id: null,
            roomNumber: 0,
            numberOfBeds: 0,
            numberOfStars: 0,
            numberOfTenants: 0,
            roomStatus: "FREE",
            roomPrice: 0.0,

            submitted: false
        };
    }

    onChangeRoomNumber(e) {
        this.setState({
            roomNumber: e.target.value
        });
    }

    onChangeNumberOfBeds(e) {
        this.setState({
            numberOfBeds: e.target.value
        });
    }

    onChangeNumberOfStars(e) {
        this.setState({
            numberOfStars: e.target.value
        });
    }

    onChangeRoomStatus(e) {
        this.setState({
            roomStatus: e.target.value
        });
    }

    onChangeRoomPrice(e) {
        this.setState({
            roomPrice: e.target.value
        });
    }

    saveRoom() {
        var data = {
            roomNumber: this.state.roomNumber,
            numberOfBeds: this.state.numberOfBeds,
            numberOfStars: this.state.numberOfStars,
            numberOfTenants: this.state.numberOfTenants,
            roomStatus: this.state.roomStatus,
            roomPrice: this.state.roomPrice,
        };

        RoomService.addRoom(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    roomNumber: response.data.roomNumber,
                    numberOfBeds: response.data.numberOfBeds,
                    numberOfStars: response.data.numberOfStars,
                    numberOfTenants: response.data.numberOfTenants,
                    roomStatus: response.data.roomStatus,
                    roomPrice: response.data.roomPrice,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newRoom() {
        this.setState({
            id: null,
            roomNumber: 0,
            numberOfBeds: 0,
            numberOfStars: 0,
            numberOfTenants: 0,
            roomStatus: "FREE",
            roomPrice: 0.0,

            submitted: false
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4><br/>
                        <button className="btn btn-success" onClick={this.newRoom}>
                            Add one more
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="title">Room number:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                value={this.state.roomNumber}
                                onChange={this.onChangeRoomNumber}
                            />
                        </div><br/>
                        <div className="form-group">
                            <label htmlFor="description">Price: </label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                value={this.state.roomPrice}
                                onChange={this.onChangeRoomPrice}
                            />
                        </div><br/>
                        <div className="form-group">
                            <label htmlFor="description">Number of beds: </label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                value={this.state.numberOfBeds}
                                onChange={this.onChangeNumberOfBeds}
                            />
                        </div><br/>
                        <div className="form-group">
                            <label htmlFor="description">Status: </label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                value={this.state.roomStatus}
                                onChange={this.onChangeRoomStatus}
                            />
                        </div><br/>
                        <div className="form-group">
                            <label htmlFor="description">Number of stars: </label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                value={this.state.numberOfStars}
                                onChange={this.onChangeNumberOfStars}
                            />
                        </div><br/>

                        <button onClick={this.saveRoom} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
        );
    }
}
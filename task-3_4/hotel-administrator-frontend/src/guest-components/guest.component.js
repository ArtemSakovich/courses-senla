import React, { Component } from "react";
import GuestService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/guest.service.js"

export default class Guest extends Component {
    constructor(props) {
        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeSurname = this.onChangeSurname.bind(this);
        this.onChangeAge = this.onChangeAge.bind(this);
        this.getGuest = this.getGuest.bind(this);
        this.updateGuest = this.updateGuest.bind(this);
        this.deleteGuest = this.deleteGuest.bind(this);

        this.state = {
            currentGuest: {
                id: null,
                name: "",
                surname: "",
                age: 0
            },
            message: ""
        };
    }

    componentDidMount() {
        this.getGuest(this.props.match.params.id);
    }

    onChangeName(e) {
        const name = e.target.value;

        this.setState(function(prevState) {
            return {
                currentGuest: {
                    ...prevState.currentGuest,
                    name: name
                }
            };
        });
    }

    onChangeSurname(e) {
        const surname = e.target.value;

        this.setState(prevState => ({
            currentGuest: {
                ...prevState.currentGuest,
                surname: surname
            }
        }));
    }

    onChangeAge(e) {
        const age = e.target.value;

        this.setState(prevState => ({
            currentGuest: {
                ...prevState.currentGuest,
                age: age
            }
        }));
    }

    getGuest(id) {
        GuestService.get(id)
            .then(response => {
                this.setState({
                    currentGuest: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateGuest() {
        GuestService.changeGuestInfo(
            this.state.currentGuest
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The guest was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteGuest() {
        GuestService.deleteGuest(this.state.currentGuest.id)
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The guest was deleted successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { currentGuest } = this.state;

        return (
            <div>
                {currentGuest ? (
                    <div className="edit-form">
                        <h4>{currentGuest.name} {currentGuest.surname}</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="title"
                                    value={currentGuest.name}
                                    onChange={this.onChangeName}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Surname</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentGuest.surname}
                                    onChange={this.onChangeSurname}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Age</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentGuest.age}
                                    onChange={this.onChangeAge}
                                />
                            </div>

                            <div className="form-group">
                                <label>
                                    <strong>Lives in hotel:</strong>
                                </label>
                                {currentGuest.hasActiveRoomAssignment ? "Yes" : "No"}
                            </div>
                        </form>
                        <button
                            className="redButtons"
                            onClick={this.deleteGuest}
                        >
                            Delete
                        </button>
                        <button
                            type="submit"
                            className="updateButton"
                            onClick={this.updateGuest}
                        >
                            Update
                        </button>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on a Guest...</p>
                    </div>
                )}
            </div>
        );
    }
}
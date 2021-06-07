import React, { Component } from "react";
import GuestService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/guest.service.js"

export default class AddGuest extends Component {
    constructor(props) {
        super(props);
        this.newGuest = this.newGuest.bind(this);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeSurname = this.onChangeSurname.bind(this);
        this.onChangeAge = this.onChangeAge.bind(this);
        this.saveGuest = this.saveGuest.bind(this);

        this.state = {
            id: null,
            name: "",
            surname: "",
            age: 0,
            hasActiveRoomAssignment: false,

            submitted: false
        };
    }

    onChangeName(e) {
        this.setState({
            name: e.target.value
        });
    }

    onChangeSurname(e) {
        this.setState({
            surname: e.target.value
        });
    }

    onChangeAge(e) {
        this.setState({
            age: e.target.value
        });
    }

    saveGuest() {
        var data = {
            name: this.state.name,
            surname: this.state.surname,
            age: this.state.age,
            hasActiveRoomAssignment: this.state.hasActiveRoomAssignment
        };

        GuestService.addGuest(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    name: response.data.name,
                    surname: response.data.surname,
                    age: response.data.age,
                    hasActiveRoomAssignment: response.data.hasActiveRoomAssignment,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newGuest() {
        this.setState({
            id: null,
            name: "",
            surname: "",
            age: 0,
            hasActiveRoomAssignment: false,

            submitted: false
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4><br/>
                        <button className="btn btn-success" onClick={this.newGuest}>
                            Add one more
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="title">Name:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                required
                                value={this.state.name}
                                onChange={this.onChangeName}
                                name="title"
                            />
                        </div><br/>

                        <div className="form-group">
                            <label htmlFor="description">Surname:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                required
                                value={this.state.surname}
                                onChange={this.onChangeSurname}
                                name="description"
                            />
                        </div><br/>

                        <div className="form-group">
                            <label htmlFor="description">Age:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                required
                                value={this.state.age}
                                onChange={this.onChangeAge}
                                name="description"
                            />
                        </div><br/>

                        <button onClick={this.saveGuest} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
        );
    }
}
import React, { Component } from "react";
import MaintenanceService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/maintenance.service.js"

export default class AddMaintenance extends Component {
    constructor(props) {
        super(props);
        this.onChangeMaintenanceName = this.onChangeMaintenanceName.bind(this);
        this.onChangeMaintenancePrice = this.onChangeMaintenancePrice.bind(this);
        this.onChangeMaintenanceSection = this.onChangeMaintenanceSection.bind(this);
        this.saveMaintenance = this.saveMaintenance.bind(this);
        this.newMaintenance = this.newMaintenance.bind(this);

        this.state = {
            id: null,
            maintenanceName: "",
            maintenancePrice: 0.0,
            maintenanceSection: "",

            submitted: false
        };
    }

    onChangeMaintenanceName(e) {
        this.setState({
            maintenanceName: e.target.value
        });
    }

    onChangeMaintenancePrice(e) {
        this.setState({
            maintenancePrice: e.target.value
        });
    }

    onChangeMaintenanceSection(e) {
        this.setState({
            maintenanceSection: e.target.value
        });
    }

    saveMaintenance() {
        var data = {
            maintenanceName: this.state.maintenanceName,
            maintenancePrice: this.state.maintenancePrice,
            maintenanceSection: this.state.maintenanceSection
        };

        MaintenanceService.addMaintenance(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    maintenanceName: response.data.maintenanceName,
                    maintenancePrice: response.data.maintenancePrice,
                    maintenanceSection: response.data.maintenanceSection,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newMaintenance() {
        this.setState({
            id: null,
            maintenanceName: "",
            maintenancePrice: 0.0,
            maintenanceSection: "",

            submitted: false
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4><br/>
                        <button className="btn btn-success" onClick={this.newMaintenance}>
                            Add one more
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="title">Maintenance Title:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                required
                                value={this.state.maintenanceName}
                                onChange={this.onChangeMaintenanceName}
                                name="title"
                            />
                        </div><br/>

                        <div className="form-group">
                            <label htmlFor="price">Price:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                required
                                value={this.state.maintenancePrice}
                                onChange={this.onChangeMaintenancePrice}
                                name="description"
                            />
                        </div><br/>

                        <div className="form-group">
                            <label htmlFor="section">Section:</label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                required
                                value={this.state.maintenanceSection}
                                onChange={this.onChangeMaintenanceSection}
                                name="description"
                            />
                        </div><br/>

                        <button onClick={this.saveMaintenance} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
        );
    }
}
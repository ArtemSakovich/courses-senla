import React, { Component } from "react";
import MaintenanceService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/maintenance.service.js"

export default class Maintenance extends Component {
    constructor(props) {
        super(props);
        this.onChangeMaintenanceName = this.onChangeMaintenanceName.bind(this);
        this.onChangeMaintenancePrice = this.onChangeMaintenancePrice.bind(this);
        this.onChangeMaintenanceSection = this.onChangeMaintenanceSection.bind(this);
        this.getMaintenance = this.getMaintenance.bind(this);
        this.updateMaintenance = this.updateMaintenance.bind(this);
        this.deleteMaintenance = this.deleteMaintenance.bind(this);

        this.state = {
            currentMaintenance: {
                id: null,
                maintenanceName: "",
                maintenancePrice: 0.0,
                maintenanceSection: ""
            },
            message: ""
        };
    }

    componentDidMount() {
        this.getMaintenance(this.props.match.params.id);
    }

    onChangeMaintenanceName(e) {
        const maintenanceName = e.target.value;

        this.setState(function(prevState) {
            return {
                currentMaintenance: {
                    ...prevState.currentMaintenance,
                    maintenanceName: maintenanceName
                }
            };
        });
    }

    onChangeMaintenancePrice(e) {
        const maintenancePrice = e.target.value;

        this.setState(prevState => ({
            currentMaintenance: {
                ...prevState.currentMaintenance,
                maintenancePrice: maintenancePrice
            }
        }));
    }

    onChangeMaintenanceSection(e) {
        const maintenanceSection = e.target.value;

        this.setState(prevState => ({
            currentMaintenance: {
                ...prevState.currentMaintenance,
                maintenanceSection: maintenanceSection
            }
        }));
    }

    getMaintenance(id) {
        MaintenanceService.get(id)
            .then(response => {
                this.setState({
                    currentMaintenance: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateMaintenance() {
        MaintenanceService.changeMaintenanceInfo(
            this.state.currentMaintenance
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The maintenance was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteMaintenance() {
        MaintenanceService.deleteMaintenance(this.state.currentMaintenance.id)
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The maintenance was deleted successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { currentMaintenance } = this.state;

        return (
            <div>
                {currentMaintenance ? (
                    <div className="edit-form">
                        <h4>{currentMaintenance.maintenanceName}</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Title</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="title"
                                    value={currentMaintenance.maintenanceName}
                                    onChange={this.onChangeMaintenanceName}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Price</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentMaintenance.maintenancePrice}
                                    onChange={this.onChangeMaintenancePrice}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Section</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentMaintenance.maintenanceSection}
                                    onChange={this.onChangeMaintenanceSection}
                                />
                            </div>
                        </form>

                        <button
                            className="redButtons"
                            onClick={this.deleteMaintenance}
                        >
                            Delete
                        </button>
                        <button
                            type="submit"
                            className="updateButton"
                            onClick={this.updateMaintenance}
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
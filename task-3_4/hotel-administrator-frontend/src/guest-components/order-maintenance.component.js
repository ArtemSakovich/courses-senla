import React, { Component } from "react";
import GuestService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/guest.service.js"
import MaintenanceService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/maintenance.service.js"

export default class OrderMaintenance extends Component {
    constructor(props) {
        super(props);
        this.retrieveMaintenances = this.retrieveMaintenances.bind(this);
        this.setActiveMaintenance = this.setActiveMaintenance.bind(this);
        this.orderMaintenance = this.orderMaintenance.bind(this);

        this.state = {
            maintenances: [],
            currentMaintenance: null,
            currentGuest: null,
            currentMaintenanceIndex: null,

            message: ""
        };
    }

    componentDidMount() {
        this.retrieveMaintenances();
    }

    retrieveMaintenances() {
        MaintenanceService.getAllMaintenances()
            .then(response => {
                this.setState({
                    maintenances: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    setActiveMaintenance(maintenance, index) {
        this.setState({
            currentMaintenance: maintenance,
            currentMaintenanceIndex: index
        });
    }

    orderMaintenance() {
        GuestService.orderMaintenance(this.props.match.params.id, this.state.currentMaintenance.id)
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "Maintenance has been ordered!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const {maintenances, currentGuest, currentMaintenance, currentMaintenanceIndex} = this.state;
        return (
            <div className="accommodate-form">
                <h4>Order maintenance</h4><br/>
                <ul className="list-group">
                    {maintenances &&
                    maintenances.map((maintenance, index) => (
                        <li
                            className={
                                "list-group-item " +
                                (index === currentMaintenanceIndex ? "active" : "")
                            }
                            onClick={() => this.setActiveMaintenance(maintenance, index)}
                            key={index}
                        >
                            {maintenance.maintenanceName}: ${maintenance.maintenancePrice}
                        </li>
                    ))}
                </ul><br/>
                <button
                    className="badge badge-danger mr-2"
                    onClick={this.orderMaintenance}
                >
                    Order
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
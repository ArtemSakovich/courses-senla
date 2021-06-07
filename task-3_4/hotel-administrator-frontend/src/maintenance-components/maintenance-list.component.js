import React, { Component } from "react";
import MaintenanceService from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/services/maintenance.service.js"
import {Link} from "react-router-dom";

export default class MaintenanceList extends Component {
    constructor(props) {
        super(props);
        this.retrieveMaintenances = this.retrieveMaintenances.bind(this);
        this.setActiveMaintenance = this.setActiveMaintenance.bind(this);

        this.state = {
            maintenances: [],
            currentMaintenance: null,
            currentIndex: -1,
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
            currentIndex: index
        });
    }

    render() {
        const { maintenances, currentMaintenance, currentIndex } = this.state;

        return (
            <div className="list row">
                <div className="col-md-6">
                    <h4>All maintenances</h4>

                    <ul className="list-group">
                        {maintenances &&
                        maintenances.map((maintenance, index) => (
                            <li
                                className={
                                    "list-group-item " +
                                    (index === currentIndex ? "active" : "")
                                }
                                onClick={() => this.setActiveMaintenance(maintenance, index)}
                                key={index}
                            >
                                {maintenance.maintenanceName}: ${maintenance.maintenancePrice}
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="col-md-6">
                    {currentMaintenance ? (
                        <div>
                            <h4>Maintenance</h4>
                            <div>
                                <label>
                                    <strong>Title:</strong>
                                </label>{" "}
                                {currentMaintenance.maintenanceName}
                            </div>
                            <div>
                                <label>
                                    <strong>Price:</strong>
                                </label>{" "}
                                ${currentMaintenance.maintenancePrice}
                            </div>
                            <div>
                                <label>
                                    <strong>Section:</strong>
                                </label>{" "}
                                {currentMaintenance.maintenanceSection}
                            </div><br/>
                            <Link
                                to={"/maintenances/" + currentMaintenance.id}
                                className="orangeButtons"
                            >
                                Edit
                            </Link>
                        </div>

                    ) : (
                        <div>
                            <br />
                            <p>Please click on a maintenance</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}


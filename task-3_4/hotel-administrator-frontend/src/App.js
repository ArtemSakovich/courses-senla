import './index.css';
import { ReactComponent as CaretIcon } from '/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/icons/caret.svg';
import React, { useState, useEffect, useRef } from 'react';
import { CSSTransition } from 'react-transition-group';
import {Switch, Route, Link } from "react-router-dom";
import GuestList from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/guest-components/guests-list.component.js";
import Guest from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/guest-components/guest.component.js";
import "bootstrap/dist/css/bootstrap.min.css";
import AccommodateGuest from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/guest-components/accommodate-guest.component.js";
import Room from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/room-components/room.component.js";
import RoomList from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/room-components/room-list.component.js";
import MaintenanceList from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/maintenance-components/maintenance-list.component.js";
import Maintenance from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/maintenance-components/maintenance.component.js";
import AddGuest from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/guest-components/add-guest.component.js";
import AddRoom from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/room-components/add-room.component.js";
import AddMaintenance from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/maintenance-components/add-maintenance.component.js";
import OrderMaintenance from "/Users/artemsakovich/IdeaProjects/courses-senla/task-3_4/hotel-administrator-frontend/src/guest-components/order-maintenance.component.js";

function App() {
    return (
        <div>
            <Navbar>
                <NavItem icon={<CaretIcon />} name = "Guest">
                    <DropdownMenu caller={"guest"}/>
                </NavItem>
                <NavItem icon={<CaretIcon />} name = "Room">
                    <DropdownMenu caller={"room"}/>
                </NavItem>
                <NavItem icon={<CaretIcon />} name = "Maintenance">
                    <DropdownMenu caller={"maintenance"}/>
                </NavItem>
            </Navbar>
            <div className="container mt-3">
                <Switch>
                    <Route exact path="/guests/all" component={GuestList} />
                    <Route exact path="/rooms/all" component={RoomList} />
                    <Route exact path="/maintenances/all" component={MaintenanceList} />
                    <Route exact path="/guests/add" component={AddGuest} />
                    <Route exact path="/rooms/add" component={AddRoom} />
                    <Route exact path="/maintenances/add" component={AddMaintenance} />
                    <Route path="/guests/:id" component={Guest} />
                    <Route path="/accommodate-guest/:id" component={AccommodateGuest} />
                    <Route path="/order-maintenance/:id" component={OrderMaintenance} />
                    <Route path="/rooms/:id" component={Room} />
                    <Route path="/maintenances/:id" component={Maintenance} />
                </Switch>
            </div>
        </div>
    );
}

function Navbar(props) {
    return (
        <nav className="navbar2">
            <ul className="navbar-nav2">{props.children}</ul>
        </nav>
    );
}

function NavItem(props) {
    const [open, setOpen] = useState(false);

    return (
        <li className="nav-item2">
            <a href="#" className="icon-button" onClick={() => setOpen(!open)}>
                {props.name}
                {props.icon}
            </a>

            {open && props.children}
        </li>
    );
}

function DropdownMenu(props) {
    const [activeMenu, setActiveMenu] = useState('main');
    const [menuHeight, setMenuHeight] = useState(null);
    const [open, setOpen] = useState(false);
    const dropdownRef = useRef(null);

    useEffect(() => {
        setMenuHeight(dropdownRef.current?.firstChild.offsetHeight)
    }, [])

    function calcHeight(el) {
        const height = el.offsetHeight;
        setMenuHeight(height);
    }

    function DropdownItem(props) {
        return (
            <Link to={props.link} className="menu-item2" onClick={() => setOpen(!open)}>
                {props.children}
            </Link>
        );
    }

    function GuestDropdownMenuItems() {
        return (
            <div className="menu2">
                <DropdownItem link = "/guests/all">All guests</DropdownItem>
                <DropdownItem link = "/guests/add" goToMenu = "">Add guest</DropdownItem>
            </div>
        )
    }

    function RoomDropdownMenuItems() {
        return (
            <div className="menu2">
                <DropdownItem link = "/rooms/all">All rooms</DropdownItem>
                <DropdownItem link = "/rooms/add">Add room</DropdownItem>
            </div>
        )
    }

    function MaintenanceDropdownMenuItems() {
        return (
            <div className="menu2">
                <DropdownItem link = "/maintenances/add">Add maintenance</DropdownItem>
                <DropdownItem link = "/maintenances/all">All maintenances</DropdownItem>
            </div>
        )
    }

    if (props.caller === 'guest') {
        return GuestReturnFunction();
    } else if (props.caller === 'room') {
        return RoomReturnFunction();
    } else {
        return MaintenanceReturnFunction();
    }

    function GuestReturnFunction() {
        return (
            <div className="dropdown2" style={{height: menuHeight}} ref={dropdownRef}>
                <CSSTransition
                    in={activeMenu === 'main'}
                    timeout={500}
                    classNames="menu-primary"
                    unmountOnExit
                    onEnter={calcHeight}>
                    <GuestDropdownMenuItems/>
                </CSSTransition>
            </div>
        );
    }

    function RoomReturnFunction() {
        return (
            <div className="dropdown2" style={{height: menuHeight}} ref={dropdownRef}>
                <CSSTransition
                    in={activeMenu === 'main'}
                    timeout={500}
                    classNames="menu-primary"
                    unmountOnExit
                    onEnter={calcHeight}>
                    <RoomDropdownMenuItems/>
                </CSSTransition>
            </div>
        );
    }

    function MaintenanceReturnFunction() {
        return (
            <div className="dropdown2" style={{height: menuHeight}} ref={dropdownRef}>
                <CSSTransition
                    in={activeMenu === 'main'}
                    timeout={500}
                    classNames="menu-primary"
                    unmountOnExit
                    onEnter={calcHeight}>
                    <MaintenanceDropdownMenuItems/>
                </CSSTransition>
            </div>
        );
    }
}
export default App;

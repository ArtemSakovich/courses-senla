CREATE DATABASE hotel_administrator_db;

CREATE TABLE guest (
	id SERIAL PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	surname VARCHAR(30) NOT NULL,
	age SMALLINT NOT NULL,
	CONSTRAINT chk_guest_age CHECK (age >= 6)
);

CREATE TABLE maintenance (
	id SERIAL PRIMARY KEY,
	maintenanceName VARCHAR(50) NOT NULL,
	maintenancePrice DECIMAL(6, 3) NOT NULL,
	maintenanceSection VARCHAR(50) NOT NULL,
	orderDate date,
	CONSTRAINT chk_maintenance_section CHECK (maintenanceSection IN ('MEDICAL', 'REPAIR', 'CLEANING', 'FOOD'))
);

CREATE TABLE room (
	id SERIAL PRIMARY KEY,
	roomNumber SMALLINT NOT NULL,
	roomStatus VARCHAR(50) NOT NULL, 
	roomPrice DECIMAL(6, 3) NOT NULL,
	numberOfBeds SMALLINT NOT NULL,
	numberOfStars SMALLINT NOT NULL,
	CONSTRAINT chk_room_status CHECK (roomStatus IN ('FREE', 'OCCUPIED', 'SERVICED', 'REPAIRED')),
	CONSTRAINT chk_number_of_stars CHECK (numberOfStars <= 5)
);

CREATE TABLE roomAssignment (
	id SERIAL PRIMARY KEY,
	guestId INT NOT NULL,
	roomId INT NOT NULL,
	checkInDate TIMESTAMP NOT NULL,
	checkOutDate TIMESTAMP NOT NULL,
	roomAssignmentStatus VARCHAR(50) NOT NULL,
	createdOn TIMESTAMP NOT NULL,
	CONSTRAINT chk_room_assignment_status CHECK (roomAssignmentStatus IN ('BOOKED', 'CANCELLED', 'ACTIVE', 'CLOSED')),
	CONSTRAINT guest_id_fk FOREIGN KEY (guestId) REFERENCES guest (id),
	CONSTRAINT room_id_fk FOREIGN KEY (roomId) REFERENCES room (id)
); 


CREATE TABLE orderedMaintenances (
roomAssignmentId INT NOT NULL,
maintenanceId INT NOT NULL,
orderDate TIMESTAMP NOT NULL,
CONSTRAINT room_assignment_id_fk FOREIGN KEY (roomAssignmentId) REFERENCES roomAssignment (id),
CONSTRAINT maintenance_id_fk FOREIGN KEY (maintenanceId) REFERENCES maintenance (id)
);
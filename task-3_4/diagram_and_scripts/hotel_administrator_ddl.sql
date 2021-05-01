CREATE TABLE guests (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(20) NOT NULL,
                        surname VARCHAR(30) NOT NULL,
                        age INT NOT NULL,
                        CONSTRAINT chk_guest_age CHECK (age >= 6)
);

CREATE TABLE maintenances (
                              id BIGINT PRIMARY KEY,
                              maintenance_name VARCHAR(50) NOT NULL,
                              maintenance_price DOUBLE PRECISION NOT NULL,
                              maintenance_section VARCHAR(50) NOT NULL,
                              CONSTRAINT chk_maintenance_section CHECK (maintenance_section IN ('MEDICAL', 'REPAIR', 'CLEANING', 'FOOD'))
);

CREATE TABLE rooms (
                       id BIGINT PRIMARY KEY,
                       room_number INT NOT NULL,
                       room_status VARCHAR(50) NOT NULL,
                       room_price DOUBLE PRECISION NOT NULL,
                       number_of_beds INT NOT NULL,
                       number_of_stars INT NOT NULL,
                       CONSTRAINT chk_room_status CHECK (room_status IN ('FREE', 'OCCUPIED', 'SERVICED', 'REPAIRED')),
                       CONSTRAINT chk_number_of_stars CHECK (number_of_stars <= 5)
);

CREATE TABLE room_assignments (
                                  id BIGINT PRIMARY KEY,
                                  guest_id BIGINT NOT NULL,
                                  room_id BIGINT NOT NULL,
                                  check_in_date TIMESTAMP NOT NULL,
                                  check_out_date TIMESTAMP NOT NULL,
                                  room_assignment_status VARCHAR(50) NOT NULL,
                                  created_on TIMESTAMP NOT NULL,
                                  CONSTRAINT chk_room_assignment_status CHECK (room_assignment_status IN ('BOOKED', 'CANCELLED', 'ACTIVE', 'CLOSED')),
                                  CONSTRAINT guest_id_fk FOREIGN KEY (guest_id) REFERENCES guests (id),
                                  CONSTRAINT room_id_fk FOREIGN KEY (room_id) REFERENCES rooms (id)
);


CREATE TABLE ordered_maintenances (
                                      id BIGINT PRIMARY KEY,
                                      room_assignment_id BIGINT NOT NULL,
                                      maintenance_id BIGINT NOT NULL,
                                      order_date TIMESTAMP NOT NULL,
                                      CONSTRAINT room_assignment_id_fk FOREIGN KEY (room_assignment_id) REFERENCES room_assignments (id),
                                      CONSTRAINT maintenance_id_fk FOREIGN KEY (maintenance_id) REFERENCES maintenances (id)
);

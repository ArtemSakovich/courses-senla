INSERT INTO guest (name, surname, age)
VALUES ('Artem', 'Sakovich', '19'),
       ('Ivan', 'Ivanov', 22),
       ('Petya', 'Petrov', '17');
       
INSERT INTO maintenance (maintenaceName, maintenancePrice, maintenanceSection)
VALUES ('Cleaning', '35.50', 'CLEANING'),
       ('Cooking', '15.80', 'FOOD'),
       ('Treatment', '11.65', 'MEDICAL');   
       
INSERT INTO room (roomNumber, roomStatus, roomPrice, numberOfBeds, numberOfStars)
VALUES ('12', 'FREE', '25.50', '4', '3'),
       ('32', 'SERVICED', '16.70', '2', '3'),
       ('9', 'FREE', '42.90', '3', '5');
      
INSERT INTO roomAssignment (guestId, roomId, checkInDate, checkOutDate, roomAssignmentStatus, createdOn)
VALUES ('1', '1', '2021-05-15 12:00:00', '2021-05-27 12:00:00', 'ACTIVE', '2021-04-15 12:00:00');

INSERT INTO orderedmaintenances (roomAssignmentId, maintenanceId, orderDate)
VALUES ('1', '1', '2021-05-20 13:00:00');

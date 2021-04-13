INSERT INTO product (maker, model, type)
VALUES ('Apple', 'pc_1', 'PC'),
       ('Acer', 'pc_2', 'PC'),
       ('HP', 'pc_3', 'PC'),
       ('DELL', 'pc_4', 'PC'),
       ('Apple', 'pc_5', 'PC'),
       ('Apple', 'pc_6', 'PC'),
       ('Xerox', 'pc_7', 'PC'),
       ('Canyon', 'printer_1', 'Printer'),
       ('Xerox', 'printer_2', 'Printer'),
       ('Ricoh', 'printer_3', 'Printer'),
       ('Samsung', 'laptop_1', 'Laptop'),
       ('Apple', 'laptop_2', 'Laptop'),
       ('Prestigio', 'laptop_3', 'Laptop');

INSERT INTO pc (model, speed, ram, hd, cd, price)
VALUES ('pc_1', 3700, 8, 256, '8x', 2000),
       ('pc_2', 2200, 2, 128, '24x', 430),
       ('pc_3', 2200, 2, 256, '4x', 700),
       ('pc_4', 1700, 4, 128, '12x', 1000),
       ('pc_5', 1850, 4, 512, '14x', 2700),
       ('pc_6', 2000, 8, 128, '20x', 3200),
       ('pc_7', 2500, 2, 256, '10x', 3000);

INSERT INTO printer (model, color, type, price)
VALUES ('printer_1', 'y', 'Matrix', 200),
       ('printer_2', 'y', 'Jet', 150)
    ('printer_3', 'n', 'Laser', 350);


INSERT INTO laptop (model, speed, ram, hd, screen, price)
VALUES ('laptop_1', '1400', 2, 64, '10', 1100),
       ('laptop_2', '1150', 4, 128, '11', 900),
       ('laptop_3', '1700', 6, 32, '9', 1250);
	
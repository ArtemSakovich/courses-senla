CREATE DATABASE courses_senla_task_10;

CREATE TABLE product (
                         maker VARCHAR(10) NOT NULL,
                         model VARCHAR(50) NOT NULL,
                         type VARCHAR(50) NOT NULL,
                         CONSTRAINT product_pk PRIMARY KEY (model),
                         CONSTRAINT chk_type CHECK (type IN ('PC', 'Laptop', 'Printer'))
);

CREATE TABLE pc (
                    code SERIAL PRIMARY KEY,
                    model VARCHAR(50) NOT NULL,
                    speed SMALLINT NOT NULL,
                    ram SMALLINT NOT NULL,
                    hd REAL NOT NULL,
                    cd VARCHAR(10),
                    price DECIMAL(18 , 4 ),
                    CONSTRAINT pc_fk FOREIGN KEY (model)
                        REFERENCES product (model)
);

CREATE TABLE laptop (
                        code SERIAL PRIMARY KEY,
                        model VARCHAR(50) NOT NULL,
                        speed SMALLINT NOT NULL,
                        ram SMALLINT NOT NULL,
                        hd REAL NOT NULL,
                        screen INT NOT NULL,
                        PRICE DECIMAL(18 , 4 ),
                        CONSTRAINT laptop_fk FOREIGN KEY (model)
                            REFERENCES product (model)
);

CREATE TABLE printer (
                         code SERIAL PRIMARY KEY,
                         model VARCHAR(50) NOT NULL,
                         color CHAR(1) NOT NULL,
                         type VARCHAR(50) NOT NULL,
                         price DECIMAL(18 , 4 ) NOT NULL,
                         CONSTRAINT chk_type CHECK (type IN ('Laser', 'Jet', 'Matrix')),
                         CONSTRAINT printer_fk FOREIGN KEY (model)
                             REFERENCES product (model)
);
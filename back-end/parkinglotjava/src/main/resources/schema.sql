create database societygeneral ;

use societygeneral;


CREATE TABLE parkinglots(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    created_at  DATETIME NOT NULL,
    lot INT NOT NULL,
    parking_amount INT NOT NULL,
    parking_duration INT NOT NULL,
    updated_at  DATETIME NOT NULL,
    vehicle_number INT NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY vehicle_number (vehicle_number),
    UNIQUE KEY lot (lot)

);
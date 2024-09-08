create database alphasystem;

use alphasystem;

CREATE TABLE `user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(150) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` CHAR(64) NOT NULL,
    `lastAccess` DATETIME,
    `active` BOOLEAN DEFAULT TRUE
);
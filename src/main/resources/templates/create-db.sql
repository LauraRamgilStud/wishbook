CREATE SCHEMA wishbook;
CREATE TABLE wishbook.User(
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `fname` VARCHAR(255) NOT NULL,
                            `lname` VARCHAR(255) NOT NULL,
                            `email` VARCHAR(255) NOT NULL,
                            `password` VARCHAR(255) NOT NULL,
                            PRIMARY KEY (`id`));

CREATE TABLE wishbook.Wishlist(
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `user_id` INT,
                            `list_name` VARCHAR(255) NOT NULL,
                            `occasion` VARCHAR(255),
                            `cover_pic` BLOB,
                            PRIMARY KEY (`id`),
                            FOREIGN KEY (`user_id`) REFERENCES User(`id`));

CREATE TABLE wishbook.Wish(
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `wishlist_id` INT,
                            `wish_name` VARCHAR(255) NOT NULL,
                            `description` VARCHAR(500),
                            `price` DOUBLE,
                            `quantity` INT(10),
                            `wish_pic` BLOB,
                            `url` VARCHAR(500),
                            PRIMARY KEY (`id`),
                            FOREIGN KEY (`wishlist_id`) REFERENCES Wishlist(`id`));

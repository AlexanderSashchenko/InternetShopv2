CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internetshop`.`items` (
    `item_id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `price` DECIMAL(6,2) NOT NULL,
    PRIMARY KEY (`item_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`users` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `email` VARCHAR(90) NULL,
    `first_name` TEXT NULL,
    `last_name` TEXT NULL,
    PRIMARY KEY (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`orders` (
    `order_id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`order_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`buckets` (
    `bucket_id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`bucket_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`roles` (
    `role_id` INT NOT NULL,
    `role_name` TEXT NOT NULL,
    PRIMARY KEY (`role_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`user_roles` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`user_orders` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `order_id` INT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`order_items` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `order_id` INT NOT NULL,
    `item_id` INT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internetshop`.`bucket_items` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `bucket_id` INT NOT NULL,
    `item_id` INT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

ALTER TABLE `internetshop`.`user_roles`
    ADD INDEX `user_roles_to_users_fk_idx` (`user_id` ASC) VISIBLE,
    ADD INDEX `user_roles_to_roles_fk_idx` (`role_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`user_roles`
    ADD CONSTRAINT `user_roles_to_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internetshop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `user_roles_to_roles_fk`
        FOREIGN KEY (`role_id`)
            REFERENCES `internetshop`.`roles` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `internetshop`.`user_orders`
    ADD INDEX `user_orders_to_users_fk_idx` (`user_id` ASC) VISIBLE,
    ADD INDEX `user_orders_to_orders_fk_idx` (`order_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`user_orders`
    ADD CONSTRAINT `user_orders_to_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internetshop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `user_orders_to_orders_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `internetshop`.`orders` (`order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `internetshop`.`order_items`
    ADD INDEX `order_items_to_orders_fk_idx` (`order_id` ASC) VISIBLE,
    ADD INDEX `order_items_to_items_fk_idx` (`item_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`order_items`
    ADD CONSTRAINT `order_items_to_orders_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `internetshop`.`orders` (`order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `order_items_to_items_fk`
        FOREIGN KEY (`item_id`)
            REFERENCES `internetshop`.`items` (`item_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `internetshop`.`bucket_items`
    ADD INDEX `bucket_items_to_items_fk_idx` (`item_id` ASC) VISIBLE,
    ADD INDEX `bucket_items_to_buckets_fk_idx` (`bucket_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`bucket_items`
    ADD CONSTRAINT `bucket_items_to_items_fk`
        FOREIGN KEY (`item_id`)
            REFERENCES `internetshop`.`items` (`item_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `bucket_items_to_buckets_fk`
        FOREIGN KEY (`bucket_id`)
            REFERENCES `internetshop`.`buckets` (`bucket_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `internetshop`.`buckets`
    ADD INDEX `bucket_to_users_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`buckets`
    ADD CONSTRAINT `bucket_to_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internetshop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

INSERT INTO `internetshop`.`items` (`title`, `price`) VALUES ('xiaomi laptop', '999.99');
INSERT INTO `internetshop`.`items` (`title`, `price`) VALUES ('samsung smartphone', '499.99');

SELECT * FROM `internetshop`.`items` WHERE item_id=1;

UPDATE `internetshop`.`items` SET `title`='updated title', `price`='000.01' WHERE "item_id"=1;

DELETE FROM `internetshop`.`items` WHERE "item_id"=2;

SELECT * FROM `internetshop`.`items`;

INSERT INTO `internetshop`.`users`
    (`login`, `password`, `email`, `first_name`, `last_name`)
    VALUES ('alex', 'xela', 'test@test.com', 'A', 'B');

INSERT INTO `internetshop`.`users`
(`login`, `password`, `email`, `first_name`, `last_name`)
VALUES ('vlad', 'dalv', 'test2@test.com', 'A', 'B');

SELECT * FROM `internetshop`.`users` WHERE user_id=1;

UPDATE `internetshop`.`users`
SET `login`='alex1', `password`='1xela', `email`='test1@test.com', `first_name`='C', `last_name`='D'
WHERE "user_id"=1;

DELETE FROM `internetshop`.`users` WHERE "user_id"=2;

SELECT * FROM `internetshop`.`users` WHERE login='alex1';

SELECT * FROM `internetshop`.`users`;

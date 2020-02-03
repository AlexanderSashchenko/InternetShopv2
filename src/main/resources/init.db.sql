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
    `password` VARCHAR(256) NOT NULL,
    `salt` VARBINARY(256) NOT NULL,
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

ALTER TABLE `internetshop`.`orders`
    ADD INDEX `orders_to_users_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `internetshop`.`orders`
    ADD CONSTRAINT `orders_to_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internetshop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

INSERT INTO `internetshop`.`items` (`title`, `price`) VALUES ('test_item_1', '999.99');
INSERT INTO `internetshop`.`items` (`title`, `price`) VALUES ('test_item_2', '100');
INSERT INTO `internetshop`.`items` (`title`, `price`) VALUES ('test_item_3', '0');

INSERT INTO `internetshop`.`roles` (`role_id`, `role_name`) VALUES ('1', 'USER');
INSERT INTO `internetshop`.`roles` (`role_id`, `role_name`) VALUES ('2', 'ADMIN');


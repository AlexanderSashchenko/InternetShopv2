CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internetshop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`item_id`));

INSERT INTO `internetshop`.`items` (`title`, `price`) VALUES ('xiaomi laptop', '999.99');
INSERT INTO `internetshop`.`items` (`title`, `price`) VALUES ('samsung smartphone', '499.99');

SELECT FROM `internetshop`.`items` WHERE item_id=1;

UPDATE `internetshop`.`items` SET `title`='updated title', `price`='000.01' WHERE "item_id"=1;

DELETE FROM `internetshop`.`items` WHERE "item_id"=1;

SELECT * FROM `internetshop`.`items`;
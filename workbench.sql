create database books_shop;
use books_shop;
CREATE TABLE `books_shop`.`role_user` (
  `id_role` INT NOT NULL AUTO_INCREMENT,
  `name_role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_role`));
  CREATE TABLE `books_shop`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `birth_of_day` DATE NOT NULL,
  `email_user` LONGTEXT NOT NULL,
  `gender_user` BIT(1) NOT NULL,
  `is_delete` BIT(1) NOT NULL,
  `pass_user` LONGTEXT NOT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `role_user_id_role` INT NULL,
  PRIMARY KEY (`id_user`),
    FOREIGN KEY (`role_user_id_role`)
    REFERENCES `books_shop`.`role_user` (`id_role`));
CREATE TABLE `books_shop`.`status_order` (
  `id_status` INT NOT NULL AUTO_INCREMENT,
  `name_status` VARCHAR(255) NULL,
  PRIMARY KEY (`id_status`));
CREATE TABLE `books_shop`.`type_product` (
  `id_type_product` INT NOT NULL AUTO_INCREMENT,
  `name_type_product` LONGTEXT NOT NULL,
  PRIMARY KEY (`id_type_product`));
  CREATE TABLE `books_shop`.`products` (
  `id_product` INT NOT NULL AUTO_INCREMENT,
  `author` VARCHAR(255) NOT NULL,
  `describe_book` LONGTEXT NULL,
  `img` LONGTEXT NOT NULL,
  `is_delete` BIT(1) NOT NULL,
  `name_product` VARCHAR(255) NOT NULL,
  `nation_book` VARCHAR(255) NOT NULL,
  `price_book` DOUBLE NOT NULL,
  `publication_year` VARCHAR(255) NOT NULL,
  `publishing_company` VARCHAR(255) NOT NULL,
  `quantity_books` INT NOT NULL,
  `id_type_product` INT NULL,
  PRIMARY KEY (`id_product`),
    FOREIGN KEY (`id_type_product`)REFERENCES `books_shop`.`type_product` (`id_type_product`));
    CREATE TABLE `books_shop`.`order_book` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `address_people` LONGTEXT NOT NULL,
  `day_order` DATE NOT NULL,
  `day_take` DATE NULL,
  `falg_delete` BIT(1) NOT NULL,
  `note_order` LONGTEXT NULL,
  `status_id_status` INT NULL,
  `user_id_user` INT NULL,
  PRIMARY KEY (`id_order`),
    FOREIGN KEY (`status_id_status`)
    REFERENCES `books_shop`.`status_order` (`id_status`),
    FOREIGN KEY (`user_id_user`)
    REFERENCES `books_shop`.`user` (`id_user`));
    CREATE TABLE `books_shop`.`order_detail` (
  `id_detail` INT NOT NULL AUTO_INCREMENT,
  `number_of_detail` INT NOT NULL,
  `order_id_order` INT NULL,
  `product_id_product` INT NULL,
  PRIMARY KEY (`id_detail`),
    FOREIGN KEY (`order_id_order`)
    REFERENCES `books_shop`.`order_book` (`id_order`),
    FOREIGN KEY (`product_id_product`)
    REFERENCES `books_shop`.`products` (`id_product`));
DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45)      DEFAULT NULL,
  `last_name`  VARCHAR(45)      DEFAULT NULL,
  `company`    VARCHAR(45)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

CREATE DATABASE IF NOT EXISTS `hb_student_tracker`;
USE `hb_student_tracker`;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45)      DEFAULT NULL,
  `last_name`  VARCHAR(45)      DEFAULT NULL,
  `email`      VARCHAR(45)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

ALTER TABLE `hb_student_tracker`.`student`
  ADD COLUMN `date_of_birth` DATETIME NULL AFTER `last_name`;

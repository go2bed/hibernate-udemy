DROP SCHEMA IF EXISTS `hb-05-many-to-many`;

CREATE SCHEMA `hb-05-many-to-many`;

USE `hb-05-many-to-many`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `instructor_detail`;

CREATE TABLE `instructor_detail` (
  `id`              INT(11) NOT NULL AUTO_INCREMENT,
  `youtube_channel` VARCHAR(128)     DEFAULT NULL,
  `hobby`           VARCHAR(45)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `instructor`;

CREATE TABLE `instructor` (
  `id`                   INT(11) NOT NULL AUTO_INCREMENT,
  `first_name`           VARCHAR(45)      DEFAULT NULL,
  `last_name`            VARCHAR(45)      DEFAULT NULL,
  `email`                VARCHAR(45)      DEFAULT NULL,
  `instructor_detail_id` INT(11)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`instructor_detail_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`)
  REFERENCES `instructor_detail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT,
  `title`         VARCHAR(128)     DEFAULT NULL,
  `instructor_id` INT(11)          DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE KEY `TITLE_UNIQUE` (`title`),

  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),

  CONSTRAINT `FK_INSTRUCTOR`
  FOREIGN KEY (`instructor_id`)
  REFERENCES `instructor` (`id`)

    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id`        INT(11) NOT NULL AUTO_INCREMENT,
  `comment`   VARCHAR(256)     DEFAULT NULL,
  `course_id` INT(11)          DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_COURSE_ID_idx` (`course_id`),

  CONSTRAINT `FK_COURSE`
  FOREIGN KEY (`course_id`)
  REFERENCES `course` (`id`)

    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

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

DROP TABLE IF EXISTS `course_student`;

CREATE TABLE `course_student` (
  `course_id`  INT(11) NOT NULL,
  `student_id` INT(11) NOT NULL,

  PRIMARY KEY (`course_id`, `student_id`),

  KEY `FK_STUDENT_idx` (`student_id`),

  CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`)
  REFERENCES `course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,

  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`)
  REFERENCES `student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

SET FOREIGN_KEY_CHECKS = 1;

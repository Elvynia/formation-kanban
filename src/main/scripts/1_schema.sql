DROP database IF EXISTS `kanban`;
CREATE database `kanban`;
USE `kanban`;

CREATE TABLE `kanban` (
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`openedOn` DATE NOT NULL,
	`closedOn` DATE NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `category` (
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL,
	`zorder` int NOT NULL,
	`id_kanban` int(11) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `name_UNIQUE` (`name`),
	KEY `FK_category_kanban_idx` (`id_kanban`),
	CONSTRAINT `FK_category_kanban` FOREIGN KEY (`id_kanban`)
		REFERENCES `kanban` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `task` (
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`title` varchar(80) NOT NULL,
	`description` TEXT DEFAULT NULL,
	`points` TINYINT(1) DEFAULT NULL,
	`createdOn` DATE NOT NULL,
	`lastModifiedOn` DATE NOT NULL,
	`id_category` int(11) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	KEY `FK_task_category_idx` (`id_category`),
	CONSTRAINT `FK_task_category` FOREIGN KEY (`id_category`)
		REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

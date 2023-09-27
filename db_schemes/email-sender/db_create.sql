CREATE TABLE `mail_message` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `email_from` varchar(100) DEFAULT NULL,
                                `email_to` varchar(100) DEFAULT NULL,
                                `text` varchar(500) DEFAULT NULL,
                                `timestamp` datetime DEFAULT NULL,
                                PRIMARY KEY (`id`)
);

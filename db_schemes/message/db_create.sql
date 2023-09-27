CREATE TABLE `user` (
                        `id` bigint NOT NULL,
                        `country` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `firstname` varchar(255) DEFAULT NULL,
                        `lastname` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
);
CREATE TABLE `chat` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        PRIMARY KEY (`id`)
);
CREATE TABLE `message` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `message` varchar(255) DEFAULT NULL,
                           `timestamp` datetime(6) DEFAULT NULL,
                           `chat_id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                           FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`)
);
CREATE TABLE `user_chat` (
                             `chat_id` bigint NOT NULL,
                             `user_id` bigint NOT NULL,
                             PRIMARY KEY (`chat_id`,`user_id`),
                             FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
                             FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

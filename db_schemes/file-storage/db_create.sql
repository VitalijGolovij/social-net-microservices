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
CREATE TABLE `image` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `data` blob,
                         `name` varchar(255) DEFAULT NULL,
                         `type` varchar(255) DEFAULT NULL,
                         `user_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);
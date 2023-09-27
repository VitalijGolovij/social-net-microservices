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
CREATE TABLE `user_friends` (
                                `user_id` bigint NOT NULL,
                                `friends_id` bigint NOT NULL,
                                PRIMARY KEY (`user_id`,`friends_id`),
                                FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                                FOREIGN KEY (`friends_id`) REFERENCES `user` (`id`)
);
CREATE TABLE `user_friends_request` (
                                        `user_id` bigint NOT NULL,
                                        `friends_request_id` bigint NOT NULL,
                                        PRIMARY KEY (`user_id`,`friends_request_id`),
                                        FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                                        FOREIGN KEY (`friends_request_id`) REFERENCES `user` (`id`)
);



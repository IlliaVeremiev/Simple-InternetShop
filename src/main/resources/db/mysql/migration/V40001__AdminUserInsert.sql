INSERT INTO `users` (`id`, `email`, `password`) VALUES (00989001, 'admin@simple.shop', '$2a$10$.KoUJXo8mbTxa8FFftxPFOz./29N/QKiWaWbx56mVqLFRYKbWo8kS');
INSERT INTO `users_authorities` (`user_id`, `authority_id`) VALUES (00989001, 00988001);
INSERT INTO `carts` (`id`, `user_id`) VALUES (01001001, 00989001);
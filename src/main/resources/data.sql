insert into users (id, email, password, username, created_at, updated_at)
values
(1, 'admin@test.com', '$2a$10$fEeAPpgR5bhWrZ/w1EoCH.a7zmvUD1.c2BW586zj5rrN2nq.pgyum', 'admin', NOW(), NOW()),
(2, 'customer@test.com', '$2a$10$fEeAPpgR5bhWrZ/w1EoCH.a7zmvUD1.c2BW586zj5rrN2nq.pgyum', 'customer', NOW(), NOW());

insert into user_roles (user_id, roles)
values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_CUSTOMER')
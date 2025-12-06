insert into users (id, email, password, username, created_at, updated_at)
values
(1, 'admin@test.com', '$2a$10$fEeAPpgR5bhWrZ/w1EoCH.a7zmvUD1.c2BW586zj5rrN2nq.pgyum', 'admin', NOW(), NOW()),
(2, 'customer@test.com', '$2a$10$fEeAPpgR5bhWrZ/w1EoCH.a7zmvUD1.c2BW586zj5rrN2nq.pgyum', 'customer', NOW(), NOW());

insert into user_roles (user_id, roles)
values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_CUSTOMER');

insert into products (id, slug, name, price, description, created_by, created_at, updated_at)
values
    (1, 'plain-office-shirt-1', 'Plain office shirt one', 30.5, 'Plain office shirt 1 for professional environment', 1, NOW(), NOW()),
    (2, 'plain-office-shirt-2', 'Plain office shirt two', 30.5, 'Plain office shirt 2 for professional environment', 1, NOW(), NOW()),
    (3, 'plain-office-shirt-3', 'Plain office shirt three', 30.5, 'Plain office shirt 3 for professional environment', 1, NOW(), NOW()),
    (4, 'plain-office-shirt-4', 'Plain office shirt four', 30.5, 'Plain office shirt 4 for professional environment', 1, NOW(), NOW());

insert into product_colors (id, color, product_id)
values
    (1, 'white', 1),
    (2, 'black', 1),
    (3, 'blue', 2),
    (4, 'green', 2),
    (5, 'yellow', 3),
    (7, 'purple', 3),
    (8, 'pink', 4),
    (9, 'brown', 4);

insert into product_images (id, url, product_id)
values
    (1, 'https://tailwindcss.com/plus-assets/img/ecommerce-images/product-page-01-related-product-01.jpg', 1),
    (2, 'https://tailwindcss.com/plus-assets/img/ecommerce-images/product-page-01-related-product-02.jpg', 2),
    (3, 'https://tailwindcss.com/plus-assets/img/ecommerce-images/product-page-01-related-product-03.jpg', 3),
    (4, 'https://tailwindcss.com/plus-assets/img/ecommerce-images/product-page-01-related-product-04.jpg', 4);

insert into product_size (id, size, product_id)
values
    (1, 'XSM', 1),
    (2, 'SM', 2),
    (3, 'MD', 3),
    (4, 'LG', 4),
    (5, 'XLG', 4);

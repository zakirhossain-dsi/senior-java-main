CREATE TABLE products
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR NOT NULL,
    price       DOUBLE  NOT NULL,
    description VARCHAR,
    vat         ENUM('10','18','20')
);

INSERT INTO products (id, name, price, description, vat)
VALUES (1, 'Apple', 1.25, 'tellus id nunc interdum feugiat. Sed nec', '10'),
       (2, 'Banana', 3.00, 'nec, diam. Duis mi enim, condimentum eget, volutpat ornare, facilisis eget, ipsum. Donec sollicitudin adipiscing ligula. Aenean', '10'),
       (3, 'Pineapple', 5.75, 'quam. Curabitur vel lectus. Cum sociis natoque penatibus', '10'),
       (4, 'Orange', 1.50, 'risus, at fringilla purus mauris a nunc. In at pede.', '10'),
       (5, 'Notebook', 2099.90, 'neque sed sem egestas blandit. Nam nulla magna, malesuada vel, convallis in, cursus et, eros. Proin', '20');
DROP SCHEMA IF EXISTS theatre;
CREATE SCHEMA theatre;
use theatre;

CREATE TABLE shows (
show_id INT PRIMARY KEY AUTO_INCREMENT,
show_name VARCHAR(255),
type VARCHAR(255),
description VARCHAR(255),
duration INT,
language VARCHAR(255),
has_live_music BOOLEAN,
performers VARCHAR(255)
);

INSERT INTO shows (show_name, type, description,duration) VALUES 
("test1", "musical", "test1",  165),
("test2", "opera", "test2", 130),
("test3", "musical", "test3", 178);

 CREATE TABLE performances (
performance_id INT PRIMARY KEY AUTO_INCREMENT,
show_id INT,
date DATE, 
stage_time enum('Evening', 'Matinee'),
stall_price DOUBLE,
circle_price DOUBLE,
FOREIGN KEY (show_id) REFERENCES shows(show_id)
);

INSERT INTO performances (show_id, date, stage_time, stall_price, circle_price) VALUES (1, '2023-02-11', 'Evening', 12.50, 10.00),(2, '2023-04-05', 'Evening', 20.00, 19.50), (3, '2023-05-23', 'Matinee', 25.00, 24.30);

CREATE TABLE seats (
seat_id INT,
performance_id INT,
seat_type VARCHAR(255),
seat_booked BOOLEAN,
CONSTRAINT seatsPK PRIMARY KEY (seat_id, performance_id),
FOREIGN KEY (performance_id) REFERENCES performances(performance_id)
);

INSERT INTO seats (seat_id,performance_id, seat_type, seat_booked) VALUES
(1,1, "stall", false),
(2,1, "stall", false),
(3,1, "stall", false),
(4,1, "stall", true), 
(5,1, "stall", false);

INSERT INTO seats (seat_id, performance_id, seat_type, seat_booked) VALUES (121, 1, "circle", false), (122, 1, "circle", true);

CREATE TABLE basket (
user_id INT,
performance_id INT,
standard_or_concession ENUM('Standard', 'Concession'),
seat_id INT,
price DOUBLE,
CONSTRAINT basket PRIMARY KEY (seat_id, performance_id),
FOREIGN KEY (performance_id) REFERENCES performances(performance_id),
FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
);


CREATE TABLE users(
user_id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(255) UNIQUE ,
password VARCHAR(255)
);


CREATE TABLE customers (
customer_id INT,
first_name VARCHAR(255),
last_name VARCHAR(255),
address VARCHAR(255),
FOREIGN KEY (customer_id) REFERENCES users(user_id)
);

INSERT INTO users (username, password) VALUES ('admin', 'admin');

CREATE TABLE orders (
order_id INT PRIMARY KEY AUTO_INCREMENT,
customer_id INT,
order_date DATE,
collection_required BOOLEAN,
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE order_line (
line_id INT PRIMARY KEY AUTO_INCREMENT,
order_id INT,
performance_id INT,
seat_id INT,
standard_or_concession ENUM('Standard', 'Concession'),
price DOUBLE,
FOREIGN KEY (order_id) REFERENCES orders(order_id),
FOREIGN KEY (performance_id) REFERENCES performances(performance_id),
FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
);

DELIMITER /
CREATE PROCEDURE insertShow(
IN var_show_name VARCHAR(255),
IN var_type VARCHAR(255),
IN var_description VARCHAR(255),
IN var_duration INT,
IN var_language VARCHAR(255),
IN var_has_live_music BOOLEAN,
IN var_performers VARCHAR(255)
)
BEGIN 
INSERT INTO shows (show_name, type, description, duration, language, has_live_music, performers) 
VALUES (var_show_name, var_type, var_description, var_duration, var_language, var_has_live_music,var_performers);
END;
/

CREATE PROCEDURE stagePerformance(
IN var_show_id INT,
IN var_date DATE, 
IN var_stage_time enum('Evening', 'Matinee'),
IN var_stall_price DOUBLE,
IN var_circle_price DOUBLE
)
BEGIN 
INSERT INTO performances( show_id, date, stage_time, stall_price , circle_price)
VALUES (var_show_id,var_date,var_stage_time,var_stall_price,var_circle_price);
END;
/

CREATE PROCEDURE orderCompleteInsertion(
IN var_customer_id INT,
IN var_first_name VARCHAR(255),
IN var_last_name VARCHAR(255),
IN var_address VARCHAR(255),
IN var_order_date DATE,
IN var_collection_required BOOLEAN
)
BEGIN 
INSERT INTO customers (customer_id, first_name, last_name, address) 
VALUES (var_customer_id, var_first_name, var_last_name, var_address);
INSERT INTO orders (customer_id, order_date, collection_required)
VALUES (var_customer_id, var_order_date, var_collection_required);
END;
/

CREATE PROCEDURE orderLineInsertion(
IN var_order_id INT,
IN var_performance_id INT,
IN var_seat_id INT,
IN var_standard_or_concession ENUM('Standard', 'Concession'),
IN var_price DOUBLE
)
BEGIN 
INSERT INTO order_line(order_id, performance_id, seat_id, standard_or_concession, price)
VALUES (var_order_id, var_performance_id, var_seat_id, var_standard_or_concession,var_price);
END;
/

CREATE PROCEDURE getOrderLines(
IN var_order_id VARCHAR(255)
)
BEGIN
SELECT order_line.performance_id, order_line.seat_id, seats.seat_type, order_line.standard_or_concession, order_line.price, shows.show_name, performances.date, performances.stage_time FROM order_line 
JOIN performances ON order_line.performance_id = performances.performance_id
JOIN seats ON seats.performance_id = order_line.performance_id AND seats.seat_id = order_line.seat_id
JOIN shows ON performances.show_id = shows.show_id
WHERE order_id = var_order_id;
END;
/
DELIMITER /
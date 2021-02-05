-- Create the tables in my DB

-- Store info about each user
CREATE TABLE users(
    username CHAR(20)  PRIMARY KEY,
    passwords CHAR(20) NOT NULL,
    users_name CHAR(30) NOT NULL,
    dob DATE NOT NULL
);

-- Store the info about each car and whether or not it is in the lot or not as well as if it has an owner
CREATE TABLE cars (
    car_id SERIAL PRIMARY KEY,
    own_status CHAR(5) NOT NULL,
    lot CHAR(3) NOT NULL,
	make INT NOT NULL,
	model CHAR(30) NOT NULL,
	color CHAR(15) NOT NULL,
    price NUMERIC NOT NULL,
	username CHAR(20) REFERENCES users(username)
);

-- Store the info of the payment history of cars that are now owned by the customers
CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    car_id INT NOT NULL REFERENCES cars(car_id),
    amount NUMERIC NOT NULL,
    total_payment NUMERIC NOT NULL,
    monthly_payment NUMERIC NOT NULL,
    remaining_payments NUMERIC NOT NULL,
    date DATE NOT NULL
);

-- Store all offers made by customers and the statuses of each offer
CREATE TABLE offers(
	offer_id SERIAL PRIMARY KEY,
	username CHAR(20) NOT NULL,
	car_id INT NOT NULL REFERENCES cars(car_id),
	offer NUMERIC NOT NULL,
	status CHAR(10) NOT NULL
);

-- In case of major edits or complete restructuring
DROP TABLE users CASCADE;
DROP TABLE cars CASCADE;
DROP TABLE offers;
DROP TABLE payments;

-- Alter the sequences of the serial id's
ALTER SEQUENCE cars_car_id_seq RESTART WITH 99 INCREMENT BY 1;
ALTER SEQUENCE offers_offer_id_seq RESTART WITH 999 INCREMENT BY 1;
ALTER SEQUENCE payments_payment_id_seq RESTART WITH 9999 INCREMENT BY 1;

-- Need to change the remaining_payments from Numeric to Int
ALTER TABLE payments ALTER COLUMN remaining_payments TYPE INT;

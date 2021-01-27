-- Create the tables in my DB

-- Store info about each user
CREATE TABLE "user" (
    "username" CHAR   NOT NULL,
    "password" CHAR   NOT NULL,
    "name" CHAR   NOT NULL,
    "age" INT   NOT NULL,
    "dob" DATE   NOT NULL,
    CONSTRAINT "pk_user" PRIMARY KEY (
        "username"
     )
);

-- Store the info about each car and whether or not it is in the lot or not as well as if it has an owner
CREATE TABLE "cars" (
    "car_id" INT   NOT NULL,
    "owner" CHAR   NOT NULL,
    "lot" CHAR   NOT NULL,
    "price" NUMERIC   NOT NULL,
    CONSTRAINT "pk_cars" PRIMARY KEY (
        "car_id"
     )
);

-- Store the info of the payment history of cars that are now owned by the customers
CREATE TABLE "payment_history" (
    "history_id" INT   NOT NULL,
    "car_id" INT   NOT NULL,
    "amount" NUMERIC   NOT NULL,
    "total_payment" NUMERIC   NOT NULL,
    "monthly_payment" NUMERIC   NOT NULL,
    "remaining_payments" NUMERIC   NOT NULL,
    "date" DATE   NOT NULL,
    CONSTRAINT "pk_payment_history" PRIMARY KEY (
        "history_id"
     )
);

-- Acts as a bridge to customers that own cars.
-- This is needed because even though the customer to car relationship is one to many,
-- A customer does not have to have a car and a car does not have to have an owner.
-- Thus this table is need so as not to break the logic in the users or cars tables.
CREATE TABLE "customer_cars" (
    "id" INT   NOT NULL,
    "username" CHAR   NOT NULL,
    "car_id" INT   NOT NULL,
    CONSTRAINT "pk_customer_cars" PRIMARY KEY (
        "id"
     )
);

-- Foreign Key Constraints

ALTER TABLE "payment_history" ADD CONSTRAINT "fk_payment_history_car_id" FOREIGN KEY("car_id")
REFERENCES "cars" ("car_id");

ALTER TABLE "customer_cars" ADD CONSTRAINT "fk_customer_cars_username" FOREIGN KEY("username")
REFERENCES "user" ("username");

ALTER TABLE "customer_cars" ADD CONSTRAINT "fk_customer_cars_car_id" FOREIGN KEY("car_id")
REFERENCES "cars" ("car_id");
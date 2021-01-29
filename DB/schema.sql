-- Create the tables in my DB

-- Store info about each user
CREATE TABLE "user" (
    "username" CHAR(20)   NOT NULL,
    "password" CHAR(20)   NOT NULL,
    "name" CHAR(30)   NOT NULL,
    "age" INT   NOT NULL,
    "dob" DATE   NOT NULL,
    CONSTRAINT "pk_user" PRIMARY KEY (
        "username"
     )
);

-- Store the info about each car and whether or not it is in the lot or not as well as if it has an owner
CREATE TABLE "cars" (
    "car_id" INT   NOT NULL,
    "owner" CHAR(30)   NOT NULL,
    "lot" CHAR(3)   NOT NULL,
	"make" INT NOT NULL,
	"model" CHAR(30) NOT NULL,
	"color" CHAR(15) NOT NULL,
    "price" NUMERIC   NOT NULL,
	"username" CHAR(20),
    CONSTRAINT "pk_cars" PRIMARY KEY (
        "car_id"
     )
);

-- Store the info of the payment history of cars that are now owned by the customers
CREATE TABLE "payment" (
    "payment_id" INT NOT NULL,
    "car_id" INT   NOT NULL,
    "amount" NUMERIC   NOT NULL,
    "total_payment" NUMERIC   NOT NULL,
    "monthly_payment" NUMERIC   NOT NULL,
    "remaining_payments" NUMERIC   NOT NULL,
    "date" DATE   NOT NULL,
    CONSTRAINT "pk_payment" PRIMARY KEY (
        "payment_id"
     )
);

-- Store all offers made by customers and the statuses of each offer
CREATE TABLE "offers"(
	"offer_id" INT NOT NULL,
	"username" CHAR(20) NOT NULL,
	"car_id" INT NOT NULL,
	"offer" NUMERIC NOT NULL,
	"status" CHAR(10) NOT NULL,
	CONSTRAINT "pk_offer" PRIMARY KEY(
		"offer_id"
	)
);

-- Foreign Key Constraints

ALTER TABLE "payment" ADD CONSTRAINT "fk_payment_car_id" FOREIGN KEY("car_id")
REFERENCES "cars" ("car_id");

ALTER TABLE "cars" ADD CONSTRAINT "fk_cars_username" FOREIGN KEY("username")
REFERENCES "user" ("username");

ALTER TABLE "offers" ADD CONSTRAINT "fk_offers_username" FOREIGN KEY("username")
REFERENCES "user" ("username");

ALTER TABLE "offers" ADD CONSTRAINT "fk_offers_car_id" FOREIGN KEY("car_id")
REFERENCES "cars" ("car_id");
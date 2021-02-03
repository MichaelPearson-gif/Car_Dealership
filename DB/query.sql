-- Writing test queries so as to know what works and what doesn't work
SELECT c.owner_name, c.username, u.username, u.users_name
FROM cars c
JOIN users u 
ON c.username = u.username;

-- Updating using update join and sub-queries
UPDATE cars  
SET owner_name = u.users_name
FROM users u
WHERE car_id = 1 and u.users_name IN(
	SELECT u.users_name
	FROM users u
	WHERE u.users_name = 'James'
);

-- Possible query to use to update car info
-- ? denotes where values from the program will be inserted
UPDATE cars 
SET owner_name = u.users_name, lot = 'off', username = 'caliman'
FROM users u
WHERE car_id = 3 AND u.users_name IN(
	SELECT u.users_name 
	FROM users u
	WHERE u.username = 'caliman'
);
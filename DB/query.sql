-- Writing test queries so as to know what works and what doesn't work
SELECT c.owner_name, c.username, u.username, u.users_name
FROM cars c
JOIN users u 
ON c.username = u.username;

-- Updating using sub-queries
UPDATE cars  
SET owner_name = u.users_name
FROM users u
WHERE u.username IN(
	SELECT u.users_name
	FROM users u
	WHERE u.username = 'caliman'
);
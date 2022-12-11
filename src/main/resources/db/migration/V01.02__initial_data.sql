INSERT INTO products (version, name, brand, description, color, material,
length_mm, width_mm, height_mm, weight_grams, prize_euro) VALUES
	(0, 'Bio Tee', 'TeeMaker',  'Pack with christmas Tea Bio', null, null, 130, 140, 150, 20, 6.5),
	(0, 'Tee Cup', 'CupMaker',  'The perfect cup for tee', 'white', 'Glass', 124, 123, 121, 410, 19.9);

INSERT INTO orders (version, product_id, username, amount, creation_date, status, recipient_name, recipient_address) VALUES
    	(0, 1, 'test user', 3, '2022-10-10 10:10:10' , 'SUBMITTED', 'Max Mustermann', 'Odeonspl. 1, 80539 Munich');

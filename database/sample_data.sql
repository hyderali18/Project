-- TechGo Sample Data
-- Insert sample gadgets with specifications and reviews

-- Insert Mobile Phones
INSERT INTO gadgets (name, brand, category, price, description, image_url, rating, review_count) VALUES
('iPhone 15 Pro Max', 'Apple', 'mobiles', 1199.00, 'Latest iPhone with titanium design, A17 Pro chip, and advanced camera system', 'https://example.com/iphone15promax.jpg', 4.5, 1250),
('Samsung Galaxy S24 Ultra', 'Samsung', 'mobiles', 1299.00, 'Premium Android phone with S Pen, 200MP camera, and Galaxy AI features', 'https://example.com/galaxys24ultra.jpg', 4.4, 980),
('Google Pixel 8 Pro', 'Google', 'mobiles', 999.00, 'AI-powered phone with exceptional camera and Google Tensor G3 chip', 'https://example.com/pixel8pro.jpg', 4.3, 750),
('OnePlus 12', 'OnePlus', 'mobiles', 799.00, 'Flagship killer with Snapdragon 8 Gen 3 and fast charging', 'https://example.com/oneplus12.jpg', 4.2, 620),
('Xiaomi 14 Pro', 'Xiaomi', 'mobiles', 899.00, 'Premium phone with Leica camera and fast charging technology', 'https://example.com/xiaomi14pro.jpg', 4.1, 450),
('iPhone 15', 'Apple', 'mobiles', 799.00, 'Standard iPhone 15 with Dynamic Island and USB-C', 'https://example.com/iphone15.jpg', 4.4, 890),
('Samsung Galaxy S24', 'Samsung', 'mobiles', 799.00, 'Compact flagship with powerful performance and AI features', 'https://example.com/galaxys24.jpg', 4.3, 670),
('Google Pixel 8', 'Google', 'mobiles', 699.00, 'Compact AI phone with excellent camera quality', 'https://example.com/pixel8.jpg', 4.2, 540);

-- Insert Laptops
INSERT INTO gadgets (name, brand, category, price, description, image_url, rating, review_count) VALUES
('MacBook Pro 16" M3 Max', 'Apple', 'laptops', 3499.00, 'Most powerful MacBook with M3 Max chip, stunning display, and all-day battery', 'https://example.com/macbookpro16.jpg', 4.7, 890),
('Dell XPS 15', 'Dell', 'laptops', 2499.00, 'Premium Windows laptop with stunning 4K display and powerful performance', 'https://example.com/dellxps15.jpg', 4.5, 670),
('HP Spectre x360', 'HP', 'laptops', 1899.00, 'Versatile 2-in-1 with premium design and excellent performance', 'https://example.com/hpspectrex360.jpg', 4.4, 520),
('Lenovo ThinkPad X1 Carbon', 'Lenovo', 'laptops', 2099.00, 'Business ultrabook with legendary keyboard and durability', 'https://example.com/thinkpadx1.jpg', 4.6, 780),
('ASUS ROG Zephyrus G16', 'ASUS', 'laptops', 2299.00, 'Gaming laptop with slim design and powerful RTX graphics', 'https://example.com/rogzephyrus.jpg', 4.3, 410),
('MacBook Air M2', 'Apple', 'laptops', 1299.00, 'Thin and light laptop with excellent performance and battery life', 'https://example.com/macbookair.jpg', 4.5, 1120);

-- Insert Tablets
INSERT INTO gadgets (name, brand, category, price, description, image_url, rating, review_count) VALUES
('iPad Pro 12.9" M2', 'Apple', 'tablets', 1099.00, 'Professional tablet with M2 chip and stunning mini-LED display', 'https://example.com/ipadpro129.jpg', 4.6, 780),
('Samsung Galaxy Tab S9 Ultra', 'Samsung', 'tablets', 1199.00, 'Largest Android tablet with S Pen and stunning AMOLED display', 'https://example.com/tabs9ultra.jpg', 4.4, 520),
('Microsoft Surface Pro 9', 'Microsoft', 'tablets', 1299.00, 'Versatile 2-in-1 with powerful performance and Windows 11', 'https://example.com/surfacepro9.jpg', 4.3, 440),
('Amazon Fire Max 11', 'Amazon', 'tablets', 229.00, 'Affordable tablet with large display and good performance', 'https://example.com/firemax11.jpg', 3.9, 280),
('Lenovo Tab P12 Pro', 'Lenovo', 'tablets', 599.00, 'Premium tablet with OLED display and productivity features', 'https://example.com/tabp12pro.jpg', 4.2, 310),
('iPad Air M1', 'Apple', 'tablets', 599.00, 'Perfect balance of performance and price', 'https://example.com/ipadair.jpg', 4.5, 890);

-- Insert Earphones
INSERT INTO gadgets (name, brand, category, price, description, image_url, rating, review_count) VALUES
('AirPods Pro 2', 'Apple', 'earphones', 249.00, 'Premium true wireless earbuds with active noise cancellation', 'https://example.com/airpodspro2.jpg', 4.5, 2340),
('Sony WH-1000XM5', 'Sony', 'earphones', 399.00, 'Industry-leading noise cancelling headphones', 'https://example.com/sonywh1000xm5.jpg', 4.7, 1890),
('Bose QuietComfort Ultra', 'Bose', 'earphones', 429.00, 'Premium noise cancelling headphones with exceptional comfort', 'https://example.com/boseqc.jpg', 4.6, 1560),
('JBL Tour Pro 2', 'JBL', 'earphones', 199.00, 'True wireless earbuds with smart charging case', 'https://example.com/jbltourpro2.jpg', 4.2, 890),
('Samsung Galaxy Buds2 Pro', 'Samsung', 'earphones', 229.00, 'Premium earbuds with active noise cancellation', 'https://example.com/galaxybuds2pro.jpg', 4.3, 1120),
('Sony WF-1000XM5', 'Sony', 'earphones', 299.00, 'Compact true wireless earbuds with premium sound', 'https://example.com/sonywf1000xm5.jpg', 4.4, 1340);

-- Insert Speakers
INSERT INTO gadgets (name, brand, category, price, description, image_url, rating, review_count) VALUES
('JBL Charge 5', 'JBL', 'speakers', 179.00, 'Portable Bluetooth speaker with powerful bass and long battery', 'https://example.com/jblcharge5.jpg', 4.4, 2670),
('Bose SoundLink Flex', 'Bose', 'speakers', 149.00, 'Portable speaker with waterproof design and PositionIQ', 'https://example.com/soundlinkflex.jpg', 4.5, 1890),
('Sonos Move 2', 'Sonos', 'speakers', 499.00, 'Battery-powered smart speaker with Wi-Fi and Bluetooth', 'https://example.com/sonosmove2.jpg', 4.6, 1230),
('Sony SRS-XG300', 'Sony', 'speakers', 249.00, 'Portable party speaker with powerful sound and lights', 'https://example.com/sonyxg300.jpg', 4.3, 890),
('UE Wonderboom 3', 'UE', 'speakers', 99.00, 'Compact waterproof speaker with 360-degree sound', 'https://example.com/wonderboom3.jpg', 4.2, 1560),
('Bose SoundLink Revolve II', 'Bose', 'speakers', 219.00, '360-degree sound speaker with deep bass', 'https://example.com/revolveii.jpg', 4.4, 2010);

-- Insert specifications for each gadget (sample data)
-- iPhone 15 Pro Max specifications
INSERT INTO gadget_specifications (gadget_id, spec_name, spec_value) VALUES
(1, 'Display', '6.7" Super Retina XDR OLED'),
(1, 'Processor', 'A17 Pro chip'),
(1, 'RAM', '8GB'),
(1, 'Storage', '256GB/512GB/1TB'),
(1, 'Camera', '48MP main, 12MP ultrawide, 12MP telephoto'),
(1, 'Battery', '4,423mAh'),
(1, 'Operating System', 'iOS 17'),
(1, 'Weight', '221g'),
(1, 'Dimensions', '159.9 x 76.7 x 8.25 mm');

-- Samsung Galaxy S24 Ultra specifications
INSERT INTO gadget_specifications (gadget_id, spec_name, spec_value) VALUES
(2, 'Display', '6.8" Dynamic AMOLED 2X'),
(2, 'Processor', 'Snapdragon 8 Gen 3'),
(2, 'RAM', '12GB'),
(2, 'Storage', '256GB/512GB/1TB'),
(2, 'Camera', '200MP main, 50MP periscope, 12MP ultrawide, 10MP telephoto'),
(2, 'Battery', '5,000mAh'),
(2, 'Operating System', 'Android 14'),
(2, 'Weight', '232g'),
(2, 'Dimensions', '162.3 x 79 x 8.6 mm'),
(2, 'Special Features', 'S Pen included');

-- MacBook Pro 16" specifications
INSERT INTO gadget_specifications (gadget_id, spec_name, spec_value) VALUES
(13, 'Display', '16.2" Liquid Retina XDR'),
(13, 'Processor', 'Apple M3 Max (12-core CPU, 38-core GPU)'),
(13, 'RAM', '36GB unified memory'),
(13, 'Storage', '1TB SSD'),
(13, 'Graphics', 'Apple M3 Max GPU'),
(13, 'Battery', '100Wh (up to 22 hours)'),
(13, 'Operating System', 'macOS Sonoma'),
(13, 'Weight', '2.16kg'),
(13, 'Dimensions', '355.7 x 248.1 x 17.0 mm'),
(13, 'Ports', '3x Thunderbolt 4, HDMI, SD card, MagSafe');

-- iPad Pro 12.9" specifications
INSERT INTO gadget_specifications (gadget_id, spec_name, spec_value) VALUES
(19, 'Display', '12.9" mini-LED Liquid Retina XDR'),
(19, 'Processor', 'Apple M2'),
(19, 'RAM', '8GB'),
(19, 'Storage', '128GB/256GB/512GB/1TB/2TB'),
(19, 'Camera', '12MP wide, 10MP ultrawide, LiDAR'),
(19, 'Battery', '10,758mAh'),
(19, 'Operating System', 'iPadOS 17'),
(19, 'Weight', '682g (Wi-Fi), 684g (Wi-Fi+Cellular)'),
(19, 'Dimensions', '280.6 x 214.9 x 6.4 mm');

-- AirPods Pro 2 specifications
INSERT INTO gadget_specifications (gadget_id, spec_name, spec_value) VALUES
(25, 'Driver Size', '11mm'),
(25, 'Noise Cancellation', 'Active Noise Cancellation'),
(25, 'Transparency Mode', 'Yes'),
(25, 'Battery Life', '6 hours listening, 4.5 hours talking'),
(25, 'Charging Case', '30 hours total listening time'),
(25, 'Connectivity', 'Bluetooth 5.3'),
(25, 'Water Resistance', 'IPX4'),
(25, 'Features', 'Adaptive Audio, Conversation Awareness'),
(25, 'Weight', '5.3g per earbud');

-- Insert sample reviews
INSERT INTO reviews (gadget_id, user_name, email, rating, comment) VALUES
(1, 'John Smith', 'john.smith@email.com', 5, 'Absolutely love the titanium design! The camera is incredible and battery life is much better than expected.'),
(1, 'Sarah Johnson', 'sarah.j@email.com', 4, 'Great phone but very expensive. The performance is top-notch, but I wish the storage options were better.'),
(1, 'Mike Chen', 'mike.chen@email.com', 5, 'Best iPhone yet! The A17 Pro chip handles everything I throw at it. Gaming performance is outstanding.'),
(2, 'Emily Davis', 'emily.d@email.com', 5, 'The S Pen functionality is amazing for productivity. Camera quality is superb, especially the 200MP main sensor.'),
(2, 'Alex Wilson', 'alex.w@email.com', 4, 'Great phone with excellent features. Battery life could be better, but fast charging helps.'),
(13, 'David Brown', 'david.b@email.com', 5, 'Perfect for my video editing work. The M3 Max chip handles 4K video editing flawlessly. Display is stunning!'),
(13, 'Lisa Anderson', 'lisa.a@email.com', 4, 'Incredible performance but very heavy. The screen is beautiful and keyboard is excellent.'),
(25, 'Tom Harris', 'tom.h@email.com', 5, 'The new adaptive audio feature is game-changing. Noise cancellation is even better than the first generation.'),
(25, 'Jennifer Lee', 'jennifer.l@email.com', 4, 'Great sound quality and comfortable fit. The charging case is convenient but a bit bulky.');

-- Insert more reviews for variety
INSERT INTO reviews (gadget_id, user_name, email, rating, comment) VALUES
(19, 'Robert Taylor', 'robert.t@email.com', 5, 'Perfect for digital art and design. The large screen and M2 performance are incredible.'),
(19, 'Maria Garcia', 'maria.g@email.com', 4, 'Excellent tablet for media consumption. Very expensive but worth it for professionals.'),
(25, 'James White', 'james.w@email.com', 4, 'Sound quality is exceptional. The transparency mode is very useful when running.'),
(13, 'Amanda Martinez', 'amanda.m@email.com', 5, 'Worth every penny! Handles all my development work and creative tasks with ease.'),
(2, 'Kevin Thompson', 'kevin.t@email.com', 3, 'Good phone but too large for one-handed use. Software features are nice but some feel gimmicky.'),
(1, 'Nancy Rodriguez', 'nancy.r@email.com', 5, 'Best camera phone I have ever owned. Low light photos are amazing and video quality is cinema-like.'),
(19, 'Daniel Kim', 'daniel.k@email.com', 4, 'Great performance and beautiful display. The lack of macOS limits some professional workflows.'),
(25, 'Sophie Turner', 'sophie.t@email.com', 5, 'Perfect for calls and music. The fit is secure and the sound quality is crystal clear.');
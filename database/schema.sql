-- TechGo Gadget Comparison Database Schema
-- MySQL 8.0+ Compatible

-- Create gadgets table
CREATE TABLE gadgets (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    category ENUM('mobiles', 'laptops', 'tablets', 'earphones', 'speakers') NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    rating DECIMAL(3,2) DEFAULT 0.00,
    review_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_brand (brand),
    INDEX idx_category (category),
    INDEX idx_price (price),
    INDEX idx_rating (rating)
);

-- Create gadget_specifications table
CREATE TABLE gadget_specifications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    gadget_id BIGINT NOT NULL,
    spec_name VARCHAR(100) NOT NULL,
    spec_value TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (gadget_id) REFERENCES gadgets(id) ON DELETE CASCADE,
    INDEX idx_gadget_id (gadget_id)
);

-- Create reviews table
CREATE TABLE reviews (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    gadget_id BIGINT NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (gadget_id) REFERENCES gadgets(id) ON DELETE CASCADE,
    INDEX idx_gadget_id (gadget_id),
    INDEX idx_rating (rating)
);

-- Create comparison_lists table
CREATE TABLE comparison_lists (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP DEFAULT (CURRENT_TIMESTAMP + INTERVAL 7 DAY)
);

-- Create comparison_items table
CREATE TABLE comparison_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    comparison_id VARCHAR(36) NOT NULL,
    gadget_id BIGINT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (comparison_id) REFERENCES comparison_lists(id) ON DELETE CASCADE,
    FOREIGN KEY (gadget_id) REFERENCES gadgets(id) ON DELETE CASCADE,
    UNIQUE KEY unique_comparison_gadget (comparison_id, gadget_id)
);

-- Add sample categories and brands for reference (optional lookup tables)
CREATE TABLE brands (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert some popular brands for each category
INSERT INTO brands (name, category) VALUES
('Apple', 'mobiles'),
('Samsung', 'mobiles'),
('Google', 'mobiles'),
('OnePlus', 'mobiles'),
('Xiaomi', 'mobiles'),
('Dell', 'laptops'),
('HP', 'laptops'),
('Lenovo', 'laptops'),
('Apple', 'laptops'),
('ASUS', 'laptops'),
('Apple', 'tablets'),
('Samsung', 'tablets'),
('Microsoft', 'tablets'),
('Amazon', 'tablets'),
('Lenovo', 'tablets'),
('Apple', 'earphones'),
('Sony', 'earphones'),
('Bose', 'earphones'),
('JBL', 'earphones'),
('Samsung', 'earphones'),
('JBL', 'speakers'),
('Bose', 'speakers'),
('Sony', 'speakers'),
('Sonos', 'speakers'),
('UE', 'speakers');
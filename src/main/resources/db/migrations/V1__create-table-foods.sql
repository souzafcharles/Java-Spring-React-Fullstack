-- Drop the table if it exists
DROP TABLE IF EXISTS tb_foods;

-- Create the table
CREATE TABLE tb_foods (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    imgUri TEXT NOT NULL
);

-- Insert data into the table
INSERT INTO tb_foods (title, price, imgUri) VALUES
('Pizza Margherita', 29.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/pizza.jpg'),
('Hambúrguer Clássico', 19.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/burger.jpg'),
('Suco Natural de Laranja', 9.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/orange-juice.jpg'),
('Macarronada à Bolonhesa', 24.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/spaghetti.jpg'),
('Batata Frita Crocante', 14.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/fries.jpg'),
('Sorvete de Chocolate', 12.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/ice-cream.jpg'),
('Sushi Variado', 34.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/sushi.jpg'),
('Sanduíche Natural', 16.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/sandwich.jpg'),
('Pizza Calabresa', 32.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/calabresa-pizza.jpg'),
('Hambúrguer Vegetariano', 22.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/veggie-burger.jpg'),
('Suco de Morango', 10.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/strawberry-juice.jpg'),
('Lasanha à Bolonhesa', 27.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/lasagna.jpg'),
('Sorvete de Morango', 13.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/strawberry-ice-cream.jpg'),
('Temaki de Salmão', 29.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/temaki.jpg'),
('Sanduíche de Frango', 18.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/static/img/chicken-sandwich.jpg');

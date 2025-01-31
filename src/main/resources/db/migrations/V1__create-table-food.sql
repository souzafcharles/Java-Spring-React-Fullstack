CREATE TABLE foods (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    imgUri TEXT NOT NULL
);

INSERT INTO foods (title, price, imgUri) VALUES
('Pizza Margherita', 29.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/pizza.jpg'),
('Hambúrguer Clássico', 19.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/burger.jpg'),
('Suco Natural de Laranja', 9.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/orange-juice.jpg'),
('Macarronada à Bolonhesa', 24.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/spaghetti.jpg'),
('Batata Frita Crocante', 14.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/fries.jpg'),
('Sorvete de Chocolate', 12.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/ice-cream.jpg'),
('Sushi Variado', 34.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/sushi.jpg'),
('Sanduíche Natural', 16.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/sandwich.jpg'),
('Pizza Calabresa', 32.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/calabresa-pizza.jpg'),
('Hambúrguer Vegetariano', 22.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/veggie-burger.jpg'),
('Suco de Morango', 10.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/strawberry-juice.jpg'),
('Lasanha à Bolonhesa', 27.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/lasagna.jpg'),
('Sorvete de Morango', 13.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/strawberry-ice-cream.jpg'),
('Temaki de Salmão', 29.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/temaki.jpg'),
('Sanduíche de Frango', 18.99, 'https://github.com/souzafcharles/Java-Spring-React-Fullstack/raw/main/src/main/resources/img/chicken-sandwich.jpg');
-- Drop the table if it exists
DROP TABLE IF EXISTS tb_foods;

-- Create the table
CREATE TABLE tb_foods (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    image TEXT NOT NULL
);

-- Insert data into the table
INSERT INTO tb_foods (title, price, image) VALUES
('Batata Rústica', 24.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/rustic-fries.webp'),
('Coxinha de Carne Seca', 9.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/beef-coxinha.webp'),
('Esfirra de Frango', 23.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/chicken-esfiha.webp'),
('Feijoada', 34.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/feijoada.webp'),
('Hambúrguer', 29.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/burger.webp'),
('Hambúrguer Vegetariano', 24.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/veggie-burger.webp'),
('Lasanha à Bolonhesa', 45.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/lasagna.webp'),
('Macarronada à Bolonhesa', 39.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/spaghetti.webp'),
('Omelete', 14.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/omelette.webp'),
('Pizza de Calabresa', 49.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/pizza.webp'),
('Prato Feito', 29.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/set-meal.webp'),
('Sanduíche Natural', 25.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/sandwich.webp'),
('Sorvete de Chocolate', 19.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/ice-cream.webp'),
('Suco de Morango', 14.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/strawberry-juice.webp'),
('Sushi', 59.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/sushi.webp');

-- Insert additional data into the table
INSERT INTO tb_foods (title, price, image) VALUES
('Tapioca de Carne Seca', 12.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/tapioca.webp'),
('Temaki de Salmão', 34.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/temaki.webp'),
('Strogonoff de Frango', 29.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/stroganoff.webp'),
('Batata Frita Palito', 19.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/fries.webp'),
('Vinho Tinto', 49.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/wine.webp'),
('Água sem Gás', 4.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/bottle-water.webp'),
('Pudim', 14.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/pudding.webp'),
('Suco de Laranja', 9.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/orange-juice.webp'),
('Sorvete de Morango', 17.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/strawberry-ice-cream.webp'),
('Sanduíche de Frango', 21.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/chicken-sandwich.webp'),
('Pizza Margherita', 44.99, 'https://raw.githubusercontent.com/souzafcharles/Java-Spring-React-Fullstack/refs/heads/main/Backend/digitalMenu/src/main/resources/static/img/margherita-pizza.webp');
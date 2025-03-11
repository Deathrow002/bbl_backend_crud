-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    username VARCHAR(255),
    email VARCHAR(255),
    street VARCHAR(255),
    suite VARCHAR(255),
    city VARCHAR(255),
    zipcode VARCHAR(20),
    lat DOUBLE PRECISION,
    lng DOUBLE PRECISION,
    phone VARCHAR(50),
    website VARCHAR(255),
    company_name VARCHAR(255),
    company_catchphrase VARCHAR(255),
    company_bs VARCHAR(255)
);

-- Insert data into the users table (no need to provide 'id' as it's auto-incrementing)
INSERT INTO users (name, username, email, phone, website, street, suite, city, zipcode, lat, lng, company_name, company_catchphrase, company_bs)
VALUES
('Leanne Graham', 'Bret', 'Sincere@april.biz', '1-770-736-8031 x56442', 'hildegard.org', 'Kulas Light', 'Apt. 556', 'Gwenborough', '92998-3874', -37.3159, 81.1496, 'Romaguera-Crona', 'Multi-layered client-server neural-net', 'harness real-time e-markets'),
('Ervin Howell', 'Antonette', 'Shanna@melissa.tv', '010-692-6593 x09125', 'anastasia.net', 'Victor Plains', 'Suite 879', 'Wisokyburgh', '90566-7771', -43.9509, -34.4618, 'Deckow-Crist', 'Proactive didactic contingency', 'synergize scalable supply-chains'),
('Clementine Bauch', 'Samantha', 'Nathan@yesenia.net', '1-463-123-4447', 'ramiro.info', 'Douglas Extension', 'Suite 847', 'McKenziehaven', '59590-4157', -68.6102, -47.0653, 'Romaguera-Jacobson', 'Face to face bifurcated interface', 'e-enable strategic applications'),
('Patricia Lebsack', 'Karianne', 'Julianne.OConner@kory.org', '493-170-9623 x156', 'kale.biz', 'Hoeger Mall', 'Apt. 692', 'South Elvis', '53919-4257', 29.4572, -164.2990, 'Robel-Corkery', 'Multi-tiered zero tolerance productivity', 'transition cutting-edge web services'),
('Chelsey Dietrich', 'Kamren', 'Lucio_Hettinger@annie.ca', '(254)954-1289', 'demarco.info', 'Skiles Walks', 'Suite 351', 'Roscoeview', '33263', -31.8129, 62.5342, 'Keebler LLC', 'User-centric fault-tolerant solution', 'revolutionize end-to-end systems'),
('Mrs. Dennis Schulist', 'Leopoldo_Corkery', 'Karley_Dach@jasper.info', '1-477-935-8478 x6430', 'ola.org', 'Norberto Crossing', 'Apt. 950', 'South Christy', '23505-1337', -71.4197, 71.7478, 'Considine-Lockman', 'Synchronised bottom-line interface', 'e-enable innovative applications'),
('Kurtis Weissnat', 'Elwyn.Skiles', 'Telly.Hoeger@billy.biz', '210.067.6132', 'elvis.io', 'Rex Trail', 'Suite 280', 'Howemouth', '58804-1099', 24.8918, 21.8984, 'Johns Group', 'Configurable multimedia task-force', 'generate enterprise e-tailers'),
('Nicholas Runolfsdottir V', 'Maxime_Nienow', 'Sherwood@rosamond.me', '586.493.6943 x140', 'jacynthe.com', 'Ellsworth Summit', 'Suite 729', 'Aliyaview', '45169', -14.3990, -120.7677, 'Abernathy Group', 'Implemented secondary concept', 'e-enable extensible e-tailers'),
('Glenna Reichert', 'Delphine', 'Chaim_McDermott@dana.io', '(775)976-6794 x41206', 'conrad.com', 'Dayna Park', 'Suite 449', 'Bartholomebury', '76495-3109', 24.6463, -168.8889, 'Yost and Sons', 'Switchable contextually-based project', 'aggregate real-time technologies'),
('Clementina DuBuque', 'Moriah.Stanton', 'Rey.Padberg@karina.biz', '024-648-3804', 'ambrose.net', 'Kattie Turnpike', 'Suite 198', 'Lebsackbury', '31428-2261', -38.2386, 57.2232, 'Hoeger LLC', 'Centralized empowering task-force', 'target end-to-end models');

CREATE TABLE news (
    id SERIAL UNIQUE PRIMARY KEY,
    header VARCHAR(150) NOT NULL,
    image_filename VARCHAR(15),
    text VARCHAR(5000) NOT NULL,
    creation_date DATE NOT NULL
);
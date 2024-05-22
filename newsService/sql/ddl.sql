CREATE TABLE news (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               title VARCHAR(255) NOT NULL,
                               image BLOB,
                               date TIMESTAMP,
                               content VARCHAR(255) NOT NULL
);
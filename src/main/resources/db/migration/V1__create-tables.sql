CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    document VARCHAR(20) NOT NULL UNIQUE,
    user_type VARCHAR(20) NOT NULL,
    balance DECIMAL(19, 2) NOT NULL
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    id_payer BIGINT NOT NULL,
    id_payee BIGINT NOT NULL,
    CONSTRAINT fk_payer FOREIGN KEY (id_payer) REFERENCES users(id),
    CONSTRAINT fk_payee FOREIGN KEY (id_payee) REFERENCES users(id)
);

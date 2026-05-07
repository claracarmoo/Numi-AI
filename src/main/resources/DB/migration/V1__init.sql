CREATE TABLE users (
    id            CHAR(36)     NOT NULL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transactions (
    id          CHAR(36)       NOT NULL PRIMARY KEY,
    user_id     CHAR(36)       NOT NULL,
    description VARCHAR(255)   NOT NULL,
    amount      DECIMAL(15, 2) NOT NULL,
    type        VARCHAR(20)    NOT NULL,   -- INCOME | EXPENSE
    category    VARCHAR(30)    NOT NULL,   -- FOOD | TRANSPORT | etc
    date        DATE           NOT NULL,
    created_at  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transactions_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE financial_profiles (
    user_id               CHAR(36)       NOT NULL PRIMARY KEY,
    monthly_income_goal   DECIMAL(15, 2),
    monthly_savings_goal  DECIMAL(15, 2),

    CONSTRAINT fk_financial_profile_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX idx_transactions_user_id ON transactions (user_id);
CREATE INDEX idx_transactions_date    ON transactions (date);
CREATE INDEX idx_transactions_type    ON transactions (type);
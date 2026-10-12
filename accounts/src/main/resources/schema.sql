CREATE TABLE  if not exists customer (
                          customer_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name          VARCHAR(100) NOT NULL,
                          email         VARCHAR(150) UNIQUE NOT NULL,
                          mobile_number VARCHAR(15) UNIQUE NOT NULL,
                          created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          created_by    VARCHAR(50),
                          updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          updated_by    VARCHAR(50)
);

CREATE TABLE if not exists accounts (
                          account_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
                          customer_id    BIGINT NOT NULL,
                          account_number VARCHAR(30) UNIQUE NOT NULL,
                          account_type   VARCHAR(20) NOT NULL,
                          branch_address VARCHAR(200),
                          created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          created_by     VARCHAR(50),
                          updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          updated_by     VARCHAR(50),
                          CONSTRAINT fk_customer
                              FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);


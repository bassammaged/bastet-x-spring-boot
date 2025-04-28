-- Departments Table
CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Teams Table
CREATE TABLE teams (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Members Table
CREATE TABLE members (
    id BINARY(16) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    team_id BINARY(16),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);
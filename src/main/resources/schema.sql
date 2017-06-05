CREATE TABLE mutable_entity (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nic VARCHAR,
  dependency_id INT
);
CREATE TABLE immutable_entity (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nic VARCHAR,
  dependency_id INT
);
CREATE TABLE dependency (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nic VARCHAR
);
CREATE TABLE dependency_snapshot (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nic VARCHAR,
  dependency_id INT
);
CREATE TABLE something (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nic VARCHAR,
  mutable_entity_id INT,
  immutable_entity_id INT
);
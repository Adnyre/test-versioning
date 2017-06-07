CREATE TABLE dependency (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nic VARCHAR
);
CREATE TABLE dependency_snapshot (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nic VARCHAR,
  dependency_id INT,
  FOREIGN KEY (dependency_id) REFERENCES dependency (id)
);
CREATE TABLE mutable_entity (
  id INT PRIMARY KEY,
  nic VARCHAR,
  dependency_id INT,
  FOREIGN KEY (dependency_id) REFERENCES dependency (id)
);
CREATE TABLE immutable_entity (
  id INT PRIMARY KEY,
  nic VARCHAR,
  dependency_id INT,
  FOREIGN KEY (dependency_id) REFERENCES dependency_snapshot (id)
);
CREATE SEQUENCE entity_seq;
CREATE TABLE something (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nic VARCHAR,
  mutable_entity_id INT,
  immutable_entity_id INT,
  FOREIGN KEY (mutable_entity_id) REFERENCES mutable_entity (id),
  FOREIGN KEY (immutable_entity_id) REFERENCES immutable_entity (id)
);
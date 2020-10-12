

CREATE TABLE tour(
  code CHAR(2) NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

insert into tour(code, name) values
('BC', 'Backpack Cal'),
('CC', 'California Calm'),
('CH', 'California Hot springs'),
('CY', 'Cycle California'),
('DS', 'From Desert to Sea'),
('KC', 'Kids California'),
('NW', 'Nature Watch'),
('SC', 'Snowboard Cali'),
('TC', 'Taste of California');

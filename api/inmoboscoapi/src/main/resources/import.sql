INSERT INTO Type (id,type) values (1,'Casa');

INSERT INTO Type (id,type) values (2,'Apartamento');

INSERT INTO Type (id,type) values (3,'Piso');

INSERT INTO City (id, name) VALUES(1, 'A Coruna'),(2, 'Alava'),(3, 'Albacete'),(4, 'Alicante'),(5, 'Almeria'),(6, 'Asturias'),(7, 'Avila'),(8, 'Badajoz'),(9, 'Baleares'),(10, 'Barcelona'),(11, 'Bizkaia'),(12, 'Burgos'),(13, 'Caceres'),(14, 'Cadiz'),(15, 'Cantabria'),(16, 'Castellon'),(17, 'Ceuta'),(18, 'Ciudad Real'),(19, 'Cordoba'),(20, 'Cuenca'),(21, 'Guipuzkoa'),(22, 'Girona'),(23, 'Granada'),(24, 'Guadalajara'),(25, 'Huelva'),(26, 'Huesca'),(27, 'Jaen'),(28, 'La Rioja'),(29, 'Las Palmas'),(30, 'Leon'),(31, 'Lugo'),(32, 'Lleida'),(33, 'Madrid'),(34, 'Malaga'),(35, 'Melilla'),(36, 'Murcia'),(37, 'Navarra'),(38, 'Ourense'),(39, 'Palencia'),(40, 'Pontevedra'),(41, 'Salamanca'),(42, 'Segovia'),(43, 'Sevilla'),(44, 'Soria'),(45, 'Tarragona'),(46, 'Tenerife'),(47, 'Teruel'),(48, 'Toledo'),(49, 'Valencia'),(50, 'Valladolid'),(51, 'Zamora'),(52, 'Zaragoza');

INSERT INTO property (id,lat, lon, name, title, price, m2, description, total_bed_rooms, total_baths, type_id, city_id,total_meet_booking,total_visits) VALUES (1,'40.4167754', '-3.7037902', 'Apartamento en el centro', 'Hermoso apartamento cerca de todos los servicios', 1500.0, 80.0, 'Este apartamento luminoso y espacioso se encuentra en el corazón de la ciudad.', 2, 1, 2,1,0,50);

-- Sentencia 2
INSERT INTO property (id,lat, lon, name, title, price, m2, description, total_bed_rooms, total_baths, type_id, city_id,total_meet_booking,total_visits)VALUES (2,'41.3850639', '2.1734035', 'Casa de lujo en la playa', 'Espectacular casa con vistas al mar', 2500.0, 150.0, 'Esta impresionante casa ofrece vistas panorámicas al mar y todas las comodidades necesarias.', 4, 3, 1 ,23,0,500);

-- Sentencia 3
INSERT INTO property (id,lat, lon, name, title, price, m2, description, total_bed_rooms, total_baths, type_id, city_id,total_meet_booking,total_visits) VALUES (3,'39.4699019', '-0.3773957', 'Piso moderno en el barrio histórico', 'Acogedor piso cerca de lugares históricos', 1200.0, 100.0, 'Este piso moderno y elegante se encuentra en el encantador barrio histórico de la ciudad.', 3, 2, 3, 3,0,100);

-- Sentencia 4
INSERT INTO property (id,lat, lon, name, title, price, m2, description, total_bed_rooms, total_baths, type_id, city_id,total_meet_booking,total_visits) VALUES (4,'37.3890924', '-5.9844589', 'Casa con jardín amplio', 'Amplia casa, ideal para familias', 3000.0, 200.0, 'Esta espaciosa casa cuenta con un amplio jardín y es perfecta para familias.', 4, 3,1,4,0,10);

-- Sentencia 5
INSERT INTO property (id,lat, lon, name, title, price, m2, description, total_bed_rooms, total_baths, type_id, city_id,total_meet_booking,total_visits) VALUES (5,'43.2630126', '-2.9349852', 'Apartamento en el casco antiguo', 'Encantador apartamento en el corazón de la ciudad', 1800.0, 90.0, 'Este encantador apartamento se encuentra en el pintoresco casco antiguo, cerca de todos los lugares de interés.', 2, 1, 2, 5 ,10,6);
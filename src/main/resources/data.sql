INSERT INTO roles("name") VALUES ('Admin'),('Guide'),('User');

INSERT INTO users("name",email,password,phone,avatar_image,active,role_id) 
VALUES ('user1','user1@gmail.com','123456','7778523610','user1.jpg',true,1),
('user2','user2@gmail.com','123456','7778523523','user2.jpg',true,2),
('user3','user3@gmail.com','123456','7778523672','user3.jpg',true,3);

INSERT INTO guides(description,number_of_ratings,rating_average,user_id) 
VALUES ('Good for tour guide knowledge',250,4.5,2);

INSERT INTO tour_types(name)
VALUES ('Relaxing'),('Design Beforehand'),('Volunteering'),('Sightseeing'),('Culture Tourism'),('Discover Food');

SET timezone = 'Asia/Saigon';

INSERT INTO tours(name,start_date,end_date,duration,max_group_size,price,price_discount,summary,description,tour_type_id,active,tour_image_cover,rating_average,number_of_ratings,guide_id)
VALUES('Mia Nha Trang Resort','2020/07/15','2020/07/18',3,12,100,90,'Mia means ‘sugarcane’ and stays at Mia Resort sure are sweet, thanks to the private beach','The accommodation comprises 70 rooms. Work-out with fitness classes and a gym area and use a business center and a meeting room for business needs.',1,true,'NhaTrangRelaxing.jpg',5,98,1),
('Hoi An','2020-07-15 19:00:00-07','2020-07-20 19:00:00-07',5,6,120,108,'Hoi An tours are ideal for travellers looking to experience the local culture and history of the UNESCO-listed coastal town','Wander around the holy place of the Champa Kingdom,Marvel at the greatness of the holy land',5,true,'HoiAnCulture.jpg',5,85,1),
('Da Lat Tour','2020-07-18 19:00:00-07','2020-07-20 19:00:00-07',2,20,50,45,'Dalat City Tour is the tour program that you will travel inside Dalat city with the famous tourist attractions and cheapest price','Discover a city originally set up as a holiday destination, interspersed with French villas and bungalows.',4,true,'DalatSightSeeing.jpg',5,745,1),
('Vung Tau','2020-07-16 19:00:00-07','2020-07-18 19:00:00-07',2,10,20,18,'A popular weekend escape from HCMC, Vung Tau rocks at weekends when beach-starved locals and expats descend in numbers','Visit Light House - one of the attractions in Vung Tau to discover. Lying on a small Tao Phung Mountain - one of the most ancient lighthouse in Southeast Asia',1,true,'VungTauRelaxing.jpg',4,586,1),
('Phu Quoc','2020-07-14 19:00:00-07','2020-07-21 19:00:00-07',7,25,140,106,'Phu Quoc Island boasts idyllic beaches, romantic sunsets, evergreen forests, and a serene atmosphere, making it an ideal holiday destination','Aside from beachside activities, visitors can also explore traditional villages, expansive nature parks and Buddhist pagodas',6,true,'PhuQuocFoodDicovery.jpg',5,789,1),
('Sa Pa','2020-07-18 19:00:00-07','2020-07-21 19:00:00-07',3,20,150,135,'Visit the beautiful Sapa - a famous market town in Northwest Vietnam','If you were expecting a quaint alpine town, recalibrate your expectations. Modern tourism development has mushroomed haphazardly.',2,true,'SaPaDesignBeforeHand.jpg',4,985,1),
('Hue','2020-07-20 19:00:00-07','2020-07-25 19:00:00-07',5,30,40,36,'','',5,true,'HueCulture.jpg',4,125,1);
INSERT INTO tour_images(tour_id,image_url)
VALUES (1,'NhaTrangRelaxing.jpg'),(1,'NhaTrang01.jpg'),(1,'NhaTrang02.jpg'),(1,'NhaTrang03.jpg'),(1,'NhaTrang04.jpg'),
       (2,'HoiAnCulture'),(2,'HoiAn01.jpg'),(2,'HoiAn02.jpg'),(2,'HoiAn03.jpg'),(2,'HoiAn04.jpg'),
       (3,'DaLatSightSeeing.jpg'),(3,'DaLat01.jpg'),(3,'DaLat02.jpg'),(3,'DaLat03.jpg'),(3,'DaLat04.jpg'),
       (4,'VungTauRelaxing.jpg'),(4,'VungTau01.jpg'),(4,'VungTau02.jpg'),(4,'VungTau03.jpg'),(4,'VungTau04.jpg'),
       (5,'PhuQuocFoodDicovery.jpg'),(5,'PhuQuoc01.jpg'),(5,'PhuQuoc02.jpg'),(5,'PhuQuoc03.jpg'),(5,'PhuQuoc04.jpg'),
       (6,'SaPaDesignBeforeHand.jpg'),(6,'Sapa01.jpg'),(6,'Sapa02.jpg'),(6,'Sapa03.jpg'),(6,'Sapa04.jpg'),
       (7,'HueCulture.jpg'),(7,'Hue01.jpg'),(7,'Hue02.jpg'),(7,'Hue03.jpg'),(7,'Hue04.jpg');

INSERT INTO locations("name",address,latitude,longitude)
VALUES ('Nha Trang Resort','Nha Trang',12.24507,109.19432),
('Hoi An city','Hoi An',15.87944,108.355),
('Da Lat','Da Lat',11.94646,108.44193),
('Vung Tau Beach','Vung Tau',10.34599,107.08426),
('Phu Quoc VinPearl','Phu Quoc',10.1987,103.95813),
('SaPa','SaPa',22.34023,103.84415),
('Hue','Hue',16.46667,107.6),
('Phan Thiet','Phan Thiet',10.93333,108.1);

INSERT INTO tour_locations("date",location_id,tour_id) 
VALUES('2020-07-15 19:00:00-07',2,2),
('2020-07-18 19:00:00-07',3,3),
('2020-07-16 19:00:00-07',4,4);
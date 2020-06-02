INSERT INTO roles(name) VALUES('admin'), ('user'), ('guide');
INSERT INTO users(email, password, phone, name, role_id)
VALUES ('user1@gmail.com', '123', '0771123456', 'User1', 2),
              ('user2@gmail.com', '123', '0772233489', 'User2', 2),
              ('user3@gmail.com', '123', '0773346745', 'User3', 2),
('user4@gmail.com', '123', '0778855236', 'User4', 3);
INSERT INTO guides(user_id,description,number_of_ratings,rating_average) VALUES (4,'Good about tour guide',250,4.5);
INSERT INTO locations("name",latitude,longitude,address) 
VALUES ('Ha Long Bay',20.9088218,107.1795853,'Ha Long City, Quang Ninh, VN'),
('Hai Van pass',16.2000202,108.1245782,'Lang Co town, Phu Loc, Thua Thien Hue, VN'),
('My Son Sanctuary',15.7641085,108.1220657,'Duy Phu, Duy Xuyen, Quang Nam, VN');
INSERT INTO tour_types("name") VALUES ('Short');
INSERT INTO tours("name",start_date,end_date,duration,max_group_size,price,price_discount,summary,description,tour_type_id,
active,tour_image_cover,rating_average,number_of_ratings,guide_id) 
VALUES('Ha Long Tour','2020/05/02','2020/05/05',3,25,750,50,'Short tour to Ha Long Bay','You will be enjoyed Ha Long Bay in 3 days with beautiful landsccape in here',
1,true,'halong.jpg',0,0,1);
INSERT INTO tour_locations(tour_id,location_id,"date") VALUES (1,1,'12/5/2020');

INSERT INTO tour_images(tour_id,image_url) VALUES(1,'a.jpg');

               

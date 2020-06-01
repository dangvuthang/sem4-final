INSERT INTO roles(name) VALUES('admin'), ('user'), ('guide');
INSERT INTO users(email, password, phone, name, role_id)
VALUES ('user1@gmail.com', '123', '0771123456', 'User1', 2),
              ('user2@gmail.com', '123', '0772233489', 'User2', 2),
              ('user3@gmail.com', '123', '0773346745', 'User3', 2)
INSERT INTO tour_types(name)
VALUES ('Relaxing'),('Design Beforehand'),('Volunteering'),('Sightseeing'),('Culture Tourism'),('Discover Food')

INSERT INTO tours(name,start_date,end_date,duration,max_group_size,price,price_discount,summary,description,tour_type_id,active,tour_image_cover,rating_average,number_of_ratings,guide_id)
VALUES('Mia Nha Trang Resort',15/07/2020,18/07/2020,3,12,100,90,'Mia means ‘sugarcane’ and stays at Mia Resort sure are sweet, thanks to the private beach','The accommodation comprises 70 rooms. Work-out with fitness classes and a gym area and use a video projector, a business center and a meeting room for business needs.',1,1,'NhaTrangRelaxing.jpg',5,98,1),
('Hoi An',15/07/2020,20/07/2020,5,6,120,108,'Hoi An tours are ideal for travellers looking to experience the local culture and history of the UNESCO-listed coastal town','Wander around the holy place of the Champa Kingdom,Marvel at the greatness of the holy land and it’s structure,Get pleasure from a scrumptious lunch in Hoi An',5,1,'HoiAnCulture.jpg',5,85,1)

INSERT INTO tour_images(tour_id,image_url)
VALUES (1,'NhaTrangRelaxing.jpg'),(1,'NhaTrang01.jpg'),(1,'NhaTrang02.jpg'),(1,'NhaTrang03.jpg'),(1,'NhaTrang04.jpg'),
(2,'HoiAnCulture'),(2,'HoiAn01.jpg'),(2,'HoiAn02.jpg'),(2,'HoiAn03.jpg'),(2,'HoiAn04.jpg')

INSERT INTO locations(name,latitude,longitude,is_active)
VALUES ('Nha Trang Resort',12.24507,109.19432,1)
('Hoi An city',15.87944,108.355,1)
               
               

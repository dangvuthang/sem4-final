<<<<<<< HEAD
INSERT INTO roles(name) VALUES('admin'), ('user'), ('guide');
INSERT INTO users(email, password, phone, name, role_id)
VALUES ('user1@gmail.com', '123', '0771123456', 'User1', 2),
              ('user2@gmail.com', '123', '0772233489', 'User2', 2),
              ('user3@gmail.com', '123', '0773346745', 'User3', 2);
=======
>>>>>>> dba9667e4325c92f75da89e0c2eae1685f93d6ad
INSERT INTO tour_types(name)
VALUES ('Relaxing'),('Design Beforehand'),('Volunteering'),('Sightseeing'),('Culture Tourism'),('Discover Food');

SET timezone = 'Asia/Saigon';

INSERT INTO tours(name,start_date,end_date,duration,max_group_size,price,price_discount,summary,description,tour_type_id,active,tour_image_cover,rating_average,number_of_ratings,guide_id)
<<<<<<< HEAD
VALUES('Mia Nha Trang Resort','2020/07/15','2020/07/18',3,12,100,90,'Mia means ‘sugarcane’ and stays at Mia Resort sure are sweet, thanks to the private beach','The accommodation comprises 70 rooms. Work-out with fitness classes and a gym area and use a video projector, a business center and a meeting room for business needs.',1,true,'NhaTrangRelaxing.jpg',5,98,1),
('Hoi An','2020/07/15','2020/07/20',5,6,120,108,'Hoi An tours are ideal for travellers looking to experience the local culture and history of the UNESCO-listed coastal town','Wander around the holy place of the Champa Kingdom,Marvel at the greatness of the holy land and it’s structure,Get pleasure from a scrumptious lunch in Hoi An',5,false,'HoiAnCulture.jpg',5,85,1);

INSERT INTO tour_images(tour_id,image_url)
VALUES (1,'NhaTrangRelaxing.jpg'),(1,'NhaTrang01.jpg'),(1,'NhaTrang02.jpg'),(1,'NhaTrang03.jpg'),(1,'NhaTrang04.jpg'),
(2,'HoiAnCulture'),(2,'HoiAn01.jpg'),(2,'HoiAn02.jpg'),(2,'HoiAn03.jpg'),(2,'HoiAn04.jpg');

INSERT INTO locations(name,latitude,longitude)
VALUES ('Nha Trang Resort',12.24507,109.19432),
('Hoi An city',15.87944,108.355);
=======
VALUES('Mia Nha Trang Resort','15/07/2020','18/07/2020',3,12,100,90,'Mia means ‘sugarcane’ and stays at Mia Resort sure are sweet, thanks to the private beach','The accommodation comprises 70 rooms. Work-out with fitness classes and a gym area and use a video projector, a business center and a meeting room for business needs.',1,1,'NhaTrangRelaxing.jpg',5,98,1),
('Hoi An','2020-07-15 19:00:00-07','2020-07-20 19:00:00-07',5,6,120,108,'Hoi An tours are ideal for travellers looking to experience the local culture and history of the UNESCO-listed coastal town','Wander around the holy place of the Champa Kingdom,Marvel at the greatness of the holy land and it’s structure,Get pleasure from a scrumptious lunch in Hoi An',5,1,'HoiAnCulture.jpg',5,85,1),
('Da Lat Tour','2020-07-18 19:00:00-07','2020-07-20 19:00:00-07',2,20,50,45,'Dalat City Tour is the tour program that you will travel inside Dalat city with the famous tourist attractions and cheapest price','Discover a city originally set up as a holiday destination, interspersed with French villas and bungalows. Enjoy the beautiful scenery in the cool, windy city and discover the native culture throughout your trip. Visit: Robin Hill & Dalat Cable Car Station, Truc Lam Zen Monastery, Tuyen Lam Lake, Datanla Waterfall, Bao Dai King’s Summer Palace, Dalat Flower Garden, A giant golden statue of Sakyamuni Buddha, The Cock Church, Free to enjoy the artichoke tea, jams',4,1,'DalatSightSeeing.jpg',5,745,3),
('Vung Tau','2020-07-16 19:00:00-07','2020-07-18 19:00:00-07',2,10,20,18,'A popular weekend escape from HCMC, Vung Tau rocks at weekends when beach-starved locals and expats descend in numbers, but it is relatively quiet during the week','t discover this beach city in South Vietnam. Visit Light House - one of the attractions in Vung Tau to discover. Lying on a small Tao Phung Mountain - one of the most ancient lighthouse in Southeast Asia. From the lighthouse you can catch the whole panorama of Vung Tau city.Relaxing at Thuy Van Beach ( Back Beach) is considered as one of the biggest and the most beautiful beach in Vietnam',1,1,'VungTauRelaxing.jpg',4,586,5),
('Phu Quoc','2020-07-14 19:00:00-07','2020-07-21 19:00:00-07',7,25,140,106,'Phu Quoc Island boasts idyllic beaches, romantic sunsets, evergreen forests, and a serene atmosphere, making it an ideal holiday destination for travellers looking to escape bustling city life','Aside from beachside activities, visitors can also explore traditional villages, expansive nature parks and Buddhist pagodas, all of which are easily accessible via motorcycle, taxi, bus or even daytrips by reputable companies. Catering to just about any budget level and preference, Phu Quoc’s dining scene ranges from local markets selling fresh seafood and Vietnamese street food to expat-owned bistros offering authentic western and European fare',6,1,'PhuQuocFoodDicovery.jpg',5,789,5),
('Sa Pa','2020-07-18 19:00:00-07','2020-07-21 19:00:00-07',3,20,150,135,'Visit the beautiful Sapa - a famous market town in Northwest Vietnam, where colorful ethnic groups such as the Red Dzao,HMong, Dzay live in Cat Cat, Lao Chai and Ta Van Village, Ham Rong Moutain. Learn about their hill tribe cultures and see the life of locals','If you were expecting a quaint alpine town, recalibrate your expectations. Modern tourism development has mushroomed haphazardly. Thanks to rarely enforced building-height restrictions, Sapa s skyline is continually thrusting upwards.But you are not here to hang out in town. This is northern Vietnam s premier trekking base, from where hikers launch themselves into a surrounding countryside of cascading rice terraces and tiny hill-tribe villages that seem a world apart. Once you have stepped out into the lush fields, you will understand the Sapa area is real charm.',2,1,'SaPaDesignBeforeHand.jpg',4,985,6),
('Hue','2020-07-20 19:00:00-07','2020-07-25 19:00:00-07',5,30,40,36,'Hue tours and excursions prove that there are plenty of things to see and do in this ancient city. As the capital of Vietnam during the Nguyen Dynasty, Hue boasts magnificent citadels, pagodas, and royal tombs dating back to the 19th century.','see process of making handmade stuffs at Xuan Huong village.Listening stories about Vietnamese history in Royal Tombs.Admire the architectures Imperial Citadel & Thien Mu pagoda.Visit Dong Ba market, the signature market of Hue city.Cruising by dragon boat down Perfume River',5,1,'HueCulture.jpg',4,125,7),
('Phan Thiet','2020-08-03 19:00:00-07','2020-08-08 19:00:00-07',5,25,30,27,'The best attractions in Phan Thiet and the beach of Mui Ne comprise geographical wonders, traditional fishing villages, pristine beaches and verdant mountains, not forgetting ancient structures that date back as far as the 9th century. Needless to say, visitors have plenty to see and do during their holiday in the capital of Binh Thuan province','The Red Sand Dunes are one of Phan Thiet’s most unique natural formations, where you can take gorgeous snapshots and enjoy an array of activities. Located three kilometres north of Mui Ne, it features reddish-brown sand with gentle slopes that’s reminiscent of a miniature Middle Eastern desert. If you get to the highest peak, you’ll be greeted with a great sunset view of the ocean. Sand-sledding is a common (and fun) activity here, with plastic sleds available for about VND 10,000 to VND 20,000. Be aware you have to haggle to rent the boards as the local boys will try to charge extortionate fees to rent the thin sheet of plastic. Visitors can also rent quad bikes and dune buggies to explore Red Sand Dunes, but make sure you agree on the price beforehand','PhanThietRelaxing.jpg',5,698,8)

INSERT INTO tour_images(tour_id,image_url)
VALUES (1,'NhaTrangRelaxing.jpg'),(1,'NhaTrang01.jpg'),(1,'NhaTrang02.jpg'),(1,'NhaTrang03.jpg'),(1,'NhaTrang04.jpg'),
       (2,'HoiAnCulture.jpg'),(2,'HoiAn01.jpg'),(2,'HoiAn02.jpg'),(2,'HoiAn03.jpg'),(2,'HoiAn04.jpg'),
       (3,'DaLatSightSeeing.jpg'),(3,'DaLat01.jpg'),(3,'DaLat02.jpg'),(3,'DaLat03.jpg'),(3,'DaLat04.jpg'),
       (4,'VungTauRelaxing.jpg'),(4,'VungTau01.jpg'),(4,'VungTau02.jpg'),(4,'VungTau03.jpg'),(4,'VungTau04.jpg'),
       (5,'PhuQuocFoodDicovery.jpg'),(5,'PhuQuoc01.jpg'),(5,'PhuQuoc02.jpg'),(5,'PhuQuoc03.jpg'),(5,'PhuQuoc04.jpg'),
       (6,'SaPaDesignBeforeHand.jpg'),(6,'Sapa01.jpg'),(6,'Sapa02.jpg'),(6,'Sapa03.jpg'),(6,'Sapa04.jpg'),
       (7,'HueCulture.jpg'),(7,'Hue01.jpg')(7,'Hue02.jpg'),(7,'Hue03.jpg'),(7,'Hue04.jpg'),
       (8,'PhanThietRelaxing.jpg'),(8,'PhanThiet01.jpg'),(8,'PhanThiet02.jpg'),(8,'PhanThiet03.jpg'),(8,'PhanThiet04.jpg'),

INSERT INTO locations(name,latitude,longitude,is_active)
VALUES ('Nha Trang Resort',12.24507,109.19432,1),
('Hoi An city',15.87944,108.355,1),
('Da Lat',11.94646,108.44193,1),
('Vung Tau Beach',10.34599,107.08426,1),
('Phu Quoc VinPearl',10.1987,103.95813,1),
('SaPa',22.34023,103.84415,1),
('Hue',16.46667,107.6,1),
('Phan Thiet',10.93333,108.1,1)
               
>>>>>>> dba9667e4325c92f75da89e0c2eae1685f93d6ad

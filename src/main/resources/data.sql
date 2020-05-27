INSERT INTO roles(name) VALUES('admin'), ('user'), ('guide');
INSERT INTO users(email, password, phone, name, role_id)
VALUES ('user1@gmail.com', '123', '0771123456', 'User1', 2),
              ('user2@gmail.com', '123', '0772233489', 'User2', 2),
              ('user3@gmail.com', '123', '0773346745', 'User3', 2)
               

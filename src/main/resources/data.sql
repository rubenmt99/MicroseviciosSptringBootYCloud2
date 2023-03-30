INSERT into tbl_categories (id, name) VALUES (2, 'books');
INSERT into tbl_categories (id, name) VALUES (3, 'electronics');
INSERT into tbl_categories (id, name) VALUES (1, 'shoes');

INSERT into tbl_products(id, name, description,stock, price, status, created_at, category_id)
VALUES (1,'adidas CloudFoam Ultimate','Walk in the air in the black',25,90,'Created','2018-09-05',1);

INSERT into tbl_products(id, name, description,stock, price, status, created_at, category_id)
VALUES (2,'Reebok God','Reebok negras',15,60.99,'Created','2022-09-05',1);

INSERT into tbl_products(id, name, description,stock, price, status, created_at, category_id)
VALUES (3,'perico y su borrico','su mascota',5,8.50,'Created','2022-09-09',2);

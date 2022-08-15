# MINDSTORE API - SpringBoot


**SUMMARY**

E-commerce type backend Api created with the purpose of giving its users the ability to search, favorite, add to cart
and buy desired products, ranging from clothing to jewelry. Created with the purpose of being complemented with React at
the frontend level.


**REQUIREMENTS FOR SETUP**

To run the program, download 63 files and compile in IDE or using javac in terminal. 
Can be deployed in heroku or locally (by switching NOTapplication.yml to application.yml, and vice versa).

***
**IMPLEMENTATIONS**

- Model Relationships
- Spring Security (Authentication/Authorization)
- External API (powered by Fake Store API)
- Swagger
- Postman Collection
- Docker Compose
- Local Cache
- Heroku PostGres DB deployment
- Heroku Deployment
  <br/><br/>

**PACKAGES:**
- Commands: contains data transfer objects(DTO) used throughout the project;
- Config: used for initialing beans;
- Controllers: contains controllers for routes;
- Converters: for converting DTO into entities, and vice versa;
- Data loader: to set up database with predefined entities, and also consume from external API;
- Enums: to avoid redundancy and unidentified numbers;
- Exceptions: were exceptions from all over the code are handled;
- Helpers: contains some helper functions;
- Persistence: stores the entities and their respective repositories;
- Security: security (authentication and authorization) implemented with JWT token;
- Services: contains services for each entity;

***
**API ENDPOINTS:**
<br><br>

Path should be specific to localhost, or in case of heroku usage: https://mindshop-api.herokuapp.com

#### ADMIN:
- Get all products -> GET Path + api/v1/admins/products?{direction}&{field}&{page}&{pagesize}
- Get all products by price -> GET Path + api/v1/admins/products/price?{direction}&{page}&{pagesize}&{min}&{max}
- Get product by id -> GET Path + /api/v1/admins/products/{id}
- Get product by title -> GET Path + /api/v1/admins/products/title/{title}
- Get all users -> GET Path + api/v1/admins/users?{direction}&{field}&{page}&{pagesize}
- Get user by id -> GET Path + /api/v1/admins/users/{id}
- Get user by name -> GET Path + /api/v1/admins/users/name/{name}
- Login admin -> POST Path + /login + body in Json format (email and password)
- Sign up admin -> POST Path + /api/v1/admins + body in Json format
- Add product -> POST Path + /api/v1/admins/products + body in Json format
- Add user -> POST Path + /api/v1/admins/users + body in Json format
- Update admin -> PATCH Path + /api/v1/admins/{id} + body in Json format
- Update user -> PATCH Path + /api/v1/admins/users/{id} + body in Json format
- Update product -> PATCH Path + /api/v1/admins/products/{id} + body in Json format
- Delete product -> DELETE Path + /api/v1/admins/products/{id}
- Delete product by title -> DELETE Path + /api/v1/admins/products/title/{id}

#### USER:
- Get all products -> GET Path + api/v1/users/products?{direction}&{field}&{page}&{pagesize}
- Get all products by price -> GET Path + api/v1/users/products/price?{direction}&{page}&{pagesize}&{min}&{max}
- Get product by id -> GET Path + /api/v1/users/products/{id}
- Get product by title -> GET Path + /api/v1/users/products/title/{title}
- Get product by category -> GET Path + /api/v1/users/products/category?{category}&{page}&{pagesize}
- Get product by id -> GET Path + /api/v1/users/products/{id}
- Get category by id -> GET Path + /api/v1/users/categories/{id}
- Get shopping cart -> GET Path + /api/v1/users/shoppingcart/{userid}
- Get shopping cart total price -> GET Path + /api/v1/users/shoppingcart/price/{userid}
- Get own ratings -> GET Path + /api/v1/users/rating/{id}
- Login admin -> POST Path + /login + body in Json format (email and password)
- Sign up user -> POST Path + /api/v1/users + body in Json format
- Rate product -> POST Path + /api/v1/users/rating?{userid}&{productid}&{rating}
- Buy product -> POST Path + /api/v1/users/buy/{id}
- Update user -> PATCH Path + /api/v1/users/{id} + body in Json format
- Add products to cart -> PATCH Path + /api/v1/addtocart?{userid}&{productid}
- Remove products from cart -> PATCH Path + /api/v1/users/removefromcart?{userid}&{productid}
- Delete user -> DELETE Path + /api/v1/users/delete/{id}
- Delete product -> DELETE Path + /api/v1/users/rating?{userid}&{ratingid}

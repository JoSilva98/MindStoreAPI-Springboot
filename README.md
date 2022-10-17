# MindStore API - SpringBoot
***
### SUMMARY
***
MindStore API is an E-commerce created with the purpose of giving its
users the ability to search, favorite, add to the cart and buy desired
products, ranging from clothing to jewelry. Created with the purpose 
of being complemented with React at the frontend level. It also has 
got an admin role that can add, update and remove products.
<br/><br/>

### API LINKS
***
Path should be specific to localhost or to:
https://mindshop-api.herokuapp.com

Swagger documentation:
https://mindshop-api.herokuapp.com/swagger-ui/index.html
<br/><br/>

### EXTERNAL API
***
Our app is powered by Fake Store API.
<br/><br/>

### IMPLEMENTATIONS
***
- Model Relationships;
- Spring Security with JWT (Authentication/Authorization);
- External API;
- Swagger;
- Postman Collection;
- Docker Compose;
- Spring Boot Starter Cache;
- Heroku PostgreSQL DB deployment;
- Heroku Deployment.
  <br/><br/>

### METHODS
***
| Request  | Description                                 |
|----------|---------------------------------------------|
| `GET`    | Returns information of one or more records. |
| `POST`   | Used to create new record in DB.            |
| `PATCH`  | Updates date from a record.                 |
| `DELETE` | Deletes a record from the DB.               |
<br/>

### RESPONSES
***
| Responses | Description                          |
|-----------|--------------------------------------|
| `200`     | Request executed successfully.       |
| `400`     | Validation errors.                   |
| `403`     | Forbidden Access.                    |
| `404`     | Searched record not found.           |
| `405`     | Method not implemented.              |
| `409`     | Conflict trying to save same record. |
| `500`     | Server error.                        |
<br/>

### AUTHENTICATION - AUTH0
***
Our API uses [AuthO](https://auth0.com/) as a way of authentication/authorization.
<br/><br/>
**Sign Up:**

| Request | Description | Link          |
|---------|-------------|---------------|
| `POST`  | `SignUp`    | /api/v1/users |

Required JSON body (**Sign Up** endpoint):
```
{
    "firstName": "Example",
    "lastName": "Example",
    "email": "example@email.com",
    "password": "password",
    "address": "Example, nº 1 City, Country",
    "dateOfBirth": "1997-04-01"
}
```
**Note:** When someone signs up, it's role is automatically **User**.
<br/><br/>

**Login:**

| Request | Description | Link          |
|---------|-------------|---------------|
| `POST`  | `Login`     | /login        |

Required JSON body (**Login** endpoint):
```
{
    "email": "example@email.com",
    "password": "password"
}
```
<br/>

## RESOURCE GROUPS
***
**Note:** All the endpoints that require parameters should be called this way:
```
api/v1/users/products?direction=ASC&field=id&page=1&pagesize=10
```
Where `direction=ASC`, `field=id`, `page=1` and `pagesize=10` are the parameters.
<br/><br/>

### USER ( /api/v1/users )
***
#### User Info:
| Request  | Description    | Link      |
|----------|----------------|-----------|
| `GET`    | Get User by Id | /{userid} |
| `DELETE` | Delete User    | /{userid} |

| Request  | Description  | Link       |
|----------|--------------|------------|
| `PATCH`  | Update User  | /{userid}  |

Required JSON body (**Update User** endpoint):
```
{
    "firstName": "Example",
    "lastName": "Example",
    "email": "example@email.com",
    "password": "password",
    "address": "Example, nº 1 City, Country"
}
```
<br/>

#### Products:
| Request  | Description              | Link                     | Parameters                          |
|----------|--------------------------|--------------------------|-------------------------------------|
| `GET`    | Get Products List        | /products                | direction, field, page, pagesize    |
| `GET`    | Get Product by Id        | /products/{productid}    |                                     |
| `GET`    | Get Products by Price    | /products/price          | direction, page, pagesize, min, max |
| `GET`    | Get Products by Rating   | /products/rating         | direction, page, pagesize, min, max |
| `GET`    | Get Products by Name     | /products/name           | title, page, pagesize               |
| `GET`    | Get Products by Category | /products/category       | category, page, pagesize            |
| `GET`    | Get Category by Id       | /categories/{categoryid} |                                     |
<br/>

#### Shopping Cart:
| Request | Description                            | Link                         | Parameters         |
|---------|----------------------------------------|------------------------------|--------------------|
| `GET`   | Get Shopping Cart                      | /shoppingcart/{userid}       |                    |
| `GET`   | Get Shopping Cart Total Price          | /shoppingcart/price/{userid} |                    |
| `PATCH` | Add Products to the Shopping Cart      | /addtocart                   | userid, productid  |
| `PATCH` | Remove Products form the Shopping Cart | /removefromcart              | userid, productid  |
| `PATCH` | Remove All Products from the Cart      | /clearcart/{userid}          |                    |

| Request  | Description   | Link         |
|----------|---------------|--------------|
| `POST`   | Buy Products  | buy/{userid  |

Required JSON body (**Buy Products** endpoint) (value to be paid):
```
10000
```
<br/>

#### Rating:
| Request  | Description      | Link             | Parameters                |
|----------|------------------|------------------|---------------------------|
| `GET`    | Get User Ratings | /rating/{userid} |                           |
| `POST`   | Rate a Product   | /rating          | userid, productid, rating |
| `DELETE` | Delete a Rating  | /rating          | userid, productid         |
<br/>

### ADMIN ( /api/v1/admins )
***
#### User Info:
| Request | Description      | Link                   | Parameters                       |
|---------|------------------|------------------------|----------------------------------|
| `GET`   | Get Users List   | /users                 | direction, field, page, pagesize |
| `GET`   | Get User by Id   | /users/{userid}        |                                  |
| `GET`   | Get User by Name | /users/name/{username} |                                  |

| Request | Description | Link            |
|---------|-------------|-----------------|
| `POST`  | Add User    | /users/{userid} |
| `PATCH` | Update User | /users/{userid} |

Required JSON body (**Add User** and **Update User** endpoints):
```
{
    "firstName": "Example",
    "lastName": "Example",
    "email": "example@email.com",
    "password": "password",
    "address": "Example, nº 1 City, Country",
    "dateOfBirth": "1986-05-12"
}
```
<br/>

#### Products:
| Request  | Description             | Link                           | Parameters                          |
|----------|-------------------------|--------------------------------|-------------------------------------|
| `GET`    | Get Products List       | /products                      | direction, field, page, pagesize    |
| `GET`    | Get Product by Id       | /products/{productid}          |                                     |
| `GET`    | Get Products by Price   | /products/price                | direction, page, pagesize, min, max |
| `GET`    | Get Products by Title   | /products/title/{producttitle} |                                     |
| `DELETE` | Delete Product          | /products/{productid}          |                                     |
| `DELETE` | Delete Product by Title | /products/title/{producttitle} |                                     |

| Request | Description           | Link                           | Parameters |
|---------|-----------------------|--------------------------------|------------|
| `POST`  | Add Product           | /products                      |            |
| `PATCH` | Update Product        | /products/{productid}          |            |

Required JSON body (**Add Product** and **Update Product** endpoints):
```
{
    "title": "Example",
    "price": 250.99,
    "description": "Example text",
    "category": "men's clothing",
    "image": "https://cdn-images.farfetch-contents.com/18/72/51/05/18725105_40456639_1000.jpg",
    "stock": 9
}
```
<br/>

### PARAMETERS
***
| Parameter | Description                                                        | Allowed Values                                                                        |
|-----------|--------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| direction | Determines the direction of the list                               | "ASC" or "DESC"                                                                       |
| field     | Determines the field by which the table will be sorted             | "id" or "title" (same as product name)                                                |
| page      | Determines the page of the list                                    | Any positive integer                                                                  |
| pagesize  | Determines how many products are shown by page                     | Any integer between 1 and 100                                                         |
| min       | Determines the minimum rating or price (depending on the endpoint) | **Price**: Any integer between 0 and 5000<br/>**Rating**: Any integer between 0 and 5 |
| max       | Determines the maximum rating or price (depending on the endpoint) | **Price**: Any integer between 0 and 5000<br/>**Rating**: Any integer between 0 and 5 |
<br/>

### ROLES
***
| Role  | Id  |
|-------|-----|
| User  | 1   |
| Admin | 2   |
<br/>

### STRUCTURE
___
The API contains:
* **Command** package - contains the **DTO** (Data Transfer Object) classes;
* **Config** package - contains the **BeansConfig** and **CheckAuth** classes;
* **Controllers** package - contains the **Controller** classes used for routes;
* **Converters** package - contains the classes that convert an **Entity** to a
  **DTO** and vice-versa;
* **Dataloader** package - contains a class that implements the interface
  **ApplicationRunner** and populates the database when the program is started;
* **Enums** package - contains the **Enum** classes used through the project;
* **Exceptions** package - contains the custom exceptions and the **AppExceptionHandler**
  class;
* **Helpers** package - contains some methods used multiple times;
* **Persistence** package - contains the **Model** package and the **Repository**
  package:
  * **Model** package - contains the **Entity** classes;
  * **Repository** package - contains the repository interfaces that allow the
    connection with the **Database**;
* **Security** package - contains the implementation of **JWT**;
* **Services** package - contains the **Service** classes used for business logic;
* **application.yml** file - allows the connection to the **Database**;
* **docker-compose.yml** file - creates the **Database** container;
* **pom.xml** file - contains the dependencies used in the API.
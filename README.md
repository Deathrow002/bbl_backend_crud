
# BBL CRUD API

This project provides a RESTful API for managing users, including the functionality to create, read, update, and delete user information. It includes operations for handling user details such as name, username, email, phone, website, address, and company.

## Endpoints

### 1. Create User

- **POST** `/api/users`
- **Request Body**:
  ```json
  {
      "name": "John Doe",
      "username": "johndoe123",
      "email": "john.doe@example.com",
      "phone": "123-456-7890",
      "website": "https://johndoe.com",
      "address": {
          "street": "123 Main St",
          "suite": "Apt 101",
          "city": "New York",
          "zipcode": "10001",
          "geo": {
              "lat": "40.7128",
              "lng": "-74.0060"
          }
      },
      "company": {
          "nameCompany": "Doe Enterprises",
          "catchPhrase": "Innovating the Future",
          "bs": "Tech Solutions"
      }
  }
  ```
- **Response**:
    - Status: 200 OK
    - Body: Returns the created user object.

### 2. Get All Users

- **GET** `/api/users`
- **Response**:
    - Status: 200 OK
    - Body: A list of all users.

### 3. Get User by ID

- **GET** `/api/users/{id}`
- **Response**:
    - Status: 200 OK (if user exists)
    - Status: 404 Not Found (if user does not exist)
    - Body: The user object corresponding to the provided ID.

### 4. Update User

- **PUT** `/api/users/{id}`
- **Request Body**: Same as the create user request, with updated details.
- **Response**:
    - Status: 200 OK (if user updated successfully)
    - Status: 404 Not Found (if user does not exist)
    - Body: The updated user object.

### 5. Delete User

- **DELETE** `/api/users/{id}`
- **Response**:
    - Status: 204 No Content (if user deleted successfully)
    - Status: 404 Not Found (if user does not exist)

## Dependencies

- Spring Boot
- Java 17 or higher
- Spring Web
- Spring Data JPA
- Postgresql Database (or configure another database in `application.properties`)

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repository-url.git
   ```

2. Navigate to the project folder:
   ```bash
   cd your-project-folder
   ```

3. Build and run the application:
   ```bash
   docker-compose up -d
   ```

4. The application will run on `http://localhost:8080`.

---

## Postman

```json 
{
	"info": {
		"_postman_id": "123bdef8-abd4-4201-8fe2-6dbe00ed6c3d",
		"name": "BBL CRUD API",
		"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
		"_exporter_id": "9786851",
		"_collection_link": "https://bold-robot-838254.postman.co/workspace/RCP~2b6480ec-cf62-4b3f-b0b3-9ba00fe19c3f/collection/9786851-123bdef8-abd4-4201-8fe2-6dbe00ed6c3d?action=share&source=collection_link&creator=9786851"
	},
	"item": [
		{
			"name": "Create User",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"name\": \"John Doe\",\n  \"username\": \"johndoe\",\n  \"email\": \"john@example.com\",\n  \"phone\": \"123-456-7890\",\n  \"website\": \"johndoe.com\",\n  \"address\": {\n    \"street\": \"123 Main St\",\n    \"suite\": \"Apt 4B\",\n    \"city\": \"New York\",\n    \"zipcode\": \"10001\",\n    \"geo\": {\n      \"lat\": \"40.7128\",\n      \"lng\": \"-74.0060\"\n    }\n  },\n  \"company\": {\n    \"nameCompany\": \"Tech Corp\",\n    \"catchPhrase\": \"Innovative Solutions\",\n    \"bs\": \"Technology\"\n  }\n}"
				},
				"url": "http://localhost:8080/api/users"
			},
			"response": []
		},
		{
			"name": "Get All Users",
			"request": {
				"method": "GET",
				"header": [],
				"url": "http://localhost:8080/api/users"
			},
			"response": []
		},
		{
			"name": "Get User by ID",
			"request": {
				"method": "GET",
				"header": [],
				"url": "http://localhost:8080/api/users/1"
			},
			"response": []
		},
		{
			"name": "Update User",
			"request": {
				"method": "PUT",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"name\": \"John Doe Updated\",\n  \"username\": \"johndoe\",\n  \"email\": \"john_updated@example.com\",\n  \"phone\": \"987-654-3210\",\n  \"website\": \"johndoe-updated.com\",\n  \"address\": {\n    \"street\": \"456 Main St\",\n    \"suite\": \"Apt 5C\",\n    \"city\": \"New York\",\n    \"zipcode\": \"10002\",\n    \"geo\": {\n      \"lat\": \"40.7128\",\n      \"lng\": \"-74.0059\"\n    }\n  },\n  \"company\": {\n    \"nameCompany\": \"Updated Company\",\n    \"catchPhrase\": \"Updated Solutions\",\n    \"bs\": \"Tech\"\n  }\n}"
				},
				"url": "http://localhost:8080/api/users/1"
			},
			"response": []
		},
		{
			"name": "Delete User",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": "http://localhost:8080/api/users/1"
			},
			"response": []
		}
	]
}
```
# Product Management Coding Example

Your task is to implement a service with the following requirements:

- Create a REST API that provides CRUD operations for a product which is stored in the `products` table.
- The API needs to be able to return all products or a specific product.
- Implement validation for the fields of a product based on the DDL in `data.sql`.
- Provide a unit test for updating a product.
- API calls that are able to alter data should be secured via Spring Security.
  - GET calls should return data without authorization.

## Tech stack

You are required to use the tech stack defined in the `pom.xml`, but you are allowed to add additional dependencies if you think they are necessary.

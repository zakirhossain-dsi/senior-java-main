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

## Run locally
Please follow the following steps to run this application locally:
<ol>
  <li>Be sure that you have <code>docker</code> & <code>maven</code> installed on your local machine</li>
  <li>Change directory to the project directory from the terminal</li>
  <li>Build project: <code>mvn clean install</code></li>
  <li>Build docker image:  <code>docker build -t product-management:v1 .</code></li>
  <li>Run docker container: <code>docker run -p 8080:8080 --rm product-management:v1</code></li>
  <li>Go to the following ulr: <a href="http://localhost:8080/swagger-ui/index.html">http://localhost:8080/swagger-ui/index.html</a></li>
</ol>

The <code>create</code>, <code>update</code>, and <code>delete</code> apis are <b>username</b> and <b>password</b> protected. </br>
When you try to invoke one of these apis, an authentication dialog box will be shown. </br>
You need to provide the test credentials to the respective fields. Test username and password are given below:
</br></br>
Username: <b>admin</b>
</br>
Password: <b>root</b>

<b>NB:</b> you can change the above username & password from the <code>application.properties</code> file.

Once you are authenticated, you don't need to provide the username and password for the subsequent invocations. </br>
The browser will append the <code>Authorization</code> header automatically for the subsequent calls.

## Docker publish
<ol>
<li><code>docker login -u hellozakir</code></li>
<li><code>docker tag product-management:v1 hellozakir/product-management:v1</code></li>
<li><code>docker push hellozakir/product-management:v1</code></li>
</ol>

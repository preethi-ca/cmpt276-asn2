# Rate CS Teaching Staff

## How to Run Locally

1. Install Java and PostgreSQL.
2. Create a PostgreSQL database and configure the database connection in `src/main/resources/application.properties`.
3. Run the application from the project root:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Open `http://localhost:8080` in your browser.

## How to Deploy

1. Create a PostgreSQL database on Render.
2. Create a Web Service on Render and connect it to this GitHub repository.
3. Configure the database connection using environment variables.
4. Deploy the application and open the Render URL to access it.
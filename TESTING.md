# Testing

## How to Run Tests

Run all tests from the project root using:

```bash
./mvnw test
```

## Tests Included

### 1. StaffRatingTest.java
1. Tests that the overall score is calculated correctly.
2. Tests that a TA's display title is correct.
3. Tests that invalid email addresses are rejected.
4. Tests that scores above 10 are rejected.
5. Tests that an empty name is rejected.

### 2. RatingControllerTest.java
1. Tests that the home page loads and displays ratings.
2. Tests that a valid rating can be created.
3. Tests that a rating with an empty name is rejected.
4. Tests that ratings can be saved to and retrieved from the database.
5. Tests that ratings can be updated through the controller.
6. Tests that ratings can be deleted through the controller.

### 3. Asn2ApplicationTests.java
1. Tests that the Spring Boot application context loads successfully.

## Testing Approach

The project uses full Spring Boot integration tests with MockMvc for controller requests and database operations. Jakarta Bean Validation is used to test input validation.
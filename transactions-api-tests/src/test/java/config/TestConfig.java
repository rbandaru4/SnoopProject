package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestConfig {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {
        // Base configuration for all requests
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://2g3pwg85a2.execute-api.eu-west-2.amazonaws.com")
                .setBasePath("/default/transactions")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        // Common response expectations
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}
package features.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;

import static io.restassured.RestAssured.given;

public class TransactionsStepDefinitions {
    private RequestSpecification request;
    private Response response;

    @Given("I send a GET request to the transactions endpoint")
    public void i_send_a_get_request_to_the_transactions_endpoint() {
        response = given().when().get();
    }

    @Then("the response should contain an error message {string}")
    public void the_response_should_contain_an_error_message(String expectedMessage) {
        String actualMessage = response.getBody().asString();
        Assertions.assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
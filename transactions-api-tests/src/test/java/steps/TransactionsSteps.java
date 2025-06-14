package steps;

import config.TestConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Transaction;
import org.assertj.core.api.Assertions;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TransactionsSteps {

    private RequestSpecification request;
    private Response response;
    private List<Transaction> transactions;

    @Given("I set the customer ID to {string}")
    public void i_set_the_customer_id_to(String customerId) {
        if (TestConfig.requestSpec == null) {
            TestConfig.setup();
        }
        request = given().spec(TestConfig.requestSpec).queryParam("customerId", customerId);
    }

    @Given("I set the following query parameters:")
    public void i_set_the_following_query_parameters(Map<String, String> queryParams) {
        queryParams.forEach(request::queryParam);
    }

    @When("I send a GET request to the transactions endpoint")
    public void i_send_a_get_request_to_the_transactions_endpoint() {
        try {
            response = request.when().get();
            if (response.getStatusCode() == 200) {
                transactions = Arrays.asList(response.getBody().as(Transaction[].class));
            }
        }catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("the response should contain {int} transactions")
    public void the_response_should_contain_transactions(Integer expectedCount) {
        Assertions.assertThat(transactions).hasSize(expectedCount);
    }

    @Then("the response should contain no transactions")
    public void the_response_should_contain_no_transactions() {
        Assertions.assertThat(transactions).isEmpty();
    }

    @Then("all transactions should have category ID {int}")
    public void all_transactions_should_have_category_id(Integer expectedCategoryId) {
        Assertions.assertThat(transactions)
                .allMatch(t -> t.getCategoryId().equals(expectedCategoryId),
                        "All transactions should have category ID " + expectedCategoryId);
    }

    @Then("all transactions should be of type {string}")
    public void all_transactions_should_be_of_type(String expectedType) {
        Assertions.assertThat(transactions)
                .allMatch(t -> t.getType().equalsIgnoreCase(expectedType),
                        "All transactions should be of type " + expectedType);
    }

    @Then("all transactions should have status {string}")
    public void all_transactions_should_have_status(String expectedStatus) {
        Assertions.assertThat(transactions)
                .allMatch(t -> t.getStatus().equalsIgnoreCase(expectedStatus),
                        "All transactions should have status " + expectedStatus);
    }

    @Then("transactions should be ordered by status and timestamp")
    public void transactions_should_be_ordered_by_status_and_timestamp() {
        // Verify Pending transactions come before Booked
        boolean pendingFound = false;
        boolean bookedFound = false;

        for (Transaction t : transactions) {
            if (t.getStatus().equalsIgnoreCase("Pending")) {
                pendingFound = true;
                if (bookedFound) {
                    Assertions.fail("Pending transaction found after Booked transaction");
                }
            } else if (t.getStatus().equalsIgnoreCase("Booked")) {
                bookedFound = true;
            }
        }

        // Verify transactions are ordered by timestamp in descending order within each status
        for (int i = 0; i < transactions.size() - 1; i++) {
            Transaction current = transactions.get(i);
            Transaction next = transactions.get(i + 1);

            if (current.getStatus().equals(next.getStatus())) {
                Assertions.assertThat(current.getTimestamp())
                        .isAfterOrEqualTo(next.getTimestamp());
            }
        }
    }

    @Then("all transactions should be between {string} and {string}")
    public void all_transactions_should_be_between_and(String fromDate, String toDate) {
        LocalDate from = DateUtils.parseDate(fromDate);
        LocalDate to = DateUtils.parseDate(toDate);

        Assertions.assertThat(transactions)
                .allMatch(t -> {
                    LocalDate transactionDate = t.getTimestamp().toLocalDate();
                    return !transactionDate.isBefore(from) && !transactionDate.isAfter(to);
                }, "All transactions should be between " + fromDate + " and " + toDate);
    }

    @Then("the response should contain an error message {string}")
    public void the_response_should_contain_an_error_message(String expectedMessage) {
        // Parse response as a JSON string to remove outer quotes
        String actualMessage = response.getBody().asString().replace("\"", "");
        Assertions.assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
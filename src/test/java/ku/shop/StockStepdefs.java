package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StockStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private Throwable lastError;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
        lastError = null;
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I try to buy {string} with quantity {int}")
    public void i_try_to_buy_with_quantity(String name, int quantity) {
        lastError = null;
        Product prod = catalog.getProduct(name);
        try {
            order.addItem(prod, quantity);
        } catch (Throwable t) {
            lastError = t;
        }
    }

    @Then("buying should fail with {string}")
    public void buying_should_fail_with(String exceptionName) {
        assertNotNull(lastError, "Expected exception but got none");
        assertEquals(exceptionName, lastError.getClass().getSimpleName());
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        lastError = null;
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quantity);
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal(), 1e-6);
    }
}
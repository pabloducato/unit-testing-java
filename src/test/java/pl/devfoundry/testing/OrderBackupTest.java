package pl.devfoundry.testing;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void beforeAll() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @AfterAll
    static void afterAll() throws IOException {
        orderBackup.closeFile();
    }

    @BeforeEach
    void setUp() throws IOException {
        orderBackup.getWriter().append("New order: ");
    }

    @AfterEach
    void tearDown() throws IOException {
        orderBackup.getWriter().append(" successfully saved");
    }

    @Test
    @SuppressWarnings("all")
    void test_backupOrderWithOneMeal() throws IOException {
        // given
        final Meal meal = new Meal(7, 1, "Fries");
        Order order = Order.builder().build();
        order.addMealToOrder(order, meal);

        // when
        orderBackup.backupOrder(order);

        // then
        log.info("Order: " + order.getMeals() + " successfully backed up");
    }

}

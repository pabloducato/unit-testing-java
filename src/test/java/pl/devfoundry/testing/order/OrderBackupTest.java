package pl.devfoundry.testing.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import pl.devfoundry.testing.Meal;

import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@Tag("test")
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

    @Tag("fries")
    @Test
    @SuppressWarnings("all")
    void test_backupOrderWithOneMeal() throws IOException {
        // given
        final Meal meal = new Meal(7, 1, "Fries");
        pl.devfoundry.testing.order.Order order = Order.builder().build();
        order.addMealToOrder(order, meal);

        // when
        orderBackup.backupOrder(order);

        // then
        log.info("Order: " + order.getMeals() + " successfully backed up");
    }

}

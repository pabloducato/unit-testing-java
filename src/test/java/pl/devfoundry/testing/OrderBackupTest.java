package pl.devfoundry.testing;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
class OrderBackupTest {

    private static OrderBackup orderBackup;
    private final List<Meal> meals = new ArrayList<>();

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
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_backupOrderWithOneMeal() throws IOException {
        // given
        final Meal meal = new Meal(7, "Fries");
        Order order = Order.builder().build();
        order.addMealToOrder(order, meal);

        // when
        orderBackup.backupOrder(order);

        // then
        log.info("Order: " + order.getMeals() + " successfully backed up");
    }
}

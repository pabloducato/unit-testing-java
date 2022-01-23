package pl.devfoundry.testing.extensions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.order.Order;
import pl.devfoundry.testing.order.OrderBackup;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderBackupExecutionOrderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void test_callingBackupWithoutCreatingAFileFiestShouldThrowException() throws IOException {
        // given
        final OrderBackup orderBackup = new OrderBackup();

        // when // then
        assertThrows(IOException.class, () -> orderBackup.backupOrder(Order.builder().build()));
    }

}

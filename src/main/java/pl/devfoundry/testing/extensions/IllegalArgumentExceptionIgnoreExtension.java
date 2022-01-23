package pl.devfoundry.testing.extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

@Slf4j
public class IllegalArgumentExceptionIgnoreExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof IllegalArgumentException) {
            log.info("Just ignored IllegalArgumentException");
        } else {
            throw throwable;
        }
    }
}

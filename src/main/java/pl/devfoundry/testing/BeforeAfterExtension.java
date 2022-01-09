package pl.devfoundry.testing;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class BeforeAfterExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        log.info("Inside the before each extension");
    }


    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        log.info("Inside the after each extension");
    }


}

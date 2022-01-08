package pl.devfoundry.testing;

import lombok.Getter;
import lombok.ToString;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@ToString
public class OrderBackup {

    private Writer writer;

    public void createFile() throws FileNotFoundException {
        final File file = new File("orderBackup_" + ThreadLocalRandom.current().toString() + ".txt");
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        writer = new BufferedWriter(outputStreamWriter);
    }

    public void backupOrder(Order order) throws IOException {
        writer.append(order.toString());
    }

    public void closeFile() throws IOException {
        writer.close();
    }

}

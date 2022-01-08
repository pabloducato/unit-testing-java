package pl.devfoundry.testing;

import lombok.Getter;

import java.io.*;
import java.util.Calendar;

@Getter
public class OrderBackup {

    private Writer writer;

    public void createFile() throws FileNotFoundException {
        final String time = Calendar.getInstance().getTime().toString().replace(":", "_");
        final File file = new File("files/orderBackup_" + time + ".txt");
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

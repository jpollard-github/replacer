package replacer;

import com.jpollard91.boundary.ValueFileReader;
import com.jpollard91.boundary.ValueFileWriter;

import java.io.IOException;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("Thank you for using replacer!");

        if (args.length < 3) {
            System.out.println("replacer requires three arguments, java -jar replacer.jar <path of file with key-value pairs> <path of original file to replace values in> <path of new file with replaced values");
            System.out.println("your file to replace in should use the syntax {{KEY}} for values to replace");
            System.exit(-1);
        }

        String keyValueFile = args[0];
        String originalFile = args[1];
        String newFile = args[2];

        ValueFileReader valueFileReader = new ValueFileReader();
        Map<String, String> keysValues = valueFileReader.readKeyValueFile(keyValueFile);

        ValueFileWriter valueFileWriter = new ValueFileWriter();
        valueFileWriter.iterateLines(keysValues, originalFile, newFile);

        System.out.println("Values have been replaced, have a good day!");
        System.exit(0);
    }

}

package demandlane.com.booklending.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BookLoanUtility {

      public static  String convertInputStreamToString(InputStream inputStream) throws IOException {
        // Reads all bytes and then constructs a String using UTF-8 encoding
        byte[] bytes = inputStream.readAllBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }

}

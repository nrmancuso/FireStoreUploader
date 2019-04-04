import java.util.Map;

public class CSVFileReaderTest {

    public static void main(String[] args) {
        try {
            Map<String, Book> bookmap = XLSXFileReader.buildBookMap("Book_Data/books.csv");
            System.out.println(bookmap);
        } catch (Exception e) {
            System.out.println("Whoops");
        }

    }


}



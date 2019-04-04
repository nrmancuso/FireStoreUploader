import java.util.Map;

public class CSVFileReaderTest {

    public static void main(String[] args) {
        try {
            Map<String, Book> bookmap = XLSXFileReader.buildBookMap("book_data/Textbooks.xlsx");
            System.out.println(bookmap);
        } catch (Exception e) {
            System.out.println("Whoops");
        }

    }


}



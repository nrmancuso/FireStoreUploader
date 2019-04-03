import java.util.Map;

public class CSVFileReaderTest {

    public static void main(String[] args) {

           Map<String, Book> bookmap =  CSVFileReader.buildBookMap("Book_Data/books.csv");
            System.out.println(bookmap);

    }


}



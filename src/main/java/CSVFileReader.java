import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVFileReader {

    public static Map<String, Book> buildBookMap(String CSVFileName) {

        Map<String, Book> bookMap = new HashMap<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(CSVFileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");

                Book newBook = new BookBuilder()
                        //TODO:these fields must be modified to work with DTCC books
                        .setTitle(bookData[0])
                        .setAuthor(bookData[1])
                        .setCourseNumber(bookData[2])
                        .setNotes(bookData[3])
                        .createBook();

                bookMap.put(newBook.title, newBook);

            }

        } catch (IOException e) {
            System.out.println("Problem parsing file!");
        }

            return bookMap;
    }

}

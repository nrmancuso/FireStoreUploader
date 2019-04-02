import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookBuilder {


    public static List buildBookList(String CSVFileName) throws IOException {
        List<Book> bookList = new ArrayList<>();


        try (
                BufferedReader br = new BufferedReader(new FileReader(CSVFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");

                Book newBook = new Book(
                        bookData[0],
                        bookData[1],
                        bookData[2],
                        bookData[3]
                        //TODO: Implement below elements when using the real CSV file
                        //bookData[4],
                        //bookData[5],
                        //bookData[6],
                        //bookData[7],
                        //bookData[8]
                );

                bookList.add(newBook);

            }
        }
        return bookList;
    }

}

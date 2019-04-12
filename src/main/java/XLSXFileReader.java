import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class XLSXFileReader {

    public static Map<String, Book> buildBookMap(String XLSXFilename) throws IOException {

        Map<String, Book> bookMap = new HashMap<>();

        File excelFile = new File(XLSXFilename);
        FileInputStream fis = new FileInputStream(excelFile);

        //Create an XSSF Workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //Get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate on rows
        Iterator<Row> rowIt = sheet.iterator();

        //Skip first row, because headers
        Row row = rowIt.next();


        //Keep iterating through rows until there's no data left
        while (rowIt.hasNext()) {
            row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            //Make new String[] for each row of toner info
            String[] bookData = new String[15];

            //Index to keep track of cells
            int index = 0;

            //Loop through the row, reading each cell into tonerInfoStringArray
            while (cellIterator.hasNext()) {

                //Get next cell
                Cell cell = cellIterator.next();

                //Extract data and assign to correct index of tonerInfoStringArray
                bookData[index] = cell.toString();
                index++;        //increment index

            }

            Random random = new Random();

            if (bookData[7] == null || bookData[7].length() < 13 || !bookData[7].matches("[0-9]+")) {
                bookData[7] = String.valueOf(random.nextInt(999999999));
                bookData[7].concat(String.valueOf(random.nextInt(9999)));
            }

            //Build book
            Book newBook = new BookBuilder()
                    .setCourseNumber(bookData[1])
                    .setSemesterCode(bookData[2])
                    .setAuthor(bookData[4])
                    .setTitle(bookData[5])
                    .setIsbn(bookData[7].replaceAll("\\s","").replaceAll("-",""))
                    .setNotes(bookData[9])
                    .createBook();

            bookMap.put(newBook.getIsbn(), newBook);

        }

        //Close workbook and fis
        workbook.close();
        fis.close();

        //fin
        return bookMap;
    }
}

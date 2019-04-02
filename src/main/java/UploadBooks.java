import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Book {
    String semesterCode;
    String courseNumber;
    String courseName;
    String courseLeadCode;
    String courseLeadName;
    String isbn;
    String title;
    String author;
    String notes;

    public Book(){}

    public Book(String semesterCode, String courseNumber, String courseName, String courseLeadCode,
                String courseLeadName, String isbn, String title, String author, String notes) {
        this.semesterCode = semesterCode;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseLeadCode = courseLeadCode;
        this.courseLeadName = courseLeadName;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.notes = notes;
    }

    public Book(String courseLeadName, String title, String author, String notes) {
        this.semesterCode = semesterCode;
        this.courseLeadName = courseLeadName;
        this.title = title;
        this.author = author;
        this.notes = notes;
    }
}


public class UploadBooks {

    public static void main(String[] args) throws IOException {






    }
}
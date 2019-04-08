/**
 * This class represents a book in the Del Tech Computer Science database
 */

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

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLeadCode() {
        return courseLeadCode;
    }

    public void setCourseLeadCode(String courseLeadCode) {
        this.courseLeadCode = courseLeadCode;
    }

    public String getCourseLeadName() {
        return courseLeadName;
    }

    public void setCourseLeadName(String courseLeadName) {
        this.courseLeadName = courseLeadName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
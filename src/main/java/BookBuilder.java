public class BookBuilder {
    private String semesterCode;
    private String courseNumber;
    private String courseName;
    private String courseLeadCode;
    private String courseLeadName;
    private String isbn;
    private String title;
    private String author;
    private String notes;

    public BookBuilder setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
        return this;
    }

    public BookBuilder setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
        return this;
    }

    public BookBuilder setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public BookBuilder setCourseLeadCode(String courseLeadCode) {
        this.courseLeadCode = courseLeadCode;
        return this;
    }

    public BookBuilder setCourseLeadName(String courseLeadName) {
        this.courseLeadName = courseLeadName;
        return this;
    }

    public BookBuilder setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Book createBook() {
        return new Book(semesterCode, courseNumber, courseName, courseLeadCode, courseLeadName, isbn, title, author, notes);
    }
}
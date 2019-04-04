
//Initialize cloud platform
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
//Firebase imports
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
//Java Imports
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FSUploader {

    public static void main(String[] args) {

        String jsonFileName;
        String XLSXFileName;
        String collection;
        String firebaseURL;
        Firestore db;

        if (args.length == 0) {
            displayHelp();
        } else {

            switch (args[0]) {

                //upload to database
                case "-u":
                    jsonFileName = args[1];
                    firebaseURL = args[2];
                    XLSXFileName = args[3];

                    db = initializeDB(jsonFileName, firebaseURL);


                    try {
                        Map<String, Book> bookmap = XLSXFileReader.buildBookMap(XLSXFileName);

                        bookmap.forEach((k, v) -> {
                                addDocument(db, "library", k, v);
                        });

                    } catch (Exception e) {

                        System.out.println("Problem parsing XLSX file!");
                    }
                    break;

                //read from database
                case "-r":
                    jsonFileName = args[1];
                    firebaseURL = args[2];
                    collection = args[3];

                    db = initializeDB(jsonFileName, firebaseURL);


                    try {

                        retrieveAllDocuments(db, collection);

                    } catch (Exception e) {

                        System.out.println("Document fetch interrupted!");
                    }
                    break;

                default:
                    displayHelp();

            }

        }

    }

    // Help/ welcome dialog
    static void displayHelp() {

        System.out.println("  ______ _           _____ _                     \n" +
                " |  ____(_)         / ____| |                    \n" +
                " | |__   _ _ __ ___| (___ | |_ ___  _ __ ___     \n" +
                " |  __| | | '__/ _ \\\\___ \\| __/ _ \\| '__/ _ \\    \n" +
                " | |    | | | |  __/____) | || (_) | | |  __/    \n" +
                " |_|   _|_|_|  \\___|_____/ \\__\\___/|_|  \\___|    \n" +
                " | |  | |     | |                   | |          \n" +
                " | |  | |_ __ | |     ___   __ _  __| | ___ _ __ \n" +
                " | |  | | '_ \\| |    / _ \\ / _` |/ _` |/ _ \\ '__|\n" +
                " | |__| | |_) | |___| (_) | (_| | (_| |  __/ |   \n" +
                "  \\____/| .__/|______\\___/ \\__,_|\\__,_|\\___|_|   \n" +
                "        | |                                      \n" +
                "        |_|");

        System.out.println("**************************************************************");
        System.out.println();
        System.out.println("You will need the JSON file from Firestore Admin Panel to use this program.");
        System.out.println();
        System.out.println("This program can upload a CSV file containing books to your collection in FireStore, " +
                "\nor print your collection to the console.");
        System.out.println();
        System.out.println("Usage:\n" +
                "  fsuploader -u <JSON file name> <FireStore URL> <CSV file name>\n" +
                "  fsuploader -r <JSON file name> <FireStore URL> <FireStore collection name>\n" +
                "\n" +
                "Options:\n" +
                "  -h --help     Show this screen.\n");
    }

    //Initialize firestore database
    static Firestore initializeDB(String jsonFileName, String firebaseURL) {

        try {
            FileInputStream serviceAccount =
                    new FileInputStream(jsonFileName);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(firebaseURL)
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println("Problem with authentication!");
        }

        return FirestoreClient.getFirestore();
    }

    //List all elements in given collection
    static void retrieveAllDocuments(Firestore db, String collection) throws Exception {

        ApiFuture<QuerySnapshot> query = db.collection(collection).get();
        QuerySnapshot querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents) {

            System.out.println();
            System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("first"));

            System.out.println("Semester Code: " + document.getString("semester code"));
            System.out.println("Course Number: " + document.getString("course number"));
            System.out.println("Course Name: " + document.getString("course name"));
            System.out.println("Course Lead Code: " + document.getString("course lead code"));
            System.out.println("ISBN: " + document.getString("ISBN"));
            System.out.println("Title: " + document.getString("title"));
            System.out.println("Author: " + document.getString("author"));
            System.out.println("Notes: " + document.getString("notes"));

        }
    }

    //Add documents to collection
    static void addDocument(Firestore db, String collection, String docName, Book newBook) {

        DocumentReference docRef = db.collection(collection).document(docName);

        Map<String, Object> data = new HashMap<>();

        data.put("semestercode", newBook.getSemesterCode());
        data.put("coursenumber", newBook.getCourseNumber());
        data.put("coursename", newBook.getCourseName());
        data.put("courseleadcode", newBook.getCourseLeadCode());
        data.put("courseleadname", newBook.getCourseLeadName());
        data.put("ISBN", newBook.getIsbn());
        data.put("title", newBook.getTitle());
        data.put("author", newBook.getAuthor());
        data.put("notes", newBook.getNotes());

        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // ...
        // result.get() blocks on response

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println("System / server time sync issue!");
        }
    }
}


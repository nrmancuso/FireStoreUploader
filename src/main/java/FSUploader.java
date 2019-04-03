
//Initialize cloud platform

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class FSUploader {

    public static void main(String[] args) {

        String jsonFileName;
        String CSVFileName;
        String collection;
        Firestore db;

        if (args.length == 0) {
            displayHelp();
        } else {

            switch (args[0]) {

                //upload to database
                case "-u":
                    jsonFileName = args[1];
                    CSVFileName = args[2];

                    db = initializeDB(jsonFileName);


                    try {
                        Map<String, Book> bookmap = CSVFileReader.buildBookMap(CSVFileName);

                        bookmap.forEach((k, v) -> {
                            try {
                                addDocument(db, "library", k, v);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        System.out.println("Problem parsing CSV file!");
                    }
                    break;

                //read from database
                case "-r":
                    jsonFileName = args[1];
                    collection = args[2];

                    db = initializeDB(jsonFileName);


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
                "  fsuploader -u <JSON file name> <CSV file name>\n" +
                "  fsuploader -r <JSON file name> <FireStore collection name>\n" +
                "\n" +
                "Options:\n" +
                "  -h --help     Show this screen.\n");
    }

    static Firestore initializeDB(String jsonFileName) {

        try {
            FileInputStream serviceAccount =
                    new FileInputStream(jsonFileName);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://test-90f54.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println("Problem with authentication!");
        }

        return FirestoreClient.getFirestore();
    }

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

    static void addDocument(Firestore db, String collection, String docName, Book newBook) throws Exception {

        DocumentReference docRef = db.collection(collection).document(docName);

        Map<String, Object> data = new HashMap<>();

        data.put("semester code", newBook.getSemesterCode());
        data.put("course number", newBook.getCourseNumber());
        data.put("course name", newBook.getCourseName());
        data.put("course lead code", newBook.getCourseLeadCode());
        data.put("course lead name", newBook.getCourseLeadName());
        data.put("ISBN", newBook.getIsbn());
        data.put("title", newBook.getTitle());
        data.put("author", newBook.getAuthor());
        data.put("notes", newBook.getNotes());

        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // ...
        // result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
}


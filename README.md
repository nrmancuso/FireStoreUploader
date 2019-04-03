# FireStore Uploader

This project is a quick solution for uploading data to Google's FireStore database.
## Getting Started

Make sure you have your JSON file from the FireStore admin console, found [here](https://console.firebase.google.com/).

### Prerequisites

You need to have the Oracle JRE 11 or one of the open-source variants installed, and be familiar with the command line.
This program only supports parsing CSV files right now. Obviously, you will need a CSV file of books if you want to upload
anything.  Luckily, I've included one [here](https://github.com/nmancus1/FireStoreUploader/blob/master/book_data/books.csv).


### Usage

Right now, there are only two different command line arguments that can be passed:

To create new documents (records) in your firestore database:

```
fsuploader -u <JSON file name> <FireStore URL> <CSV file name>
```

To read all of your documents in a collection:
```
fsuploader -r <JSON file name> <FireStore URL> <FireStore collection name>
```


## Built With

* [FireBase](https://firebase.google.com/) - Google's NOSQL database
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Any contributions are welcome!

## Authors

* **Nick Mancuso** - *Developer* - [Website](https://www.nickmancuso.dev)

See also the list of [contributors](https://github.com/nmancus1/FireStoreUploader/graphs/contributors) who participated in this project.

## Acknowledgments

* Google for the excellent cloud database
* [Billie Thompson](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2) for the great readme template
* [jaidevd](https://gist.github.com/jaidevd/23aef12e9bf56c618c41) for the great csv file with all the books,
used for testing

# ALV Left Right diff Services Project

### Database
The project uses H2 database for temporal storage of the messages. This database is configured to be created when the server is running only.

### Endpoints

#### Left
The left endpoint receive a path variable for the id of the request, and in the body the request with the message with binary data in bas64 and store it in the data base with the attribute side as "left". This method will return the object stored in the DB.

#### Right
The right endpoint receive a path variable for the id of the request, and in the body the request with the message with binary data in bas64 and store it in the data base with the attribute side as "right". This method will return the object stored in the DB.

#### Diff
This endpoint receive a path variable for the id of the request, and will search for the left and right messages in the H2 database. If one is missing, it will be advice in the response. If two messages exists, will compare the binary data and will return the results of the different comparisons.

## Testing
I used mock and jUnit to test the endpoints.

## Stack used
This project was developed in java 8, with spring, springboot, springdata, hibernate, h2 db, jUnit, Mock.

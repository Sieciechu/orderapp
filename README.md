## Introduction
This is application for handling orders. Feeds in memory database from xml and csv files.
Allows to print reports to the screen and to a CSV file. I treat this app as an excercise for learning java.

Table of Contents:
* [Build](#build)
* [Run](#run)
* [More details](#description)
--- 

<a name="build"></a>
### Build
Using gradle:
`$ ./gradlew build`

Using docker:
`$ docker run --rm -v "$PWD":/home/gradle/project -w /home/gradle/project -u "gradle:gradle" gradle:jdk11 gradle build`

### Test:
Using gradle:
`$ ./gradlew test`

Using docker:
`$ docker run --rm -v "$PWD":/home/gradle/project -w /home/gradle/project -u "gradle:gradle" gradle:jdk11 gradle test`

<a name="run"></a>
#### Run:
Locally:
`$ java -jar build/libs/orderapp-all.jar "$PWD/src/main/resources/orders.csv" "$PWD/src/main/resources/orders.xml"`

Using docker:

`$ docker image build -t order-app .`

`$ docker container run --rm -it order-app`

<a name="description"></a>
### More details
Application for handling orders. Requirements:
1. The application accepts input params. The input params is list of csv and xml files
2. Each file contains one or more orders (check the format in resources dir, in the example orders.csv, orders.xml)
3. Each order need to be stored in "data base" (it can be in memory db)

4. Each order has obligatory fields:

    a. CustomerId - alphanumeric, without spaces, not longer than 6 chars,

    b. RequestId - long integer

    c. Name - alphanumeric with spaces, not longer than 255 chars,

    d. Quantity - numeric integer

    e. Price - numeric double

5. The application allows to generate reports:

    a. Number of all orders,

    b. Number of orders for given customer,

    c. Total price of all orders,

    d. Total price of all orders for given customer

    e. List of all orders,

    f. List of orders for given customer,

    g. Average price of all orders,

    h. Average price of all orders for given customer.

6. Each report can be shown on the screen or written to csv file
7. Database is not shared between launches
8. Incorrect lines during file import are ignored

# Readme
### Table of Contents
  * [Build](#build)
  * [Run](#run)
  * [Description](#description)

<a name="build"></a>
### Build
#### Using gradle:
`$ ./gradlew build`

#### Using docker:

`$ docker run --rm -v "$PWD":/home/gradle/project -w /home/gradle/project -u "gradle:gradle" gradle:jdk11 gradle build`

### Test:
#### Using gradle:
`$ ./gradlew build`

#### Using docker:
`$ docker run --rm -v "$PWD":/home/gradle/project -w /home/gradle/project -u "gradle:gradle" gradle:jdk11 gradle task test`

<a name="run"></a>
### Run:
#### Locally:
`$ java -jar build/libs/orderapp.jar`

#### Using docker:
`$ docker image build -t order-app .`

`$ docker container run --rm order-app`

<a name="description"></a>
### Description
#### Application for handling orders.
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
8. Incorrect lines during file import are ignored, but info about error is shown to the user (screen or log file)

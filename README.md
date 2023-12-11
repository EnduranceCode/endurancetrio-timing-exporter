# EnduranceTrio Timing Exporter

**REST API to collect and manages the data produced by race timing systems**

## Introduction

**EnduranceTrio Timing Exporter** is a REST API that collects and manages the data produced by
race timing systems. The initial development is made for the [MYLAPS](https://www.mylaps.com/)
timing system, but the goal is to support other timing systems available on the market.

## Development

### Technologies

**EnduranceTrio Timing Exporter** is a [Spring Boot](https://spring.io/projects/spring-boot) app
and uses a [MySQL](https://www.mysql.com/) database.

### Installation

#### Clone the repository

Make sure that you have [Java 11](https://javaalmanac.io/jdk/11/) and
[MySQL](https://www.mysql.com/) installed in your development machine and then clone the repository
with the following command:

    git clone git@github.com:EnduranceCode/endurancetrio-timing-exporter.git

#### Set up the database

##### Create a MySQL database

Login into the [MySQL](https://www.mysql.com/) server and replace the ***{LABEL}*** in the below
command as appropriate and then execute it to
[create a database](https://www.mysqltutorial.org/mysql-create-database/) for the application.

    CREATE DATABASE {DATABASE_NAME} CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

> **Label Definition**
>
> + **{DATABASE_NAME}** : The name chosen for the new database;

##### Create a MySQL user

To [create a user](https://www.mysqltutorial.org/mysql-create-user.aspx) for the application
database management, replace the ***{LABELS}*** in the below command as appropriate
and then execute it.

    CREATE USER '{USERNAME}'@'{HOST}' IDENTIFIED WITH caching_sha2_password BY '{PASSWORD}';

> **Labels Definition**
>
> + **{USERNAME}** : The new account name in the MySQL Server;
> + **{HOST}** : The name of the host from which the user connects to the MySQL Server, e.g. `localhost`;
> + **{PASSWORD}** : The password of the new account in the MySQL Server.

##### Grant privileges to a MySQL user

To [grant the necessary privileges to the created user in the MySQL Server](https://www.mysqltutorial.org/mysql-grant.aspx),
replace the ***{LABELS}*** in the below command as appropriate and then execute it.

    GRANT ALL PRIVILEGES ON {DATABASE_NAME}.* TO '{USERNAME}'@'{HOST}';

> **Labels Definition**
>
> + **{DATABASE_NAME}** : The database where the MySQL user will be granted privileges;
> + **{USERNAME}** : The account name in the MySQL Server to whom the privileges will be assigned.
> + **{HOST}** : The name of the host from which the user connects to the MySQL Server,
e.g. `localhost`.

##### Configure the application's database access

To configure the application's access to the database, take the following steps:

1. Copy the file [`confidential-template.yaml`](./src/main/resources/confidential-template.yaml) and
located in the `resources` folder and rename it to `confidential.yaml`;
2. Replace the {DATABASE_NAME} placeholder with the application database name;
3. Replace the {DATABASE_USER} placeholder with the application database user's username;
4. Replace the {DATABASE_NAME} placeholder with the application database user's password;
5. Delete this comments block at the top of the file that contains these instructions.

# License

**EnduranceTrio Timing Exporter** is licensed under the terms of [MIT License](./LICENSE).

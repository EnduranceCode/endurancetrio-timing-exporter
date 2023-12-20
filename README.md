# EnduranceTrio Timing Exporter

**REST API to receive and manage the data produced by race timing systems**

## Introduction

**EnduranceTrio Timing Exporter** is a REST API that receives and manages the data produced by
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

#### Set the MySQL Server Time Zone to UTC+00:00

[Set MySQL server time zone](https://www.scaler.com/topics/mysql-time-zone/) to UTC+00:00 in your
server configuration `my.cnf` file. For an Ubuntu server, to open the `/etc/mysql/my.cnf` file,
execute the upcoming command.

    sudo nano /etc/mysql/my.cnf

If the `my.cnf` file doesn't have the `[mysqld]` section, add it as shown below:

    [mysqld]
    default-time-zone='+00:00'

To apply the changes, restart the MySQL server with the following command:

    sudo service mysql restart

##### Create a MySQL database

Login into the [MySQL](https://www.mysql.com/) server, replace the ***{LABEL}*** in the below
command as appropriate and then execute it to
[create](https://www.mysqltutorial.org/mysql-create-database/) the **EnduranceTrio Timing Exporter**
database.

    CREATE DATABASE {DATABASE_NAME} CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

> **Label Definition**
>
> + **{DATABASE_NAME}** : The name chosen for the new database;

##### Create a MySQL user

To [create a user](https://www.mysqltutorial.org/mysql-create-user.aspx) for the
**EnduranceTrio Timing Exporter** database management, replace the ***{LABELS}*** in the below 
command as appropriate and then execute it.

    CREATE USER '{USERNAME}'@'{HOST}' IDENTIFIED WITH caching_sha2_password BY '{PASSWORD}';

> **Labels Definition**
>
> + **{USERNAME}** : The new account name in the MySQL Server;
> + **{HOST}** : The name of the host from which the user connects to the MySQL Server, e.g. `localhost`;
> + **{PASSWORD}** : The password of the new account in the MySQL Server.

##### Grant privileges to the EnduranceTrio Timing Exporter MySQL user

To [grant the necessary privileges to the created user in the MySQL Server](https://www.mysqltutorial.org/mysql-grant.aspx),
replace the ***{LABELS}*** in the below command as appropriate and then execute it.

    GRANT ALL PRIVILEGES ON {DATABASE_NAME}.* TO '{USERNAME}'@'localhost';

> **Labels Definition**
>
> + **{DATABASE_NAME}** : The database where the MySQL user will be granted privileges;
> + **{USERNAME}** : The account name in the MySQL Server to whom the privileges will be assigned.

##### Configure the application's database access

To configure the application's access to the database, take the following steps:

1. Copy the file [`confidential-template.yaml`](./src/main/resources/confidential-template.yaml) and
located in the `resources` folder and rename it to `confidential.yaml`;
2. Replace the {DATABASE_NAME} placeholder with the application database name;
3. Replace the {DATABASE_USER} placeholder with the application database user's username;
4. Replace the {DATABASE_NAME} placeholder with the application database user's password;
5. Delete this comments block at the top of the file that contains these instructions.

##### Manage the application's database access

The file [`DATABASE.md`](./src/main/resources/db/DATABASE.md), stored in the folder
[`src/main/resources/db/`](./src/main/resources/db), documents the process to manage
the **EnduranceTrio Timing Exporter** database.

# License

**EnduranceTrio Timing Exporter** is licensed under the terms of [MIT License](./LICENSE).

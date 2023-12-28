# EnduranceTrio Timing Exporter

**REST API to receive and manage the data produced by race timing systems**

## Table Of Contents

1. [Introduction](#introduction)
2. [Development](#development)
    1. [Technologies](#technologies)
    2. [Installation](#installation)
3. [Deployment](#deployment)
    1. [Prerequisites](#prerequisites)
    2. [Server setup](#server-setup)
    3. [Build and installation](#build-and-installation)
4. [License](#license)

## Introduction

**EnduranceTrio Timing Exporter** is a REST API that receives and manages the data produced by
race timing systems. The initial development is made for the [MYLAPS](https://www.mylaps.com/)
timing system, but the goal is to support other timing systems available on the market.

## Development

### Technologies

**EnduranceTrio Timing Exporter** is a [Spring Boot](https://spring.io/projects/spring-boot)
application and uses a [MySQL](https://www.mysql.com/) database.

### Installation

#### Clone the repository

Make sure that you have [Java 11](https://javaalmanac.io/jdk/11/) and
[MySQL](https://www.mysql.com/) installed in your development machine and then clone this repository
with the following command:

    git clone git@github.com:EnduranceCode/endurancetrio-timing-exporter.git

#### Set up the database

#### Set the MySQL Server Time Zone to UTC+00:00

[Set MySQL server time zone](https://www.scaler.com/topics/mysql-time-zone/) to UTC+00:00 in your
server configuration `my.cnf` file. On Ubuntu, open the `/etc/mysql/my.cnf` file executing the
following command:

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

    CREATE USER '{USERNAME}'@'localhost' IDENTIFIED WITH caching_sha2_password BY '{PASSWORD}';

> **Labels Definition**
>
> + **{USERNAME}** : The new account name in the MySQL Server;
> + **{PASSWORD}** : The password of the new account in the MySQL Server.

##### Grant privileges to the EnduranceTrio Timing Exporter MySQL user

To [grant the necessary privileges](https://www.mysqltutorial.org/mysql-grant.aspx) to the
**EnduranceTrio Timing Exporter** database user, replace the ***{LABELS}*** in the below command as
appropriate and then execute it.

    GRANT ALL PRIVILEGES ON {DATABASE_NAME}.* TO '{USERNAME}'@'localhost';

> **Labels Definition**
>
> + **{DATABASE_NAME}** : The database where the MySQL user will be granted privileges;
> + **{USERNAME}** : The account name in the MySQL Server to whom the privileges will be assigned.

##### Configure the application's database access

To configure the **EnduranceTrio Timing Exporter**  access to the database, take the following
steps:

1. Copy the file [`confidential-template.yaml`](./src/main/resources/confidential-template.yaml)
   located in the `resources` folder and rename it to `confidential.yaml`;
2. Replace the {DATABASE_NAME} placeholder with the **EnduranceTrio Timing Exporter** database name;
3. Replace the {DATABASE_USER} placeholder with the **EnduranceTrio Timing Exporter** database
   user's username;
4. Replace the {DATABASE_NAME} placeholder with the **EnduranceTrio Timing Exporter**database user's
   password;
5. Delete the comments block, at the top of the file, that contains these instructions.

##### Manage the application's database migrations

The file [`DATABASE.md`](./src/main/resources/db/DATABASE.md), stored in the folder
[`src/main/resources/db/`](./src/main/resources/db), documents the process to manage
the **EnduranceTrio Timing Exporter** database migrations.

## Deployment

The following sections will walk you through the steps to deploy and run the application on an
Ubuntu server using Apache as a reverse proxy.

### Prerequisites

- [Java 11](https://javaalmanac.io/jdk/11/) or later installed and configured on the Ubuntu server;
- [Apache Server](https://httpd.apache.org/)
  [installed and configured](https://github.com/EnduranceCode/server-setup-guide/blob/master/02-apache-server-installation.md#21-install-apache)
  on the Ubuntu server;
- [MySQL Server](https://httpd.apache.org/)
  [installed and configured](https://github.com/EnduranceCode/server-setup-guide/blob/master/03-mysql-server-installation.md#3-mysql-server-installation)
  on the Ubuntu server;
- A domain or subdomain pointing to the server's IP address;
- (Optional) Let's Encrypt SSL certificate for secure connections;

### Server setup

#### Database setup

To create the **EnduranceTrio Timing Exporter** database and user, replace the ***{LABELS}*** in the
upcoming commands as appropriate and then execute it on the Ubuntu server.

    CREATE DATABASE {DATABASE_NAME} CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
    CREATE USER '{USERNAME}'@'localhost' IDENTIFIED WITH caching_sha2_password BY '{PASSWORD}';
    GRANT ALL PRIVILEGES ON {DATABASE_NAME}.* TO '{USERNAME}'@'localhost';

> **Label Definition**
>
> + **{DATABASE_NAME}** : The database name defined in the `confidential.yaml` file;
> + **{USERNAME}** : The database user's username defined in the `confidential.yaml` file;
> + **{PASSWORD}** : The database user's password defined in the `confidential.yaml` file.

#### Configure the access of MYLAPS Timing & Scoring Software app to the database

The **EnduranceTrio Timing Exporter** application is able to manage the data produced
by [MYLAPS](https://www.mylaps.com/) devices, but it can only access this data when
the **EnduranceTrio Timing Exporter** database is added as an *exporter* of
the [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/) application. With
this procedure, the [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/)
application will be able to write the data, collected from the [MYLAPS](https://www.mylaps.com/)
decoders, into the **EnduranceTrio Timing Exporter** database.

To follow the [least privilege](https://en.wikipedia.org/wiki/Principle_of_least_privilege)
principle, the [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/)
connection to the **EnduranceTrio Timing Exporter** database must be done with a user that only has
*write permissions* for the tables used by the
[Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/) application. Those
tables are the following:

+ Chips
+ Markers
+ NewMail
+ Results
+ Times

To create the [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/) user
on the **EnduranceTrio Timing Exporter** database, login into the [MySQL](https://www.mysql.com/)
server, replace the ***{LABELS}*** in the below commands as appropriate and then execute it.

    CREATE USER '{USERNAME}'@'{HOST}' IDENTIFIED WITH caching_sha2_password BY '{PASSWORD}';
    GRANT INSERT ON {DATABASE_NAME}.Chip TO '{USERNAME}'@'{HOST}';
    GRANT INSERT ON {DATABASE_NAME}.Markers TO '{USERNAME}'@'{HOST}';
    GRANT INSERT ON {DATABASE_NAME}.NewMail TO '{USERNAME}'@'{HOST}';
    GRANT INSERT ON {DATABASE_NAME}.Results TO '{USERNAME}'@'{HOST}';
    GRANT INSERT ON {DATABASE_NAME}.Times TO '{USERNAME}'@'{HOST}';

> **Label Definition**
>
> + **{USERNAME}** : The user account name to be used by the MYLAPS
    [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/) application to
    store its data on the **EnduranceTrio Timing Exporter** database;
> + **{HOST}** : The host used by the MYLAPS
    [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/) application to
    connect to the **EnduranceTrio Timing Exporter** database, e.g. `localhost` or `%`;
> + **{PASSWORD}** : The password of the
    [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/) application account
    on the **EnduranceTrio Timing Exporter** database;
> + **{DATABASE_NAME}** : The **EnduranceTrio Timing Exporter** database name, as defined in the
    `confidential.yaml` file;
>
> Whenever possible,
> the [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/)
> connection to the **EnduranceTrio Timing Exporter** database should be done using
> [`SSH`](https://en.wikipedia.org/wiki/Secure_Shell). When this is possible, the **{HOST}**
> ***{LABEL}*** should be replaced with `localhost`. When the
> [`SSH`](https://en.wikipedia.org/wiki/Secure_Shell) connection is not possible, the **{HOST}**
> ***{LABEL}*** must be replaced with `%` to provide access from all IPs.

#### Create a user on the server to execute EnduranceTrio Timing Exporter

It's a good practice to create a dedicated user to run the **EnduranceTrio Timing Exporter**
application. This enhances security by isolating the application from the system and other user
processes.

To create this new user, replace the ***{LABELS}*** in the upcoming command as appropriate and then
execute it.

    sudo adduser {APP_USER}

Add the user's password and the additional required information as prompted.

> **Label Definition**
>
> + **{APP_USER}** : The Linux user that wil execute the **EnduranceTrio Timing Exporter** app on
    the Ubuntu server, e.g. `timing-exporter`;

#### Create a folder to store the EnduranceTrio Timing Exporter JAR file

There isn't a strict rule on the "best" folder to store an application's JAR file on an Ubuntu
server, but the `/opt` folder is often used for installing third-party software or packages.

To create a folder to store the **EnduranceTrio Timing Exporter** JAR file on the Ubuntu server
and set the correct Linux owner and group, replace the ***{LABELS}*** in the upcoming command as
appropriate and then execute it.

    sudo mkdir /opt/timing-exporter
    sudo chown {APP_USER}:{APP_USER} -R /opt/timing-exporter/

> **Label Definition**
>
> + **{APP_USER}** : The Linux user that wil execute the **EnduranceTrio Timing Exporter** app on
    the Ubuntu server, e.g. `timing-exporter`;

### Build and installation

#### Build the EnduranceTrio Timing Exporter JAR file

To build the JAR file, execute, from the root folder of this repository, the following command:

    ./mvnw clean install

#### Copy the EnduranceTrio Timing Exporter JAR file to your Ubuntu server

Copy the JAR file, generated on the previous step, to the Ubuntu server. Use SCP to copy the
file. Replace the ***{LABELS}*** in the upcoming command as appropriate and then execute it from the
root folder of this repository.

    scp target/timing-exporter*.jar {REMOTE_USERNAME}@{SERVER_IP}:~/timing-exporter.jar

> **Label Definition**
>
> + **{REMOTE_USERNAME}** : The Linux user on the Ubuntu server;
> + **{SERVER_IP}** : The Ubuntu server IP address;

On the Ubuntu server, replace the ***{LABELS}*** in the upcoming command as appropriate and
then execute it to copy the **EnduranceTrio Timing Exporter** JAR file to the previously created
folder.

    sudo mv ~/timing-exporter.jar /opt/timing-exporter/
    sudo chown {APP_USER}:{APP_USER} -R /opt/timing-exporter/
    sudo chmod 500 /opt/timing-exporter/timing-exporter.jar

> **Label Definition**
>
> + **{APP_USER}** : The Linux user that wil execute the **EnduranceTrio Timing Exporter** app on
    the Ubuntu server, e.g. `timing-exporter`;

#### systemd setup

We will run the **EnduranceTrio Timing Exporter** application as a *systemd* service on the Ubunt
server. This will make the application more reliable, due to the additional resilience to failures
of our setup.

To create a *systemd* service file (`timing-exporter.service`) in the folder `/etc/systemd/system/`
of the Ubuntu server, use the following command:

    sudo nano /etc/systemd/system/timing-exporter.service

Replace the ***{LABELS}*** in the upcoming snippet as appropriate and then paste it on the
previously created file (`timing-exporter.service`).

    [Unit]
    Description=EnduranceTrio Timing Exporter
    After=syslog.target
    
    [Service]
    User=timing-exporter
    ExecStart=java -jar /opt/timing-exporter/timing-exporter.jar
    SuccessExitStatus=143
    Restart=always
    RestartSec=5
    
    [Install]
    WantedBy=multi-user.target

> **Label Definition**
>
> + **{APP_USER}** : The Linux user that will execute the **EnduranceTrio Timing Exporter** app on
    your **Ubuntu** server, e.g. `timing-exporter`;

Save the changes with the command `CTRL + O` and then exit the nano text editor with
the command `CTRL + X`.

Apply the changes and start the new service using the following commands:

    sudo systemctl daemon-reload
    sudo systemctl start timing-exporter
    sudo systemctl enable timing-exporter

To confirm that new service is running properly, check the output of the following command:

    sudo systemctl status timing-exporter.service

#### Configure Apache Reverse Proxy

To configure Apache as a reverse proxy to redirect requests to the **EnduranceTrio Timing Exporter**
application, you need to enable the modules `proxy` and `mod_proxy` with the following commands:

    sudo a2enmod proxy
    sudo a2enmod proxy_http
    sudo systemctl restart apache2

Assuming that you already have a
[Virtual Host configured](https://github.com/EnduranceCode/server-setup-guide/blob/master/05-server-management.md#51-apache--create-a-virtual-host)
on the Ubuntu server, open the Virtual Host configuration file and add the bellow code snippet
after the `DocumentRoot` directive.

        ProxyPreserveHost On                                                                                                                                                                 
        ProxyPass /timing-exporter http://localhost:8081/                                                                                                                                    
        ProxyPassReverse /timing-exporter http://localhost:8081/

Save the changes with the command `CTRL + O` and the exit then nano text editor with
the command `CTRL + X`.

Validate the Apache Server configuration with the following command:

    sudo apachectl configtest

To activate the new Virtual Host configuration, replace the ***{LABEL}***  in the below commands as
appropriate and then execute it.

    sudo a2dissite {VIRTUAL_HOST_CONFIG_FILE}
    sudo a2ensite {VIRTUAL_HOST_CONFIG_FILE}
    sudo systemctl reload apache2

> **Label Definition**
>
> + **{VIRTUAL_HOST_CONFIG_FILE}** : The Virtual Host configuration file;

To add a [Let's Encrypt](https://letsencrypt.org/) certificate to your Apache configuration, you can
[use Certbot](https://github.com/EnduranceCode/server-setup-guide/blob/master/05-server-management.md#52-apache--secure-apache-with-lets-encrypt),
which is a tool for automatically obtaining and renewing SSL certificates from Let's Encrypt.

## License

**EnduranceTrio Timing Exporter** is licensed under the terms of [MIT License](./LICENSE).

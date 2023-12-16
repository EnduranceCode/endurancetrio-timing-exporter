# EnduranceTrio Timing Exporter Database

This file documents the process to manage the database used by the **EnduranceTrio Timing Exporter**
REST API.

## Database migration

The [database migrations](https://en.wikipedia.org/wiki/Schema_migration) are made using
[Flyway](https://www.red-gate.com/products/flyway/).
The [DDL](https://en.wikipedia.org/wiki/Data_definition_language) migration scripts are stored
in the folder [`migration/ddl`](./migration/ddl) and the
[DML](https://en.wikipedia.org/wiki/Data_manipulation_language) migration scripts are stored
in the folder [`migration/dml`](./migration/dml).

The [migration scripts](https://documentation.red-gate.com/flyway/quickstart-how-flyway-works/why-database-migrations)
must respect the following naming convention (which is compatible with the
[Flyway naming patterns](https://www.red-gate.com/blog/database-devops/flyway-naming-patterns-matter)):

    Vxxx.yyy.zzz.nnn__free-description.sql

> ***xxx*** : Major version number at the time of the script creation
>
> ***yyy*** : Minor version number at the time of the script creation
>
> ***zzz*** : Patch version number at the time of the script creation
>
> ***nnn*** : Order number for version number at the time of the script creation
>
> ***free_description*** : A short free description of the scripts actions with words separated with
> dashes 

## MYLAPS Timing & Scoring Software

The **EnduranceTrio Timing Exporter** REST API is able to manage the data produced
by [MYLAPS](https://www.mylaps.com/) devices, but it can only access this data when
the **EnduranceTrio Timing Exporter** database is added as an *exporter* of
the [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/) application. With
this procedure, the [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/)
application will be able to write the data, it collects from the [MYLAPS](https://www.mylaps.com/)
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
server and replace the ***{LABELS}*** in the below commands as appropriate and then execute it.

    CREATE USER '{USERNAME}'@'{HOST}' IDENTIFIED WITH caching_sha2_password BY '{PASSWORD}';
    GRANT INSERT ON {DATABASE_NAME}.Chip TO '{USERNAME}'@'{HOST}';
    GRANT INSERT ON {DATABASE_NAME}.Markers TO '{USERNAME}'@'{HOST}';
    GRANT INSERT ON {DATABASE_NAME}.NewMail TO '{USERNAME}'@'{HOST}';
    GRANT INSERT ON {DATABASE_NAME}.Results TO '{USERNAME}'@'{HOST}';
    GRANT INSERT ON {DATABASE_NAME}.Times TO '{USERNAME}'@'{HOST}';

> **Label Definition**
>
> + **{USERNAME}** : The new account name in the MySQL Server;
> + **{HOST}** : The name of the host from which the user connects to the MySQL Server,
> e.g. `localhost` or `%`;
> + **{PASSWORD}** : The password of the new account in the MySQL Server.
> + **{DATABASE_NAME}** : The name chosen for the new database;
>
> Whenever possible, the [Timing & Scoring Software](https://www.mylaps.com/timing-scoring-software/)
> connection to the **EnduranceTrio Timing Exporter** database should be done using
> [`SSH`](https://en.wikipedia.org/wiki/Secure_Shell). When this is possible, the **{HOST}**
> ***{LABEL}*** should be replaced with `localhost`. When the
> [`SSH`](https://en.wikipedia.org/wiki/Secure_Shell) connection is not possible, the **{HOST}**
> ***{LABEL}*** must be replaced with `%` to provide access from all IPs.

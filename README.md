# stock-webscraper
Web scraper to collect stock indicators.


## To generate the database changelog for liquibase
```
mvn org.liquibase:liquibase-maven-plugin:generateChangeLog -Dliquibase.url=jdbc:postgresql://localhost/webscraper -Dliquibase.username=webscraper -Dliquibase.password=webscraper -Dliquibase.outputChangeLogFile=changeLog.postgresql.sql
```

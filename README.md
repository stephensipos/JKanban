# JKanban

Minimal Kanban board application using JavaFX.

To try:

```bash
git clone https://github.com/stephensipos/JKanban.git
cd JKanban
mvn compile
mvn exec:java
```
By default, it uses an in-memory H2 database. To switch to a persistent backend
 see [persistence.xml](src/main/resources/META-INF/persistence.xml), and supply the selected persistence unit from the command line:

```bash
mvn exec:java -Djkanban.persistenceUnit=jkanban-mariadb
```

[License: MIT](LICENSE.txt)

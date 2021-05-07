Create a .java file inside the main directory of the PetMePlus project called 
```DBConnection.java```. Then, copy the following code into ```DBConnection.java```.

```java
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public Connection dbLink;

    public Connection getConnection() {
        String dbName = "db_name_here";
        String dbUser = "root";
        String dbPassword = "db_password_here";
        String url = "jdbc:mysql://localhost/" + dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return dbLink;
    }
}
```
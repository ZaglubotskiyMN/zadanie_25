package part1.lessons25.task1;

import java.sql.*;

public class Main {

    static final String DB_url= "jdbc:postgresql://127.0.0.1:5432/MyProject";
    static final String login ="postgres";
    static final String pass = "hrnzpsods7753191";
    static  final String createtable = "CREATE TABLE history ("
        + "id SERIAL,"
        + "product VARCHAR(30) NOT NULL,"
        + "old_price NUMERIC(15,2) NOT NULL,"
        + "createby VARCHAR(20) NOT NULL,"
        + "PRIMARY KEY (id)"
        + ");";

    static final String droptable = "DROP TABLE history";
    static final String addsql ="INSERT INTO history"+"(product,old_price,createby)"+ "VALUES" + "('Электротовары','1500','java');";



    public static void main(String[] args) {

        System.out.println("Testing PostgreSQL:");
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e)
        {
            System.out.println("Driver error");
            e.printStackTrace();
            return;
        }
        System.out.println("Driver -OK!");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_url,login,pass);
        }catch (SQLException e)
        {
            System.out.println("Connection failed");
            e.printStackTrace();
            return;
        }
        if (conn != null)
        {
            System.out.println("Connect - Ok!");
        } else {
            System.out.println("Not connection");
        }
        Statement statement = null;
        System.out.println("Add TABLE");
        try {
            statement= conn.createStatement();
            statement.execute(createtable);
        } catch (SQLException e) {
            System.out.println("Table not created");
            e.printStackTrace();
        }
        System.out.println("TABLE is created");
        System.out.println("add history");
        try {
            statement=conn.createStatement();
            statement.execute(addsql);
            System.out.println("add history -Ok!");
        } catch (SQLException e) {
            System.out.println("Values not add");
            e.printStackTrace();
        }
        /*System.out.println("DROP Table");
        try {
            statement= conn.createStatement();
            statement.execute(droptable);
            System.out.println("DROP Table - Ok!");
        } catch (SQLException e) {
            System.out.println("DROP TABLE Error!");
            e.printStackTrace();
        }*/
        finally {
            try {
                if (statement != null)
                statement.close();
            } catch (SQLException e) {
                System.out.println("Stat not closed");
                e.printStackTrace();
            }
        }










        try {
            if(conn != null)
            {
                conn.close();
            }
        }catch (SQLException e)
        {
         System.out.println("Unable to close the connection");
         e.printStackTrace();
         return;
        }






    }
}

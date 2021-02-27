package part1.lessons25.task1;

import javax.naming.spi.DirStateFactory;
import java.sql.*;

public class Selectall {

    static final String DB_url= "jdbc:postgresql://127.0.0.1:5432/MyProject";
    static final String login ="postgres";
    static final String pass = "hrnzpsods7753191";

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        Statement statement = null;
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

        statement=conn.createStatement();
        String sql ="SELECT * FROM history";
        Boolean ok=statement.execute(sql);
        System.out.println("Успешен запрос"+ok);
        System.out.println("Выводим на экран");
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next())
        {
            int id = resultSet.getInt("id");
            String product = resultSet.getString("product");
            double old_price = resultSet.getDouble("old_price");
            String createby = resultSet.getString("createby");

            System.out.println("id:"+" "+id);
            System.out.println("product:"+" "+product);
            System.out.println("old_price:"+" "+old_price);
            System.out.println("createby:"+" "+createby);
            System.out.println("\n---------------");

        }


        try {
            resultSet.close();
            statement.close();
            conn.close();
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

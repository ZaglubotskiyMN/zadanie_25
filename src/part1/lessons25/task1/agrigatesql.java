package part1.lessons25.task1;

import java.sql.*;

public class agrigatesql {
    static final String DB_url = "jdbc:postgresql://127.0.0.1:5432/MyProject";
    static final String login = "postgres";
    static final String pass = "hrnzpsods7753191";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        System.out.println("Testing PostgreSQL:");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver error");
            e.printStackTrace();
            return;
        }
        System.out.println("Driver -OK!");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_url, login, pass);
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return;
        }
        if (conn != null) {
            System.out.println("Connect - Ok!");
        } else {
            System.out.println("Not connection");
        }

        statement=conn.createStatement();
        String sql ="SELECT MAX(count) FROM purchase_item";
        Boolean ok=statement.execute(sql);
        System.out.println("Успешен запрос:"+" "+ok);
        System.out.println("Выводим на экран");
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next())
        {
            int max = resultSet.getInt("max");
            System.out.println("max:"+" "+max);
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

package part1.lessons25.task1;

import java.sql.*;

public class wheresql {

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
        String sql ="SELECT * from city \n" +
                "WHERE name = 'Москва'";
        Boolean ok=statement.execute(sql);
        System.out.println("Успешен запрос:"+" "+ok);
        System.out.println("Выводим на экран");
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next())
        {
            int city_id = resultSet.getInt("city_id");
            String name = resultSet.getString("name");
            int timezone_id = resultSet.getInt("timezone_id");
            System.out.println("city_id:"+" "+city_id);
            System.out.println("name:"+" "+name);
            System.out.println("timezone_id:"+" "+timezone_id);
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

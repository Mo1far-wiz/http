package Database;

import java.sql.*;
import java.util.ArrayList;

public class LoginDatabase {
    private Connection con;
    private final String name = "Connections";

    public void initialization() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + name);
            PreparedStatement st = con.prepareStatement(
                    "CREATE TABLE if NOT EXISTS 'Connection' (" +
                            "'login' VARCHAR(20) PRIMARY KEY UNIQUE, " +
                            "'password' VARCHAR(50) NOT NULL, " +
                            "'host' VARCHAR(20) NOT NULL, " +
                            "'port' INTEGER NOT NULL)");

            st.executeUpdate();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC not found");
            e.printStackTrace();
            System.exit(0);

        } catch (SQLException e) {
            System.out.println("Invalid SQL");
            e.printStackTrace();
        }
    }

    public void insertUser(String login, String password, String host, int port) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO Connection(login, password, host, port) " +
                        "VALUES (?, ?, ?, ?)");

        statement.setString(1, login);
        statement.setString(2, password);
        statement.setString(3, host);
        statement.setInt(4, port);

        statement.executeUpdate();
        statement.close();
    }

    public ArrayList<Connections> getUserList() throws SQLException {
        PreparedStatement statement = con.prepareStatement("SELECT * FROM Connection");
        ResultSet res = statement.executeQuery();

        ArrayList<Connections> arrayList = new ArrayList<>();

        while (res.next()) {
            arrayList.add(new Connections(
                    res.getString("login"),
                    res.getString("host"),
                    res.getInt("port")
            ));
        }
        res.close();
        statement.close();
        return arrayList;
    }
    public String getPassword(String login) throws SQLException {
        PreparedStatement statement = con.prepareStatement("SELECT * FROM Connection WHERE login = ?");
        statement.setString(1, login);
        ResultSet res = statement.executeQuery();

        String password = res.getString("password");

        res.close();
        statement.close();
        return password;
    }
}
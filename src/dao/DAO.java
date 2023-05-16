package dao;
import connectionMySQL.ConnectionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class DAO {
    public void create(String value1, String value2) {
        Connection connection = ConnectionMySQL.init_connection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("INSERT INTO table VALUES (?,?)");
            stmt.setString(1, value1);
            stmt.setString(2, value2);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionMySQL.stopConnection(connection, stmt);
        }
    }
    public ArrayList<String> read() {
        Connection connection = ConnectionMySQL.init_connection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<String> listReturnSelect = new ArrayList<String>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM table");
            rs = stmt.executeQuery();

            while (rs.next()) {
                listReturnSelect.add(rs.getString("ColumnLabel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionMySQL.stopConnection(connection, stmt, rs);
        }

        return listReturnSelect;
    }

    public void update(String columnValue,int whereValue) {
        Connection connection = ConnectionMySQL.init_connection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("UPDATE table SET columnLabel = ?  WHERE value = ?");
            stmt.setString(1, columnValue);
            stmt.setInt(2, whereValue);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionMySQL.stopConnection(connection, stmt);
        }
    }

    public void delete(int whereValue) {
        Connection connection = ConnectionMySQL.init_connection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM table WHERE value = ?");
            stmt.setInt(1, whereValue);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionMySQL.stopConnection(connection, stmt);
        }
    }
}

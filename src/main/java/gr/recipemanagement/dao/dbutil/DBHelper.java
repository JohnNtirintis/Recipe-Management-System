package gr.recipemanagement.dao.dbutil;

import gr.recipemanagement.service.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ntirintis John
 */
public class DBHelper {

    public DBHelper() {}

    public static void eraseData() throws SQLException {
        Connection connection = null;
        ResultSet rs = null;

        try {
          connection = DBUtil.getConnection();
          connection.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

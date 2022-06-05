import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC 原生操作 PrepareStatement 手动提交事务 批处理
 */
public class Main2 {

  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/test",
              "root",
              "123456"
      );
      conn.setAutoCommit(false);
      stmt = conn.prepareStatement("update student set name=? where id = ?");
      stmt.setString(1, "xiaoming");
      stmt.setString(2, "2001");
      stmt.addBatch();
      stmt.setString(1, "xiaohong");
      stmt.setString(2, "2002");
      stmt.addBatch();
      stmt.executeBatch();
      stmt.clearBatch();
      conn.commit();
    } catch (ClassNotFoundException | SQLException e) {
      try {
        conn.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      e.printStackTrace();
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

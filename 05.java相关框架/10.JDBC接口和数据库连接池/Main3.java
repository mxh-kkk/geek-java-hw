import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用 Hikari
 */
public class Main3 {
  public static void main(String[] args) {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDataSourceJNDI("com.mysql.jdbc.Driver");
    hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("123456");
    HikariPool hikariPool = new HikariPool(hikariConfig);
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = hikariPool.getConnection();
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

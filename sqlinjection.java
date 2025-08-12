// File: src/main/java/example/InsecureLogin.java
package example;

import java.sql.*;
import javax.servlet.http.*;

public class InsecureLogin extends HttpServlet {
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    String user = req.getParameter("user");
    String pass = req.getParameter("pass");

    // VULNERABLE: user-controlled input concatenated into SQL
    String sql = "SELECT * FROM users WHERE username='" + user + "' AND password='" + pass + "'";
    try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost/test","root","root");
         Statement st = c.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
      // ...
    } catch (SQLException e) { e.printStackTrace(); }
  }
}

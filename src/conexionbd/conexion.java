package conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    public static Connection getConexion() {
        Connection con = null;
        try {
            // 🚨 Aquí usas los datos de tu Railway o Workbench
            String url = "jdbc:mysql://root:JTuKQjVAmaHRdZVsVwrwiggkoVwYpIql@switchyard.proxy.rlwy.net:51254/railway";
            String user = "root";
            String password = "JTuKQjVAmaHRdZVsVwrwiggkoVwYpIql";

            con = DriverManager.getConnection(url + "?useSSL=false&serverTimezone=UTC", user, password);
            System.out.println("✅ Conectado a la base de datos!");
        } catch (Exception e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
        return con;
    }
}
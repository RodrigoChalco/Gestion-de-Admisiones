package servicios;
import java.sql.*;

public class GestorPostulantes {

    private final NotificadorPostulante notificador = new NotificadorPostulante();

    public void actualizarEstado(int idPostulante, String nuevoEstado) {
        String url = "jdbc:mysql://localhost:3306/tu_basedatos";
        String user = "root";      // 👉 tu usuario MySQL
        String password = "";      // 👉 tu contraseña MySQL

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            
            // 1️⃣ Obtener correo
            String correo = null;
            PreparedStatement ps = conn.prepareStatement(
                "SELECT correo FROM postulantes WHERE id = ?");
            ps.setInt(1, idPostulante);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                correo = rs.getString("correo");
            }

            // 2️⃣ Actualizar estado en la BD
            ps = conn.prepareStatement(
                "UPDATE postulantes SET estado = ? WHERE id = ?");
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idPostulante);
            ps.executeUpdate();

            // 3️⃣ Notificar
            if (correo != null) {
                notificador.notificar(correo, nuevoEstado);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error BD: " + e.getMessage());
        }
    }
}

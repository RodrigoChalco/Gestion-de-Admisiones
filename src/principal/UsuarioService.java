/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;
import java.sql.*;

/**
 *
 * @author Home
 */
public class UsuarioService {
    
    private Connection conn;

    public UsuarioService(Connection conn) {
        this.conn = conn;
    }
    
    private int obtenerIdUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT id FROM usuarios WHERE nombre_usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1;
    }
    public boolean cambiarContraseña(String nombreUsuario, String passwordActual, String nuevaPassword) {
        
        try {
  
            int idUsuario = obtenerIdUsuario(nombreUsuario);
            
            if (idUsuario == -1) {
                System.out.println("Error: El usuario no existe.");
                return false;
            }
            
            String sqlVerificacion = "SELECT COUNT(*) FROM usuarios WHERE id = ? AND password = ?";
            try (PreparedStatement stmtVerificacion = conn.prepareStatement(sqlVerificacion)) {
                stmtVerificacion.setInt(1, idUsuario);
                stmtVerificacion.setString(2, passwordActual); 
                
                try (ResultSet rs = stmtVerificacion.executeQuery()) {
                    rs.next();
                    // Si el conteo es 0, la contraseña actual es incorrecta.
                    if (rs.getInt(1) == 0) {
                        System.out.println("Error: Contraseña actual incorrecta.");
                        return false;
                    }
                }
            }
            
            // 3. Actualizar la nueva contraseña
            String sqlUpdate = "UPDATE usuarios SET password = ? WHERE id = ?";
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                stmtUpdate.setString(1, nuevaPassword);
                stmtUpdate.setInt(2, idUsuario);
                
                int filas = stmtUpdate.executeUpdate();
                return filas > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}

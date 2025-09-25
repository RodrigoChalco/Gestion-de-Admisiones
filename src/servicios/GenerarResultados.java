package servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexionbd.conexion; 

public class GenerarResultados {

    public void notificarResultadosFinales() {
        System.out.println("Iniciando el proceso de notificación de resultados...");
        
       
        NotificadorPostulante notificador = new NotificadorPostulante();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            con = conexion.getConnection(); 
            String sql = "SELECT correo, estado FROM postulantes WHERE estado = 'admitido' OR estado = 'no admitido'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String correoPostulante = rs.getString("correo");
                String estadoFinal = rs.getString("estado");
                
                
                if ("admitido".equals(estadoFinal)) {
                    notificador.notificar(correoPostulante, "aprobado"); 
                } else if ("no admitido".equals(estadoFinal)) {
                    notificador.notificar(correoPostulante, "rechazado");
                }
            }
            
            System.out.println("✅ Notificaciones enviadas a todos los postulantes.");
            
        } catch (SQLException e) {
            System.err.println("❌ Error en la base de datos: " + e.getMessage());
        } finally {
            
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
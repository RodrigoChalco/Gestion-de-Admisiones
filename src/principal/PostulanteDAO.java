/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import conexionbd.conexionbd;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostulanteDAO{

    public Postulante getPostulanteById(int id) {
        String sql = "SELECT id_postulante, nombre, ci, correo, carrera_postulada FROM postulantes WHERE id_postulante = ?";
        try (Connection c = conexionbd.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Postulante(
                    rs.getInt("id_postulante"),
                    rs.getString("nombre"),
                    rs.getString("ci"),
                    rs.getString("correo"),
                    rs.getString("carrera_postulada")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Documento> getDocumentosByPostulante(int id) {
        List<Documento> docs = new ArrayList<>();
        String sql = "SELECT nombre_documento, estado FROM documentos WHERE id_postulante = ?";
        try (Connection c = conexionbd.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                docs.add(new Documento(rs.getString("nombre_documento"), rs.getString("estado")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return docs;
    }

    public String getUltimoResultado(int id) {
        String sql = "SELECT estado FROM resultados WHERE id_postulante = ? ORDER BY fecha_publicacion DESC LIMIT 1";
        try (Connection c = conexionbd.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("estado");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "pendiente";
    }

    // Consulta extra: progreso de documentos (total/completados)
    public int[] getProgresoDocumentos(int id) {
        String sql = "SELECT COUNT(*) AS total, SUM(CASE WHEN estado<>'pendiente' THEN 1 ELSE 0 END) AS completados FROM documentos WHERE id_postulante = ?";
        int[] res = new int[]{0,0};
        try (Connection c = conexionbd.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                res[0] = rs.getInt("total");
                res[1] = rs.getInt("completados");
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return res;
    }
}

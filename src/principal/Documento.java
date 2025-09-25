/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

/**
 *
 * @author Pc
 */
public class Documento {
    private String nombreDocumento;
    private String estado;
    public Documento(String nombreDocumento, String estado) {
        this.nombreDocumento = nombreDocumento; this.estado = estado;
    }
    public String getNombreDocumento(){return nombreDocumento;}
    public String getEstado(){return estado;}
}
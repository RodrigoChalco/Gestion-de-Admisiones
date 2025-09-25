/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

/**
 *
 * @author Pc
 */
public class Postulante {
    private int id;
    private String nombre, ci, correo, carrera;
    public Postulante(int id, String nombre, String ci, String correo, String carrera) {
        this.id = id; this.nombre = nombre; this.ci = ci; this.correo = correo; this.carrera = carrera;
    }
    // getters
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getCi(){return ci;}
    public String getCorreo(){return correo;}
    public String getCarrera(){return carrera;}
}
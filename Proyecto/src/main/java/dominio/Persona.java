
package dominio;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author jonat
 */
public abstract class Persona implements Serializable{
    private int idPersona;
    private String nombre, apellidos, pais;
    private Date fechaNac;
    
    //Constructores

    public Persona(int idPersona, String nombre, String apellidos, String pais, Date fechaNac) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pais = pais;
        this.fechaNac = fechaNac;
    }

    public Persona(String nombre, String apellidos, String pais, Date fechaNac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pais = pais;
        this.fechaNac = fechaNac;
    }
    
    

    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Persona() {
        
    }
    
    //Get y Set

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
    
    //Hash and equals

    

    @Override
    public String toString() {
        return "\nID Persona:" + idPersona + "\nNombre: " + nombre + " " + apellidos + "\nPais: " + pais + "\nFecha de nacimiento: " + fechaNac;
    }
}


package dominio;

import java.io.Serializable;
import java.util.Date;

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
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idPersona;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        return this.idPersona == other.idPersona;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellidos=" + apellidos + ", pais=" + pais + ", fechaNac=" + fechaNac + '}';
    }
    
    
    
}

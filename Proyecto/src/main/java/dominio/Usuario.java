
package dominio;


import java.io.Serializable;
import java.util.*;

/**
 *
 * @author jonat
 */
public class Usuario extends Persona implements Serializable{
    private int idUsuario;
    private String nomUsu, clave, email;
    private boolean estado;
    
    //Constructores
    public Usuario(int idUsuario, String nomUsu, String clave, String email, int idPersona, String nombre, String apellidos, String pais, Date fechaNac) {
        super(idPersona, nombre, apellidos, pais, fechaNac);
        this.idUsuario = idUsuario;
        this.nomUsu = nomUsu;
        this.clave = clave;
        this.email = email;
    }

    public Usuario(int idUsuario, String nomUsu, String clave, String email) {
        this.idUsuario = idUsuario;
        this.nomUsu = nomUsu;
        this.clave = clave;
        this.email = email;
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(int idPersona, String nombre, String apellidos, String pais, Date fechaNac) {
        super(idPersona, nombre, apellidos, pais, fechaNac);
    }

    public Usuario(boolean estado) {
        this.estado = estado;
    }

    public Usuario() {
    }
    
    
    
    //Get y Set
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    //Hash and equals
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.email);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.email, other.email);
    }
    
    //toString

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nomUsu=" + nomUsu + ", clave=" + clave + ", email=" + email + '}';
    }
}

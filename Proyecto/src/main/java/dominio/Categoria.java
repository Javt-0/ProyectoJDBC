
package dominio;

import java.io.Serializable;

/**
 *
 * @author jonat
 */
public class Categoria implements Serializable {
    private int idCategoria;
    private String nombre;
    
    //Constructores
    
    public Categoria(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }
    
    public Categoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria() {
        
    }
   
    //Get y Set

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Hash and equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idCategoria;
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
        final Categoria other = (Categoria) obj;
        return this.idCategoria == other.idCategoria;
    }
    
    
    //toString

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + '}';
    }
    
    
    
}

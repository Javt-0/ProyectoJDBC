
package dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jonat
 */
public class Videojuego implements Serializable {
    private int idVideojuego, stock;
    private String nombre, descripcion;
    private double precio;
    
    //Constructores
    public Videojuego(int idVideojuego, int stock, String nombre, String descripcion, double precio) {
        this.idVideojuego = idVideojuego;
        this.stock = stock;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public Videojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }
    
    public Videojuego() {
        
    }
    //Get y Set
    
    public int getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //Hash and equals

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.nombre);
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
        final Videojuego other = (Videojuego) obj;
        return Objects.equals(this.nombre, other.nombre);
    }
    

    //toString

    @Override
    public String toString() {
        return "Videojuego{" + "idVideojuego=" + idVideojuego + ", stock=" + stock + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }
    
  
}

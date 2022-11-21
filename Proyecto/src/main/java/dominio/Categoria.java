
package dominio;

import datos.CategoriaDaoMySQL;
import datos.IAccesoDatosCategoria;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public Categoria(String nombre) {
        this.nombre = nombre;
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
        hash = 47 * hash + Objects.hashCode(this.nombre);
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
        return Objects.equals(this.nombre, other.nombre);
    }

    //toString

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + '}';
    }
    
    static Scanner teclado = new Scanner(System.in);
    
    public void nuevaCategoria(){
        IAccesoDatosCategoria cDAO = new CategoriaDaoMySQL();
        
        ArrayList<Categoria> categorias = new ArrayList<>();
        
        try {
            categorias.addAll(cDAO.READ());
        } catch (SQLException ex) {
            System.out.println("No se pudo cargar los datos");
        }
        
        System.out.println("Ingresa el nombre de la nueva categoria: ");
        String nombreEntrada = teclado.nextLine().toUpperCase();
        Categoria c = new Categoria(nombreEntrada);

        while(categorias.contains(c) == true){
            System.out.println("La categoria ya esta dentro del sistema, ingrese el nombre de nuevo porfavor: ");
            nombreEntrada = teclado.nextLine().toUpperCase();
            c = new Categoria(nombreEntrada);
        }
        
        System.out.println("Los datos de la nueva categoria son: \n" + c);
        System.out.println("¿Deseas incluir este Categoria al sistema? (S/N): ");
        String elegir = teclado.nextLine();

        if(elegir.toUpperCase().compareToIgnoreCase("S") == 0){
            cDAO.CREATE(c);
            System.out.println("Se añadio con exito");
        }else{
            System.out.println("No se añadio la nueva categoria.\n");
        }
    }
    
}

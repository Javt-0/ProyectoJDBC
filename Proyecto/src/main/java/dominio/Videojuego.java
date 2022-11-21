
package dominio;

import datos.IAccesoDatosVideojuegos;
import datos.VideojuegoDaoMySQL;
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

    public Videojuego(int stock, String nombre, String descripcion, double precio) {
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

    public Videojuego(String nombre) {
        this.nombre = nombre;
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
        return "\n\nID Videojuego: " + idVideojuego + "\nStock: " + stock + "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nPrecio: " + precio;
    }
    
    
    
    static Scanner teclado = new Scanner(System.in);
    
    public void eliminarVideojuego(){
        IAccesoDatosVideojuegos vDAO = new VideojuegoDaoMySQL();
        
        ArrayList<Videojuego> videojuegos = new ArrayList<>();
        try {
            videojuegos.addAll(vDAO.READ());
            System.out.println("Dame el nombre del videojuego que deseas eliminar: ");
            String nombreEntrada = teclado.nextLine();
            Videojuego v = new Videojuego(nombreEntrada);
            
            for(int i=0; i<videojuegos.size(); i++){
                if(videojuegos.get(i).getNombre().equalsIgnoreCase(nombreEntrada) == true){
                    System.out.println("Seguro quieres borrar el videojuego " + videojuegos.get(i).getNombre() + "?(Yes/No)");
                    String opcion = teclado.nextLine();
                    if(opcion.equalsIgnoreCase("Yes") == true){
                        vDAO.DELETE(v);
                    }else{
                        System.out.println("No se borro el videojuego");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Sin datos");
        }
    }
    
    public void añadirVideojuego(){
        IAccesoDatosVideojuegos vDAO = new VideojuegoDaoMySQL();
        
        ArrayList<Videojuego> videojuegos = new ArrayList<>();
        try {
            videojuegos.addAll(vDAO.READ());
            } catch (SQLException ex) {
            System.out.println("No se cargo la base de datos");
            return;
        }
            System.out.println("Dame el nombre del videojuego que deseas ingresar: ");
            String nombreEntrada = teclado.nextLine().toUpperCase();
            Videojuego v = new Videojuego(nombreEntrada);
            
            while(videojuegos.contains(v) == true){
                System.out.println("El videojuego ya esta dentro del sistema, ingrese el nombre de nuevo porfavor:");
                nombreEntrada = teclado.nextLine().toUpperCase();
                v = new Videojuego(nombreEntrada);
            }
            
            try{
                System.out.println("Ingresa el precio del videouego:");
                double precioEntrada = teclado.nextDouble();
                v.setPrecio(precioEntrada);
            }catch (Exception e) {
                System.out.println("ERROR.... Ingresa el precio con una coma no con un punto");
                return;
            }
            
            System.out.println("Ingresa una descripcion del videojuego");
            teclado.nextLine();
            String descripcionEntrada = teclado.nextLine();
            v.setDescripcion(descripcionEntrada);
            
            try{
                System.out.println("Ingresa el stock que hay del videojuego");
                int stockEntrada = teclado.nextInt();
                v.setStock(stockEntrada);
            }catch (Exception e) {
                System.out.println("ERROR.... Ingrese un numero");
                return;
            }
            
            System.out.println("Los datos del videojuego son: \n" + v);
            System.out.println("¿Deseas incluir este Videojuego al sistema? (S/N): ");
            teclado.nextLine();
            String elegir = teclado.nextLine();
            
            if(elegir.toUpperCase().compareToIgnoreCase("S") == 0){
                vDAO.CREATE(v);
                System.out.println("Se añadio con exito");
            }else{
                System.out.println("No se añadio el videojuego.\n");
            }
    }
    
    public void cambioStockVideojuego(){
        IAccesoDatosVideojuegos vDAO = new VideojuegoDaoMySQL();
        ArrayList<Videojuego> videojuegos = new ArrayList<>();
        
        try {
            videojuegos.addAll(vDAO.READ());
  
            System.out.println("Dame el nombre del videojuego que deseas cambiar stock: ");
            String nombreEntrada = teclado.nextLine();
            
            for(int i=0; i<videojuegos.size(); i++){
                if(videojuegos.get(i).getNombre().equalsIgnoreCase(nombreEntrada) == true){
                    System.out.println("Cual es el nuevo stock del videojuego: ");
                    int stockEntrada = teclado.nextInt();
                    videojuegos.get(i).setStock(stockEntrada);
                    vDAO.UPDATE(videojuegos.get(i));
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Sin datos");
        }
    }
    
    public void listarVideojuegos(){
        IAccesoDatosVideojuegos vDAO = new VideojuegoDaoMySQL();
        
        try {
            System.out.println(vDAO.READ());
        } catch (SQLException ex) {
            System.out.println("No se encontraron datos");
        }
    }
}

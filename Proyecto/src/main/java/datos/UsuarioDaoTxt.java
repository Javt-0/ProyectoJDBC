/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import dominio.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat
 */
public class UsuarioDaoTxt implements IAccesoDatosUsuario{
    
    @Override
    public int CREATE(Usuario u){
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new FileWriter("Usuarios.txt", true));
            salida.print(u.getIdUsuario() + "-");
            salida.print(u.getNombre() + "-");
            salida.print(u.getApellidos() + "-");
            salida.print(u.getNomUsu() + "-");
            salida.print(u.getPais() + "-");
            salida.print(u.getIdPersona() + "-");
            salida.print(u.getEmail() + "-");
            salida.print(u.getFechaNac() + "-");
            salida.print(u.getClave() + "-");
            salida.print(u.isEstado() + "\n");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }finally{
            salida.close();
        }
        return 0;
    }
    
    @Override
    public List<Usuario> READ() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        
        BufferedReader entrada = null;
        //File archivo = new File("Usuarios.txt");
        try {
                entrada = new BufferedReader (new FileReader("Usuarios.txt"));
                String lectura = entrada.readLine();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy");
                while (lectura != null) {
                    String [] partes = lectura.split("-");
                    int IdUsuario = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String apellidos = partes[2];
                    String nomUsu = partes[3];
                    String pais = partes[4];
                    int idPersona = Integer.parseInt(partes[5]);
                    String email = partes[6];
                    String añoString = partes[7];
                    String mesString = partes[8];
                    String diaString = partes[9];
                    String clave = partes[10];
                    boolean estado = Boolean.parseBoolean(partes[11]);
                    
                    String fechaEntrada = (diaString + "/" + mesString + "/" + añoString);
                    LocalDate fechaLocalDate = LocalDate.parse(fechaEntrada, formato);
                    java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaLocalDate);
                    
                    usuarios.add(new Usuario(IdUsuario, nomUsu, clave, email, estado, idPersona, nombre, apellidos, pais, fechaSQL));
                    //Producto p = new Producto(nombrePro, precio);
                    //colecProductos.add(p);
                    lectura = entrada.readLine();
                }
                //System.out.println(colecProductos);
                entrada.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace(System.out);
            } catch (IOException ex){
                ex.printStackTrace(System.out);
            }
        
        
        return usuarios;
    }
    
    @Override
    public int UPDATE(Usuario u){
        PrintWriter salida = null;
        File archivo= new File("Usuarios.txt");
        
        try {
            salida = new PrintWriter(archivo);
            salida.print(u.getIdUsuario() + "-");
            salida.print(u.getNombre() + "-");
            salida.print(u.getApellidos() + "-");
            salida.print(u.getNomUsu() + "-");
            salida.print(u.getPais() + "-");
            salida.print(u.getIdPersona() + "-");
            salida.print(u.getEmail() + "-");
            salida.print(u.getFechaNac() + "-");
            salida.print(u.getClave() + "-");
            salida.print(u.isEstado() + "\n");
        } catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        } finally{
            salida.close();
        }
        return 0;
    }
    
    @Override
    public int DELETE(Usuario u){
        
        return 0;
    }
    
    @Override
    public List<Usuario> SEARCH_PAIS(String nombreBusqueda) throws SQLException{
        
        List<Usuario> usuarios = new ArrayList<>();
        return usuarios;
    }
}

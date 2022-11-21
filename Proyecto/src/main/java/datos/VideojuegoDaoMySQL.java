/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Usuario;
import dominio.Videojuego;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonat
 */
public class VideojuegoDaoMySQL implements IAccesoDatosVideojuegos{
    
    private static final String SQL_INSERT_VIDEOJUEGOS = "INSERT INTO videojuegos (nombre, precio, descripcion, stock)"
                                                        + "VALUES (?, ?, ?, ?)";
    
    private static final String SQL_SELECT_VIDEOJUEGOS = "SELECT * FROM videojuegos";
    
    private static final String SQL_UPDATE_VIDEOJUUEGOS = "UPDATE videojuegos SET nombre=?, precio=?, descripcion=?, stock=? WHERE idVideojuegos = ?";
    
    private static final String SQL_DELETE_VIDEOJUEGOS = "DELETE FROM videojuegos WHERE idVideojuegos = ?";
    
    @Override
    public int CREATE(Videojuego v){
        Connection conexVideo = null;
        PreparedStatement consultaVideojuego = null;
        
        int registrarVideojuego = 0;
        
        try {
            conexVideo = getConnection();
            consultaVideojuego = conexVideo.prepareStatement(SQL_INSERT_VIDEOJUEGOS);
            
            consultaVideojuego.setString(1 , v.getNombre());
            consultaVideojuego.setDouble(2, v.getPrecio());
            consultaVideojuego.setString(3, v.getDescripcion());
            consultaVideojuego.setInt(4, v.getStock());
            
            registrarVideojuego = consultaVideojuego.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaVideojuego);
                close(conexVideo);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registrarVideojuego;
    }
    
    @Override
    public List<Videojuego> READ() throws SQLException{
        Connection conexVideo = null;
        PreparedStatement consultaVideojuego = null;
        ResultSet recibidoVideojuego = null;
        List<Videojuego> videojuegos = new ArrayList<>();
        
        conexVideo = getConnection();
        consultaVideojuego = conexVideo.prepareStatement(SQL_SELECT_VIDEOJUEGOS);
        recibidoVideojuego = consultaVideojuego.executeQuery();
        
        
        while(recibidoVideojuego.next()){
            int idVideojuego = recibidoVideojuego.getInt("idVideojuegos");
            String nombre = recibidoVideojuego.getString("nombre");
            double precio = recibidoVideojuego.getDouble("precio");
            String descripcion = recibidoVideojuego.getString("descripcion");
            int stock = recibidoVideojuego.getInt("stock");
            
            videojuegos.add(new Videojuego(idVideojuego, stock, nombre, descripcion, precio));
        }
        
        close(recibidoVideojuego);
        close(consultaVideojuego);
        close(conexVideo);
        
        return videojuegos;
    }
    
    @Override
    public int UPDATE(Videojuego v){
        Connection conexVideo = null;
        PreparedStatement consultaVideojuego = null;
        
        int registrarVideojuego = 0;
        
        try {
            conexVideo = getConnection();
            consultaVideojuego = conexVideo.prepareStatement(SQL_UPDATE_VIDEOJUUEGOS);
            
            consultaVideojuego.setString(1 , v.getNombre());
            consultaVideojuego.setDouble(2, v.getPrecio());
            consultaVideojuego.setString(3, v.getDescripcion());
            consultaVideojuego.setInt(4, v.getStock());
            consultaVideojuego.setInt(5, v.getIdVideojuego());
            
            registrarVideojuego = consultaVideojuego.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaVideojuego);
                close(conexVideo);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return registrarVideojuego;
    }
    
    @Override
    public int DELETE(Videojuego v){
        Connection conexVideo = null;
        PreparedStatement consultaVideojuego = null;
        
        int registrarVideojuego = 0;
        
        try {
            conexVideo = getConnection();
            consultaVideojuego = conexVideo.prepareStatement(SQL_DELETE_VIDEOJUEGOS);
            
            consultaVideojuego.setInt(1, v.getIdVideojuego());
            
            registrarVideojuego = consultaVideojuego.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaVideojuego);
                close(conexVideo);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registrarVideojuego;
    }
    
    
}

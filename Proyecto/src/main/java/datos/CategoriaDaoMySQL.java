/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Categoria;
import dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonat
 */
public class CategoriaDaoMySQL implements IAccesoDatosCategoria{
    
    private static final String SQL_INSERT_CATEGORIA = "INSERT INTO categoria (nombre)"
                                                        + "VALUES (?)";
    
    private static final String SQL_SELECT_CATEGORIA  = "SELECT * FROM categoria";
    
    private static final String SQL_UPDATE_CATEGORIA  = "UPDATE categoria SET nombre=? WHERE idCategoria = ?";
    
    private static final String SQL_DELETE_CATEGORIA = "DELETE FROM categoria WHERE idCategoria = ?";
    
    @Override
    public int CREATE(Categoria c){
        Connection conexCategoria = null;
        PreparedStatement consultaCategoria = null;
        
        int registrarCategoria = 0;
        
        try {
            conexCategoria = getConnection();
            consultaCategoria = conexCategoria.prepareStatement(SQL_INSERT_CATEGORIA);
            
            consultaCategoria.setString(1, c.getNombre());
            
            registrarCategoria = consultaCategoria.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaCategoria);
                close(conexCategoria);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
        return registrarCategoria;
    }
    
    @Override
    public List<Categoria> READ() throws SQLException{
        Connection conexCategoria = null;
        PreparedStatement consultaCategoria = null;
        ResultSet recibidoCategoria = null;
        List<Categoria> categorias = new ArrayList<>();
        
        conexCategoria = getConnection();
        consultaCategoria = conexCategoria.prepareStatement(SQL_SELECT_CATEGORIA);
        recibidoCategoria = consultaCategoria.executeQuery();
        
        while(recibidoCategoria.next()){
            int idCategoria = recibidoCategoria.getInt("idCategoria");
            String nombre = recibidoCategoria.getString("nombre");
            
            categorias.add(new Categoria(idCategoria, nombre));
        }
        
        close(recibidoCategoria);
        close(consultaCategoria);
        close(conexCategoria);
        
        return categorias;
    }
    
    @Override
    public int UPDATE(Categoria c){
        Connection conexCategoria = null;
        PreparedStatement consultaCategoria = null;
        
        int registrarCategoria = 0;
        
        try {
            conexCategoria = getConnection();
            consultaCategoria = conexCategoria.prepareStatement(SQL_UPDATE_CATEGORIA);
            
            consultaCategoria.setString(1, c.getNombre());
            consultaCategoria.setInt(2, c.getIdCategoria());
            
            registrarCategoria = consultaCategoria.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaCategoria);
                close(conexCategoria);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return registrarCategoria;
    }
    
    @Override
    public int DELETE(Categoria c){
        Connection conexCategoria = null;
        PreparedStatement consultaCategoria = null;
        
        int registrarCategoria = 0;
        
        try {
            conexCategoria = getConnection();
            consultaCategoria = conexCategoria.prepareStatement(SQL_DELETE_CATEGORIA);
            
            consultaCategoria.setInt(1, c.getIdCategoria());
            
            registrarCategoria = consultaCategoria.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaCategoria);
                close(conexCategoria);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registrarCategoria;
    }
}


package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Persona;
import dominio.Usuario;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonat
 */


public class UsuarioDaoMySQL implements IAccesoDatosUsuario{
    
    private static final String SQL_SELECT_USUARIO = "SELECT * FROM usuario";
    
    private static final String SQL_SELECT_PERSONA = "SELECT * FROM persona";
    
    private static final String SQL_INSERT_PERSONAS = "INSERT INTO persona (nombre, apellidos, pais, fechaNac) "
                                            + "VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_USUARIO = "INSERT INTO usuario (nomUsu, clave, email, estado) "
                                            + "VALUES (?, ?, ?, ?)";
    
    private static final String SQL_UPDATE_USUARIO = "UPDATE usuario SET nomUsu=?, clave=?, email=?, estado=? WHERE idUsuario = ?";
    
    private static final String SQL_UPDATE_PERSONA = "UPDATE persona SET nombre=?, apellidos=?, pais=?, fechaNac=? WHERE idPersona = ?";
    
    //No se borraran los datos de algun usurio porque los datos me sirven para hacer estadisticas solo el campo 
    //estado cambiara de true a false para saber que esta de alta o de baja 
    private static final String SQL_DELETE_PERSONA = "UPDATE usuario SET estado=? WHERE idUsuario = ?";
    
    private static final String SQL_SEARCH_PERSONA = "SELECT * FROM persona WHERE pais=?";
    
    private static final String SQL_SEARCH_USUARIO = "SELECT * FROM usuario WHERE idUsuario=?";
    
    
    @Override
    public int CREATE(Usuario u){
        Connection conexPer = null;
        Connection conexUsu = null;
        PreparedStatement consultaPersona = null;
        PreparedStatement consultaUsuario = null;
        int registrar = 0;
        int registrar2 = 0;
        
        try {     
            conexPer = getConnection();
            conexUsu = getConnection();
            
            consultaPersona = conexPer.prepareStatement(SQL_INSERT_PERSONAS);
            consultaUsuario = conexUsu.prepareStatement(SQL_INSERT_USUARIO);
            //Datos insertados en la tabla persona
            consultaPersona.setString(1, u.getNombre());
            consultaPersona.setString(2, u.getApellidos());
            consultaPersona.setString(3, u.getPais());
            consultaPersona.setDate(4, u.getFechaNac());
            
            //datos ingresados en la tabla usuario
            consultaUsuario.setString(1, u.getNomUsu());
            consultaUsuario.setString(2, u.getClave());
            consultaUsuario.setString(3, u.getEmail());
            consultaUsuario.setBoolean(4, u.isEstado());
            
            registrar = consultaPersona.executeUpdate();
            registrar2 = consultaUsuario.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaPersona);
                close(consultaUsuario);
                close(conexPer);
                close(conexUsu);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registrar;
    }
    
    @Override
    public List<Usuario> READ() throws SQLException{
        Connection conex = null;
        PreparedStatement consultaUsuario = null;
        PreparedStatement consultaPersona = null;
        ResultSet recibidoUsuario = null;
        ResultSet recibidoPersona = null;
        Persona persona = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        conex = getConnection();
        //Preparo la consulta para sql
        consultaUsuario = conex.prepareStatement(SQL_SELECT_USUARIO);
        consultaPersona = conex.prepareStatement(SQL_SELECT_PERSONA);
        recibidoUsuario = consultaUsuario.executeQuery();
        recibidoPersona = consultaPersona.executeQuery();
        
        while(recibidoUsuario.next()){
            //Datos recibidos de la tabla usuario
            int idUsuario = recibidoUsuario.getInt("idUsuario");
            String nomUsu = recibidoUsuario.getString("nomUsu");
            String clave = recibidoUsuario.getString("clave");
            String email = recibidoUsuario.getString("email");
            boolean estado = recibidoUsuario.getBoolean("estado");
          
            usuarios.add(new Usuario(idUsuario, nomUsu, clave, email, estado));
        }
        int i = 0;
        while(recibidoPersona.next()){
            //Datos recibidos de la tabla persona
            int idPersona = recibidoPersona.getInt("idPersona");
            String nombre = recibidoPersona.getString("nombre");
            String apellidos = recibidoPersona.getString("apellidos");
            String pais = recibidoPersona.getString("pais");
            Date fecha = recibidoPersona.getDate("fechaNac");
            usuarios.get(i).setIdPersona(idPersona);
            usuarios.get(i).setNombre(nombre);
            usuarios.get(i).setApellidos(apellidos);
            usuarios.get(i).setPais(pais);
            usuarios.get(i).setFechaNac(fecha);
            i++;
        }
        
        close(recibidoUsuario);
        close(recibidoPersona);
        
        close(consultaUsuario);
        close(consultaPersona);
        
        close(conex);
        
        return usuarios;
    }
    
    @Override
    public int UPDATE(Usuario u){
        Connection conexUsu = null;
        Connection conexPer = null;
        PreparedStatement consultaUsuario = null;
        PreparedStatement consultaPersona = null;
        int registrosUsu = 0;
        int registrosPer = 0;
        
        try {
            conexPer = getConnection();
            conexUsu = getConnection();
                 
            consultaUsuario = conexUsu.prepareStatement(SQL_UPDATE_USUARIO);
            consultaPersona = conexPer.prepareStatement(SQL_UPDATE_PERSONA);
            
            consultaPersona.setString(1, u.getNombre());
            consultaPersona.setString(2, u.getApellidos());
            consultaPersona.setString(3, u.getPais());
            consultaPersona.setDate(4, u.getFechaNac());
            consultaPersona.setInt(5, u.getIdPersona());
            
            consultaUsuario.setString(1, u.getNomUsu());
            consultaUsuario.setString(2, u.getClave());
            consultaUsuario.setString(3, u.getEmail());
            consultaUsuario.setBoolean(4, u.isEstado());
            consultaUsuario.setInt(5, u.getIdUsuario());
            
            registrosUsu = consultaUsuario.executeUpdate();
            registrosPer = consultaPersona.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                close(consultaPersona);
                close(consultaUsuario);
                
                close(conexPer);
                close(conexUsu);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return registrosUsu;
    }
    
    @Override
    public int DELETE(Usuario u){
        Connection conexUsu = null;
        PreparedStatement consultaUsuario = null;
        int registrosUsu = 0;
        
        try {
            conexUsu = getConnection();
            consultaUsuario = conexUsu.prepareStatement(SQL_DELETE_PERSONA);
            
            consultaUsuario.setBoolean(1, u.isEstado());
            consultaUsuario.setInt(2, u.getIdUsuario());
            
            
            registrosUsu = consultaUsuario.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                close(consultaUsuario);
                close(conexUsu);
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return registrosUsu;
    }
    
    @Override
    public List<Usuario> SEARCH_PAIS(String nombreBusqueda) throws SQLException{
        Connection conex = null;
        PreparedStatement consultaUsuario = null;
        PreparedStatement consultaPersona = null;
        ResultSet recibidoPersona = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        conex = getConnection();
        //Preparo la consulta para sql
        consultaPersona = conex.prepareStatement(SQL_SEARCH_PERSONA);
        consultaPersona.setString(1, nombreBusqueda);
        
        recibidoPersona = consultaPersona.executeQuery();
        
        while(recibidoPersona.next()){
            //Datos recibidos de la tabla persona
            int idPersona = recibidoPersona.getInt("idPersona");
            String nombre = recibidoPersona.getString("nombre");
            String apellidos = recibidoPersona.getString("apellidos");
            String pais = recibidoPersona.getString("pais");
            Date fecha = recibidoPersona.getDate("fechaNac");
          
            usuarios.add(new Usuario(idPersona, nombre, apellidos, pais, fecha));
        }
        
        close(recibidoPersona);
        close(consultaPersona);
        close(conex);
        
        return usuarios;
    }
}

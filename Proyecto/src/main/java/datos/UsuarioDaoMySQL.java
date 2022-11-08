
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Usuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonat
 */
public class UsuarioDaoMySQL implements IAccesoDatosUsuario{
    
    private static final String SQL_INSERT_PERSONAS = "INSERT INTO personas (nombre, apellidos, pais, fechaNac) "
                                            + "VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_Usuario = "INSERT INTO personas (nomUsu, clave, email, estado) "
                                            + "VALUES (?, ?, ?, ?)";
    
    @Override
    public void CREATE(Usuario u){
        Connection conex = null;
        PreparedStatement consultaPersona = null;
        PreparedStatement consulta = null;
        int registrar = 0;
        
        try {
            //1. establecesmos la conexion
            conex = getConnection();
            
            //2. preparo mi instruccion para ejecutarla contra la base de datos
            consultaPersona = conex.prepareStatement(SQL_INSERT_PERSONAS);
            //Asignar los valores de los interrogantes(?) de la consulta1
            //Tiene que estar en orden los atributos
            consultaPersona.setString(1, u.getNomUsu());
            consultaPersona.setString(2, u.se;
            consultaPersona.setString(3, obj.getEmail());
            consultaPersona.setString(4, obj.getTelefono());
            
            //3. Ejecuto la Query
            registrar = consultaPersona.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaPersona);
                close(conex);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registrar;
    }
    
    @Override
    public void READ(){
        
    }
    
    @Override
    public void UPDATE(){
        
    }
    
    @Override
    public void DELETE(){
        
    }
}

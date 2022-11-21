
package datos;

import dominio.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jonat
 */
public interface IAccesoDatosUsuario {
    
    public int CREATE(Usuario u);
    
    public List<Usuario> READ() throws SQLException;
    
    public int UPDATE(Usuario u);
    
    public int DELETE(Usuario u);
    
    public List<Usuario> SEARCH_PAIS(String nombreBusqueda) throws SQLException;
    
}

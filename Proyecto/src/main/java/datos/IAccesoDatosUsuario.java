
package datos;

import dominio.Usuario;

/**
 *
 * @author jonat
 */
public interface IAccesoDatosUsuario {
    
    public void CREATE(Usuario u);
    
    public void READ();
    
    public void UPDATE();
    
    public void DELETE();
    
}

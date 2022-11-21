/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos;

import dominio.Usuario;
import dominio.Videojuego;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jonat
 */
public interface IAccesoDatosVideojuegos {
    
    public int CREATE(Videojuego v);
    
    public List<Videojuego> READ() throws SQLException;
    
    public int UPDATE(Videojuego v);
    
    public int DELETE(Videojuego v);
}

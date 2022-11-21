/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos;

import dominio.Categoria;
import dominio.Videojuego;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jonat
 */
public interface IAccesoDatosCategoria {
    
    public int CREATE(Categoria c);
    
    public List<Categoria> READ() throws SQLException;
    
    public int UPDATE(Categoria c);
    
    public int DELETE(Categoria c);
}

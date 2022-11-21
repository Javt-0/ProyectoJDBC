/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos;

import dominio.ReviewsComentarios;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jonat
 */
public interface IAccesoDatosReviewsComentarios {
    
    public int CREATE(ReviewsComentarios rc);
    
    public List<ReviewsComentarios> READ() throws SQLException;
    
    public int UPDATE(ReviewsComentarios rc);
    
    public int DELETE(ReviewsComentarios rc);
}

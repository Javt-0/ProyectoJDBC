/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.ReviewsComentarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat
 */
public class ReviewsComentariosDaoMySQL implements IAccesoDatosReviewsComentarios{
    
    private static final String SQL_INSERT_REVIEW = "INSERT INTO reviewcomentarios (descripcion, puntuacion)"
                                                        + "VALUES (?, ?)";
    
    private static final String SQL_SELECT_REVIEW  = "SELECT * FROM reviewcomentarios";
    
    private static final String SQL_UPDATE_REVIEW  = "UPDATE reviewcomentarios SET descripcion=?, puntuacion=? WHERE idReviewComentarios = ?";
    
    private static final String SQL_DELETE_REVIEW = "DELETE FROM reviewcomentarios WHERE idReviewComentarios = ?";
    
    @Override
    public int CREATE(ReviewsComentarios rc){
        
        Connection conexReview = null;
        PreparedStatement consultaReview = null;
        
        int registrarReview = 0;
        
        try {
            conexReview = getConnection();
            consultaReview = conexReview.prepareStatement(SQL_INSERT_REVIEW);
            
            consultaReview.setString(1, rc.getDescripcion());
            consultaReview.setInt(2, rc.getPuntuacion());
            
            registrarReview = consultaReview.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaReview);
                close(conexReview);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
        return registrarReview;
    }
    
    @Override
    public List<ReviewsComentarios> READ() throws SQLException{
        Connection conexReview = null;
        PreparedStatement consultaReview = null;
        ResultSet recibidoReview = null;
        List<ReviewsComentarios> reviewsComentarios = new ArrayList<>();
        
        conexReview = getConnection();
        consultaReview = conexReview.prepareStatement(SQL_SELECT_REVIEW);
        recibidoReview = consultaReview.executeQuery();
        
        while(recibidoReview.next()){
            int idReviewComentarios = recibidoReview.getInt("idReviewComentarios");
            String descripcion = recibidoReview.getString("descripcion");
            int puntuacion = recibidoReview.getInt("puntuacion");
            
            reviewsComentarios.add(new ReviewsComentarios(idReviewComentarios, puntuacion, descripcion));
        }
        
        close(recibidoReview);
        close(consultaReview);
        close(conexReview);
        
        return reviewsComentarios;
    }
    
    @Override
    public int UPDATE(ReviewsComentarios rc){
        Connection conexReview = null;
        PreparedStatement consultaReview = null;
        
        int registrarReview = 0;
        
        try {
            conexReview = getConnection();
            consultaReview = conexReview.prepareStatement(SQL_UPDATE_REVIEW);
            
            consultaReview.setString(1, rc.getDescripcion());
            consultaReview.setInt(2, rc.getPuntuacion());
            consultaReview.setInt(3, rc.getIdReviewsComentarios());

            
            registrarReview = consultaReview.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaReview);
                close(conexReview);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
        return registrarReview;
    }
    
    @Override
    public int DELETE(ReviewsComentarios rc){
        Connection conexReview = null;
        PreparedStatement consultaReview = null;
        
        int registrarReview = 0;
        
        try {
            conexReview = getConnection();
            consultaReview = conexReview.prepareStatement(SQL_DELETE_REVIEW);
            
            consultaReview.setInt(1, rc.getIdReviewsComentarios());

            registrarReview = consultaReview.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(consultaReview);
                close(conexReview);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
        return registrarReview;
    }
}

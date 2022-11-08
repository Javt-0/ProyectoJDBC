
package dominio;

import java.io.Serializable;

/**
 *
 * @author jonat
 */
public class ReviewsComentarios implements Serializable {
    private int idReviewsComentarios, puntuacion;
    private String descripcion;
    
    //Constructores

    public ReviewsComentarios(int idReviewsComentarios) {
        this.idReviewsComentarios = idReviewsComentarios;
    }

    public ReviewsComentarios() {
        
    }
    
    public ReviewsComentarios(int idReviewsComentarios, int puntuacion, String descripcion) {
        this.idReviewsComentarios = idReviewsComentarios;
        this.puntuacion = puntuacion;
        this.descripcion = descripcion;
    }
    
    //Get y Set

    public int getIdReviewsComentarios() {
        return idReviewsComentarios;
    }

    public void setIdReviewsComentarios(int idReviewsComentarios) {
        this.idReviewsComentarios = idReviewsComentarios;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    //Hash and equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idReviewsComentarios;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReviewsComentarios other = (ReviewsComentarios) obj;
        return this.idReviewsComentarios == other.idReviewsComentarios;
    }
    
    
    //toString
    @Override
    public String toString() {
        return "ReviewsComentarios{" + "idReviewsComentarios=" + idReviewsComentarios + ", puntuacion=" + puntuacion + ", descripcion=" + descripcion + '}';
    }
}


package com.proyecto.proyecto;

import datos.CategoriaDaoMySQL;
import datos.IAccesoDatosUsuario;
import datos.ReviewsComentariosDaoMySQL;
import datos.UsuarioDaoMySQL;
import datos.UsuarioDaoTxt;
import datos.VideojuegoDaoMySQL;
import dominio.Categoria;
import dominio.ReviewsComentarios;
import dominio.Usuario;
import dominio.Persona;
import dominio.Videojuego;
import java.sql.SQLException;
import java.text.SimpleDateFormat;  
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jonat
 */
public class ProyectoMain {
    
    static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args){ 
        menu();
    }
    
    public static void menu() {
        
        try {
            Usuario u = new Usuario();
            int opcion = -1;
            while(opcion != 0){
                //System.out.flush();
                System.out.println("\t\t\t\t********************************************************");
                System.out.println("\t\t\t\t********************** BIENVENIDO **********************");
                System.out.println("\t\t\t\t********* SISTEMA DE GESTION EN TIENDA ONLINE **********");
                System.out.println("\t\t\t\t******************* (VERSION ALPHA) ********************");
                System.out.println("\t\t\t\t********************************************************");
                System.out.println("\t\t\t\t========================================================");
                System.out.println("\t\t\t\t||         1. INICIAR SESIÓN COMO ADMIN               ||");
                System.out.println("\t\t\t\t||         2. INICIAR SESIÓN COMO USUARIO             ||");
                System.out.println("\t\t\t\t||         3. REGISTRAR                               ||");
                System.out.println("\t\t\t\t||         0. SALIR                                   ||");
                System.out.println("\t\t\t\t========================================================");
                System.out.println("Seleccionar una de las opciones del menú: ");
                opcion = teclado.nextInt();
                
                switch(opcion){
                    case 1:
                        if(u.validarAdmin() == false){
                            System.out.println("Fallo en las credenciales de ADMIN");
                            enterContinuar();
                            break;
                        }else{
                            menuAdmin();
                        }
                        break;
                    case 2:
                        Usuario u1 = u.validarUsuario();
                        if(u1.getIdUsuario() == 0){
                            System.out.println("El usuario no existe o las credenciales de Usuario son incorrectas");
                            enterContinuar();
                            break;
                        }else{
                            menuUsuario(u1);
                        }
                        break;
                    case 3:
                        u.registrarUsuario();
                        enterContinuar();
                        break;
                    case 0:
                        System.out.println("******** GRACIAS POR UTILIZAR EL PROGRAMA ********");
                        enterContinuar();
                        break;
                    default:
                        System.out.println("!!!!!!!! ERROR !!!!!!!!\n");
                        System.out.println("Fuera de los parámetros establecidos, ingrese de nuevo la opción\n");
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR DE INGRESO POR TECLADO....");
            enterContinuar();
            return;
        }
    }
    
    public static void menuAdmin() {
        
        try {
            Categoria c = new Categoria();
            Usuario u = new Usuario();
            Videojuego v = new Videojuego();
            IAccesoDatosUsuario uDaoTxt = new UsuarioDaoTxt();
            int opcion = -1;
            while(opcion != 0){
                //System.out.flush();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("\t\t\t\t********************************************************");
                System.out.println("\t\t\t\t****************** MENU ADMINISTRADOR ******************");
                System.out.println("\t\t\t\t******************* (VERSION ALPHA) ********************");
                System.out.println("\t\t\t\t********************************************************");
                System.out.println("\t\t\t\t========================================================");
                System.out.println("\t\t\t\t||         1. LISTAR TODOS LOS USUARIOS                ||");
                System.out.println("\t\t\t\t||         2. MOSTRAR LOS USUARIOS DE BAJA             ||");
                System.out.println("\t\t\t\t||         3. AÑADIR UN VIDEOJUEGO                     ||");
                System.out.println("\t\t\t\t||         4. ELIMINAR VIDEOJUEGO                      ||");
                System.out.println("\t\t\t\t||         5. CAMBIAR STOCK DE UN VIDEOJUEGO           ||");
                System.out.println("\t\t\t\t||         6. LISTAR VIDEOJUEGOS                       ||");
                System.out.println("\t\t\t\t||         7. INGRESAR UNA NUEVA CATEGORIA             ||");
                System.out.println("\t\t\t\t||         8. BUSCAR POR PAIS                          ||");
                System.out.println("\t\t\t\t||         9. LISTAR USUARIOS(CLAVE SIN ENCRIPTAR)     ||");
                System.out.println("\t\t\t\t||         0. VOLVER                                   ||");
                System.out.println("\t\t\t\t========================================================");
                System.out.println("Seleccionar una de las opciones del menú: ");
                opcion = teclado.nextInt();
  
                switch(opcion){
                    case 1:
                        u.listar();
                        enterContinuar();
                        break;
                    case 2:
                        u.usuariosDeBaja();
                        enterContinuar();
                        break;
                    case 3:
                        v.añadirVideojuego();
                        enterContinuar();
                        break;
                    case 4:
                        v.eliminarVideojuego();
                        enterContinuar();
                        break;
                    case 5:
                        v.cambioStockVideojuego();
                        enterContinuar();
                        break;
                    case 6:
                        v.listarVideojuegos();
                        enterContinuar();
                        break;
                    case 7:
                        c.nuevaCategoria();
                        enterContinuar();
                        break;
                    case 8:
                        u.filtrarPais();
                        enterContinuar();
                        break;
                    case 9:
                        System.out.println(uDaoTxt.READ());
                        enterContinuar();
                        break;
                    case 0:
                        System.out.println("CERRANDO SESION COMO ADMINISTRADOR");
                        enterContinuar();
                        break;
                    default:
                        System.out.println("!!!!!!!! ERROR !!!!!!!!\n");
                        System.out.println("Fuera de los parámetros establecidos, ingrese de nuevo la opción\n");
                        break;
                }

            }
        } catch (Exception ex) {
            System.out.println("ERROR DE INGRESO POR TECLADO....");
            enterContinuar();
            return;
        }
        
    }
    
    public static void menuUsuario(Usuario u) {
        
        try {
            
            Videojuego v = new Videojuego();
            int opcion = -1;
            while(opcion != 0){
                //System.out.flush();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("\t\t\t\t********************************************************");
                System.out.println("\t\t\t\t********************* MENU USUARIO *********************");
                System.out.println("\t\t\t\t******************* (VERSION ALPHA) ********************");
                System.out.println("\t\t\t\t********************************************************");
                System.out.println("\t\t\t\t========================================================");
                System.out.println("\t\t\t\t||         1. PERFIL (MOSTRAR DATOS)                  ||");
                System.out.println("\t\t\t\t||         2. CAMBIAR CONTRASEÑA                      ||");
                System.out.println("\t\t\t\t||         3. CAMBIAR PAIS                            ||");
                System.out.println("\t\t\t\t||         4. MODIFICAR CORREO ELECTRONICO            ||");
                System.out.println("\t\t\t\t||         5. DARSE DE BAJA EN EL SISTEMA             ||");
                System.out.println("\t\t\t\t||         0. CERRAR SESION                           ||");
                System.out.println("\t\t\t\t========================================================");
                System.out.println("Seleccionar una de las opciones del menú: ");
                opcion = teclado.nextInt();
                
                switch(opcion){
                    case 1:
                        u.mostrarDatos(u);
                        enterContinuar();
                        break;
                    case 2:
                        u.cambioClaveUsuario(u);
                        enterContinuar();
                        break;
                    case 3:
                        u.cambioPaisUsuario(u);
                        enterContinuar();
                        break;
                    case 4:
                        u.cambioCorreoUsuario(u);
                        enterContinuar();
                        break;
                    case 5:
                        if(u.bajaUsuario(u) == false){
                            System.out.println("Tu cuenta se dio de baja");
                            enterContinuar();
                            return;
                        }else{
                            System.out.println("Tu cuenta no se pudo dar de baja, intenta de nuevo porfavor");
                            enterContinuar();
                        }
                        break;
                    case 0:
                        System.out.println("SESION CERRADA");
                        enterContinuar();
                        break;
                    default:
                        System.out.println("!!!!!!!! ERROR !!!!!!!!\n");
                        System.out.println("Fuera de los parámetros establecidos, ingrese de nuevo la opción\n");
                        break;
                }

                
            }
        } catch (Exception ex) {
            System.out.println("ERROR DE INGRESO POR TECLADO....");
            enterContinuar();
            return;
        }
        
    }
    
    public static void enterContinuar(){
        System.out.println("\n\nPulse ENTER para continuar....");
        teclado.nextLine();
        teclado.nextLine();
        for(int i=0; i<30; i++){
            System.out.println("");
        }
    }
}
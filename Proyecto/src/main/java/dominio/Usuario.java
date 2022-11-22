
package dominio;


import datos.IAccesoDatosUsuario;
import datos.UsuarioDaoMySQL;
import datos.UsuarioDaoTxt;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author jonat
 */
public class Usuario extends Persona implements Serializable{
    private int idUsuario;
    private String nomUsu, clave, email;
    private boolean estado;
    
    //Constructores

    public Usuario(int idUsuario, String nomUsu, String clave, String email, boolean estado, int idPersona, String nombre, String apellidos, String pais, Date fechaNac) {
        super(idPersona, nombre, apellidos, pais, fechaNac);
        this.idUsuario = idUsuario;
        this.nomUsu = nomUsu;
        this.clave = clave;
        this.email = email;
        this.estado = estado;
    }
    
    
    
    public Usuario(int idUsuario, String nomUsu, String clave, String email, String nombre, String apellidos, String pais, Date fechaNac, boolean estado) {
        super(nombre, apellidos, pais, fechaNac);
        this.idUsuario = idUsuario;
        this.nomUsu = nomUsu;
        this.clave = clave;
        this.email = email;
        this.estado = estado;
    }

    public Usuario(int idUsuario, String nomUsu, String clave, String email, boolean estado) {
        this.idUsuario = idUsuario;
        this.nomUsu = nomUsu;
        this.clave = clave;
        this.email = email;
        this.estado = estado;
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(int idPersona, String nombre, String apellidos, String pais, Date fechaNac) {
        super(idPersona, nombre, apellidos, pais, fechaNac);
    }

    public Usuario(boolean estado) {
        this.estado = estado;
    }

    public Usuario() {
    }

    public Usuario(String nomUsu, String clave, String email, boolean estado, String nombre, String apellidos, String pais, Date fechaNac) {
        super(nombre, apellidos, pais, fechaNac);
        this.nomUsu = nomUsu;
        this.clave = clave;
        this.email = email;
        this.estado = estado;
    }
    
    //Get y Set
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    //Hash and equals
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.email);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.email, other.email);
    }
    
    //toString

    @Override
    public String toString() {
        return "\n\nID Usuario: " + idUsuario + "\nNombre de Usuario: " + nomUsu + "\nClave: " + clave + "\nEmail: " + email + "\nEstado: " + estado +super.toString();
    }
    
   
    static Scanner teclado = new Scanner(System.in);
    
    
    //METODOS CUANDO INICIE SESION COMO ADMINISTRADOR ***********************************************
    
    public boolean validarAdmin(){
            IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        try {
            
            String claveOri = uDAO.READ().get(0).getClave();
            System.out.println("Ingrese nombre de administrador: ");
            String nombre = teclado.nextLine();
        
            System.out.println("Ingrese la contraseña: ");
            String claveEn = encriptadoMD5(teclado.nextLine());
           
            if(nombre.equalsIgnoreCase(uDAO.READ().get(0).getNomUsu()) == false || claveEn.equalsIgnoreCase(claveOri) == false){
                return false;
            }else{
                return comparadorMD5(claveOri, claveEn);
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    //Metodo de encriptacion de contraseñas
    public String encriptadoMD5(String clave) throws Exception {
        String md5 = null;
        if (null == clave)
            return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(clave.getBytes(), 0, clave.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
        return md5;
    }
    
    //Metodo que compara la contraseña para saber si es igual para que usuario inicie sesion
    public boolean comparadorMD5(String claveEntrada, String claveEncriptada){
        boolean resul = true;
        if(claveEntrada.equalsIgnoreCase(claveEncriptada) == true){
            resul = true;
        }else{
            resul = false;
        }
        return resul;
    }
    
    public void listar(){
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        
        try {
            System.out.println(uDAO.READ());
        } catch (SQLException ex) {
            System.out.println("Sin datos");
        }
    }
    
    public void usuariosDeBaja(){
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Usuario u = new Usuario();
            usuarios.addAll(uDAO.READ());
            for(int i=0; i<usuarios.size(); i++){
                if(usuarios.get(i).isEstado() == false){
                    System.out.println(usuarios.get(i));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Sin datos");
        }
    }
    
    //METODOS CUANDO INICIE SESION COMO USUARIO ***********************************************************
    
    public Usuario validarUsuario(){
        Usuario u = new Usuario();
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        ArrayList<Usuario> usuarios = new ArrayList<>();
            try {
                usuarios.addAll(uDAO.READ());
                
                System.out.println("Ingrese nombre de usuario: ");
                String nombreEntrada = teclado.nextLine();

                System.out.println("Ingrese la contraseña: ");
                String claveEntrada = encriptadoMD5(teclado.nextLine());
                
                
                for(int i=1; i<usuarios.size(); i++){
                    if(nombreEntrada.equalsIgnoreCase(usuarios.get(i).getNomUsu()) == true && claveEntrada.equalsIgnoreCase(usuarios.get(i).getClave()) == true && usuarios.get(i).isEstado() == true){
                        System.out.println(usuarios.get(i).getNomUsu());
                        System.out.println(usuarios.get(i).getClave());
                        u = usuarios.get(i);   
                    }
                }
        } catch (Exception ex) {
            return u;
        }
        
        return u; 
    }
    
    
    public void mostrarDatos(Usuario uEntrada){
        System.out.println("\n\nNombre: " + uEntrada.getNombre() + " " + uEntrada.getApellidos());
        System.out.println("Pais: " + uEntrada.getPais());
        System.out.println("Fecha de nacimiento: " + uEntrada.getFechaNac());
        System.out.println("Edad: " + calcularEdad(uEntrada.getFechaNac()).getYears() + " años");
        System.out.println("Correo electronico: " + uEntrada.getEmail());
        System.out.println("Nombre de Usuario: " + uEntrada.getNomUsu());
    }
    
    public Period calcularEdad(Date fechaNacEntrada){
        DateTimeFormatter formatoFechaSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleDateFormat formatoFechaEntrada = new SimpleDateFormat("dd/MM/yyyy");
        
        //Se transforma la fecha a String
        String fechaString = formatoFechaEntrada.format(fechaNacEntrada);
        
        LocalDate fechaNacSalida = LocalDate.parse(fechaString, formatoFechaSalida);
        LocalDate fechaActual = LocalDate.now();

        Period periodo = Period.between(fechaNacSalida, fechaActual);
        
        return periodo;
    }
    
    public boolean bajaUsuario(Usuario uEntrada){
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        uEntrada.setEstado(false);
        uDAO.UPDATE(uEntrada);
        
        return uEntrada.isEstado();
    }
    
    public void cambioClaveUsuario(Usuario uEntrada){
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        IAccesoDatosUsuario uDaoTxt = new UsuarioDaoTxt();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        
        System.out.println("Ingresa la nueva contraseña: ");
        String claveEntrada = teclado.nextLine();
        
        try {
            usuarios.addAll(uDAO.READ());
            //Aqui añadir el metodo que guarda la contraseña sin encriptar en un txt
            
            //
            uEntrada.setClave(encriptadoMD5(claveEntrada));
            uDAO.UPDATE(uEntrada);
            System.out.println("Se cambio la contraseña");
        } catch (Exception ex) {
            System.out.println("No se pudo encriptar la contraseña");
        }
    }
    
    public void cambioPaisUsuario(Usuario uEntrada){
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        
        System.out.println("Ingresa el Pais: ");
        String paisEntrada = teclado.nextLine();
        
        
        
        
        
        
        uEntrada.setPais(paisEntrada);
        uDAO.UPDATE(uEntrada);
    }
    
    public void cambioCorreoUsuario(Usuario uEntrada){
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        
        System.out.println("Ingresa el nuevo correo: ");
        String emailEntrada = teclado.nextLine();
        boolean validar = validarCorreo(emailEntrada);
        
        while(validar == false){
            System.out.println("El email ingresado es inválido, ingresa el nuevo correo: ");
            emailEntrada = teclado.nextLine();
            validar = validarCorreo(emailEntrada);
        }
        
        uEntrada.setEmail(emailEntrada);
        uDAO.UPDATE(uEntrada);
    }
    
    public boolean validarCorreo(String email){
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // El email a validar
        Matcher mather = pattern.matcher(email);
        boolean devolver = false;
        
        if (mather.find() == true) {
            devolver = true;
        } else {
            devolver = false;
        }
        return devolver;
    }
    
    //METODO PARA REGISTRAR UN NUEVO USARIO ******************************************************************
    
    public void registrarUsuario(){
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        IAccesoDatosUsuario uDaoTxt = new UsuarioDaoTxt();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        //Estas dos variables me sirven para gusrdar los id por si hay algun usurio que solo se dio de baja y quiera volver
        //a registrarse entonces solo actualizamos datos
        int idActualizarDatosUsuario = 0;
        int idActualizarDatosPersona = 0;
        
        try {
            usuarios.addAll(uDAO.READ());
        } catch (SQLException ex) {
            System.out.println("No se cargo los usurios");
        }
        
        System.out.println("Ingresa un correo electronico valido: ");
        String emailEntrada = teclado.nextLine();
        boolean validar = validarCorreo(emailEntrada);
        
        //Hasta que no sea un correo valido no se puede avanzar
        while(validar == false){
            System.out.println("El email ingresado es inválido, ingresa un correo valido: ");
            emailEntrada = teclado.nextLine();
            validar = validarCorreo(emailEntrada);
        }
        
        //Verificamos que no exista un correo igual al introducido y si es igual comparamos el estado de su cuenta
        Usuario uNuevo = new Usuario(emailEntrada);
        for(int i=1; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(emailEntrada) == true && usuarios.get(i).isEstado() == false){
                idActualizarDatosUsuario = usuarios.get(i).getIdUsuario();
                idActualizarDatosPersona = usuarios.get(i).getIdPersona();
                System.out.println("tengo los dos id " + idActualizarDatosUsuario + "  " + idActualizarDatosPersona + " " + uNuevo.getEmail());
                break;  
            }else{
                while(usuarios.contains(uNuevo) == true){
                    
                    System.out.println("El correo ingresado esta dentro del sistema, ingresa de nuevo el correo");
                    emailEntrada = teclado.nextLine();
                    validar = validarCorreo(emailEntrada);
                    while(validar == false){
                        System.out.println("El email ingresado es inválido, ingresa un correo valido: ");
                        emailEntrada = teclado.nextLine();
                        validar = validarCorreo(emailEntrada);
                    }
                    uNuevo = new Usuario(emailEntrada);
                }
            }
        }
        
        
        System.out.println("Ingrese su nombre porfavor");
        String nombreEntrada = teclado.nextLine().toUpperCase();
        uNuevo.setNombre(nombreEntrada);
        
        System.out.println("Ingrese sus apellidos porfavor");
        String apellidoEntrada = teclado.nextLine().toUpperCase();
        uNuevo.setApellidos(apellidoEntrada);
        
        System.out.println("Ingrese el pais donde reside porfavor");
        String paisEntrada = teclado.nextLine().toUpperCase();
        uNuevo.setPais(paisEntrada);
        
        //Se transforma los datos de la fecha a sql.Date
        System.out.println("A continuacion va ingresar la fecha de nacimiento");
        uNuevo.setFechaNac(validarFecha());
        
        System.out.println("Ingrese su nombre de usuario: ");
        teclado.nextLine();
        String nomUsuEntrada = teclado.nextLine();
        for(int i=1; i<usuarios.size(); i++){
            if(usuarios.get(i).getNomUsu().equals(nomUsuEntrada) == true && usuarios.get(i).isEstado() == false){
                uNuevo.setNomUsu(nomUsuEntrada);
                System.out.println("tengo el nombre " + usuarios.get(i).getNomUsu());
                break;
            }else{
                while(usuarios.get(i).getNomUsu().equals(nomUsuEntrada) == true){
                    System.out.println("El nombre de usuario ya esta en uso, ingrese de nuevo un nombre de usuario: ");
                    nomUsuEntrada = teclado.nextLine();
                }
                uNuevo.setNomUsu(nomUsuEntrada);
            }
        }
        
        //Ingreso de contraseña
        System.out.println("Ingresa una contraseña: ");
        String claveEntrada = teclado.nextLine();
        uNuevo.setClave(claveEntrada);
        
        //Cambio del campo Estado
        uNuevo.setEstado(true);
        
        System.out.println("Revisa tus datos:\n");
        mostrarDatos(uNuevo);
        
        System.out.println("¿Tus datos estan bien, quieres registrarte? (S/N): ");
        String elegir = teclado.nextLine();
        
        if(elegir.toUpperCase().compareToIgnoreCase("S") == 0){
            if(idActualizarDatosUsuario != 0 && idActualizarDatosPersona != 0){
                uNuevo.setIdPersona(idActualizarDatosPersona);
                uNuevo.setIdUsuario(idActualizarDatosUsuario);
                try {
                    //metodo que ingresa en un txt el usurio nuevo antes de encriptar la contraseña
                    
                    uNuevo.setClave(encriptadoMD5(uNuevo.getClave()));
                    uDAO.UPDATE(uNuevo);
                } catch (Exception ex) {
                    System.out.println("No se pudo encriptar la contraseña");
                }
                
            }else{
                try {
                    //metodo que ingresa en un txt el usurio nuevo antes de encriptar la contraseña
                    Usuario uTxt = uNuevo;
                    
                    uTxt.setIdPersona(usuarios.get(usuarios.size()-1).getIdPersona() + 1);
                    uTxt.setIdUsuario(usuarios.get(usuarios.size()-1).getIdUsuario()+ 1);
                    uDaoTxt.CREATE(uTxt);
                    uNuevo.setClave(encriptadoMD5(uNuevo.getClave()));
                    uDAO.CREATE(uNuevo);
                } catch (Exception ex) {
                    System.out.println("No se pudo encriptar la contraseña");
                }
            }   
            System.out.println("Registro con exito.");
        }else{
            System.out.println("No te has registrado.\n");
        }

    }
    
    //Metodo que devuelve un tipo de dato sql.Date
    public Date validarFecha(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy");
        int dia = 0;
        int mes = 0;
        int año = 0;
        System.out.println("\nIntroduzca año de nacimiento(AAAA)): ");
        año = teclado.nextInt();
        if ((año%4 == 0) && ((año%100 != 0) || (año%400 == 0))){
            System.out.println("\nIntroduzca mes de nacimiento(MM): ");
            mes = teclado.nextInt();
                while(mes<1 || mes>12){
                    System.out.println("El mes debe estar comprendido entre 1 y 12: ");
                    mes = teclado.nextInt();
                }
                if(mes == 2){
                    System.out.println("\nIntroduzca día de nacimiento(dd): ");
                    dia = teclado.nextInt();
                    while(dia<1||dia>29){
                        System.out.println("El día debe estar comprendido entre 1 y 29: ");
                        dia=teclado.nextInt();
                    }
                }else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==9 || mes==11){
                    System.out.println("\nIntroduzca día de nacimiento(dd): ");
                    dia = teclado.nextInt();
                    while(dia<1||dia>31){
                        System.out.println("El día debe estar comprendido entre 1 y 31: ");
                        dia = teclado.nextInt();
                    }
                }else{
                    System.out.println("\nIntroduzca día de nacimiento(dd): ");
                    dia = teclado.nextInt();
                    while(dia<1 || dia>30){
                        System.out.println("El día debe estar comprendido entre 1 y 30: ");
                        dia = teclado.nextInt();
                    }
                }
        }else{
            System.out.println("\nIntroduzca mes de nacimiento(MM): ");
            mes = teclado.nextInt();
            while(mes<1 || mes>12){
                System.out.println("El mes debe estar comprendido entre 1 y 12: ");
                mes = teclado.nextInt();
            }
            if(mes == 2){
                System.out.println("\nIntroduzca día de nacimiento(dd): ");
                dia = teclado.nextInt();
                while(dia<1 || dia>28){
                    System.out.println("El día debe estar comprendido entre 1 y 29: ");
                    dia = teclado.nextInt();
                }
            }else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==9 || mes==11){
                System.out.println("\nIntroduzca día de nacimiento(dd): ");
                dia = teclado.nextInt();
                while(dia<1 || dia>31){
                    System.out.println("El día debe estar comprendido entre 1 y 31: ");
                    dia = teclado.nextInt();
                }
            }else{
                System.out.println("\nIntroduzca día de nacimiento(dd): ");
                dia = teclado.nextInt();
                while(dia<1 || dia>30){
                    System.out.println("El día debe estar comprendido entre 1 y 30: ");
                    dia = teclado.nextInt();
                }
            }
        }
        
        String diaString = String.valueOf(dia);
        String mesString = String.valueOf(mes);
        String añoString = String.valueOf(año);
        String fechaEntrada = (diaString + "/" + mesString + "/" + añoString);
        LocalDate fechaLocalDate = LocalDate.parse(fechaEntrada, formato);
        java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaLocalDate);
        return fechaSQL;
    }
    
    public void filtrarPais(){
        Usuario u = new Usuario();
        IAccesoDatosUsuario uDAO = new UsuarioDaoMySQL();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Usuario> usuariosFiltrados = new ArrayList<>();
        
        System.out.println("Ingresa el pais por el que quieres filtar: ");
        String paisEntrada = teclado.nextLine().toUpperCase();
        
        try {
            usuarios.addAll(uDAO.READ());
            usuariosFiltrados.addAll(uDAO.SEARCH_PAIS(paisEntrada));
            for(int i=1; i<usuarios.size(); i++){
                for(int j=0; j<usuariosFiltrados.size(); j++){
                    if(usuariosFiltrados.get(j).getIdPersona() == usuarios.get(i).getIdUsuario()){
                        mostrarDatos(usuarios.get(i));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}

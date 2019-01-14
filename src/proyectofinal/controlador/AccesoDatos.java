/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase que gestiona la base de datos. Tiene las siguiente funciones:
 *  - Insertar.
 *  - Listar.
 *  - Buscar por campo.
 *  - Ver registro.
 * @author raul
 */
public class AccesoDatos {
    private static final String rutaBD="jdbc:sqlite:src/proyectofinal/bd/dbBasica.db";
    private static final String RUTA_JDBC="org.sqlite.JDBC";
    private static final String CARGA_PRINCIPAL="SELECT NOMBRE, AUTOR, ANNO, GENERO FROM LIBRO";
    private static final String NUMERO_REGISTRO="SELECT COUNT(CODIGO) FROM LIBRO";
    private static final String BUSCAR_LIBRO="SELECT NOMBRE, AUTOR, ANNO, GENERO, ARGUMENTO, IMAGEN FROM LIBRO WHERE NOMBRE=?;";
    private static final String ULTIMO_LIBRO="SELECT MAX(CODIGO) FROM LIBRO";
    private static final String INSERTAR="INSERT INTO LIBRO (CODIGO,NOMBRE,AUTOR,ANNO,GENERO,ARGUMENTO,IMAGEN) VALUES(?,?,?,?,?,?,?)";
    
    private Connection conexion;
/**
 * Constructor de la clase.
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
    public AccesoDatos() throws ClassNotFoundException, SQLException{
        Class.forName(RUTA_JDBC);
        conexion = DriverManager.getConnection(rutaBD);
    }
    
    /**
     * Método interno que obtiene el siguiente código disponible.
     * @return Devuelve el código siguiente.
     */
    private int nuevoCodigo(){
        int numero = -1;
        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            ResultSet result=sentencia.executeQuery(ULTIMO_LIBRO);
            result.next();
            numero=result.getInt(1)+1;
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numero;
    }
    /**
     * Inserta un registro en la base de datos.
     * @param libro
     * @return Devuelve true si se ha introducido correctamente y false en caso contrario.
     */
    public boolean insertaLibro(String[] libro){
        boolean insertado=false;
        PreparedStatement sentencia;
        try {
            sentencia=conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, nuevoCodigo());
            sentencia.setString(2, libro[0]);
            if(libro[1]==null){
                sentencia.setNull(3, Types.VARCHAR);
            }
            else{
                sentencia.setString(3, libro[1]);
            }
            if(libro[2]==null){
                sentencia.setNull(4, Types.INTEGER);
            }
            else{
                sentencia.setInt(4, Integer.parseInt(libro[2]));
            }
            if(libro[3]==null){
                sentencia.setNull(5, Types.VARCHAR);
            }
            else{
                sentencia.setString(5, libro[3]);
            }
            if(libro[4]==null){
                sentencia.setNull(6, Types.VARCHAR);
            }
            else{
                sentencia.setString(6, libro[4]);
            }
            if(libro[5]==null){
                sentencia.setNull(7, Types.VARCHAR);
            }
            else{
                sentencia.setString(7, libro[5]);
            }
            
            sentencia.execute();
            sentencia.close();
            insertado=true;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return insertado;
    }

    /**
     * Realiza una búsqueda por los parámetros introducidos.
     * @param opcion
     * @param texto
     * @return Devuelve un array resultado de la búsqueda
     */
    public Object[][] obtenerBusqueda(String opcion, String texto){
        Object[][] libros = null;
        String sentencia="SELECT NOMBRE, AUTOR, ANNO, GENERO FROM LIBRO WHERE "+opcion+" LIKE '%"+texto+"%';";
        String numero="SELECT COUNT(CODIGO) FROM LIBRO WHERE "+opcion+" LIKE '%"+texto+"%';";
        Statement numeroDevuelto;
        Statement librosDevuelto;
        try {
            numeroDevuelto=conexion.createStatement();
            ResultSet resultado=numeroDevuelto.executeQuery(numero);
            libros=new Object[resultado.getInt(1)][4];
            resultado.close();
            numeroDevuelto.close();
            
            librosDevuelto=conexion.createStatement();
            ResultSet resultadoLibros=librosDevuelto.executeQuery(sentencia);
            int i=0;
            while(resultadoLibros.next()){
                libros[i][0]=resultadoLibros.getString("NOMBRE");
                libros[i][1]=resultadoLibros.getString("AUTOR");
                libros[i][2]=resultadoLibros.getInt("ANNO");
                libros[i][3]=resultadoLibros.getString("GENERO");
                i++;
            }
            resultadoLibros.close();
            librosDevuelto.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return libros;
    }
    
    /**
     * Hace un listado de todos los registros de la base de datos.
     * @return
     * @throws SQLException 
     */
    public Object[][] obtenerLibros() throws SQLException{
        Object[][] a = null;
        ResultSet resultadoQuery,resultNumero;
        Statement numeroRegistro; 
        PreparedStatement sentencia = conexion.prepareStatement(CARGA_PRINCIPAL);
        resultadoQuery=sentencia.executeQuery();
        int n;
        numeroRegistro=conexion.createStatement();
        resultNumero=numeroRegistro.executeQuery(NUMERO_REGISTRO);
        resultNumero.next();
        n=resultNumero.getInt(1);
        
        int i=0;
        a=new Object[n][4];
        
        while (resultadoQuery.next()) {
            a[i][0]=resultadoQuery.getString(1);
            a[i][1]=resultadoQuery.getString(2);
            a[i][2]=resultadoQuery.getInt(3);
            a[i][3]=resultadoQuery.getString(4);
            i++;
        }
        sentencia.close();
        resultadoQuery.close();
        return a;
    }
    
    
    /**
     * Obtiene los datos de un registro en concreto.
     * @param nombre
     * @return Devuelve el registro completo.
     * @throws SQLException 
     */
    public Object[] obtenerLibro(String nombre) throws SQLException{
        Object[] libro;
        PreparedStatement sentencia=conexion.prepareStatement(BUSCAR_LIBRO);
        ResultSet result;
        sentencia.setString(1, nombre);
        result=sentencia.executeQuery();
        result.next();
        libro=new Object[6];
        libro[0]=result.getString("NOMBRE");
        libro[1]=result.getString("AUTOR");
        libro[2]=result.getInt("ANNO");
        libro[3]=result.getString("GENERO");
        libro[4]=result.getString("ARGUMENTO");
        String url=result.getString("IMAGEN");
        libro[5]=url;
        result.close();
        sentencia.close();
        return libro;
    }
    

    /**
     * Cierra la conexión con la base de datos.
     * @throws SQLException 
     */
    public void cerrarConexion() throws SQLException {
            conexion.close();
    }
}

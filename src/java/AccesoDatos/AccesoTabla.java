
package AccesoDatos;

import AccesoDatos.Conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccesoTabla extends Conexion {
    // Declaración de las variables que interactuarán con las consultas
    private int id;
    private String campo1;
    private ResultSet resultado;
    private String tabla = "tabla";
    
    public AccesoTabla(){
        Conectar();
    }
    
    public ResultSet Listado() throws Exception {
        try {
            //  Usa la comunicación y ejecuta de ella un Query
            getStmt();
            resultado = stmt.executeQuery("select * from " + tabla);
            return resultado;
        } catch (Exception e){
            System.err.println("SQLException: " + e.getMessage());
            return null;
        }
    }
    
    //  Retorna un registro si es que el ID de dicho registro existe
    public ResultSet BuscarExistente(int id){
        try {
            getStmt();
            resultado = stmt.executeQuery("select * from " + tabla + " where id='" + id + "'");
            return resultado;
        } catch (Exception e){
            System.err.println("SQLException: " + e.getMessage());
            return null;
        }
    }
    
    //  Realiza una búsqueda por la cadena "campo1"
    public ResultSet BuscarPorCampo(String Campo1){
        try {
            getStmt();
            resultado = stmt.executeQuery("select * from " + tabla + " where campo1='" + Campo1 + "'");
            return resultado;
        } catch (Exception e){
            System.err.println("SQLException: " + e.getMessage());
            return null;
        }
    }
    
}
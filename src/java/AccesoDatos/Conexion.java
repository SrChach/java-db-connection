/*  Archivo/Clase que establece los parámetros de conexión
*/

package AccesoDatos;
import java.sql.*;


public class Conexion {
    protected Connection con;
    protected Statement stmt;
    
    //  Declaración de las constantes de configuración
    private String serverName = "localhost";
    private String portNumber = "3307";
    private String databaseName = "base";
    private String url = "jdbc:mysql://localhost:" + portNumber + "/" + databaseName;
    private String username = "root";
    private String password = "root";
    
    private String errString;
    
    public Conexion  (){}
    
    public String getConnectionUrl(){
        return url;
    }
    
    public Connection Conectar(){
        con = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            //  Establecemos la conexión
            con = DriverManager.getConnection(getConnectionUrl(), username, password);
            stmt = con.createStatement();
            System.out.println("Conectado");
        } catch (Exception e){
            errString = "Error mientras se conectaba a la base de datos: \n";
            System.out.println(errString + e);
            return null;
        }
        return con;
    }
    
    public void Desconectar (){
        try {
            stmt.close();
            con.close();
        } catch (SQLException e){
            errString = "Error mientras se cerraba la conexión: \n";
            System.out.println(errString + e);
        }
    }
    
    public Statement getStmt(){
        return this.stmt;
    }
}

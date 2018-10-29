

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import AccesoDatos.AccesoTabla;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatosTabla extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /*  Recepción del formulario por el método POST. Este servlet solo responderá al darle click al botón de búsqueda en 'index.html'
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                try{
                    ResultSet res;
                    AccesoTabla miTabla = new AccesoTabla();
                    int id = 0;
                    String campo1 = "";
                    String campo2 = "";
                    
                    out.println("<!DOCTYPE html>" +
                                "<html>" +
                                "<head>" +
                                    "<title>Listado de la tabla</title>" +            
                                    "<link href='estilo.css' rel='stylesheet' type='text/css'/>" +
                                "</head>" +
                                "<body>" +
                                    "<h1>Resultados que coinciden con la busqueda</h1>" +
                                    "<a href=\"index.html\"><button>Volver</button></a>"+
                                    //  Tabla con los contenidos de la busqueda
                                    "<table align='center'>" +
                                        "<tr class='titulo_tabla'><td>Listado de campos</td></tr>" +
                                        "<tr><td>ID</td><td>Campo1</td><td>Campo2</td></tr>");
                                        //  Criterios que validan los campos de entrada (sólo si están vacíos o no)
                                        if((request.getParameter("id") == "") && request.getParameter("campo1") != "")
                                            res = miTabla.BuscarPorCampo(request.getParameter("campo1"));
                                        else if((request.getParameter("id") != "") && request.getParameter("campo1") == "")
                                            res = miTabla.BuscarExistente(Integer.parseInt(request.getParameter("id")));
                                        else 
                                            res = miTabla.Listado();
                                        //  Impresión de los datos obtenidos de la consulta, en formato <tr><td><td></tr> (o tableRow, tableData)
                                        while(res.next()) {
                                            id = res.getInt("id");
                                            campo1 = res.getString("campo1");
                                            campo2 = res.getString("campo2");
                                            out.println("<tr><td>" + id + "</td><td>" + campo1 + "</td><td>" + campo2 + "</td></tr>");
                                        }
                    out.println(    "<table>" +
                                "</body>" +
                                "</html>");
                    out.close();
                } catch (Exception e){
                    Logger.getLogger(DatosTabla.class.getName()).log(Level.SEVERE, null, e);
                }
    }

}

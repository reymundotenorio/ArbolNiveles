/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrp;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author reymundo
 */
public class MRP {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     * @throws
     * com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        // TODO code application logic here

        Conexion con = new Conexion();
        con.Conectar(credenciales.UserPass.User, credenciales.UserPass.Pass);

        Composicion Component = new Composicion();  
        String Prod = "A";
        
        int ID_P = 0;
        
        try{
            
            ResultSet resultado = Conexion.consulta("SELECT ID_PRODUCTO FROM PRODUCTO WHERE NOMBRE = '"+Prod+"'");
            
            while(resultado.next()){
                
                ID_P = resultado.getInt(1);
            }
            
        }catch(SQLException ex){
            
        }

        Component.IniciarOrden(ID_P, Prod);

        ArrayList<Org> list = Component.list;

        Collections.sort(list);
        Component.IniciarNodos();
        Component.OrdenarNodos(list);
        Component.MostrarNodos();

        

    }

}

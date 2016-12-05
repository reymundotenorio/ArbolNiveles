package mrp;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.IOException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Reymundo Tenorio
 */
public class Conexion {

    public static Connection con;
    public static Statement state, state1;
    public static ResultSet result, result1;

    public void Conectar(String user, String pass)
            throws SQLException, ClassNotFoundException, IOException, MessagingException {

        try {

//OBTENER LA CONEXIÓN          
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost/MRP", user, pass);

//INFORMACIÓN DE LA CONEXIÓN            
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("\nDriver Information");
                System.out.println("Driver Name: " + meta.getDriverName());
                System.out.println("Driver Version: " + meta.getDriverVersion());
                System.out.println("\nDatabase Information ");
                System.out.println("Database Name: " + meta.getDatabaseProductName());
                System.out.println("Database Version: " + meta.getDatabaseProductVersion());
            }

            state = con.createStatement(result.TYPE_SCROLL_SENSITIVE, result.CONCUR_UPDATABLE);

            System.out.println("Conexión establecida a la Base de Datos");

            JOptionPane.showMessageDialog(null, "Conexión Establecida", "Conexión Establecida", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error al conectar a la Base de Datos: " + se.getMessage());
          
        }
    }

//REALIZAR CONSULTA Y RETORNAR VALORES DE LA CONSULTA
    public static ResultSet consulta(String sql) throws SQLException {

        state1 = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, result1.CONCUR_READ_ONLY);
        result1 = state1.executeQuery(sql);
        return result1;
    }


}

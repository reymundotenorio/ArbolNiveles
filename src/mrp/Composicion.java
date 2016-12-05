/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrp;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author reymundo
 */
public class Composicion extends JFrame {

    public ArrayList<Org> list = new ArrayList<>();

    void IniciarOrden(int ID_Producto, String Prod) {

        String Producto = Prod;

        list.add(new Org(ID_Producto, ID_Producto, Nivel, Producto, Producto));

        if (hasComposicion(ID_Producto)) {
            getComposicion(ID_Producto);
        }

    }

    public boolean hasComposicion(int PRINCIPAL) {

        boolean flag = false;

        try {

            ResultSet resultado = Conexion.consulta("SELECT ID_COMPOSICION FROM COMPOSICIONV WHERE ID_PRINCIPAL = " + PRINCIPAL + "");

            while (resultado.next()) {
                int ID = resultado.getInt(1);
                flag = true;
            }
        } catch (SQLException ex) {
            System.err.println("Error comprobar: " + ex.getMessage());
        }

        return flag;
    }

    int Nivel = 0;

    public void getComposicion(int PRINCIPAL) {

//        System.out.println("ID_PRINCIPAL: "+PRINCIPAL);
        
        try {

            ResultSet resultado = Conexion.consulta("SELECT * FROM COMPOSICIONV WHERE ID_PRINCIPAL = " + PRINCIPAL + "");

            while (resultado.next()) {

                int ID_C = resultado.getInt(1);
                String Princ = resultado.getString(2);
                String Secund = resultado.getString(3);
                
                int ID_P = resultado.getInt(4);
                int ID_S = resultado.getInt(5);
                       

                Nivel++;

                list.add(new Org(ID_P, ID_S, Nivel, Princ, Secund));
               
             
                if (hasComposicion(ID_S)) {

                    getComposicion(ID_S);

                }

                Nivel--;

            }
        } catch (SQLException ex) {
            System.err.println("Error Get: " + ex.getMessage());
        }

    }

    final mxGraph graph = new mxGraph();
    Object parent = graph.getDefaultParent();

    mxICell[] Nodos = new Nodos().getNodos();

    public void IniciarNodos() {

        graph.getModel().beginUpdate();
        try {

            try {

                ResultSet resultado = Conexion.consulta("SELECT ID_PRODUCTO, NOMBRE FROM PRODUCTO");

                while (resultado.next()) {
                    int ID = resultado.getInt(1);
                    String Nombre = resultado.getString(2);

                    Nodos[ID] = (mxICell) graph.insertVertex(parent, null, Nombre, 0, 0, 80, 30);

                }

            } catch (SQLException ex) {
            }

        } finally {
            graph.getModel().endUpdate();
        }
    }

    public void OrdenarNodos(ArrayList<Org> list) {

        int nivel = 0;
        String Orden = "";
        graph.getModel().beginUpdate();
        try {
            for (Org lista : list) {
                int ID_Principal = lista.getID_Princ();
                int ID_Secundario = lista.getID_Secund();

                int N_Nivel = lista.getNivel();
                String Producto = lista.getProducto();
                String Sub_Producto = lista.getSub_Producto();

                mxICell Padre = Nodos[ID_Principal];
                mxICell Hijo = Nodos[ID_Secundario];

                if (nivel == N_Nivel) {

                    if (nivel == 0) {
                        Orden = "Nivel " + N_Nivel + "-> " + Orden + Producto + "\t";

                    } else {
                        Orden = Orden + Sub_Producto + "(" + Producto + ")" + "\t";

                        graph.insertEdge(parent, null, "", Padre, Hijo);
                    }

                } else {
                    Orden = Orden + "\n" + "Nivel " + N_Nivel + "-> " + Sub_Producto + "(" + Producto + ")" + "\t";
                    graph.insertEdge(parent, null, "", Padre, Hijo);

                }

                nivel = N_Nivel;

            }

        } finally {
            graph.getModel().endUpdate();
        }

        System.err.println(Orden);

    }

    public void MostrarNodos() {
        mxIGraphLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(graph.getDefaultParent());
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
       // getContentPane().setLayout(new BorderLayout());
        
        this.setLayout( new GridBagLayout() );
        this.add(graphComponent, new GridBagConstraints());
     //   getContentPane().add(graphComponent, BorderLayout.CENTER);

        System.out.println();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }

}

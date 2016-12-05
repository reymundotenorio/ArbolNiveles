/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organigrama;

/**
 *
 * @author reymundo
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class DeleteExample extends JFrame {

    private static final long serialVersionUID = 8083868183987456695L;

    mxICell a,b,c,d,e,f,g,h;

    public DeleteExample() {

        super("Prueba");

        final mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {

            a = (mxICell) graph.insertVertex(parent, null, "a", 0, 0, 80, 30);
            b = (mxICell) graph.insertVertex(parent, null, "b", 0, 0, 80, 30);
            c = (mxICell) graph.insertVertex(parent, null, "c", 0, 0, 80, 30);
            d = (mxICell) graph.insertVertex(parent, null, "d", 0, 0, 80, 30);
            e = (mxICell) graph.insertVertex(parent, null, "e", 0, 0, 80, 30);
            f = (mxICell) graph.insertVertex(parent, null, "f", 0, 0, 80, 30);
            g = (mxICell) graph.insertVertex(parent, null, "g", 0, 0, 80, 30);
            h = (mxICell) graph.insertVertex(parent, null, "h", 0, 0, 80, 30);

            graph.insertEdge(parent, null, "", a, b);
            graph.insertEdge(parent, null, "", a, c);
            graph.insertEdge(parent, null, "", b, d);
            graph.insertEdge(parent, null, "", b, e);
            graph.insertEdge(parent, null, "", c, f);
            graph.insertEdge(parent, null, "", c, g);
            graph.insertEdge(parent, null, "", e, h);

        } finally {
            graph.getModel().endUpdate();
        }

        // define layout
        mxIGraphLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(graph.getDefaultParent());
/*
        JButton button = new JButton( "Delete edge from 'b' to 'e'");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                graph.getModel().beginUpdate();
                try {

                    Object[] edges = graph.getEdgesBetween( b, e);

                    for( Object edge: edges) {
                        graph.getModel().remove( edge);
                    }

                } finally {
                    graph.getModel().endUpdate();
                }
            }
        });
*/

        JPanel toolBar = new JPanel();
     /*   toolBar.add( button); */

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(graphComponent, BorderLayout.CENTER);
        getContentPane().add( toolBar, BorderLayout.NORTH);

    }
    public static void main(String[] args) {
        DeleteExample frame = new DeleteExample();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
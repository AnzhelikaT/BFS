package main.java;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {

    public JPanel createControlPanel() {

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);

		Graph sgv = new DownloadGraph().downloadGraph();
		Layout<Integer, String> layout = new CircleLayout(sgv);
        layout.setSize(new Dimension(250,250));
        VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,250));        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
            // Create a graph mouse and add it to the visualization component
          DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
          gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
          vv.setGraphMouse(gm);
          vv.setLocation(50,0);
  		  vv.setSize(400, 280);
          controlPanel.add(vv);

        JLabel headline = new JLabel("Начало:");
        headline.setLocation(10,300);
        headline.setSize(150,20);
        headline.setVisible(true);
        controlPanel.add(headline);

        JTextField vertexName = new JTextField(3);
        vertexName.setLocation(160,300);
        vertexName.setSize(40,20);
        vertexName.setVisible(true);
        controlPanel.add(vertexName);

        JLabel headline1 = new JLabel("Конец:");
        headline1.setLocation(10,320);
        headline1.setSize(150,20);
        headline1.setVisible(true);
        controlPanel.add(headline1);

        JTextField vertexName1 = new JTextField(3);
        vertexName1.setLocation(160,320);
        vertexName1.setSize(40,20);
        vertexName1.setVisible(true);
        controlPanel.add(vertexName1);

        JButton search = new JButton("Поиск");
        search.setLocation(250,300);
        search.setSize(70,20);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BFS bfs = new BFS();
                try {
                    String resultWay = bfs.search(sgv, Integer.parseInt(vertexName.getText()), Integer.parseInt(vertexName1.getText()));
                    JOptionPane.showMessageDialog(null, resultWay, "Кратчайший путь:", JOptionPane.PLAIN_MESSAGE);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Не были заполнены все поля", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        search.setVisible(true);
        controlPanel.add(search);

        controlPanel.setOpaque(true);
        return controlPanel;
    }
}

package main.java;

import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.io.PajekNetReader;

public class DownloadGraph {
    public Graph graph = new UndirectedSparseGraph();
    public  Graph downloadGraph() {

        String nameFile = getNameFile();

        Factory eFactory = new Factory() {
            int i;
            @Override
            public Object create() {
                return i++;
            }
        };
        PajekNetReader pnr = new PajekNetReader(eFactory);

        try {
            graph = pnr.load(nameFile, graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

	    public static String getNameFile(){
	       JFileChooser chooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose graph ","net");
	        chooser.setFileFilter(filter);
	        chooser.showOpenDialog(null);
            try {
                return chooser.getSelectedFile().getAbsolutePath();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "Файл не был загружен!", "", JOptionPane.PLAIN_MESSAGE);
                return null;
            }

	    }
}

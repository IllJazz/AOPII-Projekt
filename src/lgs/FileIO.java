package lgs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ilja Hirse
 */
public class FileIO {
	
	private FileReader file = null;
    private BufferedReader buff = null;
    private List lines = new ArrayList();							//zeilenweise Strings
    private ArrayList<String[]> teil = new ArrayList<String[]>() ;	//gesplittete Strings
    private double[][] matrix ;										//fertige Matrix
	
	public FileIO() {
	
	    // Zeilenweises Einlesen der Datei
	    try {
	        file = new FileReader("matrix.txt");
	        buff = new BufferedReader(file);
	        String line;
	        while ((line = buff.readLine()) != null) {
	            lines.add(line);
	        }
	    } catch (IOException e) {
	        System.err.println("Dateifehler :" + e);
	    } finally {
	        try {
	            buff.close();
	            file.close();
	        } catch (IOException e) {
	            System.err.println("Dateifehler :" + e);
	        }
	    }
	   
	    // aufsplitten der Strings und Ablage in Feld
	    for (int i =0;i<lines.size();i++) {
			teil.add(lines.get(i).toString().split(";"));
	    }
	    
	    // String-ArrayList in Double-Array konvertieren
	    matrix = new double[lines.size()][teil.get(0).length];
	    for (int j=0; j< lines.size();j++) {
	    	for (int k=0;k<teil.get(j).length;k++) {
	    		matrix[j][k] = Double.parseDouble(teil.get(j)[k]); 
	    	}
	    }
	}
	
	public double[][] getMatrix() {
		return matrix;
		
	}
}

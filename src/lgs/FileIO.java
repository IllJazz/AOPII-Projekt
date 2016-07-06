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
	    // Überprüfung ob jede Zeile gleiche Anzahl an Einträgen hat
	    for (int i =1;i<lines.size();i++) {
	    	if (teil.get(i).length<teil.get(i-1).length)
	    		System.out.println("Anzahl der Werte pro Zeile müssen gleich sein");
	    }
	    // maximale Anzahl an Einträgen pro Zeile bestimmen
	    int m=0;
	    for(int i=0;i<lines.size();i++) {
	    	if(m<teil.get(i).length)
	    		m=teil.get(i).length;
	    }
	    
	    // String-ArrayList in Double-Array konvertieren
	    matrix = new double[lines.size()][m];
	    for (int j=0; j< lines.size();j++) {
	    	for (int k=0;k<teil.get(j).length;k++) {
	    		try {
					matrix[j][k] = Double.parseDouble(teil.get(j)[k]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    }
	}
	
	public double[][] getMatrix() {
		return matrix;
		
	}
}

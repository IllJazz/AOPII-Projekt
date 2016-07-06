package lgs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
	
	    // Zeilenweises Einlesen der Datei in ArrayList
	    try {
	        file = new FileReader("matrix2.txt");
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
	   
	    // Aufsplitten der Strings und Ablage in ArrayList[]
	    for (int i =0;i<lines.size();i++) {
			teil.add(lines.get(i).toString().split(";"));
	    }
	    // �berpr�fung ob jede Zeile gleiche Anzahl an Eintr�gen hat
	    int m=0;
	    for (int i =1;i<lines.size();i++) {
	    	if(m<teil.get(i-1).length)
	    		m=teil.get(i-1).length;
	    	if (teil.get(i).length<teil.get(i-1).length)
	    		System.out.println("Anzahl der Werte pro Zeile m�ssen gleich sein"); //throw ...
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

	public void writeFile(Matrix matrix) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("../AOPII-Projekt/matrix_out.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    bw = new BufferedWriter(fw);

	    try {
			bw.write(matrix.toFileString());
	    	//bw.write("test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double[][] getMatrix() {
		return matrix;
		
	}
}

package lgs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zu Einlesen, Speichern und Verarbeiten von Dateien und Strings 
 * zur Weiterverarbeitung in der Klasse Matrix
 * @author Ilja Hirse
 */
public class FileIO {
	
	private FileReader file = null;
    private BufferedReader buff = null;
    private List<String> lines = new ArrayList<String>();			//zeilenweise Strings
    private ArrayList<String[]> teil = new ArrayList<String[]>() ;	//gesplittete Strings
    private double[][] matrix ;										//fertige Matrix

    /**
     * Konstruktor der Klasse 	 
     * @param 
     * @returns
     **/    
	public FileIO() {
	}

    /**
     * Erstellt einen zweidimensionalen Double Array
     * der die einzelnen Elemente des LGS in Matrixform enthält.
     * Daten werden aus einer String Arraylist konvertiert, 
     * welche aus einem Semikolongetrennten String erzeugt wurde.   	 
     * @param 
     * @returns matrix Double Array, zur Weiterverarbeitung in der Klasse Matrix
     **/    
 	public double[][] createMatrix() {
 	 	if (lines.isEmpty()) {
 	 		return null;
 		}
		// Aufsplitten der Strings und Ablage in ArrayList[]
	    for (int i =0;i<lines.size();i++) {
			teil.add(lines.get(i).toString().split(";"));
	    }
	    // Ueberpruefung ob jede Zeile gleiche Anzahl an Eintraegen hat
	    //
	    int n=0;
	    for (int i =1;i<lines.size();i++) {
	    	if(n<teil.get(i-1).length)
	    		n=teil.get(i-1).length;
	    	if (teil.get(i).length<teil.get(i-1).length) {
	    		System.out.println("Anzahl der Einträge pro Zeile stimmt nicht überein");
	    		return null; 
	    	}
	    }
	    int m=0;
	    for (int i =0;i<lines.size();i++) {
	    	if (m<teil.get(i).length)
	    		m=teil.get(i).length;
	    }
//	    System.out.println(m);
	    // String-ArrayList in zweidimensionalen Double-Array konvertieren
	    matrix = new double[lines.size()][m];
	    for (int j=0; j< lines.size();j++) {
	    	for (int k=0;k<m;k++) {
	    		try {
	    			matrix[j][k] = Double.parseDouble(teil.get(j)[k]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
	    	}
	    }
	    return matrix;
	}

 	/**
     * Liest per Filereader und BufferedReader den Inhalt einer Textdatei zeilenweise in eine ArrayList<String> ein 	 
     * @param filename String, Pfad+Dateiname der einzulesenden Datei
     * @returns output String, zur Ausgabe des Dateiinhalts im entsprechenden Textfeld der GUI
     **/    
	public String readFile(String filename) {

	    // Zeilenweises Einlesen der Datei in ArrayList

		try {
		    String line;
	        file = new FileReader(filename);
	        buff = new BufferedReader(file);

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
		String output="";
		for (int i=0;i<lines.size();i++) {
			output+=lines.get(i).toString()+"\n";
			
		}
		return output;
	    
		//	for(int i=0; i<lines.size();i++)					//for testing only
		//		System.out.println(lines.get(i).toString());	//for testing only
	}
   
	/**
     * Liest per BufferedReader einen String ein und speichert diesen zeilenweise in einer ArrayList<Sring> 	 
     * @param matrix String, Matrixdaten in Stringform aus entsprechendem Textfeld der GUI
     * @returns
     **/	
	public void readString(String matrix) {
		BufferedReader reader = new BufferedReader(new StringReader(matrix));
		try {
			String line;
			while((line =reader.readLine()) != null) {
				lines.add(line);			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Speichert eine Instanz der Klasse Matrix als String in eine Textdatei	 
     * @param matrix Matrix, zuspeichernde Instanz der Klasse Matrix
     * @param filename String, Name der zu speichernden Datei
     * @returns
     **/    
	public void writeFile(String filename, Matrix matrix) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    bw = new BufferedWriter(fw);

	    try {
			bw.write(matrix.toFileString());
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

    /**
     * Schreibt einen String in eine Textdatei und speichert diese 	 
     * @param filename String, Pfad+Dateiname der zu speichernden Datei
     * @param solution String, zu speichernder String
     * @returns
     **/    
	public void writeFile(String filename, String solution) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    bw = new BufferedWriter(fw);

	    try {
			bw.write(solution);
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
	
    /**
     * Getterfunktion für den zweidimensionalen Double Array, der die LGS Daten enthält 	 
     * @param 
     * @returns matrix Double[][], enthält LGS Daten x1-xn und b1-bn 
     **/    
	public double[][] getMatrix() {
		return matrix;
		
	}
}

package lgs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Ilja Hirse
 */
public class FileIO {
	
	private FileReader file = null;
    private BufferedReader buff = null;
    private List<String> lines = new ArrayList();							//zeilenweise Strings
    private ArrayList<String[]> teil = new ArrayList<String[]>() ;	//gesplittete Strings
    private double[][] matrix ;										//fertige Matrix
//    private String filename =""; 
//	String matrix2="2;1;3;1\n1;2;3;4\n4;3;2;1"; //for testing only
	public FileIO() {
		//readString(matrix2); //for testing only
		//readFile();
		//createMatrix();
	}

	
 	public void createMatrix() {
		  // Aufsplitten der Strings und Ablage in ArrayList[]
	    for (int i =0;i<lines.size();i++) {
			teil.add(lines.get(i).toString().split(";"));
	    }
	    // Ueberpruefung ob jede Zeile gleiche Anzahl an Eintraegen hat
	    int m=0;
	    for (int i =1;i<lines.size();i++) {
	    	if(m<teil.get(i-1).length)
	    		m=teil.get(i-1).length;
	    	if (teil.get(i).length<teil.get(i-1).length)
	    		System.out.println("Anzahl der Werte pro Zeile muessen gleich sein"); //throw ...
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
	
	public String readFile(String filename) {
	//	Scanner input= new Scanner(System.in);
	  //  System.out.print("Bitte geben Sie den Dateinamen (inklusive Dateiendung) für den Import des LGS an! > ");
	  //  filename = input.next();
	   // input.close();
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

	public void readString(String matrix) {
		BufferedReader reader = new BufferedReader(new StringReader(matrix));
		try {
			String line;
			while((line =reader.readLine()) != null) {
				lines.add(line);			
			}
		//	for(int i=0; i<lines.size();i++)					//for testing only
		//		System.out.println(lines.get(i).toString());	//for testing only
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeFile(Matrix matrix, String filename) {
		//Scanner input= new Scanner(System.in);
		//System.out.print("Bitte geben Sie den Dateinamen (inklusive Dateiendung) für den Export des LGS an! > ");
		//filename = input.next();
		// input.close();
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
	public double[][] getMatrix() {
		return matrix;
		
	}
}

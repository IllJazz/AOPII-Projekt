package lgs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Klasse für die Graphische Benutzeroberfläche zum komfortablen Bedienen aller Funktionen
 * @author Rick Werner
 */

public class GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = -7697154416089813186L;
	private String helptext =   "Das zu lösende LGS kann über die Funktion Datei>Öffnen aus einer Textdatei eingelesen werden,\n" +
							  	"oder per Eingabe über das große Textfeld im linken Bereich der GUI.\n\n" +
								
								"Die Form der Eingabe unterliegt dabei jeweils den folgenden Konventionen:\n\n" +
								
								"- alle Werte des LGS dürfen nur aus Zahlen, sowie + oder - als Vorzeichen bestehen\n" +
								"- die Werte müssen mittels Semikolon separiert sein\n" +
								"- es dürfen keine Variablen enthalten sein\n" +
								"- der letzte Eintrag in jeder Zeile ist das Ergebnis dieser Zeile\n" +
								"- fehlende Werte müssen auch separiert werden\n" +
								"- hinter dem letzten Wert wird kein Trennzeichen gesetzt\n\n" +
								
								"Bsp.\n" +
								"die folgende Zeile des LGS:\n" +
								"3x1 -4x2 +5x4 =6\n\n" +
								
								"muss diese Form in der Datei oder der Eingabe im Textfeld haben:\n" +
								"3;-4;0;5;6";
	
	
	JPanel panel;
	
	//Menueleiste
	JMenuBar menuBar;
	
	//Menue Datei
	JMenu fileMenu;
	//Menuepunkt "Matrix oeffnen" - zum oeffnen einer gespeicherten Matrix
	JMenuItem openItem;
	//Menuepunkt "Matrix speichern" - zum speichern einer eingegebenen Matrix, um sie wieder zu laden
	JMenuItem saveMatrixItem;
	//Menuepunkt "Ergebnis speicher" - zum speichern der gesamten Matrix inklusive Ergebnis, als Endstand
	JMenuItem saveResultItem;
	//Menuepunkt "Beenden" - beendet Programm
	JMenuItem closeItem;
	
	//Menue Hilfe
	JMenu helpMenu;
	//Menuepunkt "Hilfe"
	JMenuItem helpItem;
	//Menuepunkt "Ueber"
	JMenuItem ueberItem;
	
	//"Matrix berechnen"-Knopf - berechnet die eingegebene Matrix
	JButton calcButton;
	//"Beenden"-Knopf - beendet das Programm
	JButton closeButton;
	
	//Eingabefeld für die Ausgangsmatrix - Eingabe durch Benutzer 
	JTextArea enterMatrix;
	//Ausgabefeld für das berechnete Ergebnis
	JTextArea outMatrix;
	//Ausgabefeld für das berechnete Residuum
	JTextArea outResiduum;
	//Ausgabefeld für das berechnete Ea
	JTextField outa;
	//Ausgabefeld für das berechnete Er
	JTextField outr;

	
	//zur Erkennung der benoetigten Groeße
	Dimension size;

	
	
	public GUI(){
		this.setTitle("LGS-Löser");
		this.setSize(800,600);
		panel = new JPanel();
		//ohne Layoutmanager - um benutzerdefinierter zu arbeiten
		panel.setLayout(null);

		
		//Menue erstellen
		//Menueleiste wird erzeugt
		menuBar = new JMenuBar();
		//Menue Datei wird erzeugt
		fileMenu = new JMenu("Datei");
		//Menue Hilfe wird erzeugt
		helpMenu = new JMenu("Hilfe");
		//Menuepunkte erstellen
		openItem = new JMenuItem("Matrix laden");
		saveMatrixItem = new JMenuItem("Matrix speichern");
		saveResultItem = new JMenuItem("Ergebnis speichern");
		closeItem = new JMenuItem("Beenden");
		helpItem = new JMenuItem("Hilfe");
		ueberItem = new JMenuItem("Über");
		
		//Menuepunkte werden dem Datei-Menue hinzugefuegt
		fileMenu.add(openItem);
		fileMenu.add(saveMatrixItem);
		fileMenu.add(saveResultItem);
		fileMenu.add(closeItem);
		
		//Menuepunkt "Hilfe" wird dem Hilfe-Menue hinzugefuegt
		helpMenu.add(helpItem);
		helpMenu.add(ueberItem);
		
		//Datei und Hilfe - Menue werden der Menueleiste hinzugefuegt
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		//Menueleiste wird dem Hauptfenster hinzugefuegt
		this.add(menuBar, BorderLayout.NORTH);
		
		//Aktionsabfrage für Menue erstellen
		openItem.addActionListener(this);
		saveMatrixItem.addActionListener(this);
		saveResultItem.addActionListener(this);
		closeItem.addActionListener(this);
		helpItem.addActionListener(this);
		ueberItem.addActionListener(this);
		
		
		
		//Matrix Eingabefeld
		JLabel matrixEnterLabel = new JLabel("Geben Sie hier die Matrix Ihres LGS ein:");
		size=matrixEnterLabel.getPreferredSize();
		matrixEnterLabel.setBounds(20, 10, size.width, size.height);
		panel.add(matrixEnterLabel);
		enterMatrix = new JTextArea();
		enterMatrix.setBounds(20, 30, 350, 400);
		enterMatrix.setVisible(true);
		panel.add(enterMatrix);
		
		//"Berechnen"-Knopf erstellen
		calcButton = new JButton("Matrix berechnen");
		size=calcButton.getPreferredSize();
		calcButton.setBounds(130,450,size.width,size.height);
		
		//"Beenden"-Knopf erstellen
		closeButton = new JButton ("Beenden");
		size = closeButton.getPreferredSize();
		closeButton.setBounds(650, 500,size.width, size.height);
		
		//Aktionsabfrage für Knöpfe erstellen
		calcButton.addActionListener(this);
		closeButton.addActionListener(this);
		
		//Knöpfe dem Hauptfenster hinzufügen
		panel.add(calcButton);
		panel.add(closeButton);
		
		//Wenn Programm durch Kreuz beendet wird - Programm richtig beenden
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Lösung-Ausgabe 
		JLabel resultLabel = new JLabel("Die Lösung des LGS lautet:");
		size = resultLabel.getPreferredSize();
		resultLabel.setBounds(410, 10, size.width, size.height);
		this.panel.add(resultLabel);
		outMatrix = new JTextArea("Bitte geben Sie zuerst die Matrix Ihres LGS ein");
		outMatrix.setBounds(410, 30, 350, 120);
		outMatrix.setVisible(true);
		this.panel.add(outMatrix);
		
		//Residuum-Ausgabe
		JLabel residuumLabel = new JLabel("Residuum r=");
		size = residuumLabel.getPreferredSize();
		residuumLabel.setBounds(410, 160,size.width,size.height);
		this.panel.add(residuumLabel);
		outResiduum = new JTextArea("Bitte geben Sie zuerst die Matrix Ihres LGS ein");
		outResiduum.setBounds(410, 180, 350, 120);
		outResiduum.setVisible(true);
		this.panel.add(outResiduum);
		
		//Ea-Ausgabe
		JLabel aLabel = new JLabel("εa=");
		size = aLabel.getPreferredSize();
		aLabel.setBounds(410,310,size.width,30);
		this.panel.add(aLabel);
		outa = new JTextField("Bitte geben Sie zuerst die Matrix Ihres LGS ein");
		outa.setBounds(410,335, 350, 30);
		outa.setVisible(true);
		this.panel.add(outa);
		
		//Er-Ausgabe
		JLabel rLabel = new JLabel("εr=");
		size = rLabel.getPreferredSize();
		rLabel.setBounds(410,380, size.width,size.height);
		this.panel.add(rLabel);
		outr = new JTextField("Bitte geben Sie zuerst die Matrix Ihres LGS ein");
		outr.setBounds(410, 395, 350, 30);
		outr.setVisible(true);
		this.panel.add(outr);
		
		this.add(panel);
	}

	//Abfrage für Handlungen
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//"Berechnen-Knopf" gedrückt - Überprüfung Fälle, wie viele Lösungen das LGS hat:
		//	1 Lösung: Berechnung und Ausgabe von Lösung, Residuum, Ea, Er
		//	mehr als eine Lösung: Textausgabe
		//	keine Lösung: Textausgabe
		if(e.getSource()==this.calcButton){
			
			//Fehleingaben abfangen
			try {
				FileIO matrix = new FileIO();
				matrix.readString(enterMatrix.getText());
				Matrix ergebnis = new Matrix(matrix.createMatrix());
				LESType type = ergebnis.getLESType();
				switch(type){
					case ONE:
						outMatrix.setText(Matrix.toString(ergebnis.solve()));
						outResiduum.setText(Matrix.toString(ergebnis.getResiduum()));
						outa.setText(Matrix.toString(ergebnis.getEa()));
						outr.setText(Matrix.toString(ergebnis.getEr()));
						break;
					case MULTIPLE:
						outMatrix.setText("Es gibt mehr als eine Lösung");
						outResiduum.setText("-");
						outa.setText("-");
						outr.setText("-");
						break;
					case NONE:
						outMatrix.setText("Es gibt keine Lösung");
						outResiduum.setText("-");
						outa.setText("-");
						outr.setText("-");
						break;
				}
				
				JOptionPane.showMessageDialog(null, "Das Ergebnis wurde berechnet.");
				this.panel.repaint();
			} catch(Exception exception) {
				exception.printStackTrace();
				JOptionPane.showMessageDialog(null, "Die Eingabe ist unvollständig oder fehlerhaft!");
			}
			

		}
		//Beenden-Knopf im Hauptfenster - Programm vollstaendig beenden
		if (e.getSource()==this.closeButton){
			System.exit(0);
		}
		//Matrix oeffnen - vom Benutzer gewaehlter Pfad an FileIO-Klasse, Funktion readFile übergeben
		if (e.getSource()==this.openItem){
			JFileChooser openFileChooser = new JFileChooser();
			int openPress = openFileChooser.showDialog(null, "Matrix öffnen");
			String path="";
			path = openFileChooser.getSelectedFile().getAbsolutePath();
			FileIO datei = new FileIO();
			enterMatrix.setText(datei.readFile(path));
			if(openPress == JFileChooser.APPROVE_OPTION){
				System.out.println("Gewählte Datei zum Öffnen ist: " + path);
			}
		}
		//Matrix speichern - vom Benutzer gewaehlter Speicherpfad an FileIO-Klasse, Funktion writeFile übergeben
		if (e.getSource()==this.saveMatrixItem){
			JFileChooser saveFileChooser = new JFileChooser();
			int savePress = saveFileChooser.showDialog(null,"Matrix speichern");
			String path="", mat = enterMatrix.getText();
			path = saveFileChooser.getSelectedFile().getAbsolutePath();
			FileIO datei = new FileIO();
			datei.writeFile(path,mat);
			if(savePress == JFileChooser.APPROVE_OPTION)	
				System.out.println("Die Datei wurde gespeichert unter: " + path);
		}
		//Ergebnis speichern - alle Felder auslesen: Ausgangsmatrix und alle Ergebnisse in beschriebener Formatierung in gewähltem Pfad speichern
		if (e.getSource()==this.saveResultItem){
			JFileChooser saveFileChooser = new JFileChooser();
			int savePress = saveFileChooser.showDialog(null,"Ergebnis speichern");
			String path="", mat = enterMatrix.getText(), sol = outMatrix.getText(), res=outResiduum.getText(),a= outa.getText(),r=outr.getText();
			String solution = "\nAusgangsmatrix:\n\n"+mat+"\nLösungsvektor:\n\n"+sol+"\n\nResiduum:\n\n"+res+"\n\nea:\n\n"+a+"\n\ner:\n\n"+r;
			path = saveFileChooser.getSelectedFile().getAbsolutePath();
			FileIO datei = new FileIO();
			datei.writeFile(path,solution);
			
			if(savePress == JFileChooser.APPROVE_OPTION)	
				System.out.println("Die Datei wurde gespeichert unter: " + path);
		}
		//Programm beenden, Menue
		if(e.getSource()==this.closeItem){
			System.exit(0);
		}
		//Hilfe Datei oeffnen
		if(e.getSource()==this.helpItem){
			//JOptionPane.showMessageDialog(null, "Hier kommt Ilja seine Beschreibung rein", "Hilfe", 1);
			JFrame helpWindow = new JFrame("Hilfe");
			helpWindow.setSize(640,480);
			helpWindow.setLayout(null);
			JTextArea helpText = new JTextArea(helptext);
			helpText.setBounds(10,10,620,400);
			helpWindow.add(helpText);
			helpWindow.setLocationRelativeTo(null);
			JButton okHelp = new JButton("OK");
			okHelp.setBounds(300,415,60,40);
			okHelp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					helpWindow.dispose();
				}
			});
			helpWindow.add(okHelp);
			helpWindow.setVisible(true);
		
		}
		if(e.getSource()==this.ueberItem){
			JOptionPane.showMessageDialog(null, "Erschaffen im Juli 2016 von Ilja Hirse, Markus Müller und Rick Werner", "Über", 1);
		}
	}
}

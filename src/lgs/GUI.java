package lgs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Rick Werner
 */

public class GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = -7697154416089813186L;
	
	JLabel label;
	JLabel resultLabel;
	
	JPanel panel;
	
	//Menue Leiste
	JMenuBar menuBar;
	//Menue Datei
	JMenu fileMenu;
	//Menue Hilfe
	JMenu helpMenu;
	//Menuepunkt oeffnen, schließen
	JMenuItem openItem;
	JMenuItem saveMatrixItem;
	JMenuItem saveResultItem;
	JMenuItem closeItem;
	JMenuItem helpItem;
	
	JButton calcButton;
	JButton closeButton;
	
	JTextArea enterMatrix;
	JTextArea outMatrix;
	JTextArea outResiduum;
	JTextField outa;
	JTextField outr;

	
	//ermoeglicht, dass die Groeße erkannt wird, die gebraucht wird
	Dimension size;

	
	
	public GUI(){
		this.setTitle("Matrix");
		this.setSize(800,600);
		panel = new JPanel();
		panel.setLayout(null);
		

		label = new JLabel("Geben Sie hier die Matrix Ihres LGS ein:");
		size=label.getPreferredSize();
		label.setBounds(20, 10, size.width, size.height);
		panel.add(label);

		//Menueleiste wird erzeugt
		menuBar = new JMenuBar();
		//Menue Datei wird erzeugt
		fileMenu = new JMenu("Datei");
		//Menue Hilfe wird erzeugt
		helpMenu = new JMenu("Hilfe");
		//Menuepunkt oeffnen
		openItem = new JMenuItem("Matrix laden");
		saveMatrixItem = new JMenuItem("Matrix speichern");
		saveResultItem = new JMenuItem("Ergebnis speichern");
		closeItem = new JMenuItem("Beenden");
		helpItem = new JMenuItem("Hilfe");
		
		//Menuepunkte werden dem Datei-Menue hinzugefuegt
		fileMenu.add(openItem);
		fileMenu.add(saveMatrixItem);
		fileMenu.add(saveResultItem);
		fileMenu.add(closeItem);
		
		helpMenu.add(helpItem);
		//Datei Menue wird der Menueleiste hinzugefuegt
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		//Menueleiste wird dem JFrame hinzugefuegt
		this.add(menuBar, BorderLayout.NORTH);
		//ActionListener
		openItem.addActionListener(this);
		saveMatrixItem.addActionListener(this);
		saveResultItem.addActionListener(this);
		closeItem.addActionListener(this);
		
		//Matrix Eingabefeld erstellen
		enterMatrix = new JTextArea();
		enterMatrix.setBounds(20, 30, 350, 400);
		enterMatrix.setVisible(true);
		panel.add(enterMatrix);
		
		calcButton = new JButton("Matrix berechnen");
		size=calcButton.getPreferredSize();
		calcButton.setBounds(130,450,size.width,size.height);
		
		closeButton = new JButton ("Beenden");
		size = closeButton.getPreferredSize();
		closeButton.setBounds(650, 500,size.width, size.height);
		
		
		calcButton.addActionListener(this);
		closeButton.addActionListener(this);
		
		panel.add(calcButton);
		panel.add(closeButton);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		//Berechnen GUI
		resultLabel = new JLabel();
		resultLabel.setText("Die Lösung des LGS lautet:");
		size = resultLabel.getPreferredSize();
		resultLabel.setBounds(410, 10, size.width, size.height);
		this.panel.add(resultLabel);
		
		//Hier kann direkt mit der Loesungsausgabe begonnen werden
		outMatrix = new JTextArea("Bitte geben Sie zuerst die Matrix Ihres LGS ein");
		outMatrix.setBounds(410, 30, 350, 120);
		outMatrix.setVisible(true);
		this.panel.add(outMatrix);
		
		JLabel residuumLabel = new JLabel("Residuum r=");
		size = residuumLabel.getPreferredSize();
		residuumLabel.setBounds(410, 160,size.width,size.height);
		this.panel.add(residuumLabel);
		outResiduum = new JTextArea("Bitte geben Sie zuerst die Matrix Ihres LGS ein");
		outResiduum.setBounds(410, 180, 350, 120);
		outResiduum.setVisible(true);
		this.panel.add(outResiduum);
		
		JLabel aLabel = new JLabel("ε\u2090=");
		size = aLabel.getPreferredSize();
		aLabel.setBounds(410,310,size.width,30);
		this.panel.add(aLabel);
		outa = new JTextField("Bitte geben Sie zuerst die Matrix Ihres LGS ein");
		outa.setBounds(410,335, 350, 30);
		outa.setVisible(true);
		this.panel.add(outa);
		
		JLabel rLabel = new JLabel("ε\u1D63=");
		size = rLabel.getPreferredSize();
		rLabel.setBounds(410,380, size.width,size.height);
		this.panel.add(rLabel);
		outr = new JTextField("Bitte geben Sie zuerst die Matrix Ihres LGS ein");
		outr.setBounds(410, 395, 350, 30);
		outr.setVisible(true);
		this.panel.add(outr);
		
		
		this.add(panel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==this.calcButton){
			
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
					case NONE:
						outMatrix.setText("Es gibt keine Lösung");
						outResiduum.setText("-");
						outa.setText("-");
						outr.setText("-");
						break;
					case MULTIPLE:
						outMatrix.setText("Es gibt mehr als eine Lösung");
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
		
		if (e.getSource()==this.closeButton){
			System.exit(0);
		}
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
		if (e.getSource()==this.saveResultItem){
			JFileChooser saveFileChooser = new JFileChooser();
			int savePress = saveFileChooser.showDialog(null,"Ergebnis speichern");
			String path="", mat = enterMatrix.getText(), sol = outMatrix.getText(), res=outResiduum.getText(),a= outa.getText(),r=outr.getText();
			String solution = "Ausgangsmatrix:\n\n"+mat+"\nLösungsvektor:\n\n"+sol+"\n\nResiduum:\n\n"+res+"\n\nea:\n\n"+a+"\n\ner:\n\n"+r;
			path = saveFileChooser.getSelectedFile().getAbsolutePath();
			FileIO datei = new FileIO();
			datei.writeFile(path,solution);
			
			if(savePress == JFileChooser.APPROVE_OPTION)	
				System.out.println("Die Datei wurde gespeichert unter: " + path);
		}
		if(e.getSource()==this.closeItem){
			System.exit(0);
		}
		if(e.getSource()==this.helpItem){
			JOptionPane.showMessageDialog(null, "Hier kommt Ilja seine Beschreibung rein");
		}
		
		
	}
	
	
	// ε

}

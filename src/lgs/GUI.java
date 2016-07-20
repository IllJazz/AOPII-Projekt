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
	JMenuItem saveItem;
	JMenuItem closeItem;
	JMenuItem helpItem;
	
	JButton calcButton;
	JButton closeButton;
	
	JTextArea enterMatrix;
	JTextArea outMatrix;
	JTextArea outResiduum;
	JTextField oute;
	JTextField outa;
	
	//ermoeglicht, dass die Groeße erkannt wird, die gebraucht wird
	Dimension size;

	
	
	public GUI(){
		this.setTitle("Matrix");
		this.setSize(800,600);
		panel = new JPanel();
		panel.setLayout(null);
		

		label = new JLabel("Geben Sie hier Ihre Matrix ein:");
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
		openItem = new JMenuItem("Öffnen");
		saveItem = new JMenuItem("Speichern");
		closeItem = new JMenuItem("Beenden");
		helpItem = new JMenuItem("Hilfe");
		
		//Menuepunkte werden dem Datei-Menue hinzugefuegt
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(closeItem);
		
		helpMenu.add(helpItem);
		//Datei Menue wird der Menueleiste hinzugefuegt
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		//Menueleiste wird dem JFrame hinzugefuegt
		this.add(menuBar, BorderLayout.NORTH);
		//ActionListener
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
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
		
		
		
		this.add(panel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==this.calcButton){
			JOptionPane.showMessageDialog(null, "Hier muss mit Ilja zum einlesen verknuepft werden und Markus das Ergebnis ausgeben");
			
			resultLabel = new JLabel();
			resultLabel.setText("Die Matrix hat + hier String von Markus + Lösung");
			size = resultLabel.getPreferredSize();
			resultLabel.setBounds(410, 10, size.width, size.height);
			this.panel.add(resultLabel);
			
			//Hier kann direkt mit der Loesungsausgabe begonnen werden
			outMatrix = new JTextArea("Hier hinein kommt Deine Ausgabe als String, Markus");
			outMatrix.setBounds(410, 30, 350, 120);
			outMatrix.setVisible(true);
			this.panel.add(outMatrix);
			
			JLabel residuumLabel = new JLabel("Das Residuum:");
			size = residuumLabel.getPreferredSize();
			residuumLabel.setBounds(410, 160,size.width,size.height);
			this.panel.add(residuumLabel);
			outResiduum = new JTextArea("String Residuum");
			outResiduum.setBounds(410, 180, 350, 120);
			outResiduum.setVisible(true);
			this.panel.add(outResiduum);
			
			JLabel eLabel = new JLabel("E r ist:");
			size = eLabel.getPreferredSize();
			eLabel.setBounds(410,310,size.width,size.height);
			this.panel.add(eLabel);
			oute = new JTextField("Hier der e String");
			oute.setBounds(410,330, 350, 30);
			oute.setVisible(true);
			this.panel.add(oute);
			
			JLabel aLabel = new JLabel("||a|| ist:");
			size = aLabel.getPreferredSize();
			aLabel.setBounds(410,380, size.width,size.height);
			this.panel.add(aLabel);
			oute = new JTextField("Hier der a String");
			oute.setBounds(410, 400, 350, 30);
			oute.setVisible(true);
			this.panel.add(oute);

			this.panel.repaint();
		}
		
		if (e.getSource()==this.closeButton){
			System.exit(0);
		}
		if (e.getSource()==this.openItem){
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
		}
		if (e.getSource()==this.saveItem){
			//SPEICHERN FUNKTION
		}
		if(e.getSource()==this.closeItem){
			System.exit(0);
		}
		if(e.getSource()==this.helpItem){
			JOptionPane.showMessageDialog(null, "Hier kommt Ilja seine Beschreibung rein");
		}
		
		
	}
	
	
	

}

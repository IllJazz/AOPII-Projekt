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
	//Menuepunkt oeffnen, schlieﬂen
	JMenuItem openItem;
	JMenuItem closeItem;
	
	JButton calcButton;
	JButton closeButton;
	
	JTextArea enterMatrix;
	JTextArea outMatrix;
	
	//ermoeglicht, dass die Groeﬂe erkannt wird, die gebraucht wird
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
		//Menuepunkt oeffnen
		openItem = new JMenuItem("÷ffnen");
		closeItem = new JMenuItem("Beenden");
		
		//Menuepunkte werden dem Datei-Menue hinzugefuegt
		fileMenu.add(openItem);
		fileMenu.add(closeItem);
		//Datei Menue wird der Menueleiste hinzugefuegt
		menuBar.add(fileMenu);
		//Menueleiste wird dem JFrame hinzugefuegt
		this.add(menuBar, BorderLayout.NORTH);
		//ActionListener
		openItem.addActionListener(this);
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
			//Hier kann direkt mit der Loesungsausgabe begonnen werden
			outMatrix = new JTextArea("Hier hinein kommt Deine Ausgabe als String, Markus");
			outMatrix.setBounds(410, 30, 350, 400);
			outMatrix.setVisible(true);
			this.panel.add(outMatrix);
			
			resultLabel = new JLabel();
			resultLabel.setText("Das Ergebnis ist:");
			size = resultLabel.getPreferredSize();
			resultLabel.setBounds(410, 10, size.width, size.height);
			this.panel.add(resultLabel);
			
			this.panel.repaint();
		}
		
		if (e.getSource()==this.closeButton){
			System.exit(0);
		}
		if (e.getSource()==this.openItem){
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			String path="";
			path = fc.getSelectedFile().getAbsolutePath();
			FileIO datei = new FileIO();
			enterMatrix.setText(datei.readFile(path));
		}
		if(e.getSource()==this.closeItem){
			System.exit(0);
		}
		
		
	}
	
	
	

}

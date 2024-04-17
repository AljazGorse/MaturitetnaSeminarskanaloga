package Minolovec;

import javax.swing.*;

public class Poljubno extends Minolovec{
	
	Poljubno() {
		int x = Integer.parseInt(JOptionPane.showInputDialog("Vnesi velikost polja (število vrstic)"));
		this.stVrst = x;
		int y = Integer.parseInt(JOptionPane.showInputDialog("Vnesi velikost polja (število stolpcev)"));
		this.stStolp = y;
		int z = Integer.parseInt(JOptionPane.showInputDialog("Vnesi število min"));
		this.stMin = z;
	}
	
}

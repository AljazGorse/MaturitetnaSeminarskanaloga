package Minolovec;

import java.io.*;

public class Rezultat {
	
	Rezultat(){
		try {
			File konec = new File("Rezultati.txt");
			if (konec.createNewFile()) {} 
		    else {}
		} 
		catch (IOException e) {}
		}
	
	Cas cas;
	public void zapis(int rezultat, String casIgranja, int tezavnost) throws IOException {
		PrintWriter dat = new PrintWriter(new FileWriter("Rezultati.txt", true));
		
		if(tezavnost==1) {dat.write("(Lahko) ");}
		else if(tezavnost==2) {dat.write("(Normalno) ");}
		else if(tezavnost==3) {dat.write("(Težko) ");}
		else if(tezavnost==4) {dat.write("(Poljubno) ");}
		else {}
		
		if(rezultat==1) { 
			dat.write("Poraz, ");
		}
		else {
			dat.write("Zmaga ");
		}
		
		cas = new Cas();
		dat.write("čas: "+casIgranja+" sekund, ");
		dat.write("datum: "+cas.datum()+"\n");
		
		dat.close();
	}
}


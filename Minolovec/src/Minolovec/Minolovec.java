package Minolovec;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Minolovec {
	Minolovec(){
		
	}
	
	@SuppressWarnings("serial")
	class Mine extends JButton {
		int v;
		int s;
		
		Mine(int v, int s){
			this.v = v;
			this.s = s;
		}
	}
	
	int velikostPolja;
	int stVrst;
	int stStolp;
	int stMin;
	int sirina;
	int visina;
	int tezavnost;
	
	JFrame okvir = new JFrame("Minolovec");
    ImageIcon ikona = new ImageIcon("Mina.png");
    JLabel oznakaL = new JLabel();
    JPanel panelP = new JPanel();
    JPanel poljeP = new JPanel();
    
    Mine[][] poljeTab;
    ArrayList<Mine> mineTabL;
    Random rand = new Random();

    int klik = 0;
    boolean konecIgre = false;
    
    void tez(int tez) {	
    	this.velikostPolja = 40;
    	
    	if(tez==2) {
    		this.stMin = 40;
    		this.stVrst = 20;
    		this.stStolp = stVrst;
    		this.tezavnost = 2;
    	}
	    else if(tez==3){
	    	this.stMin = 99;
	    	this.stVrst = 35;
    		this.stStolp = stVrst;
    		this.tezavnost = 3;
	    }
	    else if(tez==4) {
	    	Poljubno tez4 = new Poljubno();
	    	this.stMin = tez4.stMin;
	    	this.stVrst = tez4.stVrst;
    		this.stStolp = tez4.stStolp;
    		this.tezavnost = 4;
	    }
	    else {
	    	this.stMin =10;
	    	this.stVrst = 8;
	    	this.stStolp = stVrst;
	    	this.tezavnost = 1;
	    }
    	
	    this.sirina = stStolp * velikostPolja;
	    this.visina = stVrst * velikostPolja;
	    this.poljeTab = new Mine[stVrst][stStolp];
	    
    }
    
    int st = 0;
	int sek = 0;
	String stevec = String.format("%03d", sek);
	
	Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			st += 1000;
		    sek = st/1000;
		    stevec = String.format("%03d", sek);
		    oznakaL.setText("Mine: " + Integer.toString(stMin) + " Čas: "+stevec);
		    }
		});
    
    void zacni() {
    	timer.start();
    }
    
    void ponastavicas() {
		timer.stop();
		st = 0;
		sek = 0;
	}
    
    Minolovec(int tez) {
    	tez(tez);
    	zacni();
    	
    	okvir.setIconImage(ikona.getImage());
        okvir.setSize(sirina, visina);
        okvir.setLocationRelativeTo(null);
        okvir.setResizable(false);
        okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okvir.setLayout(new BorderLayout());
        okvir.setVisible(false);
        
        JMenuBar meniVrstica;
        JMenu meni, podmeni;
        JMenuItem predmet;
        
        meniVrstica = new JMenuBar();
        meni = new JMenu("Nastavitve");
        podmeni = new JMenu("Nova igra");
     
        predmet = new JMenuItem("Lahko");
        predmet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ponastaviIgra(1);
            }
        });
        podmeni.add(predmet);
        
        predmet = new JMenuItem("Normalno");
        predmet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ponastaviIgra(2);
            }
        });
        podmeni.add(predmet);
        
        predmet = new JMenuItem("Težko");
        predmet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ponastaviIgra(3);
            }
        });
        podmeni.add(predmet);
        
        predmet = new JMenuItem("Poljubno");
        predmet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ponastaviIgra(4);
            	
            }
        });
        podmeni.add(predmet);
        
        meni.add(podmeni);
        
        predmet = new JMenuItem("Izhod");
        predmet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        meni.add(predmet);
        
        meniVrstica.add(meni);
        okvir.setJMenuBar(meniVrstica);
        
        oznakaL.setFont(new Font("Arial", Font.BOLD, 25));
        oznakaL.setHorizontalAlignment(JLabel.CENTER);
        oznakaL.setText("Mine: " + Integer.toString(stMin) + " Čas: "+stevec);
        oznakaL.setOpaque(true);

        panelP.setLayout(new BorderLayout());
        panelP.add(oznakaL);
        
        okvir.add(oznakaL, BorderLayout.NORTH);

        poljeP.setLayout(new GridLayout(stVrst, stStolp));
        okvir.add(poljeP);
    
        for (int v=0;v<stVrst;v++) {
            for (int s=0;s<stStolp;s++) {
                Mine polja = new Mine(v, s);
                poljeTab[v][s] = polja;

                polja.setFocusable(false);
                polja.setMargin(new Insets(0, 0, 0, 0));
                polja.setFont(new Font("Arial", Font.PLAIN, 25));
                polja.addMouseListener(new MouseAdapter() {
                	@Override
					public void mousePressed(MouseEvent e) {
                        if (konecIgre) {
                            return;
                        }
                        Mine polja1 = (Mine) e.getSource();

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (polja1.getText() == "") {
                                if (mineTabL.contains(polja1)) {
                                    try {
										razkrij();
									} catch (IOException e1) {}
                                }
                                else {
                                    try {
										minaPreglej(polja1.v, polja1.s);
									} catch (IOException e1) {}
                                }
                            }
                        }
                        
                        else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (polja1.getText() == "" && polja1.isEnabled()) {
                            	polja1.setText("?");
                            }
                            else if (polja1.getText() == "?") {
                            	polja1.setText("");
                            }
                        }
                    } 
                });
                
                poljeP.add(polja);
            }
        }
        okvir.setVisible(true);
        postaviMine();
    
    }
    
    void ponastaviIgra(int tez) { 
    	tezavnost = tez;
        klik = 0;
        konecIgre = false;
        okvir.dispose();
        ponastavicas();
        new Minolovec(tez);
    }
    
    void postaviMine() {
        mineTabL = new ArrayList<Mine>();
        int temp = stMin;
        while(temp>0) {
            int v = rand.nextInt(stVrst); 
            int s = rand.nextInt(stStolp);

            Mine polja = poljeTab[v][s]; 
            if (!mineTabL.contains(polja)) {
                mineTabL.add(polja);
                temp -= 1;
            }
        }
    }
    
    Rezultat rezultat = new Rezultat();
    
    void razkrij() throws IOException {
        for(int i=0;i<mineTabL.size();i++) {
            Mine polja = mineTabL.get(i);
            polja.setText("X");
        }
        konecIgre = true;
        rezultat.zapis(1,stevec,tezavnost);
        ponastavicas();
        oznakaL.setText("Konec Igre!");
    }
    
    void minaPreglej(int v, int s) throws IOException {
        if (v<0 || v>=stVrst || s<0 || s>=stStolp) {
            return;
        }

        Mine polja = poljeTab[v][s];
        if (!polja.isEnabled()) {
            return;
        }
        polja.setEnabled(false);
        klik += 1;
        
        int temp = 0;

        temp += stejMine(v-1, s-1); 
        temp += stejMine(v-1, s);   
        temp += stejMine(v-1, s+1);  

        temp += stejMine(v, s-1);    
        temp += stejMine(v, s+1);  

        temp += stejMine(v+1, s-1);  
        temp += stejMine(v+1, s);   
        temp += stejMine(v+1, s+1); 

        if (temp > 0) {
            polja.setText(Integer.toString(temp));
        }
        else {
        	polja.setText("");
            
          
            minaPreglej(v-1, s-1);   
            minaPreglej(v-1, s);     
            minaPreglej(v-1, s+1);    

            minaPreglej(v, s-1);     
            minaPreglej(v, s+1);    

            minaPreglej(v+1, s-1);   
            minaPreglej(v+1, s);      
            minaPreglej(v+1, s+1);
        }

        if (klik==stVrst*stStolp - mineTabL.size()) {
            konecIgre = true;
            rezultat.zapis(0,stevec,tezavnost);
            ponastavicas();
            oznakaL.setText("Zmaga!");
        }
    }

    int stejMine(int v, int s) {
        if(v<0 || v>=stVrst || s<0 || s>=stStolp) {
            return 0;
        }
        if (mineTabL.contains(poljeTab[v][s])) {
            return 1;
        }
        return 0;
    }
    
}


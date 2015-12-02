package presentacio;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import domini.Joc;

public class TaulellGrafic extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaulellGrafic window = new TaulellGrafic();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
    private final Color CASELLA_GRIS = Color.GRAY;
    private final Color CASELLA_BLANCA = Color.WHITE;
    

	private static final long serialVersionUID = 1L;

    private static JLabel lblEstat;
    private static JButton btnReset;
    private static JButton btnDesfer;

    private CasellaGrafica[][] casellesTaulell;
    private ImageIcon imatgeCasella;
    private ImageIcon imatgeCasellaSeleccionada;
    private Joc joc;

	private int animacio = 0;    

	
	
	/*
     * Constructor
     */
	TaulellGrafic() throws Exception{
		
		joc = new Joc();
		
		casellesTaulell = new CasellaGrafica[joc.mida][joc.mida];
		
		this.setBounds(0, 0, 700, 700);
		
		lblEstat = new JLabel(" ");
		lblEstat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstat.setText("Clica sobre el taulell per col·locar el cavall");
		this.getContentPane().add(lblEstat, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnReset = new JButton("Inici");
		panel_1.add(btnReset);

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					joc.reset();
					refreshGui();
					lblEstat.setText(String.format("Joc reiniciat!") );
				} catch (Exception ex) {
					lblEstat.setText(ex.getMessage());
				}
			}
		});
		
		
		btnDesfer = new JButton("Desfer");
		panel_1.add(btnDesfer);

		btnDesfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					joc.getHistorial().desferUltimMoviment();
					refreshGui();
					lblEstat.setText(String.format("Moviment desfet!") );
				} catch (Exception ex) {
					lblEstat.setText(ex.getMessage());
				}
			}
		});



		
		JButton btnSolucio = new JButton("Solució");
		panel_1.add(btnSolucio);
		btnSolucio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnDesfer.setEnabled(false);
					btnSolucio.setEnabled(false);
					btnReset.setEnabled(false);

					
					int moviments_taulell = joc.getTaulell().getMovimentsPosibles();
					
					joc.reset();
					String resultat = joc.solucio();

					int moviments_solucio = joc.getSolucio().getMoviment();
					animacio = 0;
					
					if(moviments_solucio == moviments_taulell) {
						int delayTime = 250;
						javax.swing.Timer myTimer = new Timer(delayTime, new ActionListener() {

						     @Override
						     public void actionPerformed(ActionEvent e) {
									try {
										
						        		joc.getTaulell().setContingut(joc.getSolucio().getSequencia(animacio).caselles());
						        		animacio++;

						        		joc.status = String.format("Reproduint seqüència de solució (%d de %d)", animacio, moviments_solucio);

						        		refreshGui();
										
										if(animacio==moviments_solucio) {
											((Timer)e.getSource()).stop();

											btnDesfer.setEnabled(true);
											btnSolucio.setEnabled(true);
											btnReset.setEnabled(true);

											joc.status = resultat;
							        		refreshGui();
										}
										
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
						     }
						  });
						  myTimer.setRepeats(true);
						  myTimer.start();
					}
					
					
				} catch (Exception ex) {
					lblEstat.setText(ex.getMessage());
				}
				
			}
		});		
	

		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel chessBoard;
        chessBoard = new JPanel(new GridLayout(0, 7)); //joc.mida
        chessBoard.setBorder(new LineBorder(Color.BLACK));
    	

        // Crea les caselles i les afegeix al taulell
        int w = 80; //this.getSize().width/joc.mida;
        int[][] sb = joc.getTaulell().caselles();
        for (int ii = 0; ii < joc.mida; ii++) {
            for (int jj = 0; jj < joc.mida; jj++) {

            	CasellaGrafica b = new CasellaGrafica(ii, jj, w);
            	if(sb[ii][jj] == Joc.CASELLA_NO_VALIDA )
            		b.setEnabled(false);
            	else {
	            	// Establir acció de cada botó
	                b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
		                	casellaClick((CasellaGrafica)e.getSource());
						}
	                });
            	}
                chessBoard.add(b);
                casellesTaulell[ii][jj] = b;
            }
        }
        
        panel.add(chessBoard);

        //Carregar imatges
		try {
			imatgeCasella = new ImageIcon(ImageIO.read(new File("res/reddot.png")));
			imatgeCasellaSeleccionada = new ImageIcon(ImageIO.read(new File("res/reddot_selected.png")));
		} catch (IOException e) {}
		
		refreshGui();
        
	}

	
	public void refreshGui() throws Exception{
		
        int[][] sb = joc.getTaulell().caselles();

        // Neteja el taulell i col·loca els números de moviment
        for (int x = 0; x < sb.length; x++) 
			for (int y = 0; y < sb[x].length; y++) {
				
				CasellaGrafica cg = this.casellesTaulell[x][y]; 
				cg.removeAll();

				boolean showtext = false;
				boolean showImages = true;

				int value = sb[x][y];

				if(showtext) {
					if(value != Joc.CASELLA_BUIDA & value != Joc.CASELLA_NO_VALIDA )
						cg.setText(String.valueOf(value));
					cg.setText(String.valueOf(value));
				}

				if(showImages) {
					ImageIcon image = null;

					if(value == Joc.CASELLA_OCUPADA)
						image = imatgeCasella;
					else if(value == Joc.CASELLA_SELECCIONADA)
						image = imatgeCasellaSeleccionada;

					if(image!=null) {
				        cg.add(new JLabel(image));				
						cg.repaint();
					}
				}
			}
		
        
        // Pinta les caselles
	    for (int x = 0; x < sb.length; x++) {
	        Color color = CASELLA_BLANCA;

	        for (int y = 0; y < sb[x].length; y++) {
				CasellaGrafica cg = this.casellesTaulell[x][y]; 

				if(sb[x][y] == Joc.CASELLA_NO_VALIDA)
					cg.setBackground(CASELLA_GRIS);
				else
					cg.setBackground(color);
				
				cg.repaint();
			}
		}

	    //btnDesfer.setEnabled(joc.moviments()>0); // Es pot deasctivar el botó si no hi ha moviments que desfer
		lblEstat.setText(joc.status);
		
		//tell this JPanel to repaint itself since the ball has moved
        repaint();
}
	
private void casellaClick(CasellaGrafica b){
	
	try {
		joc.controladorJoc(b.x, b.y);
		this.refreshGui();
	} catch (Exception e) {
		e.printStackTrace();
		
		lblEstat.setText(e.getMessage());
	}		
}
	
	
	
}

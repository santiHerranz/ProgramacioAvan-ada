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
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
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
    private static JLabel lblAnimacio;
    private static JButton btnReset;
    private static JButton btnDesfer;
    private static JButton btnSolucio;
    private static JButton btnAnimacio;
    private static JButton btnMovimentSeguent;
    private static JButton btnMovimentAnterior;

    private CasellaGrafica[][] casellesTaulell;
    private ImageIcon imatgeCasella;
    private ImageIcon imatgeCasellaSeleccionada;
    private Joc joc;

	private int animacio = 0;    
	private boolean isPlaying = false;    

	
	/*
     * Constructor
     */
	TaulellGrafic() throws Exception{
		
		joc = new Joc();
		casellesTaulell = new CasellaGrafica[joc.getTaulellMida()][joc.getTaulellMida()];
		
		this.setBounds(0, 0, 700, 700);
		
		lblEstat = new JLabel(" ");
		lblEstat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstat.setText("Clica sobre el taulell per col�locar el cavall");
		this.getContentPane().add(lblEstat, BorderLayout.NORTH);
		
		btnReset = new JButton("Inici");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doInici();
			}
		});
		
		btnDesfer = new JButton("Desfer");
		btnDesfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDesfer();
			}
		});
		btnDesfer.setVisible(false);

		
		btnSolucio = new JButton("Soluci�");
		btnSolucio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSolucio();
			}
		});		
		
		btnMovimentAnterior = new JButton("Anterior");
		btnMovimentAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAnterior();
			}
		});			
		

		btnMovimentSeguent = new JButton("Seg�ent");
		btnMovimentSeguent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSeguent();
			}
		});				
		
		
		
		btnAnimacio = new JButton("Animaci�");
		btnAnimacio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAnimacio();
			}
		});
		
		lblAnimacio = new JLabel(" ");
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.add(btnReset);
		panel_1.add(btnDesfer);
		panel_1.add(btnSolucio);
		panel_1.add(btnMovimentAnterior);
		panel_1.add(lblAnimacio);
		panel_1.add(btnMovimentSeguent);
		panel_1.add(btnAnimacio);


		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel chessBoard;
        chessBoard = new JPanel(new GridLayout(0, joc.getTaulellMida())); //joc.mida
        chessBoard.setBorder(new LineBorder(Color.BLACK));
    	

        // Crea les caselles i les afegeix al taulell
        int w = 80; //this.getSize().width/joc.mida;
        int[][] sb = joc.getTaulell().caselles();
        for (int ii = 0; ii < joc.getTaulellMida(); ii++) {
            for (int jj = 0; jj < joc.getTaulellMida(); jj++) {

            	CasellaGrafica b = new CasellaGrafica(ii, jj, w);
            	if(sb[ii][jj] == Joc.CASELLA_NO_VALIDA )
            		b.setEnabled(false);
            	else {
	            	// Establir acci� de cada bot�
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
	
	
	private void doInici() {
		try {
			joc.reset();
			refreshGui();
			lblEstat.setText(String.format("Joc reiniciat!") );
		} catch (Exception ex) {
			lblEstat.setText(ex.getMessage());
		}
	}
	
	private void doDesfer() {
		try {
			joc.getHistorial().desferUltimMoviment();
			refreshGui();
			lblEstat.setText(String.format("Moviment desfet!") );
		} catch (Exception ex) {
			lblEstat.setText(ex.getMessage());
		}
	}
		
	
	private void doSolucio() {
		try {
			joc.reset();
			animacio = 0;
			joc.status = "Buscant soluci�...";

			btnDesfer.setEnabled(false);
			btnSolucio.setEnabled(false);
			btnReset.setEnabled(false);
			btnMovimentSeguent.setEnabled(false);
			btnMovimentAnterior.setEnabled(false);
			btnAnimacio.setEnabled(false);
    		refreshGui();

			int delayTime = 500;
			javax.swing.Timer myTimer = new Timer(delayTime, new ActionListener() {

			     @Override
			     public void actionPerformed(ActionEvent e) {
				        
				        try {
					        long t1 = System.currentTimeMillis();

					        int n = 2;
					        joc.trobarNSolucions(n);

					        if (joc.getSolucio().getMoviments()>0) {
					        	long t2 = System.currentTimeMillis();
					        	String iteracions = String.format("%,d",joc.getSolucio().getIteracions());
					        	joc.status = "("+ n+") solucions trobades en " + (t2 - t1) + " ms ["+ iteracions +" iteracions]" ;
					        } else {
					        	joc.status = "No hi ha soluci�!!";
					        }

			        		joc.getTaulell().setContingut(joc.getTaulellInicial());

							btnSolucio.setEnabled(true);
							btnReset.setEnabled(true);
			        		refreshGui();
				        
				        
				        } catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			     }
			});
			  myTimer.setRepeats(false);
			  myTimer.start();
			

			
		} catch (Exception ex) {
			lblEstat.setText(ex.getMessage());
		}		
	}
	
	
	private void doSeguent() {
		int moviments_solucio = joc.getSolucio().getMoviments();
		
		if(moviments_solucio>0 && animacio<moviments_solucio-1) {
    		animacio++;
    		joc.getTaulell().setContingut(joc.getSolucio().getSequencia(animacio).caselles());

    		joc.status = String.format("Reproduint seq��ncia de soluci� (%d de %d)", animacio, moviments_solucio-1);
    		try {
				refreshGui();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void doAnterior() {
		int moviments_solucio = joc.getSolucio().getMoviments();
		
		if(moviments_solucio>0 && animacio<=moviments_solucio) {
    		try {
        		animacio--;
        		joc.getTaulell().setContingut(joc.getSolucio().getSequencia(animacio).caselles());
				refreshGui();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void doAnimacio(){
		int moviments_solucio = joc.getSolucio().getMoviments();
		
		if(moviments_solucio>0) {
			
			btnMovimentSeguent.setEnabled(false);
			btnMovimentAnterior.setEnabled(false);
			
			animacio = 0;
			
			int delayTime = 450;
			javax.swing.Timer myTimer = new Timer(delayTime, new ActionListener() {

			     @Override
			     public void actionPerformed(ActionEvent e) {
						try {
							
			        		animacio++;
			        		joc.getTaulell().setContingut(joc.getSolucio().getSequencia(animacio).caselles());
							
							if(animacio==moviments_solucio-1) {
								((Timer)e.getSource()).stop();
								isPlaying = false;

								btnMovimentSeguent.setEnabled(true);
								btnMovimentAnterior.setEnabled(true);
								btnSolucio.setEnabled(true);
								btnReset.setEnabled(true);
								btnReset.setEnabled(true);

							}
			        		refreshGui();
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			     }
			  });
			  myTimer.setRepeats(true);
			  myTimer.start();
			  isPlaying = true;
		}
	}

	
	public void refreshGui() throws Exception{
		
        int[][] sb = joc.getTaulell().caselles();

        // Neteja el taulell i col�loca els n�meros de moviment
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

	    //btnDesfer.setEnabled(joc.moviments()>0); // Es pot deasctivar el bot� si no hi ha moviments que desfer
		lblEstat.setText(joc.status);
		
		int sol_count = joc.getSolucio().getMoviments();
		btnReset.setEnabled(!isPlaying);
		btnSolucio.setEnabled(!isPlaying);
		btnMovimentAnterior.setEnabled(sol_count>0 && animacio>0 && animacio<=sol_count && !isPlaying);
		btnMovimentSeguent.setEnabled(sol_count>0 && animacio>=0 &&  animacio<sol_count-1 && !isPlaying);
		btnAnimacio.setEnabled(sol_count>0 && !isPlaying);
		if(sol_count>0)
			lblAnimacio.setText(animacio +" de "+ (sol_count-1));
		else
			lblAnimacio.setText("");
			
		
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

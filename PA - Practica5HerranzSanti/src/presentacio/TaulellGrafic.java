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
import javax.swing.border.LineBorder;

import domini.Joc;

public class TaulellGrafic extends JFrame {
	
	private String CASELLA_BUIDA = " ";
	public String CASELLA_OCUPADA = "O";
	public String CASELLA_SELECCIONADA = "I";
	private String CASELLA_NOVALIDA = "X";
	
    private final Color CASELLA_SEGUENT = Color.GREEN;
    private final Color CASELLA_GRIS = Color.GRAY;
    private final Color CASELLA_BLANCA = Color.WHITE;
    

	private static final long serialVersionUID = 1L;

    private static JLabel lblEstat;
    private static JButton btnDesfer;
    private static JButton btnReset;

    private CasellaGrafica[][] casellesTaulell;
    private ImageIcon imatgeCasella;
    private ImageIcon imatgeCasellaSeleccionada;
    private Joc joc;
    
    /*
     * Constructor
     */
	TaulellGrafic(Joc joc) throws Exception{
		
		if(joc == null) throw new Exception("El taulell depén d'un joc");
		
		// punt de referencia al joc vinculat
		this.joc = joc;
		
		casellesTaulell = new CasellaGrafica[joc.mida][joc.mida];
		
		this.setBounds(0, 0, 700, 700);
		
		lblEstat = new JLabel(" ");
		lblEstat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstat.setText("Clica sobre el taulell per col·locar el cavall");
		this.getContentPane().add(lblEstat, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnReset = new JButton("Reset");
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
		
		
		btnDesfer = new JButton("Desfer darrer moviment");
		panel_1.add(btnDesfer);

		btnDesfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//joc.desferMoviment();
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
					joc.solucio();
					refreshGui();
					lblEstat.setText(joc.status);
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
        String[][] sb = joc.estatTaulell();
        for (int ii = 0; ii < joc.mida; ii++) {
            for (int jj = 0; jj < joc.mida; jj++) {

            	CasellaGrafica b = new CasellaGrafica(ii, jj, w);
            	if(sb[ii][jj].equals(CASELLA_NOVALIDA))
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
        String[][] sb = joc.estatTaulell();

        // Neteja el taulell i col·loca els números de moviment
        for (int x = 0; x < sb.length; x++) {
			for (int y = 0; y < sb[x].length; y++) {
				CasellaGrafica cg = this.casellesTaulell[x][y]; 
				cg.setText(sb[x][y]);
				cg.removeAll();
				
				ImageIcon image = null;
				if(sb[x][y].equals(CASELLA_OCUPADA))
					image = imatgeCasella;
				else if(sb[x][y].equals(CASELLA_SELECCIONADA))
					image = imatgeCasellaSeleccionada;

				if(image!=null) {
			        cg.add(new JLabel(image));				
					cg.repaint();
				}
			}
		}
        
        // Obté la posició actual del cavall i afegeix la imatge del estatus corresponent

		
        // Pinta les caselles
	    for (int x = 0; x < sb.length; x++) {
	        Color color = CASELLA_BLANCA;
	        
			for (int y = 0; y < sb[x].length; y++) {
				
				
				CasellaGrafica cg = this.casellesTaulell[x][y]; 
				if(sb[x][y].equals(CASELLA_NOVALIDA))
					cg.setBackground(CASELLA_GRIS);
				else
					cg.setBackground(color);
	
				cg.repaint();
			}
		}

	    //btnDesfer.setEnabled(joc.moviments()>0); // Es pot deasctivar el botó si no hi ha moviments que desfer
	}
	
	

	
	private void casellaClick(CasellaGrafica b){
		
		try {
			joc.procesar(b.x, b.y);
			this.refreshGui();

			lblEstat.setText(String.format("%s", joc.status));
			
		} catch (Exception e) {
			e.printStackTrace();
			
			lblEstat.setText(e.getMessage());
		}		
	}
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Joc joc = new Joc();
					
					TaulellGrafic window = new TaulellGrafic(joc);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

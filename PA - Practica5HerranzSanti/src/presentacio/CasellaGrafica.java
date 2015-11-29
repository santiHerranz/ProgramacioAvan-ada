package presentacio;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CasellaGrafica extends JButton {
	private static final long serialVersionUID = 1L;

	
	public int x;
	public int y;
	
	
    // create the chess board squares
    Insets buttonMargin = new Insets(0,0,0,0);

    
	CasellaGrafica(int ii, int jj, int w){

		//Coordenada
		this.x = ii;
		this.y = jj;
		
		// Texte
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setFont(new Font("Tahoma", Font.PLAIN, w/3));
		this.setForeground(Color.GRAY);
		this.setToolTipText((ii) +","+ (jj));

		// Tamany del boto forçat amb una imatge
		this.setLayout(new GridBagLayout()); // Estableix imatge al centre
        ImageIcon icon = new ImageIcon(
                new BufferedImage(w, w, BufferedImage.TYPE_INT_ARGB));
        this.setIcon(icon);
		this.setMargin(buttonMargin);
		
	}
}

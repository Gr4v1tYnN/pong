import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Vaizdavimas extends JPanel
{

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Zaidimas.pongas.atvaizduoti((Graphics2D) g);
    }

}
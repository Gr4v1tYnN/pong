import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Zaidimas implements ActionListener, KeyListener
{

    public static Zaidimas pongas;

    public int ilgis = 1200, aukstis = 700;

    public Vaizdavimas vaizduoklis;

    public Rakete zaidejas;

    public Rakete AI;

    public Kamuolys kamuolys;

    public boolean up, down;

    public int statusas = 0, zaidziamaIki = 5;

    public int AILaimejo, zaidejasLaimejo;

    public JFrame jframe;

    public static void main(String[] args)
    {
        pongas = new Zaidimas();
    }

    public Zaidimas()
    {
        Timer timer = new Timer(20, this);

        jframe = new JFrame("Pongas");

        vaizduoklis = new Vaizdavimas();

        jframe.setSize(ilgis + 15, aukstis + 35);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(vaizduoklis);
        jframe.addKeyListener(this);

        timer.start();
    }

    public void startas()
    {
        statusas = 2;
        zaidejas = new Rakete( 1);
        AI = new Rakete(2);
        kamuolys = new Kamuolys(this);
    }

    public void atvaizduoti(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ilgis, aukstis);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (statusas == 0)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Vivaldi", 1, 50));

            g.drawString("PONGAS", ilgis / 2 - 150, 50);


            g.setFont(new Font("Vivaldi", 1, 30));

            g.drawString("Paspauskite SPACE norint pradeti zaidima", ilgis / 2 - 260, aukstis / 2 - 50);
            g.drawString("<< Zaidziama iki: " + zaidziamaIki + " >>", ilgis / 2 - 125, aukstis / 2);

        }


        if (statusas == 1)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Vivaldi", 1, 50));
            g.drawString("PAUZE", ilgis / 2 - 103, aukstis / 2 - 25);
        }

        if ( statusas == 2)
        {
            g.setColor(Color.WHITE);

            g.setStroke(new BasicStroke(5f));

            g.drawLine(ilgis / 2, 0, ilgis / 2, aukstis);

            g.setFont(new Font("Vivaldi", 1, 50));

            g.drawString(String.valueOf(zaidejas.rezultatas), ilgis / 2 - 90, 50);
            g.drawString(String.valueOf(AI.rezultatas), ilgis / 2 + 65, 50);

            zaidejas.atvaizduoti(g);
            AI.atvaizduoti(g);
            kamuolys.render(g);
        }

        if (statusas == 3)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Vivaldi", 1, 50));

            g.drawString("PONGAS", ilgis / 2 - 75, 50);

            if (AILaimejo == 1)
            {
                g.drawString("AI laimejo!", ilgis / 2 - 170, 200);
            }
            else
            {
                g.drawString("Zaidejas  laimejo!", ilgis / 2 - 165, 200);
            }

            g.setFont(new Font("Vivaldi", 1, 30));

            g.drawString("Paspauskite SPACE noredami zaisti vel", ilgis / 2 - 185, aukstis / 2 - 25);
        }
    }

    public void atnaujinti()
    {
        if (zaidejas.rezultatas >= zaidziamaIki)
        {
            zaidejasLaimejo = 1;
            statusas = 3;
        }

        if(AI.rezultatas >= zaidziamaIki){
            AILaimejo = 1;
            statusas = 3;
        }

        if (up)
        {
            zaidejas.judesys(true);
        }
        if (down)
        {
            zaidejas.judesys(false);
        }
        else
        {

            if (AI.Oy + AI.aukstis / 2 < kamuolys.y)
            {
                AI.judesys(false);
            }

            if (AI.Oy + AI.aukstis / 2 > kamuolys.y)
            {
                AI.judesys(true);
            }
        }

        kamuolys.atnaujinti(zaidejas, AI);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (statusas == 2)
        {
            atnaujinti();
        }

        vaizduoklis.repaint();
    }



    @Override
    public void keyPressed(KeyEvent e)
    {
        int id = e.getKeyCode();

        if (id == KeyEvent.VK_UP)
        {
            up = true;
        }
        else if (id == KeyEvent.VK_DOWN)
        {
            down = true;
        }
        else if (id == KeyEvent.VK_ESCAPE && (statusas == 2 || statusas == 3))
        {
            statusas = 0;
        }
        else if (id == KeyEvent.VK_SPACE && statusas == 0)
        {
            startas();
        }
        else if (id == KeyEvent.VK_SPACE && statusas == 3)
        {
            startas();
        }
        if (id == KeyEvent.VK_P)
        {
            statusas = 1;
        }
        if (id == KeyEvent.VK_ENTER)
        {
            statusas = 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int id = e.getKeyCode();

        if (id == KeyEvent.VK_UP)
        {
            up = false;
        }
        else if (id == KeyEvent.VK_DOWN)
        {
            down = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
import java.awt.Color;
        import java.awt.Graphics;

public class Rakete
{

    public int rakete;

    public int Ox, Oy, plotis = 20, aukstis = 200;

    public int rezultatas;

    public Rakete(int raketesNumeris)
    {
        this.rakete = raketesNumeris;

        if (raketesNumeris == 1)
        {
            this.Ox = 0;
        }

        if (raketesNumeris == 2)
        {
            this.Ox = Zaidimas.pongas.ilgis - plotis;
        }

        this.Oy = this.aukstis ;
    }

    public void atvaizduoti(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(Ox, Oy, plotis, aukstis);
    }

    public void judesys(boolean up)
    {
        int judejimoGreitis = 20;

        if (up)
        {
            if (Oy - judejimoGreitis > 0)
            {
                Oy -= judejimoGreitis;
            }
            else
            {
                Oy = 0;
            }
        }
        else
        {
            if (Oy + aukstis + judejimoGreitis < Zaidimas.pongas.aukstis)
            {
                Oy += judejimoGreitis;
            }
            else
            {
                Oy = Zaidimas.pongas.aukstis - aukstis;
            }
        }
    }

}

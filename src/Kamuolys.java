 import java.awt.Color;
        import java.awt.Graphics;
        import java.util.Random;

public class Kamuolys
{

    public int x, y, plotis = 20, aukstis = 20;

    public int judesysX, judesysY;

    public Random random;

    private Zaidimas pongas;

    public int atsimusimas;

    public Kamuolys(Zaidimas pong)
    {
        this.pongas = pong;

        this.random = new Random();

        atsiradimas();
    }

    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, plotis, aukstis);

    }

    public void atsiradimas()
    {
        this.atsimusimas = 0;
        this.x = pongas.ilgis / 2 - this.plotis / 2;
        this.y = pongas.aukstis / 2 - this.aukstis / 2;

        this.judesysY = -2 + random.nextInt(4);

        if (judesysY == 0)
        {
            judesysY = 1;
        }

        if (random.nextBoolean())
        {
            judesysX = 1;
        }
        else
        {
            judesysX = -1;
        }
    }

    public int susidurimoAtvejis(Rakete rakete)
    {
        if (this.x < rakete.Ox + rakete.plotis && this.x + plotis > rakete.Ox && this.y < rakete.Oy + rakete.aukstis && this.y + aukstis > rakete.Oy)
        {
            return 1;
        }
        else if ((rakete.Ox > x && rakete.rakete == 1) || (rakete.Ox < x - plotis && rakete.rakete == 2))
        {
            return 2;
        }

        return 0;
    }

    public void atnaujinti(Rakete zaidejas, Rakete AI)
    {
        int greitis = 5;

        this.x += judesysX * greitis;
        this.y += judesysY * greitis;

        if (this.y + aukstis - judesysY > pongas.aukstis || this.y + judesysY < 0)
        {
            if (this.judesysY < 0)
            {
                this.y = 0;
                this.judesysY = random.nextInt(4);

                if (judesysY == 0)
                {
                    judesysY = 1;
                }
            }
            else
            {
                this.judesysY = -random.nextInt(4);
                this.y = pongas.aukstis - aukstis;

                if (judesysY == 0)
                {
                    judesysY = -1;
                }
            }
        }

        if (susidurimoAtvejis(zaidejas) == 1)
        {
            this.judesysX = 1 + (atsimusimas / 5);
            this.judesysY = -2 + random.nextInt(4);

            if (judesysY == 0)
            {
                judesysY = 1;
            }

            atsimusimas++;
        }
        else if (susidurimoAtvejis(AI) == 1)
        {
            this.judesysX = -1 - (atsimusimas / 5);
            this.judesysY = -2 + random.nextInt(4);

            if (judesysY == 0)
            {
                judesysY = 1;
            }

            atsimusimas++;
        }

        if (susidurimoAtvejis(zaidejas) == 2)
        {
            AI.rezultatas++;
            atsiradimas();
        }
        else if (susidurimoAtvejis(AI) == 2)
        {
            zaidejas.rezultatas++;
            atsiradimas();
        }
    }

}
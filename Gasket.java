import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
 
public class Gasket extends JFrame {
    private SwingWorker gameLooper;
    private boolean stop;
    
    private int seconds;

    public Gasket() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        seconds = 0;
        stop = false;
        
        gameLooper = new SwingWorker() 
        {
            @Override
            protected Object doInBackground() throws Exception 
            {
                while(!stop) 
                {
                    update();
                    repaint();
                    Thread.sleep(5);
                    break;
                }
                return null;
            }
        };
        
        gameLooper.execute();
    }
 
    @Override
    public void paint(Graphics graphics) //Draw here
    {
        super.paint(graphics);
        getGraphics().setColor(Color.BLACK);
        //graphics.drawString("second: " + seconds, 200, 200);
        try
        {
            triangle(400, 400, 200, 10, graphics);
        }
        catch (Exception e)
        {

        }
        
    }
    
    public void update() //logic only
    { 
        seconds++;
    }

    public static void triangle(int x, int y, int s, int n, Graphics g)  throws Exception
    {
        if (n == 0)
        {
            return;
        }

        // Coordinates
        int x1 = x;
        int y1 = y;
        int x2 = (int)(x1+s);
        int y2 = y1;
        int x3 = (int)((x1+x2)/2.0);
        int y3 = y1 + (int)(Math.sqrt(3)*s/2);

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x1, y1, x3, y3);
        g.drawLine(x2, y2, x3, y3);

        Thread.sleep(50);

        triangle(x+s/2, (int)(y-s/2.3), (int)(s/2), n-1, g);
        triangle(x, (int)(y-s/2.3), (int)(s/2), n-1, g);

    }
 
    public static void main(String[] args) 
    {
        Gasket f = new Gasket();
        f.setVisible(true);
    }
}
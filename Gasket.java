import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.SwingWorker;


 
public class Gasket extends JFrame {
    private SwingWorker gameLooper;
    private boolean stop;
    private static Random randomGenerator;
    
    private int seconds;
    private static int topXOffset;

    public Gasket() {
        randomGenerator = new Random();
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        seconds = 0;
        topXOffset = 0;
        stop = false;
        
        //Game loop boilerplate so I could play with animations
        gameLooper = new SwingWorker() 
        {
            @Override
            protected Object doInBackground() throws Exception 
            {
                while(!stop) 
                {
                    update();
                    repaint();
                    Thread.sleep(250);
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
        //super.paint(graphics);
        //getContentPane().setBackground(new Color(70,80,70));
        getGraphics().setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) graphics;

        try
        {
            triangle(10, 550, 50, graphics);
            sTriangle(10, 980, 980, 8, g2);
        }
        catch (Exception e)
        {

        }
        
    }
    
    public void update() //logic only
    { 
        seconds++;
    }

    public static void triangle(int x, int y, int s, Graphics g)  throws Exception
    {
        // Coordinates
        int x1 = x;
        int y1 = y;
        int x2 = x1+s/2;
        int y2 = y1-(int)(Math.sqrt(3)*s/2);
        int x3 = x1+s;
        int y3 = y1;

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x1, y1, x3, y3);
        g.drawLine(x2, y2, x3, y3);
    }

    public static void sTriangle(double x, double y, double s, int n, Graphics2D g)  throws Exception
    {
        if (n <= 0)
        {
            return;
        }

        float hue = randomGenerator.nextFloat();
        float saturation = (randomGenerator.nextInt(2000) + 1000) / 10000f;
        float luminance = 0.9f;
        Color color = Color.getHSBColor(hue, saturation, luminance);
        g.setColor(color);
        
        // Coordinates of three corners
        double x1 = x; //bottom left
        double y1 = y;
        double x2 = (x1+s/2.0);  //top
        double y2 = y1- (Math.sqrt(3)*s/2.0);
        double x3 = x1+s; //bottom right
        double y3 = y1;

        //g.fillPolygon(new int[] {x1,x2,x3}, new int[] {y1,y2,y3}, 3);
        g.draw(new Line2D.Double(x1, y1, x2, y2));
        g.draw(new Line2D.Double(x1, y1, x3, y3));
        g.draw(new Line2D.Double(x2, y2, x3, y3));
        
        //Offsets for triangle.
        sTriangle(x1, y1, s/2, n-1, g);
        sTriangle((x1+x2)/2.0, (y1+y2)/2.0, s/2.0, n-1, g);
        sTriangle((x1+x3)/2.0, (y1+y3)/2.0, s/2.0, n-1, g);

    }
 
    public static void main(String[] args) 
    {
        Gasket w = new Gasket();
        w.setVisible(true);
    }
}

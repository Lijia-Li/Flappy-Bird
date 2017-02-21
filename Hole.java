/**
 * Created by Lisa on 11/11/16.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Hole extends Shape {
    int upperBound;
    int widthOfBar;
    int empty = 200;
    Color color = new Color(33, 194, 33);

    public Hole(int x, int y, int upperBound, int widthOfBar, Color color) {
        super(x, y);
        this.upperBound = upperBound;
        this.widthOfBar = widthOfBar;

    }
//hole doesn't need to override update funtion in shape (no details need to add)

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, widthOfBar, upperBound);
        g.fillRect(x, upperBound + empty, widthOfBar, 768 - upperBound - empty);

    }

    public void update(double timeMs,
                       double delayMs,
                       int screen_width,
                       int screen_height) {
        // add velocity to position
        //this.x += delayMs * this.dx;
        this.x += -8;
        if (timeMs > 1000) {
            Random random = new Random();
            this.color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        }
    }
}

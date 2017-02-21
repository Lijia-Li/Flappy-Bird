/**
 * Created by Lisa on 11/11/16.
 */
import java.awt.Color;
import java.awt.Graphics;

class Bird extends Shape {

    public Bird(int x, int y) {
        super(x, y);

    }


    public void paint(Graphics g) {
        //this doesn't work if use x and y,but does work if change x into 512 and y into 384
        g.setColor(new Color(232, 230, 76));
        g.fillOval(this.x, this.y, 50, 50);
        g.setColor(new Color(132, 77, 22));
        g.fillOval(this.x - 5, this.y + 25, 35, 15);
        g.setColor(new Color(0, 0, 0));
        g.fillOval(this.x + 30, this.y + 15, 10, 10);
        g.setColor(new Color(225, 0, 0));
        int[] xs = {this.x + 50, this.x + 50, this.x + 55};
        int[] ys = {this.y + 20, this.y + 30, this.y + 25};
        g.fillPolygon(xs, ys, 3);

    }

    public void update(double timeMs,
                       double delayMs,
                       int screen_width,
                       int screen_height) {


        // add acceleration to velocity
        this.dy += this.ddy * delayMs ;
//         add velocity to position
        this.y += this.dy * delayMs  ;



        //if the bird hit the ground, the game should stop; also, we don't care if the bird is higher than upper screen
        if (this.y + 50 > screen_height) {
            this.y = screen_height;
        }

    }

}



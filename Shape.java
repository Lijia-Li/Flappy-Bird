/**
 * Created by Lisa on 11/11/16.
 */
import java.awt.Graphics;

class Shape {
    int x;
    int y;
    double dx;
    double dy = 0;
    double ddy;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(double total_time,
                       double delay_ms,
                       int screen_width,
                       int screen_height) {
        // subclasses can override this method if necessary

        // add acceleration to velocity
        this.dy += delay_ms * this.ddy;

        // add velocity to position
        this.x += delay_ms * this.dx;
        this.y += delay_ms * this.dy;
    }

    public void paint(Graphics g) {
        // subclasses should override this method
    }
}

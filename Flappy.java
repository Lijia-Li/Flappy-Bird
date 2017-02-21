// utility stuff
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

// graphics stuff
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Flappy
        extends JPanel
        implements KeyListener
{
    int screenWidth = 1024;
    int screenHeight = 768;
    int timeMs = 0;
    int delayMs = 50;

    int hole_dis = 300;//distance between holes

    int score;
    int highScore = 0;
    Bird bird;
    Random rand = new Random();
    ArrayList<Hole> holes = new ArrayList<>();



    public Flappy() {
        if (score > highScore) {
            highScore = score;
        }
        bird = new Bird(screenWidth / 2, screenHeight / 2);
        for (int i = 0; i < 5; i ++) {
            int newUpperBound = rand.nextInt(568 - 100);
            Hole hole = new Hole(1024 + i * hole_dis, 0, newUpperBound, 70, new Color(33, 194, 33));
            holes.add();
        }

    }


    /* This function resets the game The reset method, which initializes the game.
     * That is, the current score should be set to 0, and the bird and holes should
     * be placed in their initial positions.
     */
    public void reset() {
        //this is only a testing code to see whether the update is working or not
        score = 0;
        timeMs = 0;
        //bird back to original position
        bird.x = 512;
        bird.y = 384;
        bird.dy = 0;
        bird.ddy = 0;
        for (int index = 0; index < holes.size(); index++) {
            Hole this_hole = holes.get(index);
            this_hole.x = 1024 + index * hole_dis;
            this_hole.upperBound = rand.nextInt(568 - 100);
            holes.indexOf(this);
        }



    }

    /* This function updates the position of all objects, EXCEPT for
     * how the bird moves when you press UP, which is handled in
     * keyReleased()
     */
    public void update() {
        // update the position of holes:
        // update the position of them;
        // remove the one pass 0;
        // generate new one and add to the list.
        for (int index = 0; index < holes.size(); index++) {
            Hole this_hole = holes.get(index);
            this_hole.update(timeMs, delayMs, screenWidth, screenHeight);
            if (this_hole.x + 70 < 0) {
                holes.remove(0);
                int newUpperBound = rand.nextInt(568 - 100);
                Hole hole = new Hole(1110 + hole_dis, 0, newUpperBound, 70, new Color(33, 194, 33));
                holes.add(holes.size(), hole);
            }
            //see if bird touches the hole
            // rightside of bird >= left hole and left bird is less than right hole
            if (!((bird.x + 55) <= this_hole.x ) && (!(bird.x >= (this_hole.x + this_hole.widthOfBar)))) {
                if(bird.y <= this_hole.upperBound || bird.y + 50 >= this_hole.upperBound + this_hole.empty){
                    //if bird touches the hole, bird falls to the ground and reset the game
                    //bird.y = screenHeight;
                    this.reset();
                }
            }
            //pass one hole, score+1
            if (timeMs > 2560){
                score = 1;
                int remain_time = timeMs - 2560;
                score = 1 + (int) Math.floor(remain_time/1675);
                if (score > highScore) {
                    highScore = score;
                }
            }

            if (bird.y >= 768) {
                this.reset();
            }
        }

        bird.update(timeMs, delayMs, screenWidth, screenHeight);


    }



    /* This function draws all the objects on the screen.
     */
    public void paint(Graphics g) {
        String scr = Integer.toString(score);
        String high_scr = Integer.toString(highScore);
        g.drawString("Score:",20, 20);
        g.drawString(scr, 60,20);
        g.drawString("Highest Score:",20, 40);
        g.drawString(high_scr, 115,40);
        g.
        this.bird.paint(g);
        for(Hole a: this.holes){
            a.paint(g);
        }
    }


    /* This function is called whenever a key is pressed;
     * specifically, it detects the non-keypad up-arrow.
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            bird.y -= 80;
            bird.dy = 0.005;
            bird.ddy= 0.0005;

        }
    }
    // You should not change any code below this line

    public void run() throws InterruptedException {
        this.showCanvas();
        while (true) {
            // update everything
            this.update();

            // redraw the screen
            Graphics g = this.getGraphics();
            g.clearRect(0, 0, this.screenWidth, this.screenHeight);
            this.paint(g);

            // wait for the next timestep
            Thread.sleep(this.delayMs);
            this.timeMs += this.delayMs;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void showCanvas() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.addKeyListener(this);
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(this.screenWidth, this.screenHeight);
        frame.setVisible(true);
    }

    public static void main(String[] args)
            throws InterruptedException {
        Flappy flappy = new Flappy();
        flappy.run();
    }
}

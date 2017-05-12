package game;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Created by ryan on 2/11/17.
 */
public class Square extends JComponent {

    private int x;
    private int y;
    private Color color;

    public Square(Color color){
        this.x = 0;
        this.y = 0;
        this.color = color;
    }

    public Square(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g){
        g.drawRect(x, y, super.getHeight(), super.getWidth());
        g.setColor(this.color);
        g.fillRect(0,0, super.getHeight(), super.getWidth());
    }
}

import javax.swing.*;
import java.awt.*;

/**This program will open a canvas in which minesweeper game content will be drawn.
 *
 * @author ndricimrrahmani*/

public class FrameClass extends JFrame{
    private int width, height;          //width and height of the canvas
    private int horizontal, vertical;   //number of horizotal and vertical square landmines
    private int unit;                   //how many pixels wide is a square
    private int margins = 20;           //safe space around the sides
    private ViewPanel viewPanel;        //where painting will happen
    private JLabel state;

    public FrameClass(int h, int v, int u, ViewPanel vp){
        super("Minesweeper");

        horizontal = h;
        vertical = v;
        unit = u;

        width = horizontal*unit;
        height = vertical*unit;

        viewPanel = vp;

        setSize(width+margins, height+margins*2);

        getContentPane().add(viewPanel);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

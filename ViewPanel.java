/**This class paints components into the panel
 *
 * @author ndricimrrahmani*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ViewPanel extends JPanel implements MouseListener {
    private int width, height;          //width and height of the canvas
    private int horizontal, vertical;   //number of horizotal and vertical square landmines
    private int unit;                   //how many pixels wide is a square
    private Landmine[][] landmines;
    private boolean won, lost;
    int mineNumber;

    public ViewPanel(int h, int v, int u, Landmine[][] m, int mineNum){
        horizontal = h;
        vertical = v;
        unit = u;
        width = horizontal*unit;
        height = vertical*unit;
        landmines = new Landmine[vertical][horizontal];
        landmines = m;
        mineNumber = mineNum;
        addMouseListener(this);
    }

    public void paintComponent(Graphics graphics){
        Graphics2D g = (Graphics2D)graphics;

        Color backgroundColor = new Color(220, 210, 200);
        Color accent = new Color(40, 20, 50);
        Color accent_light = new Color(100, 100, 200);
        Color red_lost = new Color(255, 50, 80);
        Color white = new Color(255, 255, 255);

        g.setColor(backgroundColor);
        g.fillRect(0,0, width, height);

        g.setColor(accent);
        for(int i=1; i<=landmines.length; i++){
            g.drawLine(i*unit, 0, i*unit, height+0);
        }
        for(int i=1; i<=landmines[0].length; i++){
            g.drawLine(0, i*unit, width+0, i*unit);
        }

        for(int i = 0; i<landmines.length; i++){
            for(int j = 0; j<landmines[0].length; j++){

                if(landmines[i][j].isOpen()){
                    if(landmines[i][j].isMined()){
                        g.setColor(red_lost);
                        g.fillRect(i*unit+1, j*unit+1, unit-2, unit-2);
                    } else {
                        if (landmines[i][j].getNeighbor()==0){
                            g.setColor(white);
                            g.fillRect(i*unit+1, j*unit+1, unit-2, unit-2);
                        } else {
                            g.setColor(white);
                            g.fillRect(i*unit+1, j*unit+1, unit-2, unit-2);
                            g.setColor(accent);
                            g.drawString(""+landmines[i][j].getNeighbor(), i*unit+(unit/3), ((j+1)*unit)-(unit/3));
                        }
                    }
                } else {
                    if(landmines[i][j].isFlagged()) {
                        g.setColor(accent_light);
                        g.fillRect(i*unit+1, j*unit+1, unit-2, unit-2);
                    } else {
                        g.setColor(backgroundColor);
                        g.fillRect(i*unit+1, j*unit+1, unit-2, unit-2);
                    }
                }
            }
        }
        for(int i = 0; i<landmines.length; i++){
            for(int j = 0; j<landmines[i].length; j++){
                if(landmines[i][j].isMined() && landmines[i][j].isOpen()){
                    lost = true;
                }
            }
        }


        if(won){
            for(int i = 0; i<vertical; i++){
                for(int j=0; j<horizontal; j++){
                    landmines[i][j].close();
                }
            }
        }
        repaint();
    }
    public void setLandmines(Landmine[][] mines){
        landmines = mines;
    }
    int x;
    int y;

    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        if(SwingUtilities.isLeftMouseButton(e)){
            landmines[x/unit][y/unit].open();
            System.out.println("Opened square("+(x/unit+1)+", "+(y/unit+1)+")");
        }
        if(SwingUtilities.isRightMouseButton(e)){
            landmines[x/unit][y/unit].flag();
            if(landmines[x/unit][y/unit].isFlagged()) {
                System.out.println("Flagged square(" + (x / unit + 1) + ", " + (y / unit + 1) + ")");
            } else {
                System.out.println("Unflagged square(" + (x / unit + 1) + ", " + (y / unit + 1) + ")");
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public boolean isLost() {
        return lost;
    }
    public boolean isWon() {
        return won;
    }
}

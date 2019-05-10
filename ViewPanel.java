/**This class paints components into the panel
 *
 * @author ndricimrrahmani*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;


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
        Color temp = white;

        g.setColor(backgroundColor);
        g.fillRect(0,0, width, height);

        checkWin();
        checkLose();

        if(!lost && !won){
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
                                g.setColor(temp);
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
        } else if (won) {
            GeneralPath path = new GeneralPath();
            path.moveTo(width/4, height/4);
            path.lineTo(width/4., 3*height/4);
            path.lineTo(3*width/4, 3*height/4);
            path.lineTo(3*width/4, height/4);
            path.lineTo(5*width/8, height/2);
            path.lineTo(width/2, height/4);
            path.lineTo(3*width/8, height/2);
            path.closePath();

            Color gold = new Color(220, 150, 0);
            g.setColor(gold);
            g.fill(path);

            g.setColor(Color.BLACK);
            g.drawString("You won! ", width/4, 21*height/24);
            g.drawString("If you want to play more,", width/4, 22*height/24);
            g.drawString("click anywhere on the screen!", width/4, 23*height/24);


        } else if (lost){
            GeneralPath path = new GeneralPath();
            path.moveTo(width/4, height/4);
            path.lineTo(3*width/8, height/4);
            path.lineTo(width/2, height/8);
            path.lineTo(5*width/8, height/4);
            path.lineTo(3*width/4, height/4);
            path.lineTo(3*width/4, 3*height/8);
            path.lineTo(7*width/8, height/2);
            path.lineTo(3*width/4, 5*height/8);
            path.lineTo(3*width/4, 3*height/4);
            path.lineTo(5*width/8, 3*height/4);
            path.lineTo(width/2, 7*height/8);
            path.lineTo(3*width/8, 3*height/4);
            path.lineTo(width/4, 3*height/4);
            path.lineTo(width/4, 5*height/8);
            path.lineTo(width/8, height/2);
            path.lineTo(width/4, 3*height/8);
            path.closePath();


            Color scarlet = new Color(222, 36, 50);
            g.setColor(scarlet);
            g.fill(path);

            g.setColor(backgroundColor);
            g.fillRect(5*width/16, 3*height/8, width/8, height/16);
            g.fillRect(9*width/16, 3*height/8, width/8, height/16);
            g.fillArc(3*width/8, 9*height/16, width/4, height/4, 0, 180);

            g.setColor(scarlet);
            g.drawString("You Lost! ", width/4, 21*height/24);
            g.drawString("If you want to play more,", width/4, 22*height/24);
            g.drawString("click anywhere on the screen!", width/4, 23*height/24);

        } else { }

        repaint();
    }

    private void checkWin(){
        int count1 = 0;
        int count2 = 0;

        for(int i = 0; i<landmines.length; i++){
            for(int j=0; j<landmines[0].length; j++){
                if (landmines[i][j].isFlagged() && landmines[i][j].isMined()){
                    count1++;
                } else { }
                if(landmines[i][j].isOpen() && !landmines[i][j].isMined()){
                    count2++;
                } else { }

            }
        }

        if(count1==mineNumber){
            won = true;
        } else if (count2==((vertical*horizontal)-mineNumber)){
            won = true;
        }

    }
    private void checkLose(){
        for(int i = 0; i<landmines.length; i++){
            for(int j = 0; j<landmines[i].length; j++){
                if(landmines[i][j].isMined() && landmines[i][j].isOpen()){
                    lost = true;
                }
            }
        }

    }

    private void advancedOpen(int x, int y){
        if(x==0){
            if(y==0){
                if(landmines[x +1][y].getNeighbor()==0){landmines[x +1][y].open();}
                if(landmines[x][y+1].getNeighbor()==0){landmines[x][y+1].open();}
                if(landmines[x+1][y+1].getNeighbor()==0){landmines[x+1][y+1].open();}
            }else if (y==(landmines[x].length-1)){
                if(landmines[x][y-1].getNeighbor()==0){landmines[x][y-1].open();}
                if(landmines[x+1][y].getNeighbor()==0){landmines[x+1][y].open();}
                if(landmines[x+1][y-1].getNeighbor()==0){landmines[x+1][y-1].open();}
            }else{
                if(landmines[x][y+1].getNeighbor()==0){landmines[x][y+1].open();}
                if(landmines[x][y-1].getNeighbor()==0){landmines[x][y-1].open();}
                if(landmines[x+1][y-1].getNeighbor()==0){landmines[x+1][y-1].open();}
                if(landmines[x+1][y].getNeighbor()==0){landmines[x+1][y].open();}
                if(landmines[x+1][y+1].getNeighbor()==0){landmines[x+1][y+1].open();}
            }
        } else if(x==(landmines.length-1)){
            if(y==0){
                if(landmines[x-1][y].getNeighbor()==0){landmines[x-1][y].open();}
                if(landmines[x-1][y+1].getNeighbor()==0){landmines[x-1][y+1].open();}
                if(landmines[x][y+1].getNeighbor()==0){landmines[x][y+1].open();}
            }else if(y==((landmines[x].length)-1)){
                if(landmines[x][y-1].getNeighbor()==0){landmines[x][y-1].open();}
                if(landmines[x-1][y-1].getNeighbor()==0){landmines[x-1][y-1].open();}
                if(landmines[x-1][y].getNeighbor()==0){landmines[x-1][y].open();}
            }else{
                if(landmines[x][y-1].getNeighbor()==0){landmines[x][y-1].open();}
                if(landmines[x][y+1].getNeighbor()==0){landmines[x][y+1].open();}
                if(landmines[x-1][y-1].getNeighbor()==0){landmines[x-1][y-1].open();}
                if(landmines[x-1][y].getNeighbor()==0){landmines[x-1][y].open();}
                if(landmines[x-1][y+1].getNeighbor()==0){landmines[x-1][y+1].open();}
            }
        } else {
            if(y==0){
                if(landmines[x-1][y].getNeighbor()==0){landmines[x-1][y].open();}
                if(landmines[x-1][y+1].getNeighbor()==0){landmines[x-1][y+1].open();}
                if(landmines[x][y+1].getNeighbor()==0){landmines[x][y+1].open();}
                if(landmines[x+1][y].getNeighbor()==0){landmines[x+1][y].open();}
                if(landmines[x+1][y+1].getNeighbor()==0){landmines[x+1][y+1].open();}
            } else if(y==((landmines[x].length)-1)){
                if(landmines[x-1][y].getNeighbor()==0){landmines[x-1][y].open();}
                if(landmines[x-1][y-1].getNeighbor()==0){landmines[x-1][y-1].open();}
                if(landmines[x][y-1].getNeighbor()==0){landmines[x][y-1].open();}
                if(landmines[x+1][y-1].getNeighbor()==0){landmines[x+1][y-1].open();}
                if(landmines[x+1][y].getNeighbor()==0){landmines[x+1][y].open();}
            } else {
                if(landmines[x-1][y-1].getNeighbor()==0){landmines[x-1][y-1].open();}
                if(landmines[x-1][y].getNeighbor()==0){landmines[x-1][y].open();}
                if(landmines[x-1][y+1].getNeighbor()==0){landmines[x-1][y+1].open();}
                if(landmines[x][y-1].getNeighbor()==0){landmines[x][y-1].open();}
                if(landmines[x][y+1].getNeighbor()==0){landmines[x][y+1].open();}
                if(landmines[x+1][y-1].getNeighbor()==0){landmines[x+1][y-1].open();}
                if(landmines[x+1][y].getNeighbor()==0){landmines[x+1][y].open();}
                if(landmines[x+1][y+1].getNeighbor()==0){landmines[x+1][y+1].open();}
            }
        }

    }
    private void setLandmines(Landmine[][] mines){
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
            if(landmines[x/unit][y/unit].getNeighbor()==0){
                advancedOpen(x/unit, y/unit);
            }
            System.out.println("Opened square("+(x/unit+1)+", "+(y/unit+1)+")");
        }
        if(SwingUtilities.isRightMouseButton(e)){
            if(!landmines[x/unit][y/unit].isFlagged()) {
                System.out.println("Flagged square(" + (x / unit + 1) + ", " + (y / unit + 1) + ")");
                landmines[x/unit][y/unit].flag();
            } else {
                System.out.println("Unflagged square(" + (x / unit + 1) + ", " + (y / unit + 1) + ")");
                landmines[x/unit][y/unit].unFlag();
            }
        }

        if(won || lost){
            for(int i = 0; i<landmines.length; i++){
                for(int j = 0; j<landmines[i].length; j++){
                    landmines[i][j].close();
                    landmines[i][j].unFlag();
                    won = false;
                    lost = false;
                }
            }
        } else { }

/**
        if(lost){
            for(int i = 0; i<landmines.length; i++){
                for(int j = 0; j<landmines[i].length; j++){
                    landmines[i][j].close();
                    landmines[i][j].unFlag();

                    lost = false;
                }
            }
        } else { } */
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
}

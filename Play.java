/**Play class starts the game, initializes mines through Helper class,
 * gets input through the mouse
 *
 * @author ndricimrrahmani*/

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class Play{
    static int horizontal, vertical;
    static int unit;
    static int minesratio;
    static Landmine[][] blocks;
    static int[] mines;
    static boolean playing = true;


    public static void main(String[] args) {
        horizontal = 20;
        vertical = 20;
        minesratio = 5;
        unit = 30;
        blocks = new Landmine[horizontal][vertical];
        play();
    }

    public static void play(){
            for (int i=0; i<blocks.length; i++){
                for(int j=0; j< blocks[0].length; j++){
                    blocks[i][j] = new Landmine();
                }
            }
            Helper help = new Helper(horizontal, vertical, minesratio);

            mines = help.getMinePlaces();

            for(int i = 0; i<mines.length; i++){
                blocks[mines[i]/horizontal][mines[i]%horizontal].mine();
            }

            for(int i = 0; i<blocks.length; i++){
                for(int j = 0; j<blocks[0].length; j++){
                    System.out.println("("+i+","+j+") - "+blocks[j][i].isMined());
                    blocks[j][i].setNeighbor(countMines(j, i));
                }
            }

            ViewPanel v = new ViewPanel(horizontal, vertical, unit, blocks, mines.length);

            FrameClass frameClass = new FrameClass(horizontal, vertical, unit, v);
    }

    public static int countMines(int i, int j){
        int count = 0;
        if(i==0){
            if(j==0){
                if(blocks[i+1][j].isMined()){count++;}
                if(blocks[i][j+1].isMined()){count++;}
                if(blocks[i+1][j+1].isMined()){count++;}
            }else if (j==(blocks[i].length-1)){
                if(blocks[i][j-1].isMined()){count++;}
                if(blocks[i+1][j].isMined()){count++;}
                if(blocks[i+1][j-1].isMined()){count++;}
            }else{
                if(blocks[i][j+1].isMined()){count++;}
                if(blocks[i][j-1].isMined()){count++;}
                if(blocks[i+1][j-1].isMined()){count++;}
                if(blocks[i+1][j].isMined()){count++;}
                if(blocks[i+1][j+1].isMined()){count++;}
            }
        } else if(i==(blocks.length-1)){
            if(j==0){
                if(blocks[i-1][j].isMined()){count++;}
                if(blocks[i-1][j+1].isMined()){count++;}
                if(blocks[i][j+1].isMined()){count++;}
            }else if(j==((blocks[i].length)-1)){
                if(blocks[i][j-1].isMined()){count++;}
                if(blocks[i-1][j-1].isMined()){count++;}
                if(blocks[i-1][j].isMined()){count++;}
            }else{
                if(blocks[i][j-1].isMined()){count++;}
                if(blocks[i][j+1].isMined()){count++;}
                if(blocks[i-1][j-1].isMined()){count++;}
                if(blocks[i-1][j].isMined()){count++;}
                if(blocks[i-1][j+1].isMined()){count++;}
            }
        } else {
            if(j==0){
                if(blocks[i-1][j].isMined()){count++;}
                if(blocks[i-1][j+1].isMined()){count++;}
                if(blocks[i][j+1].isMined()){count++;}
                if(blocks[i+1][j].isMined()){count++;}
                if(blocks[i+1][j+1].isMined()){count++;}
            } else if(j==((blocks[i].length)-1)){
                if(blocks[i-1][j].isMined()){count++;}
                if(blocks[i-1][j-1].isMined()){count++;}
                if(blocks[i][j-1].isMined()){count++;}
                if(blocks[i+1][j-1].isMined()){count++;}
                if(blocks[i+1][j].isMined()){count++;}
            } else {
                if(blocks[i-1][j-1].isMined()){count++;}
                if(blocks[i-1][j].isMined()){count++;}
                if(blocks[i-1][j+1].isMined()){count++;}
                if(blocks[i][j-1].isMined()){count++;}
                if(blocks[i][j+1].isMined()){count++;}
                if(blocks[i+1][j-1].isMined()){count++;}
                if(blocks[i+1][j].isMined()){count++;}
                if(blocks[i+1][j+1].isMined()){count++;}
            }
        }
        return count;
    }
}

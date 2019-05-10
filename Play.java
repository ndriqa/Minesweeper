/**Play class starts the game, initializes mines through Helper class,
 * gets input through the mouse
 *
 * @author ndricimrrahmani*/

public class Play{
    private static int horizontal, vertical;
    private static int unit;
    private static int minesratio;
    private static Landmine[][] blocks;
    private static int[][] mines;
    static boolean playing = true;


    public static void main(String[] args) {
        horizontal = 10;
        vertical = 10;
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
            help.initializeMines();
            mines = help.getMinePlaces();

            /**
            for(int i = 0; i<mines.length; i++){
                blocks[mines[i]/horizontal][mines[i]%horizontal].mine();
            }
             */

            int mineNum = 0;

            for(int i = 0; i<mines.length; i++){
                for(int j = 0; j<mines[i].length; j++){
                    blocks[i][mines[i][j]].mine();
                }
                mineNum += mines[i].length;
            }

            for(int i = 0; i<blocks.length; i++){
                for(int j = 0; j<blocks[0].length; j++){
                    System.out.println("("+i+","+j+") - "+blocks[j][i].isMined());
                    blocks[j][i].setNeighbor(countMines(j, i));
                }
            }

            ViewPanel v = new ViewPanel(horizontal, vertical, unit, blocks, mineNum);

            //FrameClass frameClass = new FrameClass(horizontal, vertical, unit, v);
        new FrameClass(horizontal, vertical, unit, v);
    }

    private static int countMines(int i, int j){
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

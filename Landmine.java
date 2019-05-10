/**Landmine class stores information about one block, such as: the current
 * state of the mine, wether it is open or not, flagged or not, or wether
 * it has a mine inside or not and also the number of how many neighbors
 * are mined.
 *
 * @author ndricimrrahmani*/

public class Landmine {
    boolean open;
    boolean flagged;
    boolean mined;
    int neighbor;


    public Landmine(){
        open = false;
        flagged = false;
        mined = false;
        neighbor = 0;
    }

    public void mine(){
        mined = true;
    }
    public void flag(){
        flagged = true;
    }
    public void unFlag(){
        flagged = false;
    }
    public void open() {
        open = true;
    }
    public void close(){
        open = false;
    }
    public boolean isOpen() {
        return open;
    }
    public boolean isMined() {
        return mined;
    }
    public boolean isFlagged() {
        return flagged;
    }
    public void setNeighbor(int num){
        neighbor = num;
    }
    public int getNeighbor() {
        return neighbor;
    }
    @Override
    public String toString() {
        return ""+isMined();
    }
}

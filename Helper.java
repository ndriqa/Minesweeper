/**Helper class stores some useful methods
 *
 * @author ndricimrrahmani*/
/**
public class Helper {
    int[] minePlaces;           //minePlace array stores all blocks numbers which contain a mine
    int minesRatio;             //the ratio between normal blocks and blocks with mines
    int horizontal, vertical;   //horizontal and vertical number of blocks
    int allBlocks;              //total number of blocks

    public Helper(int h, int v, int ratio){
        //numri i blloqeve horizontale dhe vertikale
        horizontal = h;
        vertical = v;

        //ne qfare raporti do te jete numri total i blloqeve me numrin total te minave.
        minesRatio = ratio;

        //numri i plote i blloqeve
        allBlocks = horizontal*vertical;

        //numrat e blloqeve qe permbajne mina
        minePlaces = new int[allBlocks/minesRatio];

        initializeMines();
    }

    public void initializeMines(){
        for(int i = 0; i<minePlaces.length; i++){
            minePlaces[i] = (int)((Math.random()*allBlocks));
        }
    }
    public int[] getMinePlaces() {
        return minePlaces;
    }
}
*/
/**Helper class stores some useful methods
 *
 * @author ndricimrrahmani*/

public class Helper {
    int[][] landmines;
    int minesRatio;             //the ratio between normal blocks and blocks with mines
    int horizontal, vertical;   //horizontal and vertical number of blocks

    public Helper(int h, int v, int ratio) {
        //numri i blloqeve horizontale dhe vertikale
        horizontal = h;
        vertical = v;

        //ne qfare raporti do te jete numri total i blloqeve me numrin total te minave.
        minesRatio = ratio;

        landmines = new int[vertical][horizontal/minesRatio];

    }

    public void initializeMines() {
        for (int i = 0; i < landmines.length; i++) {
            for (int j = 0; j < landmines[i].length; j++) {
                landmines[i][j] = (int)((Math.random() * horizontal));
            }
        }
    }
    public int[][] getMinePlaces(){
        return landmines;
    }
}
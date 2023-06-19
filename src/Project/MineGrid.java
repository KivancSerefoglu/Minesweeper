package Project;

import java.util.Random;

class MineGrid {
    private static final int MINE=-1;
    private int[] [] mineInformation;
    private int correctFlags=0;
    private static final int FLAG=-2;
    private static final int flagMine=-3;
    private int restOfFlags;
    private int numMines;

    public MineGrid(int numRows, int numCols, int numMines){
        mineInformation= new int[numRows][numCols];
        initializeCells();
        placeMines(numMines);
        setMineInformation();
        this.numMines=numMines;
        this.restOfFlags=numMines;
    }

    private void initializeCells(){
        for (int i=0; i< mineInformation.length; i++){
            for (int j=0; j< mineInformation[0].length; j++){
                mineInformation[i][j]=0;
            }
        }
    }

    private void placeMines(int numMines){
        Random random=new Random();
        for (int i=0; i< numMines;i++){
            int r=random.nextInt(mineInformation.length);
            int c= random.nextInt(mineInformation[0].length);
            if (mineInformation[r][c] !=MINE){
                mineInformation[r][c]=MINE;
            }else{
                i--;
            }
        }
    }

    public boolean isMine(int i, int j){
        return mineInformation[i][j]==MINE;
    }

    public int getNumMines() {
        return numMines;
    }

    private void setMineInformation(){
        for (int i=0; i< mineInformation.length;i++){
            for (int j=0; j< mineInformation[0].length; j++){
                if (mineInformation[i][j]==MINE){

                    incrementMineCountAt(i-1, j-1);
                    incrementMineCountAt(i-1, j);
                    incrementMineCountAt(i-1, j+1);

                    incrementMineCountAt(i, j-1);
                    incrementMineCountAt(i, j+1);

                    incrementMineCountAt(i+1, j-1);
                    incrementMineCountAt(i+1, j);
                    incrementMineCountAt(i+1, j+1);

                }
            }
        }
    }

    private void incrementMineCountAt(int i, int j){
        if (isInsideGrid(i,j)&&!isMine(i,j)){
            mineInformation[i][j]++;
        }
    }

    private boolean isInsideGrid(int i, int j){
        return (i>=0 && i< mineInformation.length)&&
                (j>=0 && j< mineInformation[0].length);
    }

    public int getCellContent(int i, int j){
        return mineInformation[i][j];
    }



    public void setFlag (int i, int j){
        if (mineInformation[i][j]==MINE) {
            mineInformation[i][j] = flagMine;
            correctFlags++;
        }else {
            mineInformation[i][j]=FLAG;
        }
        restOfFlags--;
        System.out.println("rest of flag down "+restOfFlags);

    }

    public void removeFlag(int i, int j){
        if (mineInformation[i][j]==flagMine){
            mineInformation[i][j]=MINE;
            correctFlags--;
        }else{
            mineInformation[i][j]=setFlagInformation(i,j);
        }
        restOfFlags++;
        System.out.println("rest of flag up "+restOfFlags);
    }

    public int getRestOfFlags() {
        return restOfFlags;
    }

    private int setFlagInformation(int i, int j){
        mineInformation[i][j]=0;
        if (isInsideGrid(i-1,j-1)&&(mineInformation[i-1][j-1]==MINE || mineInformation[i-1][j-1]==flagMine)){
            incrementMineCountAt(i,j);
        }if (isInsideGrid(i-1,j)&&(mineInformation[i-1][j]==MINE||mineInformation[i-1][j]==flagMine)){
            incrementMineCountAt(i,j);
        }if (isInsideGrid(i-1,j+1)&&(mineInformation[i-1][j+1]==MINE||mineInformation[i-1][j+1]==flagMine)){
            incrementMineCountAt(i,j);
        }if (isInsideGrid(i,j-1)&&(mineInformation[i][j-1]==MINE||mineInformation[i][j-1]==flagMine)){
            incrementMineCountAt(i,j);
        }if (isInsideGrid(i,j+1)&&(mineInformation[i][j+1]==MINE||mineInformation[i][j+1]==flagMine)){
            incrementMineCountAt(i,j);
        }if (isInsideGrid(i+1,j-1)&&(mineInformation[i+1][j-1]==MINE||mineInformation[i+1][j-1]==flagMine)){
            incrementMineCountAt(i,j);
        }if (isInsideGrid(i+1,j)&&(mineInformation[i+1][j]==MINE||mineInformation[i+1][j]==flagMine)){
            incrementMineCountAt(i,j);
        }if (isInsideGrid(i+1,j+1)&&(mineInformation[i+1][j+1]==MINE||mineInformation[i+1][j+1]==flagMine)) {
            incrementMineCountAt(i, j);
        }
        return mineInformation[i][j];
    }

    public boolean isFlag(int i, int j){
        return mineInformation[i][j]==flagMine||mineInformation[i][j]==FLAG;
    }

    public int getCorrectFlags() {
        return correctFlags;
    }
}

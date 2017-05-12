package game;

/**
 * Created by ryan on 2/4/17.
 */
public class Position {
    int rank;
    int file;


    public Position(int rank, int file){
        this.rank = rank;
        this.file = file;
    }
    public int getRank(){
        return rank;
    }
    public int getFile(){
        return file;
    }

    public void setFile(int newFile){
        if (newFile < 0){
            this.file = 0;
        }
        else{
            this.file = newFile;
        }
    }
    public void setRank(int newRank){
        if (newRank < 0){
            this.rank = 0;
        }
        else{
            this.rank = newRank;
        }
    }

    public boolean equals(Object o){
        Position pos = (Position) o;
        if (pos.getFile() == this.getFile() && pos.getRank() == this.getRank()){
            return true;
        }
        else{
            return false;
        }
    }

}

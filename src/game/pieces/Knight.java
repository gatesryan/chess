package game.pieces;
import game.Board;
import game.Color;
import game.Position;
import game.pieces.ChessPiece;

import java.util.ArrayList;
/**
 * Created by ryan on 2/3/17.
 */
public class Knight extends ChessPiece {


    public Knight(Color color, int rank, int file){
        this.setRank(rank);
        this.setFile(file);
        this.setColor(color);
        this.setValue(3);
        this.setPosition(new Position(rank, file));

    }


    //
    public ArrayList getPossibleMoves(Board board){
        ArrayList<Position> positions = new ArrayList<Position>();
        positions.add(new Position(this.getRank()+1, this.getFile() + 2));
        positions.add(new Position(this.getRank()+1, this.getFile() - 2));
        positions.add(new Position(this.getRank()-1, this.getFile()+2));
        positions.add(new Position(this.getRank()-1, this.getFile()-2));

        positions.add(new Position(this.getRank()+2, this.getFile() +1));
        positions.add(new Position(this.getRank()+2, this.getFile()-1));
        positions.add(new Position(this.getRank()-2, this.getFile()+1));
        positions.add(new Position(this.getRank()-2, this.getFile()-1));

        ArrayList<Position> validPositions = new ArrayList<Position>();
        for (Position currentPosition : positions){
            int currentRank = currentPosition.getRank();
            int currentFile = currentPosition.getFile();


//            if (currentRank >= 0 && currentFile >= 0 && currentRank < board.getSize() && currentFile < board.getSize()){
//                validPositions.add(currentPosition);
//            }
            if (currentRank < 0 || currentFile < 0 || currentRank >= board.getSize() || currentFile >= board.getSize()){
                continue;
            }

            if (board.getPieceAtPosition(currentRank, currentFile) == null || board.getPieceAtPosition(currentRank, currentFile).getColor() != this.getColor()){
                validPositions.add(currentPosition);
            }
        }
        return validPositions;
    }
}

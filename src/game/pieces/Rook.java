package game.pieces;

import game.Board;
import game.Color;
import game.Position;
import game.pieces.ChessPiece;
import game.pieces.King;

import java.util.ArrayList;

/**
 * Created by ryan on 2/1/17.
 */
public class Rook extends ChessPiece {


    public Rook(Color color, int rank, int file){
        this.setRank(rank);
        this.setFile(file);
        this.setColor(color);
        this.setValue(5);
        this.setPosition(new Position(rank, file));
    }



    /**
     * Determines all of the possible positions on the board the piece can move to
     * @param board
     * @return an ArrayList of Position objects determining the possible positions
     */
    public ArrayList<Position> getPossibleMoves(Board board){
        ArrayList<Position> possibleMoves = new ArrayList<Position>();
        checkRanks(board, possibleMoves);
        checkFiles(board, possibleMoves);

        return possibleMoves;
    }

    /**
     * Checks all the squares on the same horizontal as the piece and adds position to array of possible moves
     * until another piece is reached or the end of the board is reached
     * @param board
     * @param moves
     */    private void checkRanks(Board board, ArrayList<Position> moves){
        int currentFile = this.getFile();
        int currentRank = this.getRank();

        for (int file = currentFile+1; file < board.getSize(); file++){

            ChessPiece currentPosition = board.getPieceAtPosition(currentRank, file);
            if (addPosition(moves, currentPosition, currentRank, file)){
                break;
            }
        }

        for (int file = currentFile-1; file >= 0; file--){
            ChessPiece currentPosition = board.getPieceAtPosition(currentRank, file);

            if (addPosition(moves, currentPosition, currentRank, file)){
                break;
            }
        }
    }

    /**
     * Checks all the squares on the same vertical as the piece and adds position to array of possible moves
     * until another piece is reached or the end of the board is reached
     * @param board
     * @param moves
     */    private void checkFiles(Board board, ArrayList<Position> moves){
        int currentFile = this.getFile();
        int currentRank = this.getRank();

        for (int rank = currentRank+1; rank < board.getSize(); rank++){
            ChessPiece currentPosition = board.getPieceAtPosition(rank, currentFile);

            if (addPosition(moves, currentPosition, rank, currentFile)){
                break;
            }
        }

        for (int rank = currentRank-1; rank >= 0; rank--){
            ChessPiece currentPosition = board.getPieceAtPosition(rank, currentFile);

            if (addPosition(moves, currentPosition, rank, currentFile)){
                break;
            }
        }
    }

    // add position to array list of moves and return a boolean for whether or not this is the last position that can be added
    private boolean addPosition(ArrayList<Position> moves, ChessPiece pos, int rank, int file){
        if (pos == null){
            moves.add(new Position(rank, file));
            return false;
        }
        else if (pos != null && pos.getColor() != this.getColor()){
            moves.add(new Position(rank, file));
            return true;
        }
        else{
            return true;
        }
    }


}

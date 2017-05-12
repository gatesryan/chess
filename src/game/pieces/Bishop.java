package game.pieces;

import game.Board;
import game.Color;
import game.Position;

import java.util.ArrayList;

/**
 * Created by ryan on 2/3/17.
 */
public class Bishop extends ChessPiece {

    public Bishop(Color color, int rank, int file){
        this.setRank(rank);
        this.setFile(file);
        this.setColor(color);
        this.setValue(3);
        this.setPosition(new Position(rank, file));

    }


    /**
     * Determines all of the possible positions on the board the piece can move to
     * @param board
     * @return an ArrayList of Position objects determining the possible positions
     */
    public ArrayList<Position> getPossibleMoves(Board board){
        ArrayList<Position> moves = new ArrayList<Position>();
        checkDiagonals(board, moves);
        return moves;
    }

    // check all directions diagonally outward from current location and add empty positions or positions with enemy pieces to list
    private void checkDiagonals(Board board, ArrayList<Position> moves) {
        int currentFile = this.getFile();
        int currentRank = this.getRank();

        for (int file = currentFile + 1, rank=currentRank + 1; file < board.getSize() && rank < board.getSize(); file++, rank++) {
            ChessPiece currentPosition = board.getPieceAtPosition(rank, file);
            if (addPosition(moves, currentPosition, rank, file)) {
                break;
            }
        }
        for (int file = currentFile + 1, rank = currentRank - 1; file < board.getSize() && rank >= 0 ; file++, rank--) {
            ChessPiece currentPosition = board.getPieceAtPosition(rank, file);
            if (addPosition(moves, currentPosition, rank, file)) {
                break;
            }

        }
        for (int file = currentFile - 1, rank = currentRank+1; file >= 0 && rank < board.getSize(); file--, rank++) {
            ChessPiece currentPosition = board.getPieceAtPosition(rank, file);
            if (addPosition(moves, currentPosition, rank, file)) {
                break;
            }
        }
        for (int file = currentFile-1, rank = currentRank-1; file >= 0 && rank >= 0; file--, rank--){
            ChessPiece currentPosition = board.getPieceAtPosition(rank, file);
            if (addPosition(moves, currentPosition, rank, file)){
                break;
            }
        }
    }

    // add the position to the respective array of positions
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

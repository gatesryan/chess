package game.pieces; /**
 * Created by ryan on 2/3/17.
 */

import game.Board;
import game.Color;
import game.Position;
import game.pieces.ChessPiece;

import java.util.ArrayList;
public class Pawn extends ChessPiece {

    boolean hasMoved = false;

    public Pawn(Color color, int rank, int file){
        this.setRank(rank);
        this.setFile(file);
        this.setColor(color);
        this.setValue(1);
        this.setPosition(new Position(rank, file));
        this.hasMoved = false;
    }


    /**
     * Determines all of the possible positions on the board the piece can move to
     * @param board
     * @return an ArrayList of Position objects determining the possible positions
     */
    public ArrayList<Position> getPossibleMoves(Board board){
        ArrayList<Position> positions = new ArrayList<Position>();

        int movementVector;

        movementVector = this.getColor() == Color.WHITE ? -1 : 1;

        ChessPiece firstDiagonalMove;
        ChessPiece secondDiagonalMove;
        ChessPiece forwardMove = board.getPieceAtPosition(this.getRank() + movementVector, this.getFile());

        try {
            firstDiagonalMove = board.getPieceAtPosition(this.getRank() + movementVector, this.getFile() + 1);
            if (firstDiagonalMove != null && firstDiagonalMove.getColor() != this.getColor()){
                positions.add(new Position(this.getRank()+movementVector, this.getFile()+1));
            }
        } catch(IndexOutOfBoundsException ex){

        }

        try {
            secondDiagonalMove = board.getPieceAtPosition(this.getRank() + movementVector, this.getFile() - 1);
            if (secondDiagonalMove != null && secondDiagonalMove.getColor() != this.getColor()){
                positions.add(new Position(this.getRank()+movementVector, this.getFile()-1));
            }
        } catch (IndexOutOfBoundsException ex){

        }


        if (!hasMoved && board.getPieceAtPosition(this.getRank() + 2*movementVector, this.getFile()) == null){
            positions.add(new Position(this.getRank() + 2*movementVector, this.getFile()));
        }
        if (forwardMove == null){
            positions.add(new Position(this.getRank()+movementVector, this.getFile()));
        }

        return positions;

    }

    /**
     * Gets the list of positions the pawn can move to resulting in a capture of another piece
     * @param board
     * @return an ArrayList of positions
     */
    public ArrayList<Position> getPossibleCaptureMoves(Board board){
        ArrayList<Position> positions = new ArrayList<Position>();
        int movementVector;

        movementVector = this.getColor() == Color.WHITE ? -1 : 1;

        ChessPiece firstDiagonalMove;
        ChessPiece secondDiagonalMove;

        try {
            firstDiagonalMove = board.getPieceAtPosition(this.getRank() + movementVector, this.getFile() + 1);
            if (firstDiagonalMove != null && firstDiagonalMove.getColor() != this.getColor()){
                positions.add(new Position(this.getRank()+movementVector, this.getFile()+1));
            }
        } catch(IndexOutOfBoundsException ex){

        }

        try {
            secondDiagonalMove = board.getPieceAtPosition(this.getRank() + movementVector, this.getFile() - 1);
            if (secondDiagonalMove != null && secondDiagonalMove.getColor() != this.getColor()){
                positions.add(new Position(this.getRank()+movementVector, this.getFile()-1));
            }
        } catch (IndexOutOfBoundsException ex){

        }

        return positions;
    }

    public void setHasMoved(boolean val){
        this.hasMoved = val;
    }

    public boolean getHasMoved(){
        return this.hasMoved;
    }
}
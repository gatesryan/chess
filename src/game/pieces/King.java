package game.pieces;

/**
 * Created by ryan on 2/3/17.
 */
import game.Board;
import game.Color;
import game.Position;

import java.util.ArrayList;

public class King extends ChessPiece {


    public King(Color color, int rank, int file){
        this.setRank(rank);
        this.setFile(file);
        this.setColor(color);
        this.setValue(10);
        this.setPosition(new Position(rank, file));
    }


    /**
     * Determines whether king can move to a specified position
     * @param board
     * @param rank
     * @param file
     * @return boolean value determining whether piece can move
     */
    public boolean canMove(Board board, int rank, int file){
        if (this.getPossibleMoves(board).contains(new Position(rank, file)) && !this.isInCheckAfterMove(board, this,new Position(rank, file))){
            return true;
        }
        return false;
    }

    /**
     * Determines all of the possible positions on the board the piece can move to
     * @param board
     * @return an ArrayList of Position objects determining the possible positions
     */
    public ArrayList<Position> getPossibleMoves(Board board){
        int currentRank = this.getRank();
        int currentFile = this.getFile();
        ArrayList<Position> positions = new ArrayList<Position>();
        ArrayList<Position> validPositions = new ArrayList<Position>();

        //possible forward & backward positions
        positions.add(new Position(currentRank+1, currentFile));
        positions.add(new Position(currentRank-1, currentFile));

        //possible diagonal positions
        positions.add(new Position(currentRank+1, currentFile+1));
        positions.add(new Position(currentRank+1, currentFile-1));
        positions.add(new Position(currentRank-1, currentFile +1 ));
        positions.add(new Position(currentRank -1, currentFile -1));

        //possible sideways positions
        positions.add(new Position(currentRank, currentFile + 1));
        positions.add(new Position(currentRank, currentFile - 1 ));

        for (Position pos : positions){
            int posRank = pos.getRank();
            int posFile = pos.getFile();

            if (posFile < 0 || posRank < 0 || posFile >= board.getSize() || posRank >= board.getSize()){
                continue;
            }
            if (board.getPieceAtPosition(posRank, posFile) == null || board.getPieceAtPosition(posRank, posFile).getColor() != this.getColor()){
                validPositions.add(pos);
            }
        }
        return validPositions;
    }

    /**
     * Determines whether king is in check currently or not.
     * @param board
     * @return boolean determining if king is currently in check
     */
    public boolean isInCheck(Board board){
        ArrayList<ChessPiece> pieces;
        if (this.getColor() == Color.BLACK) {
            pieces = board.getPieces(Color.WHITE);
        }
        else{
            pieces = board.getPieces(Color.BLACK);
        }
        for (ChessPiece piece : pieces){
            if (piece.getPossibleMoves(board).contains(this.getPosition())) {
                return true;
            }
        }
        return false;
    }

//    public boolean isInCheck(Board board, Position pos){
//        ArrayList<ChessPiece> pieces = board.getPieces(this.getColor());
//
//        for (ChessPiece piece : pieces){
//            if (piece.getPossibleMoves(board).contains(pos)){
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * Determines whether the king has any more valid moves to remove itself from check
     * @param board
     * @return boolean determining whether the game is over or not
     */
    public boolean isInCheckMate(Board board){
        ArrayList<ChessPiece> friendlyPieces;
        friendlyPieces = board.getPieces(this.getColor());

        //loop through friendly pieces to see if there is a move that blocks or captures piece that is threatening the king
        for (ChessPiece piece : friendlyPieces){
            ArrayList<Position> moves = piece.getPossibleMoves(board);
            if (piece instanceof Pawn){
                moves = ((Pawn) piece).getPossibleCaptureMoves(board);
            }
            for (Position move : moves){
                if (!this.isInCheckAfterMove(board, piece, move)){
                    return false;
                }
            }

        }
        if (this.isInCheck(board)){
            ArrayList<Position> moves = this.getPossibleMoves(board);
            for (Position move : moves){
                if (!isInCheckAfterMove(board, this, move)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determines whether the movement of a piece places the King is check
     * @param board
     * @param movedPiece
     * @param newPosition
     * @return a boolean determining if king is in check
     */
    public boolean isInCheckAfterMove(Board board, ChessPiece movedPiece, Position newPosition){
        ArrayList<ChessPiece> enemyPieces;



        Position oldPosition = movedPiece.getPosition();

        ChessPiece pieceAtProspectivePosition = board.getPieceAtPosition(newPosition.getRank(), newPosition.getFile());
        board.setPieceAtPosition(movedPiece, newPosition.getRank(), newPosition.getFile());


        if (this.getColor() == Color.BLACK) {
            enemyPieces = board.getPieces(Color.WHITE);
        }
        else{
            enemyPieces = board.getPieces(Color.BLACK);
        }

        //loop through all enemy pieces to see if one of pieces can move to king's position
        ArrayList<Position> moves;
        for (ChessPiece piece : enemyPieces){

            if (piece instanceof Pawn){
                moves = ((Pawn) piece).getPossibleCaptureMoves(board);
            }
            else {
                moves = piece.getPossibleMoves(board);
            }

            if (moves.contains(this.getPosition())){

                board.setPieceAtPosition(movedPiece, oldPosition.getRank(), oldPosition.getFile());
                if (pieceAtProspectivePosition != null) {
                    board.setPieceAtPosition(pieceAtProspectivePosition, newPosition.getRank(), newPosition.getFile());
                }

                return true;
            }
            else if (movedPiece instanceof King && moves.contains(newPosition)){
                board.setPieceAtPosition(movedPiece, oldPosition.getRank(), oldPosition.getFile());
                if (pieceAtProspectivePosition != null) {
                    board.setPieceAtPosition(pieceAtProspectivePosition, newPosition.getRank(), newPosition.getFile());
                }

                return true;
            }
        }
        //at this point the piece can move to the position it was trying to move to
        board.setPieceAtPosition(movedPiece, oldPosition.getRank(), oldPosition.getFile());
        if (pieceAtProspectivePosition != null) {
            board.setPieceAtPosition(pieceAtProspectivePosition, newPosition.getRank(), newPosition.getFile());
        }

        return false;

    }




}

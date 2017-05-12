package game;

import game.pieces.*;

/**
 * Created by ryan on 2/1/17.
 */

public class SquareBoard extends Board {

    private ChessPiece [][] positionBoard;
    private int sideLength;


    public SquareBoard(int sideLength, boolean customGame){
        this.sideLength = sideLength;
        positionBoard = new ChessPiece[sideLength][sideLength];
        initializeBoard(customGame);
    }

    /**
     * Creates standard chessboard starting configuration
     */
    void initializeBoard(boolean customGame) {
        for (int rank = 0; rank < sideLength; rank++){
            for (int file = 0; file < sideLength; file++){
                if (rank == 0 || rank == 7){
                    switch(file){
                        case 0:case 7:
                            positionBoard[rank][file] = rank <= 1 ? new Rook(Color.BLACK, rank, file) : new Rook(Color.WHITE, rank, file); // rook
                            addPieceToArray(positionBoard[rank][file]);
                            break;
                        case 1:case 6:
                            positionBoard[rank][file] = rank <= 1 ? new Knight(Color.BLACK, rank, file): new Knight(Color.WHITE, rank, file); // bishop or knight
                            addPieceToArray(positionBoard[rank][file]);
                            break;
                        case 2:case 5:
                            positionBoard[rank][file] = rank <= 1 ? new Bishop(Color.BLACK,rank, file): new Bishop(Color.WHITE, rank, file);
                            addPieceToArray(positionBoard[rank][file]);
                            break;
                        case 3:
                            positionBoard[rank][file] = rank <= 1 ? new Queen(Color.BLACK, rank, file) : new Queen(Color.WHITE, rank, file); // queen
                            addPieceToArray(positionBoard[rank][file]);
                            break;
                        case 4:
                            positionBoard[rank][file] = rank <= 1 ? new King(Color.BLACK, rank, file) : new King(Color.WHITE, rank, file); //king
                            addPieceToArray(positionBoard[rank][file]);
                            if (positionBoard[rank][file].getColor() == Color.BLACK){
                                this.blackKing = (King) positionBoard[rank][file];
                            }
                            else{
                                this.whiteKing = (King) positionBoard[rank][file];
                            }

                            break;
                    }
                }
                else if (rank == 1){
                    positionBoard[rank][file] = new Pawn(Color.BLACK, rank, file);
                    addPieceToArray(positionBoard[rank][file]);

                }
                else if (rank == 6){
                    positionBoard[rank][file] = new Pawn(Color.WHITE, rank, file);
                    addPieceToArray(positionBoard[rank][file]);
                }
                else if (rank == 2 && file == 3 && customGame){
                    positionBoard[rank][file] = new Ferz(Color.BLACK, rank, file);
                    addPieceToArray(positionBoard[rank][file]);
                }
                else if (rank == 2 && file == 4 && customGame){
                    positionBoard[rank][file] = new Wazir(Color.BLACK, rank, file);
                    addPieceToArray(positionBoard[rank][file]);
                }
                else if (rank == 5 && file == 3 && customGame){
                    positionBoard[rank][file] = new Ferz(Color.WHITE, rank, file);
                    addPieceToArray(positionBoard[rank][file]);
                }
                else if (rank == 5 && file == 4 && customGame){
                    positionBoard[rank][file] = new Wazir(Color.WHITE, rank, file);
                    addPieceToArray(positionBoard[rank][file]);
                }

            }
        }

    }

    /**
     *
     * @return 2D Array of ChessPieces representing state of the board
     */
    public ChessPiece[][] getStateCopy(){
        ChessPiece[][] boardState = new ChessPiece[8][8];
        for (int rank = 0; rank < 8; rank++){
            for (int file = 0; file < 8; file++){
                boardState[rank][file] = positionBoard[rank][file];
            }
        }
        return boardState;
    }

    public ChessPiece[][] getState(){
        return positionBoard;
    }

    public void setState(ChessPiece[][] newState){
        for (int rank = 0; rank < 8; rank++){
            for (int file = 0; file < 8; file++){
                if (newState[rank][file] != null) {
                    newState[rank][file].setPosition(new Position(rank, file));
                    newState[rank][file].setRank(rank);
                    newState[rank][file].setFile(file);
                }
            }
        }
        this.positionBoard = newState;
    }

    /**
     * Moves a specific chess piece on the board to a specified position
     * @param piece
     * @param rank
     * @param file
     */
    public boolean movePiece(ChessPiece piece, int rank, int file){
        if (piece.canMove(this, rank, file)){
            //piece has been captured so remove from list
            removePieceFromArray(this.getPieceAtPosition(rank, file));

            positionBoard[piece.getRank()][piece.getFile()] = null;
            positionBoard[rank][file] = piece;
            piece.setPosition(new Position(rank, file));
            piece.setRank(rank);
            piece.setFile(file);
            if (piece instanceof Pawn){
                ((Pawn) piece).setHasMoved(true);
            }
            printBoard();
            return true;
        }
        printBoard();
        return false;
    }


    /**
     * Prints out the current state of the board
     */
    public void printBoard(){
        System.out.println();
        for (int rank = 0; rank < 8; rank++){
            for (int file = 0; file < 8; file++){
                if (positionBoard[rank][file] == null){
                    System.out.print("0   ");
                }
                else if (positionBoard[rank][file].getColor() == Color.WHITE){
                    System.out.print(positionBoard[rank][file].getValue() + "w" +  "  ");
                }
                else {
                    System.out.print(positionBoard[rank][file].getValue() + "b" + "  ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Returns the chess piece at the location specified
     * @param rank
     * @param file
     * @return ChessPiece object located at position
     * @throws IndexOutOfBoundsException
     */
    public ChessPiece getPieceAtPosition(int rank, int file) throws IndexOutOfBoundsException{
        if (rank >= this.sideLength || file >= this.sideLength || rank < 0 || file < 0){
            throw new IndexOutOfBoundsException();
        }
        else {
            return positionBoard[rank][file];
        }
    }

    /**
     * Places a ChessPiece object at specified position regardless of whether it can move to that position
     * @param piece
     * @param rank
     * @param file
     */
    public void setPieceAtPosition(ChessPiece piece, int rank, int file){

        positionBoard[piece.getRank()][piece.getFile()] = null; //remove piece from old position
        if (rank < 0 || file < 0 || rank > sideLength || file > sideLength){
            return;
        }
        else{
            removePieceFromArray(positionBoard[rank][file]);
            positionBoard[rank][file] = piece;
            addPieceToArray(piece);
            piece.setRank(rank);
            piece.setFile(file);
            piece.setPosition(new Position(rank, file));
        }
    }

    /**
     *
     * @return side length of the board
     */
    public int getSize(){
        return this.sideLength;
    }

    /**
     * adds the ChessPiece object to the appropriate array for its color
     * @param piece
     */
    void addPieceToArray(ChessPiece piece){
        if (piece.getColor() == Color.BLACK && !this.getPieces(Color.BLACK).contains(piece)) {
            this.getPieces(Color.BLACK).add(piece);
        }
        else if (piece.getColor() == Color.WHITE && !this.getPieces(Color.WHITE).contains(piece)){
            this.getPieces(Color.WHITE).add(piece);
        }
    }
    private void removePieceFromArray(ChessPiece piece){
        if (piece == null)
            return;

        if (piece.getColor() == Color.BLACK) {
            this.getPieces(Color.BLACK).remove(piece);
        }
        else{
            this.getPieces(Color.WHITE).remove(piece);
        }
    }

    /**
     * Generates a basic checkmate configuration on the board for testing purposes
     */
    public void generateCheckmateConfiguration(){
        this.getPieces(Color.BLACK).clear();
        this.getPieces(Color.WHITE).clear();

        positionBoard = new ChessPiece[8][8];
        for (int file = 5; file < 8; file++){
            positionBoard[1][file] = new Pawn(Color.BLACK, 1, file);
            addPieceToArray(positionBoard[1][file]);
        }
        positionBoard[0][6] = new King(Color.BLACK, 0, 6);
        positionBoard[0][0] = new Rook(Color.WHITE, 0, 0);
        positionBoard[7][6] = new King(Color.WHITE, 7, 6);

        addPieceToArray(positionBoard[0][6]);
        addPieceToArray(positionBoard[0][0]);
        addPieceToArray(positionBoard[7][6]);
    }
//
//    public isInStalemate(){
//        if (!this.getKing(Color.BLACK).isInCheck(this) && board.i)
//    }
}

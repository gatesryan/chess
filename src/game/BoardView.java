package game;
import game.pieces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;

/**
 * Created by ryan on 2/11/17.
 */
public class BoardView {

    private JPanel chessboard;
    private boolean customGame;

    private final String BLACK_PAWN = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_pdt60.png";
    private final String WHITE_PAWN = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_plt60.png";

    private final String BLACK_BISHOP = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_bdt60.png";
    private final String WHITE_BISHOP = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_blt60.png";

    private final String BLACK_KING = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_kdt60.png";
    private final String WHITE_KING = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_klt60.png";

    private final String BLACK_QUEEN = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_qdt60.png";
    private final String WHITE_QUEEN = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_qlt60.png";

    private final String BLACK_KNIGHT = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_ndt60.png";
    private final String WHITE_KNIGHT = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_nlt60.png";

    private final String BLACK_ROOK = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_rdt60.png";
    private final String WHITE_ROOK = "/Users/ryan/IdeaProjects/ChessGame/res/Chess_rlt60.png";


    public BoardView(JFrame window, boolean customGame){
        this.customGame = customGame;
        window.getContentPane().removeAll();
        //window.setLayout(new BorderLayout());

        chessboard = new JPanel(new GridLayout(8,8));
        chessboard.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        window.getContentPane().add(chessboard, BorderLayout.CENTER);

        int mod = 0;
        for (int i = 0; i < 64; i++) {
            Color lightColor = new Color(234, 184, 86);
            Color darkColor = new Color(85, 15, 10);

            if (i % 8 == 0 && mod == 0){
                mod = 1;
            }
            else if (i % 8 == 0 && mod == 1)
                mod = 0;

            if (i % 2 == mod) {
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBackground(darkColor);
                chessboard.add(panel);
            }
            else{
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBackground(lightColor);
                chessboard.add(panel);
            }

        }

        initializeBlackPieces();
        initializeWhitePieces();
        window.revalidate();

    }


    /**
     * Sets up the black pieces in the proper configuration for the beginning of a standard game.
     * All pieces taken from Wikimedia Commons at https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent
     */
    void initializeBlackPieces(){
        JPanel square;

        //initialize fairy pieces
        if (customGame) {
            square = (JPanel) chessboard.getComponent(19);
            square.add(new JLabel(new ImageIcon("/Users/ryan/IdeaProjects/ChessGame/res/Chess_bdt45.png")));

            square = (JPanel) chessboard.getComponent(20);
            square.add(new JLabel(new ImageIcon("/Users/ryan/IdeaProjects/ChessGame/res/Chess_mdt45.png")));
        }

        //initializing pawns
        for (int i = 8; i < 16; i++){
            square = (JPanel) chessboard.getComponent(i);
            square.add(new JLabel(new ImageIcon(BLACK_PAWN)));
        }

        //initializing back row
        for (int i = 0; i < 8; i++){
            square = (JPanel) chessboard.getComponent(i);
            switch(i){
                case 0:case 7:
                    square.add(new JLabel(new ImageIcon(BLACK_ROOK)));
                    break;
                case 1:case 6:
                    square.add(new JLabel(new ImageIcon(BLACK_KNIGHT)));
                    break;
                case 2:case 5:
                    square.add(new JLabel(new ImageIcon(BLACK_BISHOP)));
                    break;
                case 3:
                    square.add(new JLabel(new ImageIcon(BLACK_QUEEN)));
                    break;
                case 4:
                    square.add(new JLabel(new ImageIcon(BLACK_KING)));
                    break;

            }
        }
    }

    /**
     * Sets up the white pieces in the proper configuration for the beginning of a standard game.
     * All pieces taken from Wikimedia Commons at https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent
     */
    void initializeWhitePieces(){


        JPanel square;

        //initialize fairy pieces
        if (customGame) {
            square = (JPanel) chessboard.getComponent(43);
            square.add(new JLabel(new ImageIcon("/Users/ryan/IdeaProjects/ChessGame/res/Chess_blt45.png")));

            square = (JPanel) chessboard.getComponent(44);
            square.add(new JLabel(new ImageIcon("/Users/ryan/IdeaProjects/ChessGame/res/Chess_mlt45.png")));
        }

        for (int i = 48; i < 56; i++){
            square = (JPanel) chessboard.getComponent(i);
            square.add(new JLabel(new ImageIcon(WHITE_PAWN)));
        }

        for (int i = 56; i < 64; i++){
            square = (JPanel) chessboard.getComponent(i);
            switch(i){
                case 56:case 63:
                    square.add(new JLabel(new ImageIcon(WHITE_ROOK)));
                    break;
                case 57:case 62:
                    square.add(new JLabel(new ImageIcon(WHITE_KNIGHT)));
                    break;
                case 58:case 61:
                    square.add(new JLabel(new ImageIcon(WHITE_BISHOP)));
                    break;
                case 59:
                    square.add(new JLabel(new ImageIcon(WHITE_QUEEN)));
                    break;
                case 60:
                    square.add(new JLabel(new ImageIcon(WHITE_KING)));
                    break;

            }
        }
    }



    public void movePiece(int oldPosition, int newPosition){
        JPanel square = (JPanel) chessboard.getComponent(oldPosition);

        JLabel piece = (JLabel) square.getComponent(0);
        square.removeAll();

        square = (JPanel) chessboard.getComponent(newPosition);
        square.removeAll();

        square.add(piece);
        chessboard.repaint();
        chessboard.revalidate();
    }

    public JPanel getBoard(){
        return this.chessboard;
    }

    public void updateBoard(ChessPiece[][] board){
        for (int rank = 0; rank < 8; rank++){
            for (int file = 0; file < 8; file++){
                ChessPiece piece = board[rank][file];
                JPanel square = (JPanel) chessboard.getComponent((8*rank)+file);

                square.removeAll();
                if (piece instanceof Pawn && piece.getColor() == game.Color.BLACK){
                    square.add(new JLabel(new ImageIcon(BLACK_PAWN)));
                }
                else if (piece instanceof Pawn && piece.getColor() == game.Color.WHITE){
                    square.add(new JLabel(new ImageIcon(WHITE_PAWN)));

                }
                else if (piece instanceof Rook && piece.getColor() == game.Color.BLACK){
                    square.add(new JLabel(new ImageIcon(BLACK_ROOK)));
                }
                else if (piece instanceof Rook && piece.getColor() == game.Color.WHITE){
                    square.add(new JLabel(new ImageIcon(WHITE_ROOK)));
                }

                else if (piece instanceof Knight && piece.getColor() == game.Color.BLACK){
                    square.add(new JLabel(new ImageIcon(BLACK_KNIGHT)));
                }
                else if (piece instanceof Knight && piece.getColor() == game.Color.WHITE){
                    square.add(new JLabel(new ImageIcon(WHITE_KNIGHT)));
                }

                else if (piece instanceof Bishop && piece.getColor() == game.Color.BLACK){
                    square.add(new JLabel(new ImageIcon(BLACK_BISHOP)));
                }
                else if (piece instanceof Bishop && piece.getColor() == game.Color.WHITE){
                    square.add(new JLabel(new ImageIcon(WHITE_BISHOP)));
                }

                else if (piece instanceof Queen && piece.getColor() == game.Color.BLACK){
                    square.add(new JLabel(new ImageIcon(BLACK_QUEEN)));
                }
                else if (piece instanceof Queen && piece.getColor() == game.Color.WHITE){
                    square.add(new JLabel(new ImageIcon(WHITE_QUEEN)));
                }
                else if (piece instanceof King && piece.getColor() == game.Color.BLACK){
                    square.add(new JLabel(new ImageIcon(BLACK_KING)));
                }
                else if (piece instanceof King && piece.getColor() == game.Color.WHITE){
                    square.add(new JLabel(new ImageIcon(WHITE_KING)));
                }
            }
        }
        chessboard.repaint();
        chessboard.revalidate();
    }

}

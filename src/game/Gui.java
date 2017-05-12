package game;
import game.pieces.ChessPiece;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import game.pieces.*;
import java.util.Stack;

/**
 * Created by ryan on 2/11/17.
 */
public class Gui {

    private JFrame window;
    private BoardView board;
    private SquareBoard boardModel;
    private JPanel chessboard;
    private ScoreView score;
    Component[] squares;

    private JLabel clickedPiece;
    private int clickedPieceIndex;
    private Color clickedPieceColor;

    private int selectedSquareIndex;

    private SelectionState turnState = SelectionState.NO_PIECE_SELECTED;

    private JButton turnButton = new JButton("Take Turn");
    private JButton undoButton = new JButton("Undo");
    private JButton restartButton = new JButton("Restart");
    private JButton forfeitButton = new JButton("Forfeit");

    private JLabel feedback = new JLabel("Feedback:");

    private Border unselectedBorder;


    private JPanel sourceSquare;
    private JPanel destSquare;

    private JPanel bottomPanel = new JPanel();

    private boolean takeTurnPressed = false;

    private boolean whiteTurn = true;
    private boolean blackTurn = false;

    private boolean customGame;

    private boolean firstSetup = true;

    private Stack<ChessPiece[][]> previousStates;


    public Gui(){
        //taken from online somewhere
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }

        previousStates = new Stack<ChessPiece[][]>();
        turnButton.addActionListener(new takeTurnListener());
        restartButton.addActionListener(new RestartListener());
        undoButton.addActionListener(new undoListener());
        forfeitButton.addActionListener(new ForefeitListener());

    }

    public JFrame getWindow(){
        return window;
    }


    /**
     * Creates the main menu screen where player selects whether they want a traditional or custom game
     */
    public void createMainMenu(){
        JButton traditionalOption = new JButton("Traditional Chess Game");
        JButton customOption =  new JButton("Custom Chess Game");

        traditionalOption.setSize(250, 500);
        customOption.setSize(250, 500);

        traditionalOption.addActionListener(new traditionalButtonListener());
        customOption.addActionListener(new customButtonListener());


        GridBagLayout gridbag = new GridBagLayout();
        gridbag.setConstraints(traditionalOption, new GridBagConstraints(0, 0, 2, 2,1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        gridbag.setConstraints(customOption, new GridBagConstraints(2, 0, 2, 2,1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));


        JPanel gameSelectPanel = new JPanel(gridbag);

        window.getContentPane().add(gameSelectPanel, BorderLayout.CENTER);

        gameSelectPanel.add(traditionalOption);
        gameSelectPanel.add(customOption);
//
//        window.getContentPane().add(traditionalOption, BorderLayout.PAGE_START);
//        window.getContentPane().add(customOption, BorderLayout.PAGE_END);


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * Starts the GUI for the chess game by creating the appropriate models and setting up first screens user sees
     * Also starts the main game loop
     */
    public void startGame(){

        boardModel = new SquareBoard(8, customGame);


        window = new JFrame("Chess");

        window.setSize(500, 500);


        createMainMenu();

        King blackKing = boardModel.getKing(Color.BLACK);
        King whiteKing = boardModel.getKing(Color.WHITE);
        int selection;

        while (true){ // main game loop

            //constantly check if kings are in check so that player can be given appropriate feedback
            if (blackKing.isInCheck(boardModel)){
                feedback.setText("Black is in check!");
                if (blackKing.isInCheckMate(boardModel)){
                    selection = JOptionPane.showConfirmDialog(window, "White wins! Restart?");
                    if (selection == JOptionPane.YES_OPTION){
                        score.incrementScore(Color.WHITE);
                        boardModel = new SquareBoard(8, customGame);
                        setupGame();
                    }
                    else{
                        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                        break;
                    }
                }
            }
            else if (whiteKing.isInCheck(boardModel)){
                feedback.setText("White is in check!");
                if (whiteKing.isInCheckMate(boardModel)) {
                    selection = JOptionPane.showConfirmDialog(window, "Black wins! Restart?");
                    if (selection == JOptionPane.YES_OPTION) {
                        boardModel = new SquareBoard(8, customGame);
                        score.incrementScore(Color.BLACK);
                        setupGame();
                    }
                    else{
                        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                    }
                }

            }


        }


    }


    /**
     * Gets the index of a specific square on the JPanel grid
     * @param square
     * @return integer of the coordinate
     */
    private int getIndexOfSquare(JPanel square){
        for (int i = 0; i < squares.length; i++){
            if (squares[i].equals(square)){
                return i;
            }
        }
        return -1; //should never reach here. indicates error
    }

    /**
     * Sets up the board view for a new game
     */
    private void setupGame(){
        if (firstSetup) {
            score = new ScoreView(window);
            firstSetup = false;
        }

        board = new BoardView(window, this.customGame);
        window.getContentPane().add(bottomPanel, BorderLayout.PAGE_END);

        bottomPanel.add(turnButton, BorderLayout.CENTER);
        bottomPanel.add(undoButton, BorderLayout.LINE_START);
        bottomPanel.add(restartButton, BorderLayout.LINE_END);
        bottomPanel.add(forfeitButton, BorderLayout.PAGE_END);


        bottomPanel.add(feedback, BorderLayout.PAGE_END);
        feedback.setText("White's turn"); //white always goes first

        squares = board.getBoard().getComponents();
        chessboard = board.getBoard();
        for (int i = 0; i < 64; i++) {
            chessboard.getComponent(i).addMouseListener(new mouseEventListener());
        }

        window.getContentPane().add(score.scoreLabel, BorderLayout.PAGE_START);
        window.revalidate();
    }

    public class traditionalButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            customGame = false;
            boardModel = new SquareBoard(8, customGame);
            setupGame();
        }
    }
    public class customButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            customGame = true;
            boardModel = new SquareBoard(8, customGame);
            setupGame();
        }
    }

    /**
     * Class listens for button presses on the "Take Turn Button"
     */
    public class takeTurnListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if (turnState == SelectionState.NEW_LOCATION_SELECTED){ //take turn
                feedback.setText("");

                ChessPiece[][] currentState = boardModel.getStateCopy();

                //converts 1D coordinates from GridLayout to 2D coordinates to be used with board model
                int rank = clickedPieceIndex / 8;
                int file = clickedPieceIndex % 8;
                ChessPiece piece = boardModel.getPieceAtPosition(rank, file);

                int newRank = selectedSquareIndex / 8;
                int newFile = selectedSquareIndex % 8;

                //checks if move is valid then updates board view and switches player turns
                boardModel.printBoard();
                boolean moved = boardModel.movePiece(piece, newRank, newFile);
                if (moved){
                    previousStates.push(currentState);
                    System.out.println("pushed");
                    board.movePiece(clickedPieceIndex, selectedSquareIndex);
                    blackTurn = !blackTurn;
                    whiteTurn = !whiteTurn;

                    String feedbackStr = whiteTurn ? "White's turn" : "Black's turn";
                    if (!boardModel.blackKing.isInCheck(boardModel) && !boardModel.whiteKing.isInCheck(boardModel)) {
                        feedback.setText(feedbackStr);
                    }
                }
                else{
                    feedback.setText("Invalid Move!");
                }
                sourceSquare.setBorder(unselectedBorder);
                destSquare.setBorder(unselectedBorder);
                turnState = SelectionState.NO_PIECE_SELECTED;

            }

        }
    }

    /**
     * Class for listening for clicks on undo button
     */
    public class undoListener implements ActionListener{

        //removes last
        public void actionPerformed(ActionEvent ae){

            if (previousStates.size() != 0) {
                ChessPiece[][] lastConfig = previousStates.pop();
                boardModel.setState(lastConfig);
                board.updateBoard(lastConfig);

                whiteTurn = !whiteTurn;
                blackTurn = !blackTurn;

                String feedbackStr = whiteTurn ? "White's turn" : "Black's turn";
                turnState = SelectionState.NO_PIECE_SELECTED;
                feedback.setText(feedbackStr);
            }
            boardModel.printBoard();

        }
    }

    public class RestartListener implements ActionListener{
        public void actionPerformed(ActionEvent ae) {
            int option = JOptionPane.showConfirmDialog(window, "Are you sure you want to restart?");
            if (option == JOptionPane.YES_OPTION) {
                boardModel = new SquareBoard(8, customGame);
                score.resetScore();
                setupGame();
            }
        }
    }

    public class ForefeitListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            int option = JOptionPane.showConfirmDialog(window, "Are you sure you want to forfeit?");
            if (option == JOptionPane.YES_OPTION) {
                boardModel = new SquareBoard(8, customGame);
                if (whiteTurn)
                    score.incrementScore(Color.BLACK);
                else
                    score.incrementScore(Color.WHITE);
                setupGame();
            }
        }
    }
    public class mouseEventListener implements MouseListener{
        public void mouseEntered(MouseEvent me){

        }

        public void mouseClicked(MouseEvent me){

            JPanel square = (JPanel) me.getSource();

            if (square.getComponents().length != 0) {
                clickedPiece = (JLabel) square.getComponent(0);
                char color = clickedPiece.getIcon().toString().charAt(46);
                clickedPieceColor = color == 'd' ? Color.BLACK : Color.WHITE;

                //if it's not the selected piece's turn then do not highlight or change state return and wait
                //for right selection
                if (((clickedPieceColor == Color.BLACK && !blackTurn) || (clickedPieceColor == Color.WHITE && !whiteTurn)) &&
                        turnState == SelectionState.NO_PIECE_SELECTED){
                    //System.out.println(turnState);
                    return;
                }
            }



            Border redBorder = BorderFactory.createLineBorder(java.awt.Color.RED,2);
            System.out.println(square.getComponents().length);

            //player has selected the piece they want to move so highlight it
            if (square.getComponents().length != 0 && turnState == SelectionState.NO_PIECE_SELECTED){
                sourceSquare = square;
                turnState = SelectionState.INITIAL_PIECE_SELECTED;


                clickedPieceIndex = getIndexOfSquare(square);

                unselectedBorder = square.getBorder();
                square.setBorder(redBorder);
                System.out.println(turnState);
                return;
            }

            //player has selected the location to which they want to move the piece
            else if (turnState == SelectionState.INITIAL_PIECE_SELECTED){
                destSquare = square;
                unselectedBorder = square.getBorder();
                square.setBorder(redBorder);
                turnState = SelectionState.NEW_LOCATION_SELECTED;
                selectedSquareIndex = getIndexOfSquare(square);
                System.out.println(turnState);
            }
            //window.revalidate();

        }

        public void mousePressed(MouseEvent me){
            // not needed
        }
        public void mouseReleased(MouseEvent me){
            // not needed
        }

        public void mouseExited(MouseEvent me){
            // not needed
        }
    }

}

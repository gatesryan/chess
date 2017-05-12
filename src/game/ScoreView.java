package game;
import javax.swing.*;
import java.awt.BorderLayout;
/**
 * Created by ryan on 2/18/17.
 */
public class ScoreView {

    int blackPlayerScore = 0;
    int whitePlayerScore = 0;
    String text;
    String blackPlayerName;
    String whitePlayerName;
    JLabel scoreLabel;
    JFrame window;

    public ScoreView(JFrame window){
        this.window = window;
        text = String.format("White: %d     Black: %d", blackPlayerScore , whitePlayerScore);
        scoreLabel = new JLabel(text);
        window.getContentPane().add(scoreLabel, BorderLayout.PAGE_START);
        window.revalidate();
    }

    public void incrementScore(Color color){
        if (color == Color.BLACK){
            blackPlayerScore++;
        }
        else{
            whitePlayerScore++;
        }
        update();
    }

    public void resetScore(){
        blackPlayerScore = 0;
        whitePlayerScore = 0;
        update();
    }

    public void update(){
        text = String.format("White: %d     Black: %d", whitePlayerScore, blackPlayerScore);
        scoreLabel.setText(text);
        scoreLabel.paintImmediately(scoreLabel.getVisibleRect());
    }

}

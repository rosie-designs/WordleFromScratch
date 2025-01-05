/*
 * Rosie Chai
 * Jan 22, 2023
 * WordPanel Class: Controls the letter boxes displayed on screen
*/

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.awt.event.*;
import javax.swing.border.Border;

public class WordPanel extends JPanel{
  //instance variables
  public JLabel[] wordLetters = new JLabel[30]; //changed from 20-30
  private static final int HOR_MARGIN = 19;
  private static final int VERT_MARGIN = 0;
  private static final Border whiteBorder = BorderFactory.createLineBorder(Color.white);
//---------------------------------------------------------------------------------------------------------------------
  //constructor
  public WordPanel(JPanel wordleBoard){
    //setting up the layout
    this.setLayout(new GridLayout(1, 5, HOR_MARGIN, VERT_MARGIN));
    for (int cols = 0; cols < 5; cols++){
      this.wordLetters[cols] = new JLabel();
      this.wordLetters[cols].setOpaque(true);
      this.wordLetters[cols].setBorder(whiteBorder);
      this.wordLetters[cols].setBackground(new Color(22, 16, 50));
      this.add(wordLetters[cols]);
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: Updates wordle board
  public void updateBoard(Color[][] letterColorsArray, int guessNum, String wordleWord){
    //changes the background colour depending on the colour array
    for (int letters = 0; letters < 5; letters++){
      this.wordLetters[letters].setBackground(letterColorsArray[guessNum][letters]);
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: writes to board
  public void writeToBoard(String userText, int letterNum){
    this.wordLetters[letterNum].setFont(new Font("Calibri", Font.PLAIN, 25));
    this.wordLetters[letterNum].setVerticalAlignment(JLabel.CENTER);
    this.wordLetters[letterNum].setHorizontalAlignment(JLabel.CENTER);
    this.wordLetters[letterNum].setForeground(Color.WHITE);
    this.wordLetters[letterNum].setText(userText);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: getter for the user's inputted guess
  public String getUserInput(){
    String text = "";
    for (int i = 0; i < 5; i++){
      text = text + this.wordLetters[i].getText();
    }
    return text;
  }
}
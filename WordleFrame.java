/**
 * Rosie Chai
 * Jan 21, 2023
 * Wordle Frame Class (Ties everything together)
 */

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.event.*;

public class WordleFrame extends JFrame{
  //instance variables
  private static WordleBoard wordleBoard; //wordle word
  private boolean[] gameOver = new boolean[2]; //instance boolean var
  
  //screens
  private static StartScreen startScreenPanel = new StartScreen();
  private static CategoryScreen categoryScreenPanel = new CategoryScreen();
  private static WordleWord wordleWord;
  private static EndScreen endScreenPanel = new EndScreen();
  private static JPanel panelCont = new JPanel();
  private static CardLayout cLayout = new CardLayout();
  
  //sound
  private WordleAudio bgMusic = new WordleAudio("doveLoveMSC.wav");
  private static WordleAudio loseSFX = new WordleAudio("loseSFX.wav");
  private static WordleAudio victorySFX = new WordleAudio("victorySFX.wav");
//---------------------------------------------------------------------------------------------------------------------
  //Constructor
  public WordleFrame() { 
    //set up panel container
    this.panelCont.setLayout(cLayout);
    this.panelCont.setBounds(0, 0, 800, 800);
    
    //set up sound
    bgMusic.play();
    newGame();    
    addCards();
    changeScreen("startScreen");
    
    //set up frame
    this.add(this.panelCont);
    setUpFrame();
    this.setVisible(true);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: sets up new game
  private static void newGame(){
    wordleBoard = new WordleBoard();
    wordleBoard.addKeyListener(wordleBoard);
  }    
//---------------------------------------------------------------------------------------------------------------------
  //method: adds screens to card layout
  private void addCards(){
    this.panelCont.add(this.startScreenPanel, "startScreen"); //each screen is assigned a key
    this.panelCont.add(this.categoryScreenPanel, "categoryScreen");
    this.panelCont.add(this.wordleBoard, "wordleGame");
    this.panelCont.add(this.endScreenPanel, "endScreen");
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: sets up the JFrame
  private void setUpFrame(){    
    this.setResizable(false); //SET TO FALSE AFTER!
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setLocationRelativeTo(null);  

    //creating a logo for the frame
    ImageIcon image = new ImageIcon("wordleLogo.png");
    this.setIconImage(image.getImage());
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: setter for the wordle word
  public static void setWordleWord(String category){ //word file depends on category selected
    if (category.equals("animals")){
      wordleWord = new WordleWord("C:\\Users\\rosie\\OneDrive\\Desktop\\Gr.11 CompSci\\WORDLE\\WordList_Animals.txt");
    } else if (category.equals("movies")){
      wordleWord = new WordleWord("C:\\Users\\rosie\\OneDrive\\Desktop\\Gr.11 CompSci\\WORDLE\\WordList_Movies.txt");
    } else if (category.equals("random")){
      wordleWord = new WordleWord("C:\\Users\\rosie\\OneDrive\\Desktop\\Gr.11 CompSci\\WORDLE\\WordList_Random.txt");
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: getter for the wordle word
  public static String getWordleWord(){
    return wordleWord.getWordleWord();
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: changes screen
  public static void changeScreen(String screen){ //changes according to key
    //switches "cards"
    cLayout.show(panelCont, screen);
    //gets game ready
    if (screen == "wordleGame"){
      wordleBoard.requestFocusInWindow();
      wordleBoard.setFocusable(true);
      wordleBoard.clearBoard();
      wordleBoard.runGame(true);
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: checks if game is over
  public static void checkGameOver(boolean[] gameOverArray){
    if (gameOverArray[1] == true){ //if guessed correctly
      endScreenPanel.loadImage(true); //argument: win = true
      endScreenPanel.setText(true, getWordleWord(), wordleBoard.getGuessNum());
      changeScreen("endScreen");
      //play sound effects
      victorySFX.reset();
      victorySFX.play();
    } else if (gameOverArray[0] == true){ //if user reached 6 guesses
      endScreenPanel.loadImage(false); //win = false
      endScreenPanel.setText(false, getWordleWord(), wordleBoard.getGuessNum());
      changeScreen("endScreen");
      //play sound effects
      loseSFX.reset();
      loseSFX.play();
    }    
  }
}
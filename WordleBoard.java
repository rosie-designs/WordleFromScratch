/**
 * Wordle Board Class
 * Rosie Chai
 * Jan 17, 2023
 */

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.border.Border;

public class WordleBoard extends JPanel implements KeyListener{
   
  //colour vars
  private final Color LIGHT_GRAY = new Color(226, 226, 226);
  private final Color GREEN = new Color(106, 170, 100);
  private final Color YELLOW = new Color(237, 201, 95);
  private final Color DARK_GRAY = new Color(145, 145, 145);
  
  private final Color[] CORRECT_COLOR_ARRAY = {GREEN, GREEN, GREEN, GREEN, GREEN};
  private final Color RESET = new Color(22, 16, 50);
  private final Color[][] RESET_COLOURS = {{RESET, RESET, RESET, RESET, RESET}, {RESET, RESET, RESET, RESET, RESET}, {RESET, RESET, RESET, RESET, RESET}, {RESET, RESET, RESET, RESET, RESET}, {RESET, RESET, RESET, RESET, RESET}, {RESET, RESET, RESET, RESET, RESET}};
  private Color[][] letterColorsArray = new Color[6][5];
  
  //title panel instance vars
  private JPanel titlePanel = new JPanel();
  private JLabel titleLabel = new JLabel();
  
  //word panel instance vars
  private WordPanel[] wordPanelArray = new WordPanel[6];
  private JPanel wordPanelMain = new JPanel();
  
  //keyboard panel instance vars
  private JPanel keyboardPanelMain = new JPanel();
  private KeyboardPanel[] keyboardPanelArray = new KeyboardPanel[3];
  public static JButton[] keyboardKeys = new JButton[26];
  public static JButton[] specialKeys = new JButton[2];
  private JButton enterBtn;
  private JButton backspaceBtn;
  
  //wordle word guesses vars
  private int guessNum = 0;
  static String wordleWord;
  private String[] userGuessWord;
  private int letterNum = 0;

  
  //setting up user GUI
  UserInput userGUI;
  
  //sound
  private WordleAudio wrongSFX = new WordleAudio("wrongSFX.wav");
  private WordleAudio validSFX = new WordleAudio("validSFX.wav");
//---------------------------------------------------------------------------------------------------------------------
  //constructor
  public WordleBoard() {
    //set panel layout
    this.setBounds(0, 0, 800, 800);
    this.setLayout(null);
    this.setBackground(new Color(22, 16, 50));    
    
    //creating the letter boxes
    for (int i = 0; i < 6; i++){
      this.wordPanelArray[i] = new WordPanel(this);
      this.wordPanelArray[i].setOpaque(false);
      this.wordPanelMain.add(this.wordPanelArray[i]);
    }
      
    //creating keyboard
    for (int i = 0; i < 3; i++){
      this.keyboardPanelArray[i] = new KeyboardPanel(i);
      this.add(this.keyboardPanelArray[i]);
    }
      
    //adding special keyboard buttons   
    this.enterBtn = new JButton("GO");
    this.backspaceBtn = new JButton("<-");
    specialKeys[0] = this.enterBtn;
    specialKeys[1] = this.backspaceBtn;
    this.enterBtn.setBounds(36, 582, 85, 55);
    this.backspaceBtn.setBounds(557, 582, 85, 55);
    for (int i = 0; i < 2; i++){
      specialKeys[i].setFocusable(false);
      specialKeys[i].setBackground(LIGHT_GRAY);
      specialKeys[i].setVisible(true);
      this.add(specialKeys[i]);
    }
    //setting up letter key buttons
    for (int buttonNum = 0; buttonNum < 26; buttonNum++){
      keyboardKeys[buttonNum].setFocusable(false);
      keyboardKeys[buttonNum].setBackground(LIGHT_GRAY);
    }
      
    //organizing panels
    this.wordPanelMain.setBounds(165, 72, 350, 372);
    this.wordPanelMain.setOpaque(false);
    this.wordPanelMain.setLayout(new GridLayout(6, 1, 0, 10));
    this.keyboardPanelMain.setBounds(37, 455, 606, 181);
    this.keyboardPanelMain.setOpaque(false);
    
    //adding panels to main panel
    this.add(this.wordPanelMain);
    this.add(this.keyboardPanelMain);
    this.add(this.titlePanel);
    
    //creating instance of inner class: UserInput
    userGUI = new UserInput();
    
    //clearing the board
    clearBoard();
  }
//-----------------------------------------------------------------------------------------------------------------------
  //method: run game
  public void runGame(boolean isRunnable){
    if (isRunnable){
      //resetting needed instance vars
      userGUI.canGuessNext = true;
      setTitlePanel();
      guessNum = 0;
      letterNum = 0;
      
      //getting and printing out wordle word
      this.wordleWord = WordleFrame.getWordleWord();
      System.out.println("wordle word: " + this.wordleWord); //printing out answer
      
      //refresh the panel
      this.revalidate();
      this.setVisible(true);
    }
  }
//-----------------------------------------------------------------------------------------------------------------------
  //method: returns guessNum
  public int getGuessNum(){
    return guessNum;
  }
//-----------------------------------------------------------------------------------------------------------------------
  //method: clears entire board
  public void clearBoard(){
    //clearing the word letter panels
    for (int i = 0; i < 6; i++){
      for (int j = 0; j < 5; j++){
        this.wordPanelArray[i].writeToBoard("", j);
        this.wordPanelArray[i].updateBoard(RESET_COLOURS, i, this.wordleWord);
      }
    }
    //clearing keyboard keys
    for (int i = 0; i < 26; i++){
      keyboardKeys[i].setBackground(LIGHT_GRAY);
    }    
  }
//-----------------------------------------------------------------------------------------------------------------------
  //method: changes top panel depending on selected category
  private void setTitlePanel(){
    //setting text properties
    this.titleLabel.setText(CategoryScreen.category);
    this.titleLabel.setFont(new Font("Cambria Math", Font.BOLD, 60));
    this.titleLabel.setForeground(Color.white);
    //setting panel properties
    this.titlePanel.setBounds(-10, 0, 700, 66);
    this.titlePanel.setBackground(new Color(22, 16, 50));
    this.titlePanel.add(this.titleLabel);
  }
//-----------------------------------------------------------------------------------------------------------------------
  //method: updates keyboard key colours
  private void updateKeyboard(String userGuess){
    String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNM"; 
    char[] userText = userGuess.toCharArray(); //converts user's text to a char array
    ArrayList<Integer> btnIndexes = new ArrayList<Integer>(); //array list that stores the indexes of the letter buttons used
    
    //comparing the user's guess to the keyboard button array
    for (int i = 0; i < 5; i++){ //looping through user's guess
      for (int j = 0; j < 26; j++){ //looping through array of keyboard letter buttons
        if (userText[i] == alphabet.charAt(j)){
          btnIndexes.add(j); //storing the letters in the user's word in this array list
        }
      }
    }
    
    //set keys to green
    for (int index = 0; index < 5; index++){
      if (letterColorsArray[guessNum][index].getRGB() == GREEN.getRGB()){
        keyboardKeys[btnIndexes.get(index)].setBackground(GREEN);
      }
    }
    
    //set keys to yellow
    for (int index = 0; index < 5; index++){
      if (letterColorsArray[guessNum][index].getRGB() == YELLOW.getRGB()){
        //green keys get priority, only turns yellow if not already green
        if (keyboardKeys[btnIndexes.get(index)].getBackground() != GREEN){
          keyboardKeys[btnIndexes.get(index)].setBackground(YELLOW);
        }
      } else if (keyboardKeys[btnIndexes.get(index)].getBackground().getRGB() != GREEN.getRGB()){ //turning keys dark gray
        if (keyboardKeys[btnIndexes.get(index)].getBackground().getRGB() != YELLOW.getRGB()){
          keyboardKeys[btnIndexes.get(index)].setBackground(DARK_GRAY);
        }
      }
    }
  }
//-----------------------------------------------------------------------------------------------------------------------
  //method: checks if the game is over or not
  public boolean[] isGameOver(){
    boolean[] gameOver = new boolean[2]; //first index: reached max guesses, second index, win/lose
    //if user uses up all guesses
    if (guessNum == 5){
      gameOver[0] = true;
    } else {gameOver[0] = false;}
    //if user guesses correctly
    if (Arrays.equals(letterColorsArray[guessNum], CORRECT_COLOR_ARRAY)){
      gameOver[1] = true;
    } else {gameOver[1] = false;}
    return gameOver;
  }
//-----------------------------------------------------------------------------------------------------------------------
  //implementing KeyListener methods
  @Override
  public void keyTyped(KeyEvent e){
  }

  @Override
  public void keyPressed(KeyEvent e){    
    int letterASCII = e.getKeyCode();
    if (letterASCII >= 65 && letterASCII <= 90){ //if key entered is an alphabetical letter
      userGUI.typeLetter(String.valueOf((char)letterASCII)); //call on method
    } else if (letterASCII == 10){ //ENTER KEY
      userGUI.typeEnter();
    } else if (letterASCII == 8){ //BACKSPACE KEY
      userGUI.typeBackspace();
    }        
  }

  @Override
  public void keyReleased(KeyEvent e){
  }
  
//-----------------------------------------------------------------------------------------------------------------------
  
  //INNER CLASS: UserInput
  public class UserInput implements ActionListener{
    public boolean canGuessNext = true;
    public UserGuess tempGuess;
    private String userText = "";
//-----------------------------------------------------------------------------------------------------------------------
    public UserInput(){
      //add action listener to keyboard buttons
      for (int i = 0; i < keyboardKeys.length; i++){
        keyboardKeys[i].addActionListener(this);
      }
      for (int i = 0; i < specialKeys.length; i++){
        specialKeys[i].addActionListener(this);
      }
    }
//-----------------------------------------------------------------------------------------------------------------------
    //method: type letter
    public void typeLetter(String letter){
      if (letterNum < 5 && canGuessNext){
        wordPanelArray[guessNum].writeToBoard(letter, letterNum);
        letterNum++; //because you increment it after, the value will be 5 after 5 letters
      } else{
        canGuessNext = false;
      }
    }
//-----------------------------------------------------------------------------------------------------------------------
    //method: type enter
    public void typeEnter(){
      if (guessNum < 6 && letterNum == 5){        
        userText = wordPanelArray[guessNum].getUserInput();
        tempGuess = new UserGuess(userText, wordleWord);
        
        //validating the user's guess
        if (tempGuess.isRealWord()){
          //play sound
          validSFX.reset();
          validSFX.play();
          //display guess on board
          letterColorsArray[guessNum] = tempGuess.compareWord();
          userGuessWord = tempGuess.convertToStringArray();
          wordPanelArray[guessNum].updateBoard(letterColorsArray, guessNum, wordleWord);
          updateKeyboard(userText);   
          //check is game is over
          WordleFrame.checkGameOver(isGameOver());
          //reset variables
          guessNum++; 
          letterNum = 0;
          if (guessNum < 6){
            canGuessNext = true;
          } else{
            canGuessNext = false;
          }
          userText = "";
        } else{
          System.out.println("i'm invalid");
          //play sound
          wrongSFX.reset();
          wrongSFX.play();
          canGuessNext = false;
        }
      }   
    }
//-----------------------------------------------------------------------------------------------------------------------
    //method: if user types in backspace
    public void typeBackspace(){
      //only delete letters if there are more than 1 to prevent index out of bounds
      if (letterNum > 0){
        letterNum = letterNum - 1;
        wordPanelArray[guessNum].writeToBoard("", letterNum);
        canGuessNext = true;
      }
    }
//-----------------------------------------------------------------------------------------------------------------------
    //implementing ActionListener methods
    @Override
    public void actionPerformed(ActionEvent event) {
      //checking which key
      for (int i = 0; i < keyboardKeys.length; i++){
        if (event.getSource() == keyboardKeys[i]){
          typeLetter(keyboardKeys[i].getText());
        }
      }
      //if enter is pressed
      if (event.getSource() == enterBtn){
        typeEnter();
      } else if (event.getSource() == backspaceBtn){ //if backspace is pressed
        typeBackspace();
      }
    }
  }
}
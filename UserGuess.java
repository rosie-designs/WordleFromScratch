/**
 * Rosie Chai
 * Jan 10, 2022
 * Class the checks the user's guess
 */

import java.awt.Color;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserGuess {
  //final vars
  static final Color YELLOW = new Color(237, 201, 95);
  static final Color GREEN = new Color(106, 170, 100);
  static final Color DARK_GRAY = new Color(145, 145, 145);
  static final int wordLength = 5;
  
  //variables for the user word
  private String userWord = "";
  private String wordleWord = "";
  private Color[] colorArray = new Color[wordLength];
  private String[] userWordArray = new String[wordLength];
  
  //arrays/array lists used to compare words
  private String[] correctLettersArray = new String[wordLength];
  private ArrayList<String> correctLetters;
//---------------------------------------------------------------------------------------------------------------------
  //constructor
  public UserGuess(String userInput, String wordleWord) { 
    //setting up instance variables
    this.userWord = userInput;
    this.wordleWord = WordleFrame.getWordleWord();
    this.userWordArray = this.userWord.split("");
    
    //storing the wordle word into an array and array list
    this.correctLettersArray = this.wordleWord.split("");
    this.correctLetters = new ArrayList<String>(Arrays.asList(this.correctLettersArray));

    //starting to fill every letter with gray
    Arrays.fill(this.colorArray, DARK_GRAY);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: compares user word to wordle word and returns a colour array
  public Color[] compareWord(){
    //filling green if letters are correct
    for (int i = 0; i < wordLength; i++){
      if (this.userWord.charAt(i) == (this.wordleWord.charAt(i))){
        this.colorArray[i] = GREEN;
        this.correctLetters.set(i, " "); //removing this letter from the array list
      }
    }
    //filling yellow if wordle word contains this, but wrong place
    for (int i = 0; i < wordLength; i++){
      //green takes priority, if not green, and if found inside the remaining letters - fill yellow
      if (this.colorArray[i] != GREEN && this.correctLetters.contains(String.valueOf(this.userWord.charAt(i)))){
        this.colorArray[i] = YELLOW;
        //calls method that removes letter from array list
        updateWord(String.valueOf(this.userWord.charAt(i)));
      }
    }
    return this.colorArray;
  }
//---------------------------------------------------------------------------------------------------------------------
  //updating the instance wordle word so that letters are not counted twice
  public void updateWord(String letter){
    for (int i = 0; i < wordLength; i++){
      if ((this.correctLetters.get(i)).equals(letter)){
        this.correctLetters.set(i, " ");
        //only want to remove first occurrence of the letter, so we break
        break;
      }
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: getter for the userWordArray
  public String[] convertToStringArray(){
    return this.userWordArray;
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: getter for the colour array
  public Color[] getColorArray(){
    return this.colorArray;
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: validates the user's guess
  public boolean isRealWord(){
    try{
      BufferedReader reader = new BufferedReader(new FileReader("fiveLetterWordsFile.txt"));
      String fileWord = " ";
      //searching file for word
      while(reader.ready() && fileWord != null){
        fileWord = reader.readLine().trim().toUpperCase();
        if (fileWord.equals(this.userWord)){
          return true; //word is valid
        }
      }
      reader.close();
    } catch (IOException ignored){
    }
    return false; //word not found
  }
}
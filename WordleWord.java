/**
 * Rosie Chai
 * Jan 7, 2022
 * Class that chooses the Wordle Word
 */

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class WordleWord {
  //instance variables
  private String file;  
  private String wordleWord;
  private int totalLines = 0;
  private int randomLine;
//---------------------------------------------------------------------------------------------------------------------
  //constructor
  public WordleWord(String file){ 
    this.file = file;
    this.totalLines = totalNumLines();
    this.randomLine = randomLine();
    this.wordleWord = setWordleWord();
  }
//-----------------------------------------------------------------------------------------------------------------------
  //method: counts total number of lines in the file
  private int totalNumLines() {
    int lineCount = 0;
    String tempLine = "";
    //read through file
    try{
      BufferedReader wordFile = new BufferedReader(new FileReader(this.file));
      while (wordFile.ready()){
        tempLine = wordFile.readLine(); 
        //count each line
        lineCount += 1;
      }
      wordFile.close();
    } catch (IOException ignored){
    }
    return lineCount;
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: Generates a random number from 1-total number of lines
  private int randomLine(){
    int randomLineNum = (int)(this.totalLines * Math.random() + 1); //chooses random num 1 - max #lines (inclusive)
    return randomLineNum;
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: setter for the wordle word
  private String setWordleWord(){
    String chosenWord = "";
    try (BufferedReader wordFile = new BufferedReader(new FileReader(this.file))){ //reset bufferedreader
      for (int i = 1; i < this.randomLine; i++){ //start from 1 instead of 0 (range selected starts at 1)
        wordFile.readLine();
      }
      chosenWord = wordFile.readLine();
      wordFile.close();
    } catch(IOException ignored){
    }
    return chosenWord.toUpperCase();
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: getter for the wordle word
  public String getWordleWord(){
    return this.wordleWord;
  }
}
/*
 * Rosie Chai
 * Jan 22, 2023
 * KeyboardPanel class: controls the keyboard panels
*/

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.awt.event.*;  

public class KeyboardPanel extends JPanel{
    //fixed spacing
    private final int HOR_MARGIN = 7;
    private final int VERT_MARGIN = 0;
    private final int PANEL_HEIGHT = 55;
    
    //vars for positioning
    private int index;
    private int row;
    
    //keys for the keyboard
    public static final String[] keyboardRowsRef = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
//---------------------------------------------------------------------------------------------------------------------
    //constructor
    public KeyboardPanel(int row){
      this.row = row;
      char[] keys = keyboardRowsRef[this.row].toCharArray();
      //set up first row of keyboard
      if (this.row == 0){
        this.setLayout(new GridLayout(1, 10, HOR_MARGIN, VERT_MARGIN));
        this.setBounds(37, 455, 606, PANEL_HEIGHT);
        this.setOpaque(false);
        for (int buttonNum = 0; buttonNum < 10; buttonNum++){
          WordleBoard.keyboardKeys[buttonNum] = new JButton(Character.toString(keys[buttonNum]));
          this.add(WordleBoard.keyboardKeys[buttonNum]);          
        }
      } else if (this.row == 1){ //set up second row
        this.setLayout(new GridLayout(1, 9, HOR_MARGIN, VERT_MARGIN));
        this.setBounds(67, 518, 544, PANEL_HEIGHT);
        this.setOpaque(false);
        for (int buttonNum = 0; buttonNum < 9; buttonNum++){
          index = buttonNum + 10;
          WordleBoard.keyboardKeys[index] = new JButton(Character.toString(keys[buttonNum]));
          this.add(WordleBoard.keyboardKeys[index]);
        }
      } else{ //set up third row
        this.setLayout(new GridLayout(1, 7, HOR_MARGIN, VERT_MARGIN));
        this.setBounds(128, 582, 422, PANEL_HEIGHT);
        this.setOpaque(false);
        for (int buttonNum = 0; buttonNum < 7; buttonNum++){
          index = buttonNum + 19;
          WordleBoard.keyboardKeys[index] = new JButton(Character.toString(keys[buttonNum]));
          this.add(WordleBoard.keyboardKeys[index]);
        }
      }
    }
  }
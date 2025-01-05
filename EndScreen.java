/**
 * Rosie Chai 
 * Jan 21, 2023
 * Class that manages the end screen
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class EndScreen extends JPanel implements ActionListener, Screen{
  //declaring buttons
  private JButton playAgainBtn;
  private boolean win = false;
  private BufferedImage image;
  
  //declaring JLabels
  private JLabel wordLabel = new JLabel();
  private JLabel guessNumLabel = new JLabel();
  
  //final colours
  private final Color DARK_ORANGE = new Color(202, 72, 54);
  private final Color BEIGE = new Color(239, 209, 173);
//---------------------------------------------------------------------------------------------------------------------
  //constructor
  public EndScreen(){
    //set up panel
    this.setBounds(0, 0, 800, 800);
    this.setLayout(null);
    this.setBackground(Color.GREEN);
    this.setVisible(true);
    
    //load image
    loadImage(this.win);
    
    //add text labels
    this.add(this.wordLabel);
    this.add(this.guessNumLabel);
    
    //adding a button
    this.playAgainBtn = new JButton("PLAY AGAIN");
    this.playAgainBtn.addActionListener(this);
    this.playAgainBtn.setBounds(198, 568, 284, 69);
    this.setUpButton(this.playAgainBtn);
    this.add(this.playAgainBtn);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: changes text of JLabels
  public void setText(boolean win, String word, int guessNum){
    //word label layout
    this.wordLabel.setText("\"" + word + "\"");
    this.wordLabel.setFont(new Font("Times New Roman", Font.BOLD, 70)); //Calisto MT, Cambria Math, Constantia
    this.wordLabel.setForeground(BEIGE);
    this.wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
    //change text label depending on win/lose
    if (win){
      this.wordLabel.setBounds(142, 179, 397, 104);
      //set up guess num JLabel
      guessNum = guessNum + 1;
      if (guessNum > 1){
        this.guessNumLabel.setText(String.valueOf(guessNum) + " GUESSES");
      } else{
        this.guessNumLabel.setText(String.valueOf(guessNum) + " GUESS");
      }
      this.guessNumLabel.setFont(new Font("Times New Roman", Font.BOLD, 70));
      this.guessNumLabel.setForeground(BEIGE);
      this.guessNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
      this.guessNumLabel.setBounds(156, 385, 369, 102);
    } else{ //if they lose
      this.wordLabel.setBounds(142, 269, 397, 104);
      this.guessNumLabel.setText(""); //no label
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: load image depending on win/lose
  public void loadImage(boolean win){
    if (win){
      try{
        this.image = ImageIO.read(new File("winScreenPic.png"));
      } catch (IOException ignored){
      }
    } else{
      try{
        this.image = ImageIO.read(new File("loseScreenPic.png"));
      } catch (IOException ignored){
      }
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //implementing interface method
  public void setUpButton(JButton btn){
    btn.setFocusable(false);
    btn.setFont(new Font("Cambria Math", Font.BOLD, 40));
    btn.setHorizontalTextPosition(SwingConstants.CENTER);
    btn.setVerticalTextPosition(SwingConstants.BOTTOM);
    btn.setForeground(BEIGE);
    btn.setBackground(DARK_ORANGE);
  }
//---------------------------------------------------------------------------------------------------------------------
  //button action
  @Override
  public void actionPerformed(ActionEvent event){
    if (event.getSource() == playAgainBtn){
      WordleFrame.changeScreen("startScreen");
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //drawing image
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(this.image, -10, -20, null);
  }
}
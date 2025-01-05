/**
 * Rosie Chai
 * Jan 21, 2023
 * Start Screen Class
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class StartScreen extends JPanel implements ActionListener, Screen{
  //instance variables
  private JButton playBtn;
  private BufferedImage image;
  //final colours
  private final Color DARK_ORANGE = new Color(202, 72, 54);
  private final Color BEIGE = new Color(239, 209, 173);
//---------------------------------------------------------------------------------------------------------------------
  //constructor
  public StartScreen() { 
    //loading image
    try{
      this.image = ImageIO.read(new File("startScreenPic.png"));
    } catch (IOException ignored){
    }
    
    //set up panel
    this.setBounds(0, 0, 800, 800);
    this.setLayout(null);
    this.setBackground(Color.RED);
    this.setVisible(true);
    this.revalidate();
    
    //set up button
    this.playBtn = new JButton("PLAY");
    this.playBtn.setBounds(207, 557, 265, 74); 
    setUpButton(this.playBtn);
    this.playBtn.addActionListener(this);
    this.add(this.playBtn);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: implements method from Screen interface
  public void setUpButton(JButton btn){
    btn.setFocusable(false);
    btn.setFont(new Font("Cambria Math", Font.BOLD, 55));
    btn.setHorizontalTextPosition(SwingConstants.CENTER);
    btn.setVerticalTextPosition(SwingConstants.BOTTOM);
    btn.setForeground(BEIGE);
    btn.setBackground(DARK_ORANGE);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: button action
  @Override
  public void actionPerformed(ActionEvent event) {  
    if (event.getSource() == playBtn){
      //WordleFrame.newGame();
      WordleFrame.changeScreen("categoryScreen");
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: draws image
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(this.image, -10, 0, null);
  }
}
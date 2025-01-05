/**
 * Rosie Chai
 * Jan 21, 2023
 * Class that displays the category select screen
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CategoryScreen extends JPanel implements ActionListener, Screen{
  //buttons
  private JButton animalBtn = new JButton("ANIMALS");
  private JButton movieBtn = new JButton("MOVIES");
  private JButton randomBtn = new JButton("RANDOM");
  
  //instance vars
  public static String category;
  private BufferedImage image;
  
  //final color vars
  private final Color DARK_ORANGE = new Color(202, 72, 54);
  private final Color BEIGE = new Color(239, 209, 173);
//---------------------------------------------------------------------------------------------------------------------
  //constructor
  public CategoryScreen(){
    //loading image
    try{
      this.image = ImageIO.read(new File("categoryScreenPic.png"));
    } catch (IOException ignored){
    }
    
    //set up panel
    this.setBounds(0, 0, 800, 800);
    this.setLayout(null);
    this.setVisible(true);
    
    //creating buttons
    this.animalBtn.addActionListener(this);
    this.movieBtn.addActionListener(this);
    this.randomBtn.addActionListener(this);

    this.animalBtn.setBounds(132, 75, 415, 116);
    this.movieBtn.setBounds(132, 272, 415, 116);
    this.randomBtn.setBounds(132, 470, 415, 116);

    setUpButton(this.animalBtn);
    setUpButton(this.movieBtn);
    setUpButton(this.randomBtn);
    
    //adding buttons
    this.add(this.animalBtn);
    this.add(this.movieBtn);
    this.add(this.randomBtn);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: implementing method from interface
  public void setUpButton(JButton btn){
    btn.setFocusable(false);
    btn.setFont(new Font("Cambria Math", Font.BOLD, 55));
    btn.setHorizontalTextPosition(SwingConstants.CENTER);
    btn.setVerticalTextPosition(SwingConstants.BOTTOM);
    btn.setForeground(BEIGE);
    btn.setBackground(DARK_ORANGE);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: button event
  @Override
  public void actionPerformed(ActionEvent event){
    if (event.getSource() == animalBtn){
      WordleFrame.setWordleWord("animals");
      category = "ANIMALS";
    } else if (event.getSource() == movieBtn){
      WordleFrame.setWordleWord("movies");
      category = "MOVIES";
    } else if (event.getSource() == randomBtn){
      WordleFrame.setWordleWord("random");
      category = "RANDOM";
    }
    WordleFrame.changeScreen("wordleGame");
  }
//---------------------------------------------------------------------------------------------------------------------
  //showing image
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(this.image, -10, -20, null);
  }
}
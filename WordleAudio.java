/**
 * Rosie Chai
 * Jan 22, 2023
 * WordleAudio: Class that manages audio and sound
 */

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class WordleAudio {
  private String filePath;
  private Clip audio;
//---------------------------------------------------------------------------------------------------------------------
  //constructor
  public WordleAudio(String filePath) { 
    this.filePath = filePath;
    try{
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
      this.audio = AudioSystem.getClip();
      this.audio.open(audioStream);
    } 
    catch(IOException ignored){;}
    catch(UnsupportedAudioFileException ignored){;}
    catch(LineUnavailableException ignored){;}
  }  
//---------------------------------------------------------------------------------------------------------------------
  //method: plays the sound
  public void play(){
    //play bg music in a loop
    if (this.filePath.equals("doveLoveMSC.wav")){
      this.audio.loop(Clip.LOOP_CONTINUOUSLY);
    } else{
      this.audio.start();
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: resets the sound to the beginning
  public void reset(){
    this.audio.setMicrosecondPosition(0);
  }
//---------------------------------------------------------------------------------------------------------------------
  //method: stops playing sound
  public void stop(){
    this.audio.stop();
  }
}

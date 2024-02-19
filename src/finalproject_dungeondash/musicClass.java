/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject_dungeondash;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author jason
 */
public class musicClass {

    public static void playMusic(String musicLocation) {
        try {//create the "try" of the try catch
            File musicPath = new File(musicLocation);// create a file object containing the file path of"musicLocation"
            if (musicPath.exists()) {//check if the file exest 
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);// create an object to bring the music from the file to the java program
                Clip clip = AudioSystem.getClip();// get the audio stream from the audio input object 
                clip.open(audioInput);//open the audio clip
                clip.start();//starts the audio
                clip.loop(clip.LOOP_CONTINUOUSLY);//loops the audio continually
            } else {//otherwise
                System.out.println("Can't Find File");// display that the file cannot be found
            }
        } catch (Exception ex) {//create the "catch" of the try catch
            System.out.println("Error:" + ex);
        }
    }

    public static void playMusicNonLoop(String musicLocation) {
        try {//create the "try" of the try catch
            File musicPath = new File(musicLocation);// create a file object containing the file path of"musicLocation"
            if (musicPath.exists()) {//check if the file exest 
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);// create an object to bring the music from the file to the java program
                Clip clip = AudioSystem.getClip();// get the audio stream from the audio input object 
                clip.open(audioInput);//open the audio clip
                clip.start();//starts the audio

                clip.wait(1); //not sure what number means
                audioInput.close();

                clip.stop();
                clip.close();

            } else {//otherwise
                System.out.println("Can't Find File");// display that the file cannot be found
            }
        } catch (Exception ex) {//create the "catch" of the try catch
            System.out.println("Error:" + ex);
        }
    }

}

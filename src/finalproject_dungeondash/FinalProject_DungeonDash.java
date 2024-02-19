/*
Avinash Anand 
 */
package finalproject_dungeondash;

import GUIs.LoginWindow;
import javax.swing.JFrame;

/**
 *
 * @author jason
 */
public class FinalProject_DungeonDash extends JFrame {
// instantiat all nessisary variables 
    public static LoginWindow login;   
    public static void main(String[] args) {
// create a new jframe window   
        login = new LoginWindow();// create a new login window
        login.setResizable(false);
        login.setVisible(true);// set the window to visible  
        
    }

}

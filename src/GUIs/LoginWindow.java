/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import finalproject_dungeondash.musicClass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mayu0918
 */
public class LoginWindow extends javax.swing.JFrame implements Serializable{
// initialize all variables 
    public static MenuWindow menu;
    public static ArrayList<Users> registeredUsers = new ArrayList();
    public static Users currentUser;
    public LoginWindow() {      
        initComponents();
        FileInputStream in;
        try {
            in = new FileInputStream(System.getProperty("user.dir") + "/saves/saves");
            readFile(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //String filepath = "src\\finalproject_dungeondash\\LoadingMusic.wav";// create a string for the filepath

        //musicClass musicObject = new musicClass();// create an incedence of the music class
        //musicClass.playMusic(filepath);//pass the filepath throught the method of 'playMusic' F

    }
/**
 * writes to the file
 */
    public static void writeFile() {
        String toWrite = "";
        FileOutputStream out;
        try {         
            out = new FileOutputStream(System.getProperty("user.dir") + "/saves/saves");          
            PrintWriter p = new PrintWriter(out, true);
            // create a writer to write to the file
            //FileWriter writer = new FileWriter(in);
            // use a for loop to go throught the users and write to the test files witht eh users intfo
            
            for (int i = 0; i < registeredUsers.size(); i++) {
                if (i != 0) {
                    toWrite += "\n";
                }               
                toWrite += registeredUsers.get(i).getUsername() + "\n" + registeredUsers.get(i).getPassword() + "\n" + registeredUsers.get(i).getHighScore();
            }
            
            // write to the file and close the reader
            p.write(toWrite);
            p.close();

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
    
    
    /**
     * read the file 
     */
    private void readFile(FileInputStream in) {
        // create all nessisary variables
        String username;
        String password;
        int score;
        Users u;
        try {
            // create a new file and scanner to read the file
            //"src/GUIs/TextFiles/Users.txt"
            //file = new File("src/GUIs/TextFiles/Users.txt");
            Scanner s = new Scanner(in);
            while (s.hasNextLine()) {
                //read the username password and highscore from the next lines 
                username = s.nextLine();
                password = s.nextLine();
                score = Integer.parseInt(s.nextLine());
               // create a new user using this infomation 
                u = new Users(username, password, score);
                // add it to the user array
                registeredUsers.add(u);
            }
        } 
        catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
/**
 * log into game method
 */
    private void logIntoGame() {
        // if the menu is null, create the menu window
        if (menu == null) {
            menu = new MenuWindow(this);
        }
        // allow the user to se the menu window and close the current window 
        menu.setResizable(false);
        menu.setVisible(true);
        this.setVisible(false);
        // invoke the reset inputs window
        resetInputs();
        menu.displayWelcome();
    }
/**
 * checks if the username repeats
 * @param username the username needed to check
 * @return an integer representing if the username was repeated
 */
    private int usernameRepeat(String username) {
        // read throughte file oto see if there is a repeated username, if so return the index, if not, return -1
        for (int i = 0; i < registeredUsers.size(); i++) {
            if (registeredUsers.get(i).getUsername().equals(username)) {
                return i;
            }

        }
        return -1;
        /*       int middle;
        if (left > right) {
            if (!repeatIndex) {
                return left;
            } else {
                return -1;
            }
        }

        middle = (left + right) / 2;
        if (registeredUsers.get(middle).getUsername().compareTo(user) == 0) {
            if (!repeatIndex) {
                return -1;
            } else {
                return middle;
            }
        }

        if (registeredUsers.get(middle).getUsername().compareTo(user) > 0) {
            return usernameRepeat(left, middle - 1, user, repeatIndex);
        } else {
            return usernameRepeat(middle + 1, right, user, repeatIndex);
        }
         */

    }
/**
 * checks is the user input is satisfactory 
 * @param userName the username
 * @param password the password 
 * @return a boolean representing if the input is satisfactory
 */
    private boolean inputGood(String userName, String password) {
        // if the user name entered is blnk 
        if (userName.equals("")) {
            // tell the user they need to enter a username 
            lblErrorMsg.setText("Please enter a username!");
            return false;// return clase 
        } else if (password.equals("")) {// otherwisse if the password is empty, tell the user that it is and returnt false 
            lblErrorMsg.setText("Please enter a password!");
            return false;
        } else {// otherwise dont print an error message and return true 
            lblErrorMsg.setText("");
            return true;
        }

    }
/**
 * reset he user inputs
 */
    private void resetInputs() {
        // reset the username and pasword feilds 
        txtFieldUsername.setText("");
        txtFieldPassword.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtFieldUsername = new javax.swing.JTextField();
        txtFieldPassword = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        btnSignUp1 = new javax.swing.JButton();
        lblErrorMsg = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Ravie", 0, 48)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 255, 51));
        lblTitle.setText("Dungeon Dash");
        lblTitle.setToolTipText("");

        lblUsername.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(102, 255, 102));
        lblUsername.setText("Username: ");

        lblPassword.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(102, 255, 102));
        lblPassword.setText("Password: ");

        txtFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldUsernameActionPerformed(evt);
            }
        });

        txtFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldPasswordActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(255, 0, 51));
        btnLogin.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnSignUp.setBackground(new java.awt.Color(255, 0, 0));
        btnSignUp.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnSignUp.setText("SIGN UP");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        btnSignUp1.setBackground(new java.awt.Color(255, 0, 0));
        btnSignUp1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnSignUp1.setText("EXIT");
        btnSignUp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUp1ActionPerformed(evt);
            }
        });

        lblErrorMsg.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        lblErrorMsg.setForeground(new java.awt.Color(255, 0, 0));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIs/ICS4UBackgroundDungeon.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(lblTitle))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(txtFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(txtFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(960, 960, 960)
                .addComponent(btnSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblTitle)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsername)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblPassword))
                            .addComponent(txtFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogin)
                            .addComponent(btnSignUp)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(580, 580, 580)
                        .addComponent(btnSignUp1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(lblErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldUsernameActionPerformed

    private void txtFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldPasswordActionPerformed

    private void btnSignUp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUp1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSignUp1ActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        //get the text from the username and password and annthem to variables
        String userName = txtFieldUsername.getText();
        String password = txtFieldPassword.getText();
// if the input is clid 
        if (inputGood(userName, password)) {
            // find the placement of the repreted name 
            int placement = usernameRepeat(userName);
            // if the name is not a repeat 
            if (placement != -1) {
                //find the user
                currentUser = registeredUsers.get(placement);
                // if the passwords are equal, log in
                if (currentUser.getPassword().equals(password)) {
                    lblErrorMsg.setText("");
                    logIntoGame();
                    // otherwise say that the pasword is incorrect
                } else {
                    lblErrorMsg.setText("Password incorrect!");
                }
                // otherwise say the username is incorrect
            } else {
                lblErrorMsg.setText("Username not found!");
            }
        }


    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
                //get the text from the username and password and annthem to variables

        String userName = txtFieldUsername.getText();
        String password = txtFieldPassword.getText();
// if the input is valid 
        if (inputGood(userName, password)) {
            
            // check if the name is a repeat 
            int placement = usernameRepeat(userName);
            // if it is a repeat 
            if (placement == -1) {            
                //create a new user, regester them and log them into the game 
                currentUser = new Users(userName, password, 0);               
                registeredUsers.add(currentUser);               
                writeFile();               
                logIntoGame();
            } else {// otherwies print an error messgage 
                lblErrorMsg.setText("Username already exists!");
            }
        }
        

    }//GEN-LAST:event_btnSignUpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JButton btnSignUp1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblErrorMsg;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtFieldPassword;
    private javax.swing.JTextField txtFieldUsername;
    // End of variables declaration//GEN-END:variables
}

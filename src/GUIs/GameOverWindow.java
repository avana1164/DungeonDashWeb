/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author matth
 */
public class GameOverWindow extends javax.swing.JFrame {

    /**
     * Creates new form GameOverWindow
     */
    public GameOverWindow() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Set player score for playthrough
     */
    public void getScore() {
        int score = 0;
        int numEnemies = MenuWindow.gamePanel.enemies.length; //enemeis in enemy array
        for (int i = 0; i < numEnemies - 1; i++) { //for each enemy in enemy array except for boss
            if (MenuWindow.gamePanel.enemies[i] == null) {
                score++; //one point for each one killed
            }
        }
        for (int i = 3; i <= 4; i++) { //for each door
            if (MenuWindow.gamePanel.obj[i] == null) {
                score += 2; //2 points for each one opened
            }
        }
        
        
        if (MenuWindow.gamePanel.enemies[numEnemies - 1].dead == true) { //if player kills boss(last enemy in enemy array)
            score += 10;
            score += (MenuWindow.gamePanel.player.life / 2); //get a point for each full heart
            score += (int) (0.5 * score); //get a 1.5 times multiplier
        }
        if (score>=LoginWindow.currentUser.getHighScore()) { //check if new score is higher than player current score
            LoginWindow.currentUser.setHighScore(score); //if so than replace
            clearFile();
            LoginWindow.writeFile();
        }
        lblScore.setText("Score: " + score); //set score to user

    }
    
    
    private static void clearFile(){
        FileOutputStream out;
        try{
            out = new FileOutputStream(System.getProperty("user.dir") + "/saves/saves");
            PrintWriter p = new PrintWriter(out);
            p.print("");
            p.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblScore = new javax.swing.JLabel();
        lblGameOver1 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblScore.setFont(new java.awt.Font("Poor Richard", 0, 36)); // NOI18N
        lblScore.setForeground(new java.awt.Color(0, 255, 51));
        lblScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore.setText("Your Score:");

        lblGameOver1.setFont(new java.awt.Font("Ravie", 0, 36)); // NOI18N
        lblGameOver1.setForeground(new java.awt.Color(255, 0, 0));
        lblGameOver1.setText("GAME OVER");

        btnMenu.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        btnMenu.setText("Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIs/ICS4UBackgroundDungeon.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(lblGameOver1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(1062, 1062, 1062)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblGameOver1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(619, 619, 619)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        this.setVisible(false); //make game over invisible
        MenuWindow.gamePanel.gameState = MenuWindow.gamePanel.PLAYSTATE; //set game state back to play in prep for next round
        LoginWindow.menu.setVisible(true); //make menu visible
    }//GEN-LAST:event_btnMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblGameOver1;
    private javax.swing.JLabel lblScore;
    // End of variables declaration//GEN-END:variables
}

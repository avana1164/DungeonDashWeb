/*
2022-06-07
Matthew, Avinash, Jason
user class
 */
package GUIs;


/**
 *
 * @author mayu0918
 */
public class Users {
    //instantiate variables for user 
    private String username, password;
    private int highScore;
        /**
         * constructor- constructs a user with default values 
         */
        public Users(){
            username="";
            password="";
            highScore = 0;
        }
        /**
         * constructor- constructs a user with player given values 
         * @param username the username of the player
         * @param password the password of the palyer 
         * @param high the highscore of the player
         */
        public Users(String username,String password, int high){
            this();
            this.username = username;
            this.password = password;
            this.highScore = high;
        }
        
        /**
         * getter- gets the username
         * @return the username 
         */
        public String getUsername(){
            return username;
        }
        /**
         * getter- gets the password
         * @return the password 
         */
        public String getPassword(){
            return password;
        }
        /**
         * setter- sets the username 
         * @param username the username 
         */
        public void setUsername(String username){
            this.username=username; 
        }
        /**
         * setter- sets the password
         * @param password the password 
         */
        public void setPassword(String password){
            this.password=password; 
        }
        /**
         * setter- sets the highscore
         * @param h the highscore
         */
        public void setHighScore(int h){
            highScore = h;
            
        }
        /**
         * getter - gets the score
         * @return gets the score 
         */
        public int getHighScore(){
            return highScore;
        }
        /**
         * equals methos- checks if 2 users are equal
         * @param u the user you want to check against 
         * @return a boolean repersenting if the users are equal
         */
        public boolean equals(Users u){
            return username.equals(u.getUsername()) && password.equals(u.getPassword()) && highScore == u.getHighScore();
        }
        /**
         * clone method- clones s user
         * @return the cloned user 
         */
        public Users clone(){
            return new Users(username, password, highScore);
        }
        /**
         * Tostring- puts the info for the class into a string which can be displayed to the user
         * @return the string of info
         */
        public String toString(){
            return "Username: " + username + "\nPassword: " + password + "\nHigh Score: " + highScore;
        }
        
         
}

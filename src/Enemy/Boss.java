/*
 * Avinash Anand
 * May 25, 2022
 * The Boss class is a subclass of the Entity, so it contains all the attributes from AbstractEnemy plus speed and bullet rate attributes
 */
package Enemy;

import finalproject_dungeondash.DrawingSurface;
import finalproject_dungeondash.Entity;

public class Boss extends Entity{
/**
 * constructs a new boss
 * @param ds the drawing surface variables 
 */
    public Boss(DrawingSurface ds) {
        super(ds);
        //set all atributes of the boss to its required values
        enemyDamage = 2;
        maxLife = 10;
        sizeX = 96;
        sizeY = 168;
        life = maxLife;
        speed = 2;
        solidArea.x = 21;
        solidArea.y = 132;
        solidArea.width = 60;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        // invoke the get sprites method
        getSprites();
    }
/**
 * load the images into the file 
 */
    public void getSprites() {
        // load all 4 sprites into netbeans 
        idle1 = setup("/meleeEnemySprites/knight_m_run_anim_f0", sizeX, sizeY);
        idle2 = setup("/meleeEnemySprites/knight_m_run_anim_f1", sizeX, sizeY);
        idle3 = setup("/meleeEnemySprites/knight_m_run_anim_f2", sizeX, sizeY);
        idle4 = setup("/meleeEnemySprites/knight_m_run_anim_f3", sizeX, sizeY);
    }


    
    
    
    
    
    //private int speed; //how fast the boss moves
    //private int bulletRate; //the rate at which the boss fires a bullet
    
    /**
     * primary constructor for the Boss class with the speed and bulletRate attributes set to 0
     */
    //public Boss(){
        //speed = 0;
        //bulletRate = 0;
    //}
    
    /**
     * secondary constructor for the Boss class with all attributes set to specified values from the main class
     * @param healthPoints is the enemy's health points
     * @param attackStat is the enemy's damage output
     * @param xPos is the enemy's x position 
     * @param yPos is the enemy's y position
     * @param speed is the enemy's speed
     * @param bulletRate is the enemy's bullet rate
     */
    //public Boss(int healthPoints, int attackStat, int xPos, int yPos, int speed, int bulletRate){
    //    super(healthPoints, attackStat, xPos, yPos);
    //    this.speed = speed;
    //    this.bulletRate = bulletRate;
    //}

    //public void attack(){
        //will animate later
    //}

    //public void specialAttack(){
        //will animate later
    //}

    //public void regenHealth(){
        //will animate later
    //}

    /**
     * getSpeed is an accessor method which will return the boss's speed
     * @return the boss's speed
     */
    //public int getSpeed(){
    //    return speed;
    //}

    /**
     * setSpeed is a mutator method which will set the boss's speed
     * @param speed is the boss's new speed
     */
    //public void setSpeed(int speed){
    //    this.speed = speed;
    //}

    /**
     * getBulletRate is an accessor method which will return the enemy's bullet rate
     * @return the boss's bullet rate
     */
    //public int getBulletRate(){
    //    return bulletRate;
    //}

    /**
     * setBulletRate is a mutator method which will set the boss's bullet rate
     * @param bulletRate is the boss's new bullet rate
     */
    //public void setBulletRate(int bulletRate){
    //    this.bulletRate = bulletRate;
    //}

    /**
     * The clone method will create an exact copy of a pre-existing Boss object
     * @return the copy of the Boss object
     */
    //public Boss clone(){
    //    Boss copy; //The Boss clone is declared
    //  copy = new Boss(healthPoints, attackStat, xPos, yPos, speed, bulletRate); //The Boss clone is instantiated using the original Boss's attribute values
    //    return copy; //the copy is returned
    //}
    
    /**
     * The equals method will compare all the values of the attributes of the Boss object with all the values of the same attributes with the object b to see if both objects are the same
     * @param b is the object compared with the Boss object
     * @return true or false depending on whether the Boss object is equal to b
     */
    //public boolean equals(Boss b){
    //    return this.healthPoints == b.getHealthPoints() && this.attackStat == b.getAttackStat() && this.speed == b.getSpeed() && this.bulletRate == b.getBulletRate();
    //}

    /**
     * The toString method in the Boss class will contain the second part of the total string
     * @return the information of the boss 
     */
    //public String toString(){
    //    return super.toString() + "\nSpeed: " + speed + "\nBullet Rate: " + bulletRate;
    //}
}

class DrawingSurface {
    constructor(){
        //let gameThread; //smooth animations
        let keyH = new KeyHandler(this); //create key listener object
        let player; //instantiate player object
        
        let tileSize = 60, playerSpeed = 4; //tiles are squares 60 pixels big, player moves at relatively fast speed
        let cChecker = new CollisionChecker(this); //instantiate collision checker object to check for collisions between things

        //Settings for screen
        let maxScreenCol = 20; //columns on screeen
        let maxScreenRow = 12; //rows on screen
        let screenWidth = tileSize * maxScreenCol; //tiles on screen width
        let screenHeight = tileSize * maxScreenRow; //tiles screen height wise
        let tileM = new TileManager(this); //create a tile manager class to manage tiles

        //Settings for world:
        let maxWorldCol = 74; //columns in world
        let maxWorldRow = 42; //rows in world
        let worldWidth = DrawingSurface.tileSize * maxWorldCol; //pixels on screen width
        let worldHeight = DrawingSurface.tileSize * maxWorldRow; //pixels on screen height
        
        let aSetter = new AssetSetter(this); //create asset setter to set position of objects and enemies
        let obj = new SuperObject[5]; //ADJUST SIZE LATER

        //create object to handle on screen game panel text
        let ui = new UserInterface(this);

        let enemies = new Entity[30]; //create array of 30 enemies

        //GAME STATE
        let gameState;
        let PLAYSTATE = 0; //display game
        let PAUSESTATE = 1; //display pause screen
        let MSGSTATE = 2; //display end message
        let ENDSTATE = 3; //leave screen
    }
    

    /**
     * Create surface to draw on
     */
    DrawingSurface() {
        
        //set panel settings
        this.setPreferredSize(new Dimension(1200, 720));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        //gameState = PLAYSTATE; //start playing game
    }

    /**
     * Instantiate enemies and objects for games
     */
    setupGame() {
        player = new Player(this, keyH); //create player object
        aSetter.setObject();
        aSetter.setEnemies();

    }

    /**
     * Start game thread for animation
     */
    startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    /**
     * Overrides paintComponent in JPanel class so that we can do our own custom
     * painting
     */
    paintComponent(g) {

        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        //g2d = (Graphics2D) g;

        //tile stuff
        tileM.draw(g2d);
        
        //draw each enemy
        for (let i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {//as long as enemy exists
                enemies[i].draw(g2d); //draw it
            }
        }

        //draw each object
        for (let i = 0; i < obj.length; i++) {
            if (obj[i] != null) { //as long as object exists
                obj[i].draw(g2d, this); //draw it
            }
        }
        
        if (player.isAttacking() == true) { //if player is attacking
            player.drawSword(g2d); //draw them with their sword
        } else { //otherwise just draw the player
            player.drawPlayer(g2d);
        }
        ui.pause(g2d); //check the game state

        g2d.dispose(); //saves memory

    }

    /**
     * Code for animation
     */
    @Override
    run() {
        let drawInterval = 1000000000 / 60;
        let delta = 0;
        let lastTime = System.nanoTime();
        let currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Update screen to see animations
     */
    update() {
        if (gameState == PLAYSTATE) { //if game is currently being played
            //update player animation
            player.update(); 
            
            for (let i = 0; i < enemies.length; i++) { //update enemy animation
                if (enemies[i] != null) {
                    enemies[i].update();
                }
            }

            if (player.life <= 0 || (enemies[enemies.length - 1].dead == true)) { //if player is out of health of boss is dead        
                gameState = MSGSTATE; //display game over message
            }
        }
    }
}
/**
 * Created by Klaus Horn on 4.6.2014.
 */
import java.awt.*;
import javax.swing.*;

class Tetris extends javax.swing.JPanel implements java.awt.event.KeyListener {

    boolean gameOver = false;
    int[][] occupied = new int[10][20];
    //int[][] occupied = new int[28][20];  // expand temp to show all tokens
    // [two tokens] [four rotations] [four cells]
    static int[][][] xRotationArray = {
            { {0,0,1,2}, {0,0,0,1}, {2,0,1,2}, {0,1,1,1} }, //token number 0
            { {0,0,1,1}, {1,2,0,1}, {0,0,1,1}, {1,2,0,1} },  //token number 1
            { {1,1,0,0}, {0,1,1,2}, {1,1,0,0}, {0,1,1,2} },  //token number 2
            { {0,1,2,2}, {0,1,0,0}, {0,0,1,2}, {1,1,0,1} },  //token number 3
            { {1,0,1,2}, {1,0,1,1}, {0,1,1,2}, {0,0,1,0} },  //token number 4
            { {0,1,0,1}, {0,1,0,1}, {0,1,0,1}, {0,1,0,1} },  //token number 5
            { {0,1,2,3}, {0,0,0,0}, {0,1,2,3}, {0,0,0,0} }  //token number 6

    };

    static int [][][] yRotationArray = {
            { {0,1,0,0}, {0,1,2,2}, {0,1,1,1}, {0,0,1,2} },  //token number 0
            { {0,1,1,2}, {0,0,1,1}, {0,1,1,2}, {0,0,1,1} },  //token number 1
            { {0,1,1,2}, {0,0,1,1}, {0,1,1,2}, {0,0,1,1} },  // token number 2
            { {0,0,0,1}, {0,0,1,2}, {0,1,1,1}, {0,1,2,2} },  // token number 3
            { {0,1,1,1}, {0,1,1,2}, {0,0,1,0}, {0,1,1,2} },  // token number 4
            { {0,0,1,1}, {0,0,1,1}, {0,0,1,1}, {0,0,1,1} },  // token number 5
            { {0,0,0,0}, {0,1,2,3}, {0,0,0,0}, {0,1,2,3} }   // token number 6
    };

    int score = 0; // score
    int lineCompleted = 0; // number of lines completed
    int level = 0;

    JLabel scoreLabel = new JLabel("SCORE : 0");
    JLabel levelLabel = new JLabel("LEVEL : 0");

    public void init() {
        this.setPreferredSize(new Dimension(640,480));
        this.setBackground(Color.GREEN);

        this.setLayout(null);       // absolute coordinate system

        scoreLabel.setBounds(300,50,100,30); // x,y,w,h (in pixels)
        this.add(scoreLabel);

        levelLabel.setBounds(300,100,100,30);
        this.add(levelLabel);

        /*for (int tokenNumber = 0;tokenNumber<7;tokenNumber++) {
            for (int rotationNumber = 0;rotationNumber<4;rotationNumber++) {
                int[] xArray = xRotationArray[tokenNumber][rotationNumber];
                int[] yArray = yRotationArray[tokenNumber][rotationNumber];
                int x = tokenNumber * 4;
                int y = rotationNumber * 5;
                drawToken(x,y,xArray,yArray);
            }
        }
        */
        /*
        array of relative position
        int[] xArray = xRotationArray[0][0];    // [token 0] [rotation 0]
        int[] yArray = yRotationArray[0][0];
        int[] xArray = {0,0,1,2};
        int[] yArray = {0,1,0,0};

        drawToken(0,0,xArray,yArray);

        //first rotation
        xArray = xRotationArray[0][1];
        yArray = yRotationArray[0][1];
        drawToken(0,5,xArray,yArray);

        //second rotation
        xArray = xRotationArray[0][2];
        yArray = yRotationArray[0][2];
        drawToken(0,10,xArray,yArray);

        //third rotation
        xArray = xRotationArray[0][3];
        yArray = yRotationArray[0][3];
        drawToken(0,15,xArray,yArray);

        // second token, rotation 0
        xArray = xRotationArray[1][0];
        yArray = yRotationArray[1][0];
        drawToken(5,0,xArray,yArray);

        // second token, rotation 1
        xArray = xRotationArray[1][1];
        yArray = yRotationArray[1][1];
        drawToken(5,5,xArray,yArray);

        // second token, rotation 2
        xArray = xRotationArray[1][2];
        yArray = yRotationArray[1][2];
        drawToken(5,10,xArray,yArray);

        //second token, rotation 3
        xArray = xRotationArray[1][3];
        yArray = yRotationArray[1][3];
        drawToken(5,15,xArray,yArray);
        */
    }

    public void drawCell(int x, int y) {
        occupied[x][y] = 1;
        /*gr.setColor(Color.BLACK);
        gr.fillRect(x*24,y*24,24,24);
        gr.setColor(Color.RED);
        gr.fillRect(x*24+1,y*24+1,22,22);
        */
    }

    public void eraseCell(int x, int y) {
        occupied[x][y] = 0;
        /*gr.setColor(Color.BLACK);
        gr.fillRect(x*24+1,y*24+1,22,22);
        */
    }

    public void drawToken(int x, int y, int[] xArray, int[] yArray) {
        for (int i = 0;i<4;i++) {
            drawCell(x+xArray[i],y+yArray[i]);
        }
    }

    public void eraseToken(int x, int y, int[] xArray, int[] yArray) {
        for (int i=0;i<4;i++) {
            eraseCell(x+xArray[i], y+yArray[i]);
        }
    }

    public void paint(Graphics gr) {
        super.paint(gr);

        for (int x = 0; x<occupied.length;x++) {
            for (int y = 0; y<20; y++) {
                if (occupied[x][y] == 1) {
                    //draw cell
                    gr.setColor(Color.BLACK);
                    gr.fillRect(x*24,y*24,24,24);
                    gr.setColor(Color.RED);
                    gr.fillRect(x*24+1,y*24+1,22,22);
                }
                else {
                    // erase cell
                    gr.setColor(Color.BLACK);
                    gr.fillRect(x*24,y*24,24,24);
                }
            }
        }
    }

    public boolean isValidPosition(int x, int y, int tokenNumber, int rotationNumber) {
        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        for (int i=0;i<4;i++) {         // loop over the four cells
            int xCell = x+xArray[i];
            int yCell = y+yArray[i];

            // range check
            if (xCell<0) return false;
            if (xCell>=10) return false;
            if (yCell<0) return false;
            if (yCell>=20) return false;

            // occupancy check
            if (occupied[xCell][yCell]==1) return false;
        }
        return true;
    }

    public void randomTokenTest() {
        try { Thread.sleep(1000); } catch (Exception ignore) {}
        //int x = (int) (7*Math.random());    // random x: 0 - 6
        //int y = (int) (15*Math.random());   // random y: 0 - 14

        //int tokenNumber = (int) (7*Math.random());
        //int rotationNumber = (int) (4*Math.random());
        int x,y,tokenNumber,rotationNumber;

        while (true) {// loop until position is valid
            x = (int) (10 * Math.random());      //random x: 0-9
            y = (int) (20 * Math.random());      //random y: 0-19

            tokenNumber = (int) (7 * Math.random());
            rotationNumber = (int) (4 * Math.random());

            if (isValidPosition(x, y, tokenNumber, rotationNumber)) break;
        }

        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        drawToken(x,y,xArray,yArray);
        repaint();
    }

    public void clearCompleteRow(int[] completed) {
        //erase
        for (int blinking=0;blinking<5;blinking++) {
            for (int i=0;i<completed.length;i++) {
                if (completed[i]==1) {
                    for (int x=0;x<10;x++) {
                        occupied[x][i] = 0;
                    }
                }
            }
            repaint();
            try { Thread.sleep(100); } catch (Exception ignore) {}
        }
    }

    public void shiftDown(int[] completed) {
        for (int row=0;row<completed.length;row++) {
            if (completed[row]==1) {
                for (int y=row;y>=1;y--) {
                    for(int x=0;x<10;x++) {
                        occupied[x][y] = occupied[x][y-1];
                    }
                }
            }
        }
    }

    public void checkRowCompletion() {
        int[] complete = new int[20];
        for (int y=0;y<20;y++) {  //20 rows
            int filledCell = 0;
            for (int x=0;x<10;x++) {    // 10 columns
                if (occupied[x][y] == 1) filledCell++;
                if (filledCell == 10) {   //row completed
                    complete[y] = 1;
                }
            }
        }

        clearCompleteRow(complete);
        shiftDown(complete);

        addScore(complete);
    }

    void addScore(int[] complete) {
        int bonus = 10; // score for the first completed line
        for (int row=0;row<complete.length;row++) {
            if (complete[row] == 1) {
                lineCompleted+=1;
                score+=bonus;
                bonus*=2;   // double the bonus for every additional line
            }
        }

        //advance level for every 3 completed lines
        level = lineCompleted/3;
        if (level>30) { lineCompleted=0; level=0; } // Max lvl
        scoreLabel.setText("SCORE : "+score);
        levelLabel.setText("LEVEL : "+level);
    }

    public void addFallingToken() {
        int x=5,y=0;
        int tokenNumber, rotationNumber;

        tokenNumber = (int) (7*Math.random());
        rotationNumber = (int) (4*Math.random());

        /*while(true) { // loop until position is valid
            tokenNumber = (int) (7 * Math.random());
            rotationNumber = (int) (4 * Math.random());

            if (isValidPosition(x, y, tokenNumber, rotationNumber)) break;
        }
        */

        int[] xArray = xRotationArray[tokenNumber][rotationNumber];
        int[] yArray = yRotationArray[tokenNumber][rotationNumber];

        if (!isValidPosition(x,y,tokenNumber,rotationNumber)) {
            gameOver = true;
            drawToken(x,y,xArray,yArray);
            repaint();
            return;
        }

        drawToken(x,y,xArray,yArray);
        repaint();

        int delay = 50; //mini second / changes game pace
        int frame = 0;
        boolean reachFloor=false;
        while (!reachFloor) {
            try {Thread.sleep(delay); } catch (Exception ignore) {}
            eraseToken(x,y,xArray,yArray);

            // add keyboard control
            if(leftPressed && isValidPosition(x-1,y,tokenNumber,rotationNumber)) x -= 1;
            if (rightPressed && isValidPosition(x+1,y,tokenNumber,rotationNumber)) x += 1;
            if (downPressed && isValidPosition(x,y+1,tokenNumber,rotationNumber)) y+=1;
            if (spacePressed && isValidPosition(x,y,tokenNumber,(rotationNumber+1)%4)) {
                rotationNumber = (rotationNumber+1)%4;
                xArray = xRotationArray[tokenNumber][rotationNumber];
                yArray = yRotationArray[tokenNumber][rotationNumber];
                spacePressed = false;
            }

            if (frame % 30==0) y +=1; // fall for every 30 frame
            if (!isValidPosition(x, y, tokenNumber, rotationNumber)) {    //reached floor
                reachFloor = true;
                y-=1;   //restore position
            }
            drawToken(x,y,xArray,yArray);
            repaint();
            frame++;
        }
    }

    public void printGameOver() {
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setBounds(300,300,100,30);
        add(gameOverLabel);
        repaint();
    }

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean downPressed = false;
    boolean spacePressed = false;

    // must implement this method for KeyListener
    public void keyPressed(java.awt.event.KeyEvent event) {
        if (event.getKeyCode()==37) {   //left arrow
            leftPressed = true;
        }
        if (event.getKeyCode()==39) {   //right arrow
            rightPressed = true;
        }
        if (event.getKeyCode()==40) {   //down arrow
            downPressed = true;
        }
        if (event.getKeyCode()==32) {   //space
            spacePressed = true;
        }
    }

    // must implement this method for KeyListener
    public void keyReleased(java.awt.event.KeyEvent event) {
        if (event.getKeyCode()==37) {  //left arrow
            leftPressed = false;
        }
        if (event.getKeyCode()==39) {   //right arrow
            rightPressed = false;
        }
        if (event.getKeyCode()==40) {   //down arrow
            downPressed = false;
        }
        if (event.getKeyCode()==32) {   //space
            spacePressed = false;
        }
    }

    public void keyTyped(java.awt.event.KeyEvent event) {
        // System.out.println(event);
    }


    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame("Klaus' Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Tetris tetris = new Tetris();
        tetris.init();

        window.add(tetris);
        window.pack();
        window.setVisible(true);

        try { Thread.sleep(1000); } catch (Exception ignore) {}

        window.addKeyListener(tetris);  //listen to keyboard event
        tetris.gameOver = false;
        while(!tetris.gameOver) {
            tetris.addFallingToken();
            tetris.checkRowCompletion();
        }
        tetris.printGameOver();
    }
}

package be.ecam.moneyrain;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by aurel on 13/04/2016.
 */
public class Player extends Movable  {
    private String move = "none";
    private int lives;
    private int score;

    public Player(Point screenSize, Point position, Point speed){
        super(screenSize, position, speed);
        setImage(R.drawable.persosmall);
        this.position.x = screenSize.x/2;
        this.position.y = screenSize.y-imageSize.y;
    }

    public void move(){
        if (move == "right") {
            image = BitmapFactory.decodeResource(GameView.res,R.drawable.persosmall);
            moveRight();
        }
        else if (move == "left") {
            image = BitmapFactory.decodeResource(GameView.res,R.drawable.persosmallreverse);
            moveLeft();
        }
    }

    public void moveRight(){
        if(checkCollision() != "right")
            position.x += speed.x;
    }

    public void moveLeft(){
        if(checkCollision() != "left")
            position.x += -speed.x;
    }

    public void setMove(String move){
        this.move = move;
    }

    public boolean itemCaught(Item item){
        Point itemPos = new Point(item.getPosition());
        Point itemSize = new Point(item.getImageSize());


        if( itemPos.y+itemSize.y > position.y && itemPos.x+itemSize.x > position.x && itemPos.x < (position.x+imageSize.x) ) {
            switch(item.getImageID())
            {
                case R.drawable.bombesmall:
                    setLives(getLives()-1);
                    GameActivity.playBomb();
                    break;
                case R.drawable.piecesmall:
                    incrementScore(300);
                    GameActivity.playCoin();
                    break;
                case R.drawable.billetsmall:
                    incrementScore(400);
                    GameActivity.playCoin();
            }
            return true;
        }
        else
            return false;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore(int score) {
        this.score += score;
    }
}

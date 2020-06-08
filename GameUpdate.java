import java.util.Timer;
import java.lang.Math;
import java.util.ArrayList;

public class GameUpdate extends java.util.TimerTask
{
    MyGraphics g;
    Timer myTimer;
    SpaceShip ship = Main.ship;
    
    private int lastDiffStep = 0;
    
    //timing
    long lastTime = System.nanoTime();
    long lastShot = System.currentTimeMillis();
    long lastAsteroid = System.currentTimeMillis();
    
    public GameUpdate(Timer myTimer, MyGraphics g)
    {
      this.myTimer = myTimer;
      this.g = g;
    }
    
    public void run() //loop
    {
        //calculate delta time
        long time = System.nanoTime();
        int deltaTime = (int) ((time - lastTime) / 1000000);
        lastTime = time;
        
        Update(deltaTime);
        g.render();

        if (!Main.isRunning)
        {
            myTimer.cancel();
        }
    }
    
    private void Update(int deltaTime)
    {
         //------------------------Difficulty-Control------------------------//
         if(Main.score / 10 > lastDiffStep)
         {
            Main.maxAsteroids++;
            lastDiffStep++;
         }
         
         //---------------------------Ship-Control---------------------------//
         if(Main.myFrame.LEFT)
         {
            ship.incrementFaceAngle(-10);
         }
         
         if(Main.myFrame.RIGHT)
         {
            ship.incrementFaceAngle(10);
         }
         
         if(Main.myFrame.UP)
         {
            
            ship.moveAngle = ship.faceAngle;
            
            if(ship.currentSpeed <= 1.0)
            {
               ship.currentSpeed += 0.02;
            }
         }
         
         else if(ship.currentSpeed >= 0)
         {
            ship.currentSpeed -= 0.02;
         }
         
         if(ship.currentSpeed > 0)
         {
            ship.xPos += ship.BASE_SPEED * ship.currentSpeed * Math.cos(Math.toRadians(ship.moveAngle)) * deltaTime;
            ship.yPos += ship.BASE_SPEED * ship.currentSpeed * Math.sin(Math.toRadians(ship.moveAngle)) * deltaTime;
         }
         
         //wrap screen
         if(ship.xPos < 0)
         {
            ship.xPos = Main.frameWidth;
         }
         
         if(ship.xPos > Main.frameWidth)
         {
            ship.xPos = 0;
         }
         
         if(ship.yPos < 0)
         {
            ship.yPos = Main.frameHeight;
         }
         
         if(ship.yPos > Main.frameHeight)
         {
            ship.yPos = 0;
         }
         
         //Ship-Asteroid Collission
          for(int j = 0; j < Main.asteroids.size(); j++)
          {
             int x1 = Math.min(Main.asteroids.get(j).x, ship.xPos);
             int x2 = Math.max(Main.asteroids.get(j).x, ship.xPos);
             int y1 = Math.min(Main.asteroids.get(j).y, ship.yPos);
             int y2 = Math.max(Main.asteroids.get(j).y, ship.yPos);
             double rSqr = (Main.asteroids.get(j).radius + 15) * (Main.asteroids.get(j).radius + 15);
                  
             if((double)((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) <= rSqr)
             {
               Main.asteroids.get(j).takeDamage();
               Main.myFrame.setTitle("GAME OVER   SCORE: " + Main.score);
               ship.alive = false;
               Main.stopUpdate();
             }
           }
         
         //--------------------------Bullet-Control--------------------------//
         
         if(Main.myFrame.SHOOT)
         {
            long currentTime = System.currentTimeMillis();
      
            if((int)(currentTime - lastShot) >= ship.fireRate)
            {
               ship.shoot();  
               lastShot = System.currentTimeMillis();
            }
         }
         
         if(ship.bullets.size() > 0)
         {
            for(int i = 0; i < ship.bullets.size(); i++)
            {
               ship.bullets.get(i).move(deltaTime);
               
               //Asteroid-Bullet Collsion
               for(int j = 0; j < Main.asteroids.size(); j++)
               {
                  int x1 = Math.min(Main.asteroids.get(j).x, ship.bullets.get(i).x);
                  int x2 = Math.max(Main.asteroids.get(j).x, ship.bullets.get(i).x);
                  int y1 = Math.min(Main.asteroids.get(j).y, ship.bullets.get(i).y);
                  int y2 = Math.max(Main.asteroids.get(j).y, ship.bullets.get(i).y);
                  double rSqr = Main.asteroids.get(j).radius * Main.asteroids.get(j).radius;
                  
                  if((double)((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) <= rSqr)
                  {
                     Main.asteroids.get(j).takeDamage();
                     ship.bullets.get(i).alive = false;
                     Main.score++;
                     Main.myFrame.setTitle("Java Asteroids (Ryan Mercier)   SCORE: " + Main.score);
                  }
               }
               
               if(ship.bullets.get(i).x < 0 || ship.bullets.get(i).x > Main.frameWidth || ship.bullets.get(i).y < 0 || ship.bullets.get(i).y > Main.frameHeight || ship.bullets.get(i).alive == false)
               {
                  ship.bullets.remove(i);
                  i--;
               }
            }
         }
         
         //-------------------------Asteroid-Control-------------------------//
         
         if(Main.asteroids.size() > 0)
         {
            for(int i = 0; i < Main.asteroids.size(); i++)
            {
               if(Main.asteroids.get(i).alive)
               {
                  Main.asteroids.get(i).move(deltaTime);
               }
               
               else
               {
                  Main.asteroids.remove(i);
                  i--;
               }
            }
         }
         
         if(Main.asteroids.size() < Main.maxAsteroids)
         {
            long currentTime = System.currentTimeMillis();
      
            if((int)(currentTime - lastAsteroid) >= Main.asteroidUpdateTime)
            {
               Main.asteroids.add(new Asteroid(Main.randomInt(0, Main.frameWidth), Main.randomInt(0, Main.frameHeight), Main.randomInt(1, 3), Main.randomDouble(Main.minAsteroidSpeed, Main.maxAsteroidSpeed), Main.randomDouble(0, 180), false));  
               lastAsteroid = System.currentTimeMillis();
            }
         }
    }
}
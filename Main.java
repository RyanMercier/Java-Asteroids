import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.ArrayList;
import java.lang.Math;

public class Main
{
   public static MyFrame myFrame;
   public static int frameWidth = 1000;
   public static int frameHeight = 1000;
   
   public static int score = 0;
   
   public static SpaceShip ship = new SpaceShip(frameWidth / 2, frameHeight / 2);
   public static boolean isRunning = false;
   
   public static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
   public static int maxAsteroids = 12;
   public static final int asteroidUpdateTime = 100; //how many milliseconds between checking if another asteroid is neccessary
   
   public static final double minAsteroidSpeed = 0.05;
   public static final double maxAsteroidSpeed = 0.2;

   public static void main(String[] args)
   {  
      MyGraphics g = new MyGraphics();
      myFrame = new MyFrame(g);
      isRunning = true;
      Timer timer = new Timer();
      timer.schedule(new GameUpdate(timer, g), 0, 1000 / 30); //new timer at 30 fps
   }
   
   public static void stopUpdate()
   {
      isRunning = false;
   }
   
   public static int randomInt(int min, int max)
   {
      return (int)(Math.random()*(max - min + 1)) + min;
   }
   
   public static double randomDouble(double min, double max)
   {
      return (double)(Math.random()*(max-min))+min;
   }
}
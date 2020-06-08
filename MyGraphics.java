import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.Polygon;
import java.awt.geom.Area;
import javax.swing.*;
import java.lang.Math;
import java.util.ArrayList;

public class MyGraphics extends JPanel
{
   private static Graphics2D g2D;
   SpaceShip ship = Main.ship;
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      setBackground(Color.BLACK);
      g2D = (Graphics2D)g;
      g2D.setColor(Color.WHITE);
      
      drawShip();
      drawBullets();
      drawAsteroids();
      
      g2D.dispose();
   }
   
   public void drawShip()
   {
      //Triangle Points
      //(0, -10) (-10, 10) (10, 10)
      
      AffineTransform at = new AffineTransform();
      Dimension size = ship.body.getTriangleSize();
      at.translate(ship.xPos, ship.yPos);
      at.rotate(Math.toRadians(ship.faceAngle));
      g2D.setTransform(at);
      g2D.drawPolygon(ship.body.xCoords, ship.body.yCoords, 3);
   }
   
   public void drawBullets()
   {
      //Create Bullet
      for(Bullet b : ship.bullets)
         {
            AffineTransform at = new AffineTransform();
            at.translate(b.x, b.y);
            g2D.setTransform(at);
            g2D.fill(new Ellipse2D.Double(0, 0, 3, 3));
         }
   }
   
   public void drawAsteroids()
   {
      //Create Asteroid
      for(Asteroid a : Main.asteroids)
         {
            AffineTransform at = new AffineTransform();
            at.translate(a.x, a.y);
            g2D.setTransform(at);
            g2D.drawPolygon(a.xCoords, a.yCoords, 12);
         }
         
   }
   
   public void render()
   {
      repaint();
   }
   
}
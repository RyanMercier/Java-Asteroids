import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame
{  
   public static boolean UP = false;
   public static boolean LEFT = false;
   public static boolean RIGHT = false;
   public static boolean SHOOT = false;
   
   public MyFrame(MyGraphics graphic)
   {
      this.setSize(Main.frameWidth, Main.frameHeight);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      super.setTitle("Java Asteroids (Ryan Mercier)   SCORE: " + Main.score);
      this.add(graphic);
      this.setVisible(true);
      requestFocusInWindow();
      keyInput(graphic);
   }
   
   public void keyInput(MyGraphics g)
   {
      this.addKeyListener(new KeyListener()
      {
         public void keyTyped(KeyEvent event) {/*must override method*/}
       
         public void keyPressed(KeyEvent event)
         {
            int keyCode = event.getKeyCode();
            
            //move the ship
            if(keyCode == KeyEvent.VK_UP)
            {
               UP = true;
            }
            
            switch(keyCode)
            {
               case KeyEvent.VK_LEFT:
                  //rotate the ship left
                  LEFT = true;
                  break;
                  
               case KeyEvent.VK_RIGHT:
                   //rotate the ship right
                   RIGHT = true;
                   break;
                  
            }
            
            //Shoot
            if(keyCode == KeyEvent.VK_SPACE)
            {
               SHOOT = true;
            }
         }
         
         public void keyReleased(KeyEvent event)
         {
            int keyCode = event.getKeyCode();
            
            if(keyCode == KeyEvent.VK_UP)
            {
               UP = false;
            }
            
            if(keyCode == KeyEvent.VK_LEFT)
            {
               LEFT = false;
            }
            
            if(keyCode == KeyEvent.VK_RIGHT)
            {
               RIGHT = false;
            }
            
            if(keyCode == KeyEvent.VK_SPACE)
            {
               SHOOT = false;
            }
         };
      });
   }
   
}
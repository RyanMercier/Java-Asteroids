public class Asteroid
{
   int x;
   int y;
   int size;
   double radius;
   
   double speed;
   
   double direction;
   
   int[] xCoords;
   int[] yCoords;
   
   boolean alive = true;
   
   public Asteroid(int x, int y, int size, double speed, double direction, boolean isSplit)
   {
      this.x = x;
      this.y = y;
      this.size = size;
      this.speed = speed;
      this.direction = direction;
      
      //Spawn Positioning
      if(!isSplit)
      {
         if(Main.randomInt(0, 1) == 0)
         {
            if(Main.randomInt(0, 1) == 0)
            {
               this.x = -50;
               this.direction -= 90;
            }
         
            else
            {
               this.x = Main.frameWidth + 50;
               this.direction += 90;
            }
         }
      
         else
         {
            if(Main.randomInt(0, 1) == 0)
            {
               this.y = -50;
               this.direction += 180;
            }
         
            else
            {
               this.y = Main.frameHeight + 50;
            }
         }
      }
      
      //Size Management
      if(size == 1)
      {
         xCoords = new int[] {3, -3, -9, -13, -13, -9, -3, 3, 9, 13, 13, 9};
         yCoords = new int[] {-13, -13, -9, -3, 3, 9, 13, 13, 9, 3, -3, -9};
         radius = 13;
      }
      
      else if(size == 2)
      {
         xCoords = new int[] {7, -7, -20, -27, -27, -20, -7, 7, 20, 27, 27, 20};
         yCoords = new int[] {-27, -27, -20, -7, 7, 20, 27, 27, 20, 7, -7, -20};
         radius = 30;
      }
      
      else if(size == 3)
      {
         xCoords = new int[] {12, -12, -32, -43, -43, -32, -12, 12, 32, 43, 43, 32};
         yCoords = new int[] {-43, -43, -32, -12, 12, 32, 43, 43, 32, 12, -12, -32};
         radius = 45;
      }
   }
   
   public void takeDamage()
   {  
      if(this.size > 1)
      {
         split();
      }
      
      this.alive = false;
   }
   
   private void split()
   {
      Main.asteroids.add(new Asteroid(this.x, this.y, this.size - 1, this.speed, this.direction + Main.randomInt(5, 20), true));
      Main.asteroids.add(new Asteroid(this.x, this.y, this.size - 1, this.speed, this.direction - Main.randomInt(5, 20), true));
   }
   
   public void move(int deltaTime)
   {
      this.x += speed * Math.cos(Math.toRadians(direction)) * deltaTime;
      this.y += speed * Math.sin(Math.toRadians(direction)) * deltaTime;
      
      if(x > Main.frameWidth + 75 || x < -75)
      {
         this.alive = false;
      }
      
      if(y > Main.frameHeight + 75 || y < -75)
      {
         this.alive = false;
      }
   }
   
}
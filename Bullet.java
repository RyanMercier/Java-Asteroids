public class Bullet
{
   int x;
   int y;
   boolean alive = true;
   
   private double direction;
   
   final double SPEED = 0.6;
   
   public Bullet(int x, int y, double direction)
   {
      this.x = x;
      this.y = y;
      this.direction = direction;
   }
   
   public void move(int deltaTime)
   {
      x += SPEED * Math.cos(Math.toRadians(direction)) * deltaTime;
      y += SPEED * Math.sin(Math.toRadians(direction)) * deltaTime;
   }
}
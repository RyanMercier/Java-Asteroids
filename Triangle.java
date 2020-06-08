import java.awt.Dimension;

public class Triangle
{
   int[] xCoords;
   int[] yCoords;
   
   public Triangle(int[] _xCoords, int[] _yCoords)
   {
      xCoords = _xCoords;
      yCoords = _yCoords;
   }
   
   public Triangle()
   {
      //Triangle Points
      //(10, 0) (-10, -10) (-10, 10)
      
      xCoords = new int[] {10, -10, -10};
      yCoords = new int[] {0, -10, 10};
   
   }
   
   protected Dimension getTriangleSize() {
      int maxX = 0;
      int maxY = 0;
      
      for (int index = 0; index < xCoords.length; index++)
      {
         maxX = Math.max(maxX, xCoords[index]);
      }
      
      for (int index = 0; index < yCoords.length; index++)
      {
         maxY = Math.max(maxY, yCoords[index]);
      }
    
      return new Dimension(maxX, maxY);
}
}
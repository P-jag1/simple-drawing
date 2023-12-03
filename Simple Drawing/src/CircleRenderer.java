import java.awt.image.BufferedImage;
/**
 * trida s metodou na vykreslovani kruznice
 * @author Petr Jagos
 *
 */
public class CircleRenderer {

	private BufferedImage img;
	
	public CircleRenderer(BufferedImage img) {
		this.img = img;
	}
	
	public void drawCircle(int startX, int startY, int endX, int endY) {
		//vypocet radiusu kruznice
	    int radius=(int)(Math.sqrt((endX-startX)*(endX-startX)+(endY-startY)*(endY-startY)));  
	      
        int pi =  1 - radius;  
        int x = 0; 
        int y = radius;
        int dveX = 3;
        int dveY = 2 * radius - 2;
	      	     	      	        
        while (x <= y){ 
	        //vykreslení 8 symetrickych bodu	
        	img.setRGB((startX+x),(startY+y), 0xff5239);  
            img.setRGB((startX+x),(startY-y), 0x000000);  
            img.setRGB((startX-x),(startY+y), 0x000000);  
            img.setRGB((startX-x),(startY-y), 0xff5239);  
              
            img.setRGB((startX+y),(startY+x), 0x000000);  
            img.setRGB((startX+y),(startY-x), 0xff5239);  
            img.setRGB((startX-y),(startY+x), 0xff5239);  
            img.setRGB((startX-y),(startY-x), 0x000000); 
	            
            if (pi > 0) { 
            	pi = pi - dveY;
            	dveY = dveY - 2;
            	//pokud p > 0, je potreba zmenit hodnotu y
                y = y - 1;
            }
            	pi = pi + dveX;
            	dveX = dveX + 2;	            	 	            	            
            x++;  
        }  
    }  
	

	public void setImg(BufferedImage img) {
		this.img = img;
	}
}

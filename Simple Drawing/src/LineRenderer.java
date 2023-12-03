import java.awt.image.BufferedImage;
/**
 * trida s metodou na vykreslovani primky pomoci trivialniho algoritmu
 * @author Petr Jagos
 *
 */
public class LineRenderer {

	private BufferedImage img;

	public LineRenderer(BufferedImage img) {
		this.img = img;
	}
	
	public void drawLine(int startX, int startY, int endX, int endY) {
		double k = (endY-startY)/(double)(endX-startX);
		double q = startY-k*startX;
		//rozsviti jeden pixel, pokud budou body stejne
		if ((startX == endX) && (startY == endY)) {
	        img.setRGB(startX, startY, 0xff00ff);
		}
		//zjistime ridici osu
		if (Math.abs(k)<1){
			//pokud je ridici osa x
			if (startX > endX) {
				int po = startX;
				startX = endX;
				endX = po;
				po = startY;
				startY = endY;
				endY = po;
			}
				for (int x = startX ; x<=endX ;x++ ) {
					//dopocitavame y  
					int y = (int)(k*x+q); 
					img.setRGB(x, y, 0xff00ff);
			
				}
		} else {
			//pokud je ridici osa y
			if (startY > endY) {
				int po = startX;
				startX = endX;
				endX = po;
				po = startY;
				startY = endY;
				endY = po;
			}
				for (int y = startY ; y<=endY ;y++ ) {
					//dopocitavame x
					int x = (int)((y-q)/k); 
					img.setRGB(x, y, 0xff00ff);
					
				}
			}
		}
	
	public void setImg(BufferedImage img) {
		this.img = img;
	}

}

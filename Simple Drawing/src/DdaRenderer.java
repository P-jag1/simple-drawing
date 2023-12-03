import java.awt.image.BufferedImage;
/**
 * trida s metodou na vykreslovani primky pomoci DDA algoritmu
 * @author Petr Jagos
 *
 */
public class DdaRenderer {

	private BufferedImage img;

	public DdaRenderer(BufferedImage img) {
		this.img = img;
	}
	
public void drawDdaLine(int startX, int startY, int endX, int endY) {		
		double k = (endY-startY)/(double)(endX-startX);
		//rozsviti jeden pixel, pokud budou body stejne
		if ((startX == endX) && (startY == endY)) {
	        img.setRGB(startX, startY, 0xff00ff);
		}
		//zjistime ridici osu
		if (Math.abs(k)<=1){
			//pokud je ridici osa x
			if (startX > endX) {
				int po = startX;
				startX = endX;
				endX = po;
				po = startY;
				startY = endY;
				endY = po;
				
			}
				//ulozime si startY do promene y jako typ double, kvuli scitani s promenou(smernici) k
			    double y = (double) startY;
			    
				for (int x = startX ; x<=endX ;x++ ) {
					//zaokrouhlime y
					int round_y = (int)Math.round(y);
					img.setRGB(x, round_y, 0xff00ff);
					// k y pricteme smernici k a x zvetsime o 1(provadi se v podmince cyklu)
					y = y + k;
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
			    //ulozime si startX do promene y jako typ double, kvuli scitani s promenou(smernici) k
			    double x = (double) startX;
			
				for (int y = startY ; y<=endY ;y++ ) {
					//zaokrouhlime x
					int round_x = (int)Math.round(x);
					img.setRGB(round_x, y, 0xff00ff);
					// k x pricteme  1/k a y zvetsime o 1(provadi se v podmince cyklu)
					x += 1/k;					
				}
			}
		}
	
	public void setImg(BufferedImage img) {
		this.img = img;
	}

}


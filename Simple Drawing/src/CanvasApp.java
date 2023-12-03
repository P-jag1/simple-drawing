
import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class CanvasApp {

	private JFrame frame;
	private JPanel panel;
	private JToolBar tlb;
	private BufferedImage img;
	private MyMouseAdapter mA;
	private int btnIndex;
	private int startX, startY, endX, endY;
	int pom = 0;
	private LineRenderer rendererl;
	private CircleRenderer rendererc;
	private DdaRenderer rendererDda;
	private List<Point> points = new ArrayList<>();

	public class MyMouseAdapter extends MouseAdapter {
		// metoda, ktera definuje co se stane, kdyz uzivatel zmacke tlacitko
		// mysi
		@Override
		public void mousePressed(MouseEvent e) {
			// leve tlacitko mysi
			if (e.getButton() == MouseEvent.BUTTON1) {
				// pokud je podminka splnena, uzivatel kliknul na tlaciko pro
				// vykresleni polygonu
				if (btnIndex == 4) {
					startX = e.getX();
					startY = e.getY();
					// pomocna, ktera slouzi pro ulozeni pocatecniho bodu
					// polygonu
					if (pom == 0) {
						endX = startX;
						endY = startY;
						pom += 1;
					}
					points.add(new Point(startX, startY));
					for (int i = 0; i < points.size() - 1; i++) {
						rendererl.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(),
								points.get(i + 1).getY());
					}
				} else {
					startX = e.getX();
					startY = e.getY();			
				}

			}
			// prave tlacitko mysi
			if (e.getButton() == MouseEvent.BUTTON3) {
				// pokud uzivatel klikne na prave tlacitko mysi, polygon se
				// uzavre a pomocna se vynuluje
				if (btnIndex == 4) {
					rendererl.drawLine(startX, startY, endX, endY);
					pom = 0;
				}
			}
			present();
		}

		// metoda, ktera definuje co nastane, pokud uzivatel pusti tlacit mysi
		public void mouseReleased(MouseEvent e) {
			// leve tlacitko mysi
			if (e.getButton() == MouseEvent.BUTTON1) {
				switch (btnIndex) {
				// vykresli se primka pomoci trivialni algoritmu
				case 1:					
					endX = e.getX();
					endY = e.getY();
					rendererl.drawLine(startX, startY, endX, endY);
					break;
				// vykresli se primka pomoci DDA algoritmu
				case 2:					
					endX = e.getX();
					endY = e.getY();
					rendererDda.drawDdaLine(startX, startY, endX, endY);
					break;
				// vykresli se kruznice pomoci bresenhamova algoritmu
				case 3:
					endX = e.getX();
					endY = e.getY();
					rendererc.drawCircle(startX, startY, endX, endY);
					break;
				default:
					break;
				}
				present();
			}
		}
	}

	public CanvasApp(int width, int height) {
		frame = new JFrame();
		frame.setTitle("PGRF úloha 01");
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		tlb = new JToolBar();
		frame.add(tlb, BorderLayout.NORTH);

		JButton btnLine = new JButton("Pøímka - triviální alg.");
		tlb.add(btnLine);
		btnLine.addActionListener(e -> {
			btnIndex = 1;			
		});

		JButton btnDDALine = new JButton("Pøímka - DDA alg.");
		tlb.add(btnDDALine);
		btnDDALine.addActionListener(e -> {
			btnIndex = 2;
		});
		JButton btnCircle = new JButton("Kružnice");
		tlb.add(btnCircle);
		btnCircle.addActionListener(e -> {
			btnIndex = 3;
		});

		JButton btnPolygon = new JButton("Polygon");
		tlb.add(btnPolygon);
		btnPolygon.addActionListener(e -> {
			btnIndex = 4;
		});

		JButton btnClear = new JButton("Vyèistit Plátno");
		tlb.add(btnClear);
		btnClear.addActionListener(e -> {
			start();
			points.removeAll(points);
			pom = 0;
		});

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		rendererl = new LineRenderer(img);
		rendererc = new CircleRenderer(img);
		rendererDda = new DdaRenderer(img);

		mA = new MyMouseAdapter();
		panel.addMouseListener(mA);

	}

	public void clear(int color) {
		Graphics gr = img.getGraphics();
		gr.setColor(new Color(color));
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}

	public void present() {
		if (panel.getGraphics() != null)
			panel.getGraphics().drawImage(img, 0, 0, null);
	}

	public void draw() {
		clear(0x2f2f2f);
	}

	public void start() {
		draw();
		present();
	}

	public static void main(String[] args) {
		CanvasApp canvas = new CanvasApp(1000, 800);
		SwingUtilities.invokeLater(() -> {
			SwingUtilities.invokeLater(() -> {
				SwingUtilities.invokeLater(() -> {
					SwingUtilities.invokeLater(() -> {
						canvas.start();
					});
				});
			});
		});
	}

}
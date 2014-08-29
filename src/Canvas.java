import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements KeyListener{
	
	public Grid grid;
	public final int width=20;
	public final int height=20;
	private BufferedImage black, white;
	public int x0 = 0;
	public int y0 = 0;
	
	public Canvas(int width, int height, Grid grid, Mouse mouse){
		super();
		this.grid = grid;
		addMouseListener(mouse);
		addKeyListener(this);
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.WHITE);
		black = renderImage(true);
		white = renderImage(false);
	}
	
	private BufferedImage renderImage(boolean b){
		int rgb;
		if (b)
			rgb = Color.BLACK.getRGB();
		else
			rgb = Color.WHITE.getRGB();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++)
				if (y*x==0 || y==height-1 || x==width-1)
					image.setRGB(x, y, Color.GRAY.getRGB());
				else 
					image.setRGB(x, y, rgb);
		return image;
	}
	
	private BufferedImage getImage(boolean b){
		if (b)
			return black;
		return white;
	}
	
	public void paint(Graphics g){
		requestFocusInWindow();
		super.paint(g);
		Dimension dim = getPreferredSize();
		for (int x=0; x<dim.width/width; x++)
			for (int y=0; y<dim.height/height; y++)
				g.drawImage(getImage(grid.get(x+x0, y+y0)), x*width, y*height, width, height, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			x0--; break;
		case KeyEvent.VK_RIGHT:
			x0++; break;
		case KeyEvent.VK_UP:
			y0--; break;
		case KeyEvent.VK_DOWN:
			y0++;
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements KeyListener{
	
	private final Grid grid;
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
	}
	
	private BufferedImage renderImage(boolean b){
		int rgb;
		if (b)
			rgb = Color.BLACK.getRGB();
		else
			rgb = Color.WHITE.getRGB();
		BufferedImage image = new BufferedImage(grid.size, grid.size, BufferedImage.TYPE_INT_ARGB);
		for (int x=0; x<grid.size; x++)
			for (int y=0; y<grid.size; y++)
				if (y*x==0 || y==grid.size-1 || x==grid.size-1)
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
		black = renderImage(true);
		white = renderImage(false);
		Dimension dim = getPreferredSize();
		for (int x=0; x<dim.width/grid.size; x++)
			for (int y=0; y<dim.height/grid.size; y++)
				g.drawImage(getImage(grid.get(x+x0, y+y0)), x*grid.size, y*grid.size, null);
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
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

}

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private Canvas canvas;
	
	public View(int width, int height, PanelBar panel, Grid grid, Mouse mouse, Keyboard key){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setResizable(false);
		addKeyListener(key);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		canvas = new Canvas(width, height, grid, mouse, key);
		add(canvas, BorderLayout.NORTH);
		pack();
		setVisible(true);
		requestFocusInWindow();
	}
	
	public void update(Grid grid){
		canvas.grid = grid;
		requestFocusInWindow();
		repaint();
	}

}

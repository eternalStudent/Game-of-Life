import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private Canvas canvas;
	
	public View(int width, int height, PanelBar panel, Grid grid, Mouse mouse){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setResizable(false);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		canvas = new Canvas(width, height, grid, mouse);
		add(canvas, BorderLayout.NORTH);
		pack();
		setVisible(true);
	}
	
	public int getX(){
		if (canvas == null)
			return 0;
		return canvas.x0;
	}
	
	public int getY(){
		if (canvas == null)
			return 0;
		return canvas.y0;
	}

}

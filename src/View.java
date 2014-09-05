import javax.swing.JFrame;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private Canvas canvas;
	
	public View(int width, int height, MenuBar menuBar, Grid grid, Mouse mouse){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setResizable(false);
		setJMenuBar(menuBar);
		setContentPane(canvas = new Canvas(width, height, grid, mouse));
		setVisible(true);
		pack();
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

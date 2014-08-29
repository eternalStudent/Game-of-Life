import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameOfLife implements Runnable, ActionListener {
	
	private final Mouse mouse = new Mouse();
	private final Grid grid = new Grid();
	private Grid next;
	private final PanelBar panel;
	private final View view;
	private final Timer timer = new Timer(100, this);
	private boolean go = false;
	
	public static void main(String[] args){
		new GameOfLife();
	}
	
	private GameOfLife(){
		panel = new PanelBar(this);
		view = new View(800, 800, panel, grid, mouse);
		timer.setInitialDelay(50);
		new Thread(this).start();
	}
	
	public void run(){
		while (true){
			while(!go){
				if (!mouse.queue.isEmpty()){
					Point point = mouse.queue.remove();
					point.x = point.x/grid.width;
					point.y = point.y/grid.height;
					grid.set(point.x+view.getX(), point.y+view.getY(),!grid.get(point.x,point.y));
					view.repaint();
				}
			}
		}
	}
	
	private Grid next(){
		Grid temp = new Grid();
		temp.CopyGrid(grid);
		for (Point p: grid.actionZone()){
			boolean b= grid.get(p.x, p.y);
			int count = grid.count(p.x,p.y);
			if (b && (count<2 || count> 3))
				temp.set(p.x, p.y, false);
			if (!b && count==3)
				temp.set(p.x, p.y, true);	
		}
		return temp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == null)
			cmd = "";
		if (cmd.equals("Go!")){
			go = !go;
			if (!go)
				timer.stop();
			else{
				next = next();
				timer.start();
			}
		}	
		if (!go){
			if (cmd.equals("Clear")){
				grid.clear();
			}
			if (cmd.equals("Random")){
				grid.randomGrid(40, 40);
			}
			view.repaint();
		}
		else{
			if (grid.equals(next)){
				panel.go();
				go = false;
			}
			else{
				grid.CopyGrid(next);
				view.repaint();
				next.CopyGrid(next());
			}	
		}	
	}

}

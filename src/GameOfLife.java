import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

import controller.Mouse;
import model.Grid;
import util.LifeFilter;
import view.Board;
import view.MenuBar;

public class GameOfLife implements Runnable, ActionListener {
	
	private final Mouse mouse = new Mouse();
	private final Grid grid = new Grid();
	private final MenuBar menuBar;
	private final Board board;
	private final Timer timer = new Timer(100, this);
	
	public static void main(String[] args){
		new GameOfLife();
	}
	
	private GameOfLife(){
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
		catch (Exception e) { e.printStackTrace(); }
		menuBar = new MenuBar(this);
		board = new Board(840, 840, menuBar, grid, mouse);
		timer.setInitialDelay(50);
		new Thread(this).start();
	}
	
	public void run(){
		while (true){
			while(!timer.isRunning()){
				if (!mouse.queue.isEmpty()){
					Point point = mouse.queue.remove();
					grid.negation(point.x/grid.size+grid.x0, point.y/grid.size+grid.y0);
					board.repaint();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmd = ae.getActionCommand();
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new LifeFilter());
		chooser.setAcceptAllFileFilterUsed(false);
		if (cmd == null){
			Grid next = grid.next();
			if (grid.equals(next))
				timer.stop();
			else
				grid.copy(next);
		}	
		else{
			if (cmd.equals("Go") && !timer.isRunning())
				timer.start();
			if (cmd.equals("Stop") && timer.isRunning())
				timer.stop();
			if (cmd.equals("Step") && !timer.isRunning())
				grid.copy(grid.next());
			if (cmd.equals("Set Delay")){
				String str = (String) JOptionPane.showInputDialog(menuBar, "Set the delay in milliseconds:", "Set Delay", JOptionPane.PLAIN_MESSAGE, null, null, Integer.toString(timer.getDelay()));
				try{timer.setDelay(Integer.parseInt(str));}
				catch(Exception e){}				
			}	
			if (cmd.equals("Clear")){
				timer.stop();
				grid.clear();
			}
			if (cmd.equals("Save")){
				if (chooser.showSaveDialog(board.getContentPane()) == JFileChooser.APPROVE_OPTION) {
	                File file = chooser.getSelectedFile();
	                try{
	                	grid.writeFile(file);
	                	board.setTitle(file.getName());
	                }catch(Exception e){}
				}    
			}
			if (cmd.equals("Open")){
				if (chooser.showSaveDialog(board.getContentPane()) == JFileChooser.APPROVE_OPTION) {
	                File file = chooser.getSelectedFile();
	                try{
	                	grid.readFile(file);
	                	board.setTitle(file.getName());
	                }catch(Exception e){}
				}    
			}
			if (cmd.matches("\\d{1,2}"))
				grid.size=Integer.parseInt(cmd);	
		}
		board.repaint();
	}

}

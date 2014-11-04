package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	private ActionListener listener;
	
	public boolean go = false;
	public boolean clear=false;
	public boolean random=false;
	public int x=0;
	public int y=0;
	
	public MenuBar(ActionListener al){
		super();
		listener = al;
		JMenu file = new JMenu("File");
		JMenu run = new JMenu("Run");
		JMenu size = new JMenu("Size");
		add(file);
		add(run);
		add(size);
		addMenuItem(file, "Clear", KeyEvent.VK_N);
		addMenuItem(file, "Save", KeyEvent.VK_S);
		addMenuItem(file, "Open", KeyEvent.VK_O);
		addMenuItem(run, "Go", 0);
		addMenuItem(run, "Stop", 0);
		addMenuItem(run, "Step", 0);
		addMenuItem(run, "Set Delay", 0);
		addMenuItem(size, "8", 0);
		addMenuItem(size, "10", 0);
		addMenuItem(size, "12", 0);
		addMenuItem(size, "15", 0);
		addMenuItem(size, "20", 0);
		addMenuItem(size, "24", 0);
		addMenuItem(size, "28", 0);
	}
	
	private void addMenuItem(JMenu parent, String text, int key){
		JMenuItem item = new JMenuItem(text);
		item.setActionCommand(text);
	    item.addActionListener(listener);
	    if (key !=0)
	    	item.setAccelerator(KeyStroke.getKeyStroke(key, ActionEvent.CTRL_MASK));
		parent.add(item);
	}	

}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBar extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private final JButton goButton = new JButton("Go!");
	
	private ActionListener listener;
	
	public boolean go = false;
	public boolean clear=false;
	public boolean random=false;
	public int x=0;
	public int y=0;
	
	public PanelBar(ActionListener a){
		super();
		listener = a;
		goButton.setActionCommand("Go!");
		goButton.addActionListener(this);
		goButton.addActionListener(listener);
		add(goButton);
		addButton("Clear");
		addButton("Random");
		setBounds(0,0,720,36);
	}
	
	public void addButton(String str){
		JButton button = new JButton(str);
		button.setActionCommand(str);
		button.addActionListener(listener);
		add(button);
	}
	
	public void go() {
		go = !go;
		if (go)
			goButton.setText("STOP!");
		else
			goButton.setText("GO!");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		go();
	}	

}

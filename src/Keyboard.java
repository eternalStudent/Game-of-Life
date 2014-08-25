import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	public int x=0;
	public int y=0;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			x--; break;
		case KeyEvent.VK_RIGHT:
			x++; break;
		case KeyEvent.VK_UP:
			y--; break;
		case KeyEvent.VK_DOWN:
			y++;
		}
		
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

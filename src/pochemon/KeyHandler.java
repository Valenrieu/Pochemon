package pochemon;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if(code==KeyEvent.VK_Z) {
			upPressed = true;
		} if(code==KeyEvent.VK_S) {
			downPressed = true;
		} if(code==KeyEvent.VK_Q) {
			leftPressed = true;
		} if(code==KeyEvent.VK_D) {
			rightPressed = true;
		} if(code==KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if(code==KeyEvent.VK_Z) {
			upPressed = false;
		} if(code==KeyEvent.VK_S) {
			downPressed = false;
		} if(code==KeyEvent.VK_Q) {
			leftPressed = false;
		} if(code==KeyEvent.VK_D) {
			rightPressed = false;
		} if(code==KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
	}
}

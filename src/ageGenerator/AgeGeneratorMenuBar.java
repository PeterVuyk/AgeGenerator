package ageGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class AgeGeneratorMenuBar extends JMenuBar
{
    private static final String OPTIONS = "Options";
    private static final String QUIT = "Quit program";
    
    JMenuItem resetResult, closeFrame;
    
    public AgeGeneratorMenuBar() {
	JMenu options = new JMenu(OPTIONS);
	options.setMnemonic(KeyEvent.VK_O);
	MenuBarListener menuBarListener = new MenuBarListener();
		
	this.closeFrame = new JMenuItem(QUIT);
	options.add(this.closeFrame);
	this.closeFrame.setMnemonic(KeyEvent.VK_Q);
	this.closeFrame.addActionListener(menuBarListener);
	
	add(options);
    }
    
    private class MenuBarListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == closeFrame) {
		System.exit(0);
	    }
	}
    }
}

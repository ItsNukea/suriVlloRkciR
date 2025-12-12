package com.rickrollvirus;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static JFrame window = new JFrame("Get Rickrolled");
	
	static void main() {
		SwingUtilities.invokeLater(() -> {
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Change to JFrame.DO_NOTHING_ON_CLOSE when the virus is ready
			setFullScreen(window);
			Rick.roll();
			window.setVisible(true);
		});
		
	}
	
	private static void setFullScreen(JFrame window) {
		GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.setFullScreenWindow(window);
	}
}

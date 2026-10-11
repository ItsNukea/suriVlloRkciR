package com.rickrollvirus;

import javafx.application.*;
import javafx.embed.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
	public static JFrame window = new JFrame("Get Rickrolled");
	public static Timer fullscreenMonitor;
	
	static void main() {
		Platform.startup(() -> {});
		AltTabHooker.init();
		SwingUtilities.invokeLater(() -> {
			window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			window.addWindowFocusListener(new LockedInFullScreenWindow());
			window.setAlwaysOnTop(true);
			window.setUndecorated(true);
			setFullscreenMonitor();
			Rick.roll();
		});
		Runtime.getRuntime().addShutdownHook(new Thread(AltTabHooker::finish));
	}
	
	public static void setFullScreen() {
		GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.setFullScreenWindow(window);
	}

	public static void setFullscreenMonitor() {
		fullscreenMonitor = new Timer(10, _ ->
			SwingUtilities.invokeLater(() -> {
				GraphicsDevice gd = GraphicsEnvironment //Holy Geometry Dash Reference
						.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice();

				if (gd.getFullScreenWindow() != window) {
					gd.setFullScreenWindow(window);
				}

				if (window.getState() != Frame.NORMAL) {
					window.setState(Frame.NORMAL);
				}

				if (!window.isAlwaysOnTop()) {
					window.setAlwaysOnTop(true);
				}

				if (!window.isFocused()) {
					window.toFront();
					window.requestFocus();
				}

				Component[] components = window.getContentPane().getComponents();
				if (components.length > 0 && components[0] instanceof JFXPanel) {
					if (!components[0].isFocusOwner()) {
						components[0].requestFocusInWindow();
					}
				}
			})
		);
	}

	private static class LockedInFullScreenWindow extends WindowAdapter {
		public LockedInFullScreenWindow() {}

		@Override
		public void windowDeactivated(WindowEvent e) {
			window.toFront();
			window.requestFocus();
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			window.toFront();
			window.requestFocus();
		}
	}
}
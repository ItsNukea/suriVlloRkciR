package com.rickrollvirus;

import javafx.application.*;
import javafx.embed.swing.*;
import javafx.scene.*;
import javafx.scene.media.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class Rick {
	private static JFXPanel panel;

	public static void roll() {
		SwingUtilities.invokeLater(() -> {
			panel = new JFXPanel();
			Main.window.setLayout(new BorderLayout());
			Main.window.add(panel, BorderLayout.CENTER);
			
			Platform.runLater(() -> {
				try {
					File temp = Files.createTempFile("NGGYU", ".mp4").toFile();
					temp.deleteOnExit();

					copyResourceToFile(temp);
					createPlayer(temp.toURI().toString());
				} catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			});
		});
	}

	private static void copyResourceToFile(File destination) throws IOException {
		try(
				InputStream in = Rick.class.getResourceAsStream("/rickroll/NGGYU.mp4");
				OutputStream out = new FileOutputStream(destination)
		) {
			assert in != null;

			byte[] buffer = new byte[8192]; // Larger buffer for faster copy
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
	}

	private static void createPlayer(String uri) {
		Media media = new Media(uri);
		MediaPlayer player = new MediaPlayer(media);
		player.setCycleCount(MediaPlayer.INDEFINITE);
		MediaView view = new MediaView(player);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Group root = new Group(view);
		Scene scene = new Scene(root, screenSize.width, screenSize.height);

		panel.setScene(scene);
		player.setOnReady(() -> {
			SwingUtilities.invokeLater(() -> {
				Main.window.setVisible(true);
				Main.setFullScreen();
				Main.fullscreenMonitor.start();
			});
			player.play();
		});
	}
}
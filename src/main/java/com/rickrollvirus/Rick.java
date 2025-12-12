package com.rickrollvirus;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.media.*;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class Rick {
	private static final Path path = Paths.get("src/main/resources/rickroll/NGGYU.mp4");
	
	public static void roll() {
		SwingUtilities.invokeLater(() -> {
			JFXPanel panel = new JFXPanel();
			Main.window.setLayout(new BorderLayout());
			Main.window.add(panel, BorderLayout.CENTER);
			
			Platform.runLater(() -> {
				
				try {
					String uri = path.toUri().toString();
					Media media = new Media(uri);
					MediaPlayer player = new MediaPlayer(media);
					player.setCycleCount(MediaPlayer.INDEFINITE);
					MediaView view = new MediaView(player);
					view.setPreserveRatio(true);
					view.setFitHeight(1080);
					view.setSmooth(false);
					
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					Group root = new Group(view);
					Scene scene = new Scene(root, screenSize.width, screenSize.height);
					
					panel.setScene(scene);
					player.play();
				} catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			});
		});
	}
	
	private static void initJavaFX() {
		try {
			new JFXPanel();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

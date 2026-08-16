package com.rickrollvirus;

import javafx.application.Platform;
import javafx.embed.swing.*;
import javafx.scene.*;
import javafx.scene.media.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.jar.*;

public class Rick {
	public static void roll() {
		SwingUtilities.invokeLater(() -> {
			JFXPanel panel = new JFXPanel();
			Main.window.setLayout(new BorderLayout());
			Main.window.add(panel, BorderLayout.CENTER);
			
			Platform.runLater(() -> {
				try(InputStream in = Rick.class.getResourceAsStream("/rickroll/NGGYU.mp4")) {
					File temp = Files.createTempFile("NGGYU", ".mp4").toFile();
					try(OutputStream out = new FileOutputStream(temp)) {
						in.transferTo(out);
					}
					temp.deleteOnExit();
					String uri = temp.toURI().toString();
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
}
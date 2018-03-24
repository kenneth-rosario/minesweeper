import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;

public class MusicPlayer extends Thread {
	private String filename;
	private boolean loop;
	private Player newplayer;
	
	//constructor. it takes a sound files name and a boolean expression as parameters.
	public MusicPlayer(String filename,boolean loop) {
		this.filename = filename;
		this.loop = loop;
	}
	
	//a method from the Thread class that creates a new Player and loops it using the boolean expression.
	public void run() {
		try {
			do {
				FileInputStream newf = new FileInputStream(filename);
				newplayer = new Player(newf);
			    newplayer.play();
			}while(loop);
		}catch(Exception ioe) {
			//TODO error handling
		}
	}
	
	
}

package com.br.ajf.imagesfx.main;

import com.br.ajf.imagesfx.scene.ImageScene;

import br.com.ajf.game.model.Game;
import br.com.ajf.game.scene.Scene;
import br.com.ajf.game.thread.IGameThreadManager;
import br.com.ajf.game.util.CSVString;

public final class Main
{
	private static final CSVString config =new CSVString("res/config/game.csv");
	
	public static int HEIGHT ;
	public static int WIDTH ;
	
	private Main()
	{
		
	}
	
	public static void main(String[] args)
	{
//		System.setProperty("sun.java2d.translaccel", "true");
//		System.setProperty("sun.java2d.ddforcevram", "true");
//		System.setProperty("sun.java2d.opengl","true");
		
		String[] conf = config.getRowDataById(1);
		
		WIDTH = Integer.parseInt(conf[1]);
		HEIGHT = Integer.parseInt(conf[2]);
		
		Game game = new Game(conf[0], WIDTH, HEIGHT, 
				IGameThreadManager.GAME_THREAD_RUNNABLE);
		
		game.setIcon(conf[4]);
		
		Scene imageScene = new ImageScene(game);	
		game.addScene(imageScene);
		
//		Scene scene = new CollisionScene();
//		game.addScene(scene);
		
		game.init(IGameThreadManager.FPS_60);
	}
}
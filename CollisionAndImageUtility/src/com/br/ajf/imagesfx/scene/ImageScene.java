package com.br.ajf.imagesfx.scene;

import java.awt.Graphics2D;
import java.awt.Image;

import com.br.ajf.imagesfx.main.Main;

import br.com.ajf.game.button.GameButton;
import br.com.ajf.game.button.IGameButton;
import br.com.ajf.game.image.ImageLoader;
import br.com.ajf.game.model.Game;
import br.com.ajf.game.scene.Scene;

public class ImageScene implements Scene
{
	Game game;
	IGameButton testing;
	ImageLoader imgLoader;
	Image img;
	
	private TransitionScene transition = new TransitionScene(
			new ImageLoader().loadImage("/img/transition.gif")
			,30,0.009f,"Scene Test",Main.WIDTH,Main.HEIGHT);

	private Scene test;
	
	public ImageScene(Game game)
	{
		this.game = game;
	}

	@Override
	public void start()
	{
		transition.start();
		
		imgLoader = new ImageLoader();
		
		img = imgLoader.loadImage("/img/monsteranimation.gif");
		
		test = new SceneTest(game,imgLoader);
		test.start();
		
		testing = new GameButton("Testing", 620, 520, 90, 50, 18, 
		() ->
		{
			System.out.println(" Transition testing!");
			
			transition.start();
			
		});	
	}

	@Override
	public void update()
	{
		if(!transition.isFinished())
		{
			transition.update();
		}
		else if(transition.isFinished())
		{
			testing.update();	
			test.update();
		}
	}
	
	@Override
	public void draw(Graphics2D arg0)
	{	
		if(test != null)
		{
			test.draw(arg0);
			testing.draw(arg0);
		}
		
		if(!transition.isFinished())
		{
			transition.draw(arg0);
		}
	}
}
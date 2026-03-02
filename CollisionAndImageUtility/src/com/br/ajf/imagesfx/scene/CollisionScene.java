package com.br.ajf.imagesfx.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.br.ajf.imagesfx.main.Main;
import com.br.ajf.imagesfx.math.Vector2I;

import br.com.ajf.game.input.GameInput;
import br.com.ajf.game.math.GameRect;
import br.com.ajf.game.moviment.FourDirections;
import br.com.ajf.game.scene.Scene;

public final class CollisionScene implements Scene
{
	private List<GameRect> rects = new ArrayList<>();
	private GameRect rect = new GameRect(0,0,64,64);
	
	private Vector2I vel = new Vector2I(6,6);
	private Vector2I pos = new Vector2I();
	
	private FourDirections direction = FourDirections.DOWN;

	@Override
	public void start()
	{
		rect.setX(100);
		rect.setY(100);
		pos.set(rect.getX(), rect.getY());
		
		rects.add(new GameRect(0,Main.HEIGHT-64,Main.WIDTH,64));
		rects.add(new GameRect(0,0,64,Main.WIDTH-64));
		rects.add(new GameRect(Main.WIDTH-64,0,Main.WIDTH-64,Main.HEIGHT-64));
	}

	@Override
	public void update()
	{	
		if(GameInput.keyDown(KeyEvent.VK_UP))
		{
			pos.setY(pos.getY()-vel.getY());
			direction = FourDirections.UP;
		}
		else if(GameInput.keyDown(KeyEvent.VK_DOWN))
		{
			pos.setY(pos.getY()+vel.getY());
			direction = FourDirections.DOWN;
		}
		else if(GameInput.keyDown(KeyEvent.VK_RIGHT))
		{
			pos.setX(pos.getX()+vel.getX());
			direction = FourDirections.RIGHT;
		}
		else if(GameInput.keyDown(KeyEvent.VK_LEFT))
		{
			pos.setX(pos.getX()-vel.getX());
			direction = FourDirections.LEFT;
		}
		
		if(isCollision(pos, rect, rects))
		{
			switch(direction)
			{
				case  FourDirections.UP:
					pos.setY(pos.getY()+vel.getY());
					break;
					
				case  FourDirections.DOWN:
					pos.setY(pos.getY()-vel.getY());
					break;
					
				case  FourDirections.RIGHT:
					pos.setX(pos.getX()-vel.getX());
					break;
					
				case  FourDirections.LEFT:
					pos.setX(pos.getX()+vel.getX());
					break;
			}
		}
	}
	
	public static boolean isCollision(Vector2I pos,GameRect rect,List<GameRect> rects)
	{
		for (GameRect gameRect : rects)
		{
			if(gameRect.intersects(pos.getX(), pos.getY(), rect.getWidth(), rect.getHeight()))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCollision(Character character,List<Collider> rects)
	{	
		character.collider.setX(character.characterMoviment.position.getX() + 
				character.getContainer().solidAreaX);
		character.collider.setY(character.characterMoviment.position.getY() + 
				character.getContainer().solidAreaY);
		
		for (Collider objectCollider : rects)
		{
			if(character.collider.getLayer() == objectCollider.getLayer())
			{
				if(character.collider.intersects(objectCollider))
				{
					
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics2D graphics2d)
	{
		graphics2d.setColor(Color.blue);
		graphics2d.fillRect(this.pos.getX(), this.pos.getY(),
				this.rect.getWidth(), this.rect.getHeight());
		
		graphics2d.setColor(Color.RED);
		for (GameRect rect : rects)
		{
			graphics2d.fillRect(rect.getX(),rect.getY(),
					rect.getWidth(),rect.getHeight());
		}
	}

}
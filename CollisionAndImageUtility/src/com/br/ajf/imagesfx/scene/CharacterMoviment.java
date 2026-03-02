package com.br.ajf.imagesfx.scene;

import com.br.ajf.imagesfx.math.Vector2I;

import br.com.ajf.game.gameobject.AbstractGameObjectContainer;
import br.com.ajf.game.math.GameRect;
import br.com.ajf.game.moviment.EightDirections;

public class CharacterMoviment
{
	public final CharacterInputs data;
	public  final Vector2I position = new Vector2I(0, 0);
	public  final Vector2I velocity = new Vector2I(200,200);
	public final AbstractGameObjectContainer container;

	public CharacterMoviment(CharacterInputs data,AbstractGameObjectContainer container)
	{
		this.data = data;
		this.container = container;
		this.container.solidArea = new GameRect(0, 0,32,32);
	}
	
	private void standing()
	{
		switch(data.direction)
		{
			case EightDirections.UP:
				container.animations.setAnimationByIndex(8);
				break;
			case EightDirections.UP_RIGHT:
				container.animations.setAnimationByIndex(10);
				break;
			case EightDirections.UP_LEFT:
				container.animations.setAnimationByIndex(9);
				break;
			case EightDirections.DOWN_RIGHT:				
				container.animations.setAnimationByIndex(13);
				break;
			case EightDirections.DOWN_LEFT:
				container.animations.setAnimationByIndex(12);
				break;
			case EightDirections.DOWN:
				container.animations.setAnimationByIndex(11);
				break;
			case EightDirections.LEFT:
				container.animations.setAnimationByIndex(14);
				break;
			case EightDirections.RIGHT:
				container.animations.setAnimationByIndex(15);
				break;
		}
	}

	private void moving(float delta)
	{
		switch(data.direction)
		{
			case EightDirections.UP:
				position.setY((int)(position.getY() - velocity.getY()));
				container.animations.setAnimationByIndex(EightDirections.UP.ordinal());
	
				break;
			case EightDirections.UP_RIGHT:
				position.add((int)(velocity.getX()),(int)-(velocity.getY()));
				container.animations.setAnimationByIndex(EightDirections.UP_RIGHT.ordinal());

				break;
			case EightDirections.UP_LEFT:
				position.add(-(int)(velocity.getX()),-(int)(velocity.getY()));
				container.animations.setAnimationByIndex(EightDirections.UP_LEFT.ordinal());
		
				break;
			case EightDirections.DOWN:
				position.setY((int)(position.getY() + velocity.getY()));
				container.animations.setAnimationByIndex(EightDirections.DOWN.ordinal());
	
				break;
			case EightDirections.DOWN_RIGHT:
				position.add((int)(velocity.getX()),(int)(velocity.getY()));						
				container.animations.setAnimationByIndex(EightDirections.DOWN_RIGHT.ordinal());
		
				break;
			case EightDirections.DOWN_LEFT:
				position.add(-(int)(velocity.getX()),(int)(velocity.getY()));
				container.animations.setAnimationByIndex(EightDirections.DOWN_LEFT.ordinal());
		
				break;
			case EightDirections.LEFT:
				position.setX((int)(position.getX() - velocity.getX()));
				container.animations.setAnimationByIndex(EightDirections.LEFT.ordinal());
	
				break;
			case EightDirections.RIGHT:
				position.setX(position.getX() + (int)(velocity.getX()));
				container.animations.setAnimationByIndex(EightDirections.RIGHT.ordinal());
				
				break;
		}
	}
	
	public void prevent()
	{
		switch(data.direction)
		{
			case EightDirections.UP:
				position.setY((int)(position.getY() + velocity.getY()));
	
				break;
			case EightDirections.UP_RIGHT:
				position.add((int)-(velocity.getX()),(int)(velocity.getY()));

				break;
			case EightDirections.UP_LEFT:
				position.add((int)(velocity.getX()),(int)(velocity.getY()));
				break;
			case EightDirections.DOWN:
				position.setY((int)(position.getY() - velocity.getY()));
				break;
			case EightDirections.DOWN_RIGHT:
				position.add(-(int)(velocity.getX()),-(int)(velocity.getY()));						
				break;
			case EightDirections.DOWN_LEFT:
				position.add((int)(velocity.getX()),-(int)(velocity.getY()));
				break;
			case EightDirections.LEFT:
				position.setX((int)(position.getX() + velocity.getX()));
				break;
			case EightDirections.RIGHT:
				position.setX(position.getX() - (int)(velocity.getX()));
				break;
		}
	}
	
	public void update(float delta)
	{
		if(data.moving)
		{
			moving(delta);
			
		}
		else
		{
			standing();
		}
		
		this.container.animations.update();
	}
}
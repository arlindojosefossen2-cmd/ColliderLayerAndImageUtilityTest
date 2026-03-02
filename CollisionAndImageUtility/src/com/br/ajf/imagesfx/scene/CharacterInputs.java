package com.br.ajf.imagesfx.scene;

import java.awt.event.KeyEvent;

import br.com.ajf.game.input.GameInput;
import br.com.ajf.game.moviment.EightDirections;

public class CharacterInputs
{
	public EightDirections direction = EightDirections.DOWN;
	private Character character;
	public boolean moving;

	public CharacterInputs(Character character)
	{
		this.character = character;
	}
	
	public void updateInputs()
	{
		//testing change layer
		if(GameInput.keyDownOnce(KeyEvent.VK_N))
		{
			character.collider.setLayer(1);
		}
		
		if(isUpRight())
		{
			 direction = EightDirections.UP_RIGHT;
			 moving = true;
		}
		else if(isUpLeft())
		{
			 direction = EightDirections.UP_LEFT;
			 moving = true;	
		}
		else if(isDownRight())
		{
			 direction = EightDirections.DOWN_RIGHT;
			 moving = true;	
		}
			else if(isDownLeft())
		{
			 direction = EightDirections.DOWN_LEFT;
			 moving = true;	
		}
		else if(isUp())
		{
			 direction = EightDirections.UP;
			 moving = true;
		}
		else if(isDown())
		{
			direction = EightDirections.DOWN;
			moving = true;
		}
		else if(isLeft())
		{
			 direction = EightDirections.LEFT;
			 moving = true;
		}
		else if(isRight())
		{
			 direction = EightDirections.RIGHT;
			 moving = true;
		}
		else
		{
			moving = false;
		}
	}

	private boolean isRight()
	{
		return GameInput.keyDown(KeyEvent.VK_D) || GameInput.keyDown(KeyEvent.VK_RIGHT);
	}

	private boolean isLeft()
	{
		return GameInput.keyDown(KeyEvent.VK_A) || GameInput.keyDown(KeyEvent.VK_LEFT);
	}

	private boolean isDown()
	{
		return GameInput.keyDown(KeyEvent.VK_S) || GameInput.keyDown(KeyEvent.VK_DOWN);
	}

	private boolean isUp()
	{
		return GameInput.keyDown(KeyEvent.VK_W) || GameInput.keyDown(KeyEvent.VK_UP);
	}

	private boolean isDownLeft()
	{
		return GameInput.keyDown(KeyEvent.VK_S) && GameInput.keyDown(KeyEvent.VK_A) ||
				GameInput.keyDown(KeyEvent.VK_LEFT) && GameInput.keyDown(KeyEvent.VK_DOWN);
	}

	private boolean isDownRight()
	{
		return GameInput.keyDown(KeyEvent.VK_S) && GameInput.keyDown(KeyEvent.VK_D) ||
					GameInput.keyDown(KeyEvent.VK_RIGHT) && GameInput.keyDown(KeyEvent.VK_DOWN);
	}

	private boolean isUpLeft()
	{
		return GameInput.keyDown(KeyEvent.VK_W) && GameInput.keyDown(KeyEvent.VK_A) ||
					GameInput.keyDown(KeyEvent.VK_LEFT) && GameInput.keyDown(KeyEvent.VK_UP);
	}

	private boolean isUpRight()
	{
		return GameInput.keyDown(KeyEvent.VK_W) && GameInput.keyDown(KeyEvent.VK_D) ||
				GameInput.keyDown(KeyEvent.VK_RIGHT) && GameInput.keyDown(KeyEvent.VK_UP);
	}
}
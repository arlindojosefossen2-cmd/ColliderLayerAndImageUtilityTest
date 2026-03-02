package com.br.ajf.imagesfx.math;

public final class Vector2I
{
	private int x;
	private int y;
	
	public Vector2I()
	{
		x = 1;
		y = 1;
	}
	
	public Vector2I(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	
	public Vector2I add(int value)
	{
		
		this.x += value;
		this.y += value;
		
		return this;
	}
	
	public Vector2I add(int x,int y)
	{
		
		this.x += x;
		this.y += y;
		
		return this;
	}
	
	public Vector2I add(Vector2I vec)
	{
		
		this.x += vec.x;
		this.y += vec.y;
		
		return this;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}

	public void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
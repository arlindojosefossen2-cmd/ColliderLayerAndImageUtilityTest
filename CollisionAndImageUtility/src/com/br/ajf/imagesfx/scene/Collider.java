package com.br.ajf.imagesfx.scene;

public final class Collider
{
	private final int id;
	private int layer;
	
	private int x;
	private int y;
	private final int width;
	private final int height;
	
	private final String type;

	public Collider(final int id,final int layer,final int x,final int y,
			final int width,final int height,final String type)
	{
		this.id = id;
		this.layer = layer;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}

	public int getId()
	{
		return id;
	}

	public int getLayer()
	{
		return layer;
	}
	
	public void setLayer(int layer)
	{
		this.layer = layer;
	}
	

	public int getX()
	{
		return x;
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

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public String getType()
	{
		return type;
	}

	 /**
     * Intersects.
     *
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     * @return true, if successful
     */
    public boolean intersects(final double x,final double y,final double w,final double h) 
    {
        if (w <= 0 || h <= 0) 
        {
            return false;
        }
        
        final double x0 = getX();
        final double y0 = getY();
        
        return  x + w > x0 &&
                y + h > y0 &&
                x < x0 + getWidth() &&
                y < y0 + getHeight();
    }

    /**
	 * Intersects.
	 *
	 * @param solidArea the solid area
	 * @return true, if successful
	 */
	public boolean intersects(final Collider solidArea)
	{
		return intersects(solidArea.getX(), solidArea.getY(), solidArea.getWidth(), solidArea.getHeight());
	}
    
}
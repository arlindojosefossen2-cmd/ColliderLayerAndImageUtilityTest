package com.br.ajf.imagesfx.scene;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import br.com.ajf.game.audio.wav.IAudio;
import br.com.ajf.game.audio.wav.SoundEFX;
import br.com.ajf.game.constant.GameConstants;
import br.com.ajf.game.scene.Scene;

public class TransitionScene implements Scene
{
	private int interval;
	private float value ;
	private float counter ;
	
	private int width = -1;
	private int height = -1;
	private FontMetrics metrics;
	
	private int imgWidth ;
	private int imgHeight ;
	
	private boolean fade;
	private boolean finished;

	private int counterinterval;	
	
	private IAudio stairs;
	
	private Image img ;
	
	public String name;
	
	public TransitionScene(int maxInterval,float value,String name,int width,int height)
	{
		this.interval = maxInterval;
		this.value = value;
		this.name = name;	
		this.imgWidth = width;
		this.imgHeight = height;
		BufferedImage aux = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = aux.createGraphics();	
		g.setColor(new Color(27, 36, 71));
		g.fillRect(0, 0, aux.getWidth(), aux.getHeight());	
		g.dispose();
		this.img = aux;
	}
	
	public TransitionScene(Image img,int maxInterval,float value,String name,int width,int height)
	{
		this.interval = maxInterval;
		this.value = value;
		this.name = name;	
		this.imgWidth = width;
		this.imgHeight = height;
		this.img = img;
	}
	
	public TransitionScene(int maxInterval,float value,String name,Color transitionColor,int width,int height)
	{
		this.interval = maxInterval;
		this.value = value;
		this.name = name;	
		this.imgWidth = width;
		this.imgHeight = height;
		BufferedImage aux = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);	
		Graphics2D g = aux.createGraphics();		
		g.setColor(transitionColor);
		g.fillRect(0, 0, aux.getWidth(), aux.getHeight());	
		g.dispose();
		this.img = aux;
	}

	@Override
	public void start()
	{
		stairs = new SoundEFX("/sounds/stairs.wav");
		counter = 1f;
		fade = true;
		finished = false;
		stairs.play();
	}
	
	@Override
	public void update()
	{
		if(counterinterval < interval)
			counterinterval++;
			
		if(counterinterval >= interval)
		{
			if(fade)
			{
				counter -= value;
					
				if(counter <= 0f)
				{
					fade = false;
					counterinterval = 0;
					counter = 0f;
					finished = true;
					stairs.stop();
				}
			}
		}	
	}
	
	@Override
	public void draw(Graphics2D arg0)
	{	
		if(metrics == null)
		{
			metrics = arg0.getFontMetrics(arg0.getFont().deriveFont(GameConstants.TILESIZE));
		}
		
		if(fade)
			arg0.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,counter));	
		
		arg0.drawImage(img, 0, 0,imgWidth,imgHeight, null);
		arg0.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		arg0.setFont(arg0.getFont().deriveFont(GameConstants.TILESIZE));
		arg0.setColor(Color.WHITE);	
		
		if(width == -1 && height == -1)
		{
			width = (int)metrics.getStringBounds(name, arg0).getWidth();
			height = (int)metrics.getStringBounds(name, arg0).getHeight();
		}
		
		arg0.drawString(name,imgWidth/2-width/2, imgHeight/2+height/2);
	}

	public boolean isFinished()
	{
		return finished;
	}
}
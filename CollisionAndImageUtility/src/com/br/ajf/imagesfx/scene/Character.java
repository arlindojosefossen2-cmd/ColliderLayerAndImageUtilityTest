package com.br.ajf.imagesfx.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.br.ajf.imagesfx.main.Main;

import br.com.ajf.game.animation.Animation;
import br.com.ajf.game.animation.IAnimation;
import br.com.ajf.game.animation.PingPongAnimation;
import br.com.ajf.game.gameobject.AbstractGameObject;
import br.com.ajf.game.image.ImageLoader;
import br.com.ajf.game.image.ImageSFX;
import br.com.ajf.game.util.ResourceLoader;
import br.com.ajf.game.util.XMLUtility;

public class Character extends AbstractGameObject
{
	public final CharacterInputs data = new CharacterInputs(this);	
	public final CharacterMoviment characterMoviment = new CharacterMoviment(data, getContainer());

	public static final int SCREEN_X = Main.WIDTH/2-64/2;
	public static final int SCREEN_Y = Main.HEIGHT/2-64/2;
	
	public Collider collider;
	
//	private static BufferedImage shadow;
	
	@Override
	public void start()
	{
		this.getContainer().solidAreaX = 16;
		this.getContainer().solidAreaY = 16;
		
		loadeAnimationFromXMLFile("/res/data/manager3.xml","/data/manager3.xml");
		collider = new Collider(0,0,16,16,32,32,"solid");
//		shadow = Game.LOADER.loadBufferedImage("/carrasco/shadow.png");
	}

//	private void loadeAnimationFromXMLFile(String fileResPath,String fileFolderName)
//	{
//	    //<!-- Load Animations by diferents SpriteSheets example: IdleDown and WalkDown animations etc... -->
//		XMLUtility xml = new XMLUtility();
//		
//		try
//		{
//			Document doc = xml.parseDocument(
//					new ResourceLoader().load(Character.class, fileResPath, fileFolderName));
//			
//			List<Element> elements = xml.getAllElementsByTagName(doc.getDocumentElement(),"position");
//			
//			
//			for (Element element : elements)
//			{
//				characterMoviment.position.set(Integer.parseInt(element.getAttribute("xStart")),Integer.parseInt(element.getAttribute("yStart")));
//			}
//			
//			
//			elements = xml.getAllElementsByTagName(doc.getDocumentElement(),"velocity");
//			
//			for (Element element : elements)
//			{
//				characterMoviment.velocity.set(Integer.parseInt(element.getAttribute("xVelocity")),Integer.parseInt(element.getAttribute("yVelocity")));
//			}
//			
//			elements = xml.getAllElementsByTagName(doc.getDocumentElement(),"animation");
//			
//			for (Element element : elements)
//			{				
//				String path = String.valueOf(element.getAttribute("path"));
//				int rows = Integer.parseInt(element.getAttribute("rows"));
//				int cols =  Integer.parseInt(element.getAttribute("columns"));
//				int interval = Integer.parseInt(element.getAttribute("interval"));
//				float scale = Float.parseFloat(element.getAttribute("scale"));
//				boolean looping = Boolean.parseBoolean(element.getAttribute("looping"));
//				
//				getContainer().animations.addAnimation(new Animation(path, rows, cols,interval, scale,looping));
//			}
//		}
//		catch (ParserConfigurationException | IOException | SAXException e)
//		{
//			e.printStackTrace();
//		}
//	}
	
//	private void loadeAnimationFromXMLFile(String fileResPath,String fileFolderName)
//	{
//		//<!-- Load Animations by diferents SpriteSheets example: Idle SpriteSheet and Walk SpriteSheet animations -->
//	
//		XMLUtility xml = new XMLUtility();
//		
//		try
//		{
//			Document doc = xml.parseDocument(
//					new ResourceLoader().load(Character.class, fileResPath, fileFolderName));
//			
//			Element documentElement = doc.getDocumentElement();
//			
//			List<Element> elements = xml.getAllElementsByTagName(documentElement,"position");
//			
//			
//			for (Element element : elements)
//			{
//				characterMoviment.position.set(Integer.parseInt(element.getAttribute("xStart")),Integer.parseInt(element.getAttribute("yStart")));
//			}
//			
//			
//			elements = xml.getAllElementsByTagName(documentElement,"velocity");
//			
//			for (Element element : elements)
//			{
//				characterMoviment.velocity.set(Integer.parseInt(element.getAttribute("xVelocity")),Integer.parseInt(element.getAttribute("yVelocity")));
//			}
//			
//			elements = xml.getAllElementsByTagName(documentElement,"animations");
//			ImageLoader loader = new ImageLoader();
//			ImageSFX imageSFX = new ImageSFX();
//			
//			for (Element element : elements)
//			{			
//				String path = element.getAttribute("path");
//				float scale = Float.parseFloat(element.getAttribute("scale"));
//				int rows = Integer.parseInt(element.getAttribute("rows"));
//				int cols = Integer.parseInt(element.getAttribute("columns"));
//				
//				BufferedImage[] img = loader.loadScaledBufferedImagesFromSheet(
//						path, rows, cols, scale);
//				
//				List<Element> aux = xml.getElementsByTagName(element, "animation");
//				
//				for (Element element2 : aux)
//				{
//					int init = Integer.parseInt(element2.getAttribute("init"));
//					int fin = Integer.parseInt(element2.getAttribute("final"));
//					int interval = Integer.parseInt(element2.getAttribute("interval"));
//					boolean looping = Boolean.parseBoolean(element2.getAttribute("looping"));
//					BufferedImage[] anim = imageSFX.cropBufferedImageArray(img, init, fin);
//					
//					getContainer().animations.addAnimation(new Animation(anim, interval, looping));
//					
//				}
//			}
//		}
//		catch (ParserConfigurationException | IOException | SAXException e)
//		{
//			e.printStackTrace();
//		}
//	}
	
	private void loadeAnimationFromXMLFile(String fileResPath,String fileFolderName)
	{
		//<!-- Load All Animations of one SpriteSheet. More eficient. Less access of files resources-->
		XMLUtility xml = new XMLUtility();
		
		try
		{
			Document doc = xml.parseDocument(
					new ResourceLoader().load(Character.class, fileResPath, fileFolderName));
			
			Element documentElement = doc.getDocumentElement();
			
			List<Element> elements = xml.getAllElementsByTagName(documentElement,"position");
			
			for (Element element : elements)
			{
				characterMoviment.position.set(Integer.parseInt(element.getAttribute("xStart")),
						Integer.parseInt(element.getAttribute("yStart")));
			}
			
			
			elements = xml.getAllElementsByTagName(documentElement,"velocity");
			
			for (Element element : elements)
			{
				characterMoviment.velocity.set(Integer.parseInt(element.getAttribute("xVelocity")),
						Integer.parseInt(element.getAttribute("yVelocity")));
			}
			
			elements = xml.getAllElementsByTagName(documentElement,"animations");
			ImageLoader loader = new ImageLoader();
			ImageSFX imageSFX = new ImageSFX();
			
			for (Element element : elements)
			{			
				String path = element.getAttribute("path");
				float scale = Float.parseFloat(element.getAttribute("scale"));
				int rows = Integer.parseInt(element.getAttribute("rows"));
				int cols = Integer.parseInt(element.getAttribute("columns"));
				
				BufferedImage[] img = loader.loadScaledBufferedImagesFromSheet(
						path, rows, cols, scale);
				
				List<Element> aux = xml.getElementsByTagName(element, "animation");
				
				for (Element element2 : aux)
				{
					int init = Integer.parseInt(element2.getAttribute("init"));
					int frames = Integer.parseInt(element2.getAttribute("frames"));
					int interval = Integer.parseInt(element2.getAttribute("interval"));
					boolean looping = Boolean.parseBoolean(element2.getAttribute("looping"));
					String type = element2.getAttribute("type");
					
					BufferedImage[] anim = imageSFX.cropBufferedImage(img, init, frames);
					
					if(type.equalsIgnoreCase(IAnimation.NORMAL_ANIMATION))
					{
						getContainer().animations.addAnimation(new Animation(anim, interval, looping));
					}
					else if(type.equalsIgnoreCase(IAnimation.PINGPONG_ANIMATION))
					{
						getContainer().animations.addAnimation(new PingPongAnimation(anim, interval, looping));
					}
				}
			}
		}
		catch (ParserConfigurationException | IOException | SAXException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void update(float delta)
	{
		data.updateInputs();
		characterMoviment.update(delta);
	}

	@Override
	public void draw(Graphics2D graphics2d)
	{
//		graphics2d.drawImage(shadow, SCREEN_X, SCREEN_Y+shadow.getHeight()/16, null);
		
		graphics2d.setColor(Color.yellow);
		graphics2d.drawRect(SCREEN_X+getContainer().solidAreaX, SCREEN_Y+this.getContainer().solidAreaY,
				collider.getWidth(), collider.getHeight());
		
		getContainer().animations.draw(
				graphics2d, 
				SCREEN_X, 
				SCREEN_Y);
	}

	public void preventMoviment()
	{
		characterMoviment.prevent();
	}
}
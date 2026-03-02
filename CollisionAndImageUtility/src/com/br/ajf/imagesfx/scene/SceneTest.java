package com.br.ajf.imagesfx.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import br.com.ajf.game.animation.IAnimation;
import br.com.ajf.game.animation.PingPongAnimation;
import br.com.ajf.game.gameobject.AbstractGameObject;
import br.com.ajf.game.image.ImageLoader;
import br.com.ajf.game.image.ImageSFX;
import br.com.ajf.game.model.Game;
import br.com.ajf.game.scene.Scene;
import br.com.ajf.game.tile.ITileManager;
import br.com.ajf.game.tile.TileManager;
import br.com.ajf.game.util.ResourceLoader;
import br.com.ajf.game.util.XMLUtility;

public final class SceneTest implements Scene
{
	public static final List<Collider> rects = new ArrayList<>();

	final ImageLoader imageLoader;
	
	final Character character;
	
	//img test pingpong
	private BufferedImage img[] ; 
	private IAnimation pingPong;
	
	final ITileManager manager = new TileManager();

	private Game game;
	
	public SceneTest(Game game,ImageLoader imageLoader)
	{
		this.game = game;
		this.imageLoader = imageLoader;
		this.character = new Character();
	}
	
	@Override
	public void start()
	{	
//		manager.loadTiles("/data/islandtilesdata.txt",16,4f);
		
		manager.loadTilesByXML("/res/data/tiles-data2.xml", 
				"/data/tiles-data2.xml");
//		manager.loadData("/maps/island.txt");
		manager.loadDataFromXMLFile("/res/maps/world-maps2.xml","/maps/world-maps2.xml");
		
		character.start();
		Scene.gameObjects.add(character);
		
		//test Ping Pong Animation
		img = imageLoader.loadBufferedImagesFromSheet("/img/character.png", 4, 3);
		
		ImageSFX imgSFX = new ImageSFX();
		
		BufferedImage aux[] = imgSFX.cropBufferedImage(img,  0,3);
		
		aux = imgSFX.scaleBufferedImages(aux, 2.5f);
		
		pingPong = new PingPongAnimation(aux, 12);
		
		loadCollisionDataFromXML("/res/data/collision.xml", "/data/collision.xml");
	}
	
	public static void loadCollisionDataFromXML(String filepath,String resFilepath)
	{
		XMLUtility xmlUtility = new XMLUtility();
		
		try
		{
			Document doc = xmlUtility.parseDocument(new ResourceLoader().load(
					SceneTest.class, filepath, resFilepath));
			
			Element docElement = doc.getDocumentElement();
			int scale = Integer.parseInt(docElement.getAttribute("scale"));
			
			List<Element> colliders = xmlUtility.getElementsByTagName(docElement, "object");
			
			for (Element element : colliders)
			{
				int id = Integer.parseInt(element.getAttribute("id"));
				int layer = Integer.parseInt(element.getAttribute("layer"));
				
				int x = Integer.parseInt(element.getAttribute("x"));
				int y = Integer.parseInt(element.getAttribute("y"));
				int width = Integer.parseInt(element.getAttribute("width"));
				int height = Integer.parseInt(element.getAttribute("height"));
				
				String type = element.getAttribute("type");
				
				rects.add(new Collider(id,layer,x*scale, y*scale, width*scale, height*scale,type));
			}
		} 
		catch (ParserConfigurationException | IOException | SAXException e)
		{
			e.printStackTrace();
		}
	}

//	public void saveBufferedImageListToImages(List<BufferedImage> imgs) throws IOException
//	{
//		for (int j = 0; j < imgs.size(); j++)
//		{
//			File out = new File("C:\\Users\\user\\eclipse-workspace\\ImageSFXTest\\res\\img\\alien\\frame__"+j+"__.png");
//			if(!out.exists())
//			{
//				ImageIO.write(imgs.get(j), "PNG" , out);
//			}
//		}
//	}

	@Override
	public void update()
	{
		//clear the scene gameObjects list, update gameObjects and add to the draw gameObjects scene list
		Scene.gameObjects.clear();
		character.update(game.delta());
		Scene.gameObjects.add(character);
		
		//check collision with gameObject
		if(CollisionScene.isCollision(character,rects))
		{
			character.preventMoviment();
		}
		
		//update pingpong
		if(pingPong != null)
		{
			pingPong.update();
		}
	}
	
	@Override
	public void draw(Graphics2D arg0)
	{
		//draw all layers (after/or/before) if you want the gameObjects
		manager.draw(arg0, character.characterMoviment.position.getX(),
				character.characterMoviment.position.getY(), 
				Character.SCREEN_X,
				Character.SCREEN_Y);
		
		//draw gameObject to the scene
		for (AbstractGameObject abstractGameObject : Scene.gameObjects)
		{
			if(abstractGameObject != null)
			{
				abstractGameObject.draw(arg0);
			}
		}
		
		//testing pingpong animation
		if(pingPong != null)
		{
			pingPong.draw(arg0, 120-character.characterMoviment.position.getX()+Character.SCREEN_X,
					320-character.characterMoviment.position.getY()+Character.SCREEN_Y);
		}
		
		//draw rects for collision debug
		for (Collider rect : rects)
		{
			arg0.setColor(Color.RED);
			arg0.drawRect(rect.getX()-character.characterMoviment.position.getX()+Character.SCREEN_X,
					rect.getY()-character.characterMoviment.position.getY()+Character.SCREEN_Y,
					rect.getWidth(), rect.getHeight());;
		}
	}
}
package com.br.ajf.imagesfx.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import br.com.ajf.game.connection.DBConnection;
import br.com.ajf.game.util.CSVString;
import br.com.ajf.game.util.ResourceLoader;
import br.com.ajf.game.util.XMLUtility;


public final class MainTest
{
	private static final String SQLITEJDBC = "org.sqlite.JDBC";
	private static final String URL = "jdbc:sqlite:C:\\Users\\user\\eclipse-workspace\\BlobTest\\src\\database\\user.db";
	
	private MainTest()
	{
		
	}

	public static void main(String[] args)
	{
		main();
	}

	public static void DBConnectionTest()
	{
		try
		{
			Connection connection = DBConnection.getConnection(SQLITEJDBC, URL);
			
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM usertb");
			ResultSet result = statement.executeQuery();
			
			while(result.next())
			{
				System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getString(3));
			}
			
			DBConnection.closeConnection(connection,statement,result);
		} 
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static void main()
	{
		csvReadTest();
			
		testCSVString();

		System.out.println();
		
		testXMLRead();
		
		System.out.println();
		
		DBConnectionTest();
	}

	public static void testXMLRead()
	{
		try
		{
			xmlReadTest();
		}
		catch (ParserConfigurationException | IOException | SAXException e)
		{
			e.printStackTrace();
		}
	}

	public static void testCSVString()
	{
		CSVString test = new CSVString("res/config/csvTest.csv");
		
		String[] a = test.getRowDataById(1);
		
		for (int i = 0; i < a.length; i++)
		{
			System.out.println(a[i]);
		}
		
		System.out.println("----------------From CSVString------------------\n");
		
		System.out.println(test);
	}

	private static void xmlReadTest() throws ParserConfigurationException, IOException, SAXException
	{
		XMLUtility xmlUtility = new XMLUtility();
		
		Document doc = xmlUtility.parseDocument(new ResourceLoader().load(MainTest.class,
				"/res/config/tiles.xml","/config/tiles.xml"));
		
		List<Element> elements = xmlUtility.getAllElementsByTagName(doc.getDocumentElement(),
				"tile");
		
		for (Element element : elements)
		{
			List<Element> aux = xmlUtility.getAllElementsByTagName(element, "filename");
			
			for (Element element2 : aux)
			{
				System.out.println(element2.getTextContent());
			}
		}
	}

	private static void csvReadTest()
	{
		String csv = new ResourceLoader().read("res/config/csvTest.csv");
		
		String[] csvSplit = csv.split("\n");
		
		String[] csvTitle = csvSplit[0].split(";");
		
		for (int i = 0; i < csvTitle.length; i++)
		{
			System.out.print(csvTitle[i].replace("\"", "")+"\t");			
		}
		
		System.out.println();
		
		for (int i = 0; i < csvSplit.length; i++)
		{
			System.out.println(csvSplit[i].replace("\"", "")+"\t");			
		}
	}
}
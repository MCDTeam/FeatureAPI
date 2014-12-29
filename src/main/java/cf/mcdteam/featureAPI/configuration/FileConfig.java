package cf.mcdteam.featureAPI.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Configuration.UnicodeInputStreamReader;

public class FileConfig
{
	/**
	 * The File being Loaded
	 */
	private File file;
	
	/**
	 * The state of the file False - Saved and Unloaded True - Unsaved and Loaded
	 */
	private boolean state;
	
	public HashMap<String, ArrayList> filemap;
	
	public FileConfig(File file)
	{
		this.file = file;
		this.state = false;
		this.filemap = new HashMap<String, ArrayList>();
	}
	
	public Boolean state()
	{
		return Boolean.valueOf(state);
	}
	
	public void changestate()
	{
		if (state)
		{
			save();
		} else
		{
			load();
		}
	}
	
	public void forceload()
	{
		load();
		System.out.println("Forced Load of File " + file.toString());
	}
	
	private void load()
	{
		try
		{
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			String line;
			
			String org1 = "Pre";
			String type = "String[]";
			ArrayList<String> values = new ArrayList<String>();
			while (true)
			{
				line = buffer.readLine();
				if (line == null)
				{
					break;
				}
				
				if (!line.isEmpty())
				{
					switch (line.charAt(0))
					{
						case '@':
						{
							org1 = line.substring(1);
							break;
						}
						
						case '$':
						{
							type = line.substring(1);
							break;
						}
						
						case '=':
						{
							values.add(line.substring(1));
							break;
						}
						
						case ';':
						{
							
							values.clear();
							break;
						}
						
						default:
						{
							continue;
						}
						
					}
				}
			}
			if (buffer != null)
			{
				buffer.close();
			}
			state = true;
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void save()
	{
		try
		{
			Files.deleteIfExists(file.toPath());
			
		}
		catch (IOException e)
		{
			
		}
		filemap = new HashMap<String, HashMap<String, Object>>();
		state = false;
	}
	
	public class Data
	{
		public String data = "null";
		public String type = "String";
		public ArrayList<String> values = new ArrayList<String>();
		public Data (String data, ArrayList<String> values, String type)
		{
			this.values = values;
			this.type = type;
			this.data = data;
		}
	}
	public class Command
	{
		public String command = "null";
		public ArrayList<String> arguments = new ArrayList<String>();
		public ArrayList<String> type = new ArrayList<String>();
		public Command (String command,ArrayList<String> arguments, ArrayList<String> type)
		{
			this.arguments = arguments;
			this.type = type;
			this.command = command;
		}
	}
}

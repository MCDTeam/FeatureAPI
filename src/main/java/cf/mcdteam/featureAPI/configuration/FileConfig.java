package cf.mcdteam.featureAPI.configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Configuration.UnicodeInputStreamReader;

/**
 * Reads and Writes Config Files
 * Finished BCWADSWORTH on 1/2/15
 */
public class FileConfig
{
	private Logger log;
	/**
	 * The File being Loaded
	 */
	private File file;
	
	/**
	 * The state of the file False - Saved and Unloaded True - Unsaved and Loaded
	 */
	private boolean state;
	
	public ArrayList<Data> filedata;
	public ArrayList<Command> filecommands;
	
	public FileConfig(File file)
	{
		this.file = file;
		state = false;
		filedata = new ArrayList<Data>();
		filecommands = new ArrayList<Command>();
		log = LogManager.getLogger("FeatureAPI] [Config File IO");
	}
	
	public Boolean state()
	{
		return Boolean.valueOf(state);
	}
	
	public void changestate()
	{
		if (state)
		{
			log.info("Config saving file at " + file.toString());
			save();
		} else
		{
			log.info("Config loading file at " + file.toString());
			load();
		}
	}
	
	public void forceload()
	{
		filedata = new ArrayList<Data>();
		filecommands = new ArrayList<Command>();
		log.info("Forced Load of File " + file.toString());
		load();
	}
	
	private void load()
	{
		try
		{
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;
			
			String org = "Pre";
			String type = "String";
			Boolean commandflag = false;
			ArrayList<String> values = new ArrayList<String>();
			ArrayList<String> types = new ArrayList<String>();
			while (true)
			{
				line = buffer.readLine();
				if (line == null)
				{
					break;
				}
				
				if (!line.isEmpty())
				{
					if (commandflag)
					{
						switch (line.charAt(0))
						{
							case '$':
							{
								type = line.substring(1).toLowerCase();
								break;
							}
							
							case '=':
							{
								values.add(line.substring(1));
								types.add(type);
								break;
							}
							
							case ';':
							{
								filecommands.add(new Command(org, (ArrayList<String>) values.clone(), (ArrayList<String>) types.clone()));
								values.clear();
								types.clear();
								commandflag = false;
								break;
							}
							
							default:
							{
								continue;
							}
							
						}
					}
					else
					{
						switch (line.charAt(0))
						{
							case '@':
							{
								org = line.substring(1);
								break;
							}
							
							case '$':
							{
								type = line.substring(1).toLowerCase();
								if (type == "command")
								{
									commandflag = true;
								}
								break;
							}
							
							case '=':
							{
								values.add(line.substring(1));
								break;
							}
							
							case ';':
							{
								System.out.println(values);
								filedata.add(new Data(org, (ArrayList<String>) values.clone(), type));
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
			}
			if (buffer != null)
			{
				buffer.close();
			}
			state = true;
		} catch (IOException e)
		{
			log.warn(e.getLocalizedMessage());
			log.warn(e.getStackTrace());
		}
	}
	
	private void save()
	{
		try
		{
			Files.deleteIfExists(file.toPath());
			Files.createFile(file.toPath());
			BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
			for (Data value : filedata)
			{
				value.write(buffer);
				buffer.newLine();
			}
			
			if (buffer != null)
			{
				buffer.close();
			}
			state = true;
		}
		catch (IOException e)
		{
			
		}
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
		
		public void write (BufferedWriter writer) throws IOException
		{
			writer.write("@" + data);
			writer.newLine();
			writer.write("$" + type);
			writer.newLine();
			for (String string: values)
			{
				writer.write("=" + string);
				writer.newLine();
			}
			writer.write(";");
			writer.newLine();
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
		
		public void write (BufferedWriter writer) throws IOException
		{
			writer.write("@" + command);
			writer.newLine();
			writer.write("$COMMAND");
			writer.newLine();
			for (String string: arguments)
			{
				String type0 = type.get(arguments.indexOf(string));
				writer.write("$" + type0);
				writer.newLine();
				writer.write("=" + string);
				writer.newLine();
			}
			writer.write(";");
			writer.newLine();
		}
	}
}

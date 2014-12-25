package cf.mcdteam.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Configuration.UnicodeInputStreamReader;

public class FileReader 
{
	/**
	 * The File being Loaded
	 */
	private File file;
	
	/**
	 * The state of the file
	 * False - Saved and Unloaded
	 * True - Unsaved and Loaded
	 */
	private boolean state;
	
	public HashMap<String, HashMap<String, Object>> filemap;
	
	public FileReader(File file)
	{
		this.file = file;
		this.state = false;
		this.filemap = new HashMap<String, HashMap<String, Object>>();
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
		}
		else
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
                String org2 = "Pre";
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
					}
					
					case '#':
					{
						org2 = line.substring(1);
					}
					
					case '$': 
					{
						type = line.substring(1);
					}

					case '=': 
					{
						values.add(line.substring(1));
					}

					case ';': 
					{
						HashMap<String,Object> interior;
						if (filemap.containsKey(org1))
						{
							interior = filemap.get(org1);
						}
						else
						{
							interior = new HashMap<String,Object>();
						}
						switch (type.toLowerCase())
						{
						case "int":
						{
							if (values.isEmpty())
							{
								continue;
							}
							else if (values.size() == 1)
							{
								interior.put(org2, Integer.valueOf(values.get(0)));
								filemap.put(org1, interior);
							}
							else
							{
								ArrayList<Integer> list = new ArrayList<Integer>();
								for (String string:values)
								{
									list.add(Integer.valueOf(string));
								}
								interior.put(org2, list);
								filemap.put(org1, interior);
							}
						}
						case "float":
						{
							if (values.isEmpty())
							{
								continue;
							}
							else if (values.size() == 1)
							{
								interior.put(org2, Float.valueOf(values.get(0)));
								filemap.put(org1, interior);
							}
							else
							{
								ArrayList<Float> list = new ArrayList<Float>();
								for (String string:values)
								{
									list.add(Float.valueOf(string));
								}
								interior.put(org2, list);
								filemap.put(org1, interior);
							}
						}
						case "string":
						{
							if (values.isEmpty())
							{
								continue;
							}
							else if (values.size() == 1)
							{
								interior.put(org2, values.get(0));
								filemap.put(org1, interior);
							}
							else
							{
								interior.put(org2, values);
								filemap.put(org1, interior);
							}
						}
						case "boolean":
						{
							if (values.isEmpty())
							{
								continue;
							}
							else if (values.size() == 1)
							{
								interior.put(org2, Boolean.parseBoolean(values.get(0)));
								filemap.put(org1, interior);
							}
							else
							{
								ArrayList<Boolean> list = new ArrayList<Boolean>();
								for (String string:values)
								{
									list.add(Boolean.parseBoolean(string));
								}
								interior.put(org2, list);
								filemap.put(org1, interior);
							}
						}
						default:
						{
							if (values.isEmpty())
							{
								continue;
							}
							else if (values.size() == 1)
							{
								interior.put(org2, values.get(0));
								filemap.put(org1, interior);
							}
							else
							{
								interior.put(org2, values);
								filemap.put(org1, interior);
							}
						}
						}
						values.clear();
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
                    try
                    {
                        buffer.close();
                    } catch (IOException e){}
                }
                state = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
	
	private void save()
	{
		
		filemap = new HashMap<String, HashMap<String, Object>>();
		state = false;
	}
}

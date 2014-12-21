package cf.mcdteam.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	public File file;
	
	/**
	 * The state of the file
	 * False - Saved and Unloaded
	 * True - Unsaved and Loaded
	 */
	public boolean state;
	
	public HashMap<String, HashMap<String, Object>> filemap;
	
	public FileReader(File file)
	{
		this.file = file;
		this.state = false;
	}
	public void load()
    {
        BufferedReader buffer = null;
        String encoding = null;
        UnicodeInputStreamReader input = null;
        try
        {
            if (file.getParentFile() != null)
            {
                file.getParentFile().mkdirs();
            }

            if (!file.exists())
            {
                // Either a previous load attempt failed or the file is new; clear maps
            	file.createNewFile();
            	return;
            }

            if (file.canRead())
            {
				input = new UnicodeInputStreamReader(new FileInputStream(file), encoding);
                encoding = input.getEncoding();
                buffer = new BufferedReader(input);

                String line;
                
                line = buffer.readLine();
                if (line == null)
                {
                	return;
                }
                
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
						switch (type.toLowerCase())
						{
						case "int":
						{
							
						}
						case "float":
						{
							
						}
						case "string":
						{
							
						}
						case "boolean":
						{
							
						}
						case "hash":
						{
							
						}
						case "itemname":
						{
							
						}
						default:
						{
							if (values.isEmpty())
							{
								continue;
							}
							if (values.size() == 1)
							{
								
							}
						}
						}
					}

					default: 
					{
						continue;
					}
					
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (buffer != null)
            {
                try
                {
                    buffer.close();
                } catch (IOException e){}
            }
            if (input != null)
            {
                try
                {
                    input.close();
                } catch (IOException e){}
            }
        }
    }
}

package me.drkmatr1984.anvilstringcommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.drkmatr1984.anvilstringcommand.AnvilGUI.AnvilSlot;

public class AnvilStringConfig{
	
	AnvilStringCommand plugin;
	
	File file;
	FileConfiguration f;
	ConfigurationSection cs;
	
	public AnvilStringConfig(AnvilStringCommand anvilStringCommand) {
		this.plugin = anvilStringCommand; 
	}

	public void saveDefaultConfig(){
		file = new File(this.plugin.getDataFolder(), "config.yml");
		if(!file.exists()) {
		       this.plugin.saveResource("config.yml", true);
		}    
	}
	
	public void loadConfig(){
		file = new File(this.plugin.getDataFolder(), "config.yml");
		f = YamlConfiguration.loadConfiguration(file);
		cs = f.getConfigurationSection("config.commands");
	}
	
	public HashMap<AnvilSlot, HashMap<ItemStack, List<String>>> LoadButtonsfromConfig(String commands) {
		file = new File(this.plugin.getDataFolder(), "config.yml");
		f = YamlConfiguration.loadConfiguration(file);		
		cs = f.getConfigurationSection("config.commands");
		String material = "APPLE";
		short datavalue = -1;
		String displayname = "&6Apple";
		List<String> lore = new ArrayList<String>();
		List<String> command = new ArrayList<String>();
		HashMap<AnvilSlot, HashMap<ItemStack, List<String>>> buttonSlots = new HashMap<AnvilSlot, HashMap<ItemStack, List<String>>>();
		HashMap<ItemStack, List<String>> buttons = new HashMap<ItemStack, List<String>>();
		for(String s : cs.getKeys(false)){
		    if(s.equals(commands)){
		    	//Slot1
		    	String slot1 = "config.commands." + s + ".slot1";
		    	if(f.getString(slot1 + ".material")!=null)
		    		material = f.getString(slot1 + ".material");
		    	if((short)f.getInt(slot1 + ".datavalue") != -1)
		    		datavalue = (short) f.getInt(slot1 + ".datavalue");
		    	if(f.getString(slot1 + ".displayname")!=null)
		    		displayname = formatColor(f.getString(slot1 + ".displayname"));
		    	if(f.getStringList(slot1 + ".lore")!=null){
		    		List<String> loretemp = new ArrayList<String>();
		    		lore = f.getStringList(slot1 + ".lore");
		    		for(String l : lore){
		    			loretemp.add(formatColor(l));
		    		}
		    		lore = loretemp;
		    	}
		    	if(f.getStringList(slot1 + ".command")!=null)
		    		command = f.getStringList(slot1 + ".command");
		    	buttons = new HashMap<ItemStack, List<String>>();
		    	buttons.put(assembleButton(Material.getMaterial(material), datavalue, displayname, lore), command);
		    	buttonSlots.put(AnvilSlot.INPUT_LEFT, buttons);
		    	//Slot2
		    	String slot2 = "config.commands." + s + ".slot2";
		    	if(f.getString(slot2 + ".material")!=null)
		    		material = f.getString(slot2 + ".material");
		    	if((short)f.getInt(slot2 + ".datavalue") != -1)
		    		datavalue = (short) f.getInt(slot2 + ".datavalue");
		    	if(f.getString(slot2 + ".displayname")!=null)
		    		displayname = formatColor(f.getString(slot2 + ".displayname"));
		    	if(f.getStringList(slot2 + ".lore")!=null){
		    		List<String> loretemp = new ArrayList<String>();
		    		lore = f.getStringList(slot2 + ".lore");
		    		for(String l : lore){
		    			loretemp.add(formatColor(l));
		    		}
		    		lore = loretemp;
		    	}
		    	if(f.getStringList(slot2 + ".command")!=null)
		    		command = f.getStringList(slot2 + ".command");
		    	buttons = new HashMap<ItemStack, List<String>>();
		    	buttons.put(assembleButton(Material.getMaterial(material), datavalue, displayname, lore), command);
		    	buttonSlots.put(AnvilSlot.INPUT_RIGHT, buttons);
		    	//Result
		    	String result = "config.commands." + s + ".result";
		    	if(f.getString(result + ".material")!=null)
		    		material = f.getString(result + ".material");
		    	if((short)f.getInt(result + ".datavalue") != -1)
		    		datavalue = (short) f.getInt(result + ".datavalue");
		    	if(f.getString(result + ".displayname")!=null)
		    		displayname = formatColor(f.getString(result + ".displayname"));
		    	if(f.getStringList(result + ".lore")!=null){
		    		List<String> loretemp = new ArrayList<String>();
		    		lore = f.getStringList(result + ".lore");
		    		for(String l : lore){
		    			loretemp.add(formatColor(l));
		    		}
		    		lore = loretemp;
		    	}
		    	if(f.getStringList(result + ".command")!=null)
		    		command = f.getStringList(result + ".command");
		    	buttons = new HashMap<ItemStack, List<String>>();
		    	buttons.put(assembleButton(Material.getMaterial(material), datavalue, displayname, lore), command);
		    	buttonSlots.put(AnvilSlot.OUTPUT, buttons);
		    	
		    	return buttonSlots;
		    }
		}
		return null;
	}
	
	public String formatColor(String words)
	{
		String temp = "";
	    if(words!=null && words!=""){
		  temp = words;
	      temp = temp.replaceAll("&", "§");
	    }else{
	    	return words;
	    }
	    return temp;
	}
	
	public ItemStack assembleButton(Material mat, short dv, String name, List<String> lore){
		ItemStack item = new ItemStack(mat, 1, dv);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
}
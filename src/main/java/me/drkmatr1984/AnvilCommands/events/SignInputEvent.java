package me.drkmatr1984.AnvilCommands.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import me.drkmatr1984.AnvilCommands.handlers.SignInputHandler1_11;

public class SignInputEvent
{

	private static String[] results = null;
	
	public static class SignGUICloseEvent extends PlayerEvent {
        public static HandlerList handlerList = new HandlerList();
        public static String[] result = null;
        public static Player player;
        
        public SignGUICloseEvent(Player p, String[] lines) {
            super(p);
            result = lines;
            setResults(lines);
            SignInputHandler1_11.ejectNetty(p);
        }
        
        public String[] getLines(){
        	return result;
        }
        
        @Override
        public HandlerList getHandlers() {
            return handlerList;
        }
        
        public static HandlerList getHandlerList() {
        	return handlerList;
        }
    }

	public static String[] getResults() {
		return results;
	}

	public static void setResults(String[] results) {
		SignInputEvent.results = results;
	}
}
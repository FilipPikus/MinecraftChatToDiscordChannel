package filip.hardcorecode.minecraftChatToDiscordChannel;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static filip.hardcorecode.minecraftChatToDiscordChannel.MinecraftChatToDiscordChannel.qeue;

public class DiscordChannelListener implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        String messageContent = event.getMessage();
        String message = "<"+event.getPlayer().getDisplayName()+"> " + messageContent;
        qeue.add(message);
    }
}


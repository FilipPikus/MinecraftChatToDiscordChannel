package filip.hardcorecode.minecraftChatToDiscordChannel;

import com.eduardomcb.discord.webhook.WebhookManager;
import com.eduardomcb.discord.webhook.models.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public final class MinecraftChatToDiscordChannel extends JavaPlugin {
    public static List<String> qeue = Collections.synchronizedList(new ArrayList<>());
    public static String Webhook = "YOUR_DISCORDWEBHOOK";
    public static MinecraftChatToDiscordChannel instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new DiscordChannelListener(),this);
        sendDelayedMessage();
        Config config = ConfigUtil.getConfig("config");
        config.setDefault("DiscordWebhook",MinecraftChatToDiscordChannel.Webhook);
        MinecraftChatToDiscordChannel.Webhook = config.getString("DiscordWebhook");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void sendDelayedMessage() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(qeue.isEmpty()) {
                    return;
                }

                String msg = qeue.remove(0);

                WebhookManager webhookManager = new WebhookManager()
                        .setChannelUrl(Webhook)
                        .setMessage(new Message().setContent(msg));
                webhookManager.exec();
            }
        }.runTaskTimerAsynchronously(this, 15L, 15L);
    }
}

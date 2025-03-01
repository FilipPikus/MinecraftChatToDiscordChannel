package filip.hardcorecode.minecraftChatToDiscordChannel;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

public class Config extends YamlConfiguration {
    private File file;

    private String name;

    public Config(File file, String name) {
        this.file = file;
        this.name = name;
        try {
            load(file);
        } catch (FileNotFoundException fileNotFoundException) {

        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        } catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, (Throwable)ex);
        }
    }

    public void setDefault(String a, Object b) {
        if (!isSet(a)) {
            set(a, b);
            save();
        }
    }

    public void save() {
        try {
            save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        this.file.delete();
        if (this.name != null)
            ConfigUtil.cachemap.remove(this.name);
    }

    public File getFile() {
        return this.file;
    }
}

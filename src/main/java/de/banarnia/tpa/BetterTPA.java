package de.banarnia.tpa;

import de.banarnia.api.acf.BukkitCommandManager;
import de.banarnia.api.acf.CommandManager;
import de.banarnia.api.config.Config;
import de.banarnia.api.config.YamlConfig;
import de.banarnia.api.config.YamlVersionConfig;
import de.banarnia.api.lang.LanguageHandler;
import de.banarnia.tpa.config.TPAConfig;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BetterTPA extends JavaPlugin {

    private CommandManager commandManager;
    private TPAConfig tpaConfig;
    private LanguageHandler languageHandler;

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        // BStats
        int pluginId = 23446;
        Metrics metrics = new Metrics(this, pluginId);

        // ACF
        this.commandManager = new BukkitCommandManager(this);
        commandManager.usePerIssuerLocale(true);

        // Load config.
        Config config = YamlVersionConfig.of(this, getDataFolder(), "config.yml",
                "config.yml", "1.0");
        this.tpaConfig = new TPAConfig(this, config);

        // Lang.
//        File langFolder = new File(getDataFolder(), "lang");
//        YamlConfig.fromResource(this, "lang/en.yml", langFolder, "en.yml");
//        YamlConfig.fromResource(this, "lang/de.yml", langFolder, "de.yml");
//        this.languageHandler = new LanguageHandler(this, tpaConfig.getLanguage());
//        this.languageHandler.register(Message.class);

    }
}

package de.banarnia.tpa.config;

import de.banarnia.api.config.Config;
import de.banarnia.tpa.BetterTPA;

public class TPAConfig {

    private final BetterTPA plugin;

    private final Config config;

    public TPAConfig(BetterTPA plugin, Config config) {
        this.plugin = plugin;
        this.config = config;
    }

    public void reload() {
        config.loadConfig();
    }

    public double getRequestDuration() {
        return config.getDouble("request-duration", 60);
    }

    public double getWarmupTime() {
        return config.getDouble("teleport-warmup-time", 0);
    }

    public double getRequestCooldown() {
        return config.getDouble("request-cooldown", 10);
    }

    public String getLanguage() {
        return config.getString("language", "en");
    }

    public boolean debugMode() {
        return config.getBoolean("debug");
    }

}

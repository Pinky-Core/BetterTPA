package de.banarnia.bettertpa.manager;

import de.banarnia.bettertpa.lang.Message;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class IgnoreManager {

    // Store all individual ignored players.
    private HashMap<UUID, List<UUID>> ignoredPlayersCache = new HashMap<>();

    // Store all players that ignore any requests.
    private List<UUID> ignoreAllRequestsCache = new ArrayList<>();

    // ~~~~~ Bypass ~~~~~

    /**
     * Check if that be can bypass getting ignored.
     * @param player Player to check.
     * @return True, if player hs bypass permission. Else false.
     */
    public boolean hasIgnoreBypassPermission(Player player) {
        return player.hasPermission("bettertpa.tpaignore.bypass");
    }

    // ~~~~~ Ignore ~~~~~

    /**
     * Check if a player is ignored.
     * @param ignoredPlayer Player that might be ignored.
     * @param ignoringPlayer Player that might have ignored.
     * @return True, if ignoredPlayer is ignored by ignoringPlayer. Else false.
     */
    public boolean isIgnored(Player ignoredPlayer, Player ignoringPlayer) {
        if (!ignoredPlayersCache.containsKey(ignoringPlayer.getUniqueId()))
            return false;

        return ignoredPlayersCache.get(ignoringPlayer.getUniqueId()).contains(ignoredPlayer.getUniqueId());
    }

    /**
     * Toggles, if the player is ignored.
     * @param ignoringPlayer Player that is about to ignore/unignore the other one.
     * @param ignoredPlayer Player that is about to be ignored/unignored.
     * @param silent Set to true to send message.
     */
    public void toggleIgnore(Player ignoringPlayer, Player ignoredPlayer, boolean silent) {
        // Check if player tries to ignore himself.
        if (ignoredPlayer == ignoringPlayer) {
            if (!silent)
                ignoringPlayer.sendMessage(Message.COMMAND_ERROR_TPAIGNORE_SELF.get());

            return;
        }

        // Check if player is currently ignored.
        boolean isIgnored = isIgnored(ignoredPlayer, ignoringPlayer);

        // Check if player is currently ignored and therefore will be unignored.
        if (isIgnored)
            unignorePlayer(ignoringPlayer, ignoredPlayer, silent);
        else
            ignorePlayer(ignoringPlayer, ignoredPlayer, silent);
    }

    /**
     * Ignores a player, if he does not have a bypass permission.
     * @param ignoringPlayer Player that wants to ignore the other player.
     * @param ignoredPlayer Player that's about to be ignored.
     * @param silent Set to true to send message.
     */
    private void ignorePlayer(Player ignoringPlayer, Player ignoredPlayer, boolean silent) {
        // Check if ignoredPlayer has bypass permission.
        if (hasIgnoreBypassPermission(ignoredPlayer)) {
            if (!silent) {
                String message = Message.COMMAND_ERROR_TPAIGNORE_BYPASS.get();
                ignoringPlayer.sendMessage(message);
            }

            return;
        }

        // Insert player to blacklist.
        ignoredPlayersCache.putIfAbsent(ignoringPlayer.getUniqueId(), new ArrayList<>());
        ignoredPlayersCache.get(ignoringPlayer.getUniqueId()).add(ignoredPlayer.getUniqueId());

        // Send message.
        if (!silent) {
            String message = Message.COMMAND_INFO_TPAIGNORE_IGNORE.replace("%target%", ignoredPlayer.getName());
            ignoringPlayer.sendMessage(message);
        }
    }

    /**
     * Unignores a player.
     * @param ignoringPlayer Player that ignores the other one.
     * @param ignoredPlayer Player that is ignored.
     * @param silent Set to true to send message.
     */
    private void unignorePlayer(Player ignoringPlayer, Player ignoredPlayer, boolean silent) {
        // Remove ignored player from list.
        ignoredPlayersCache.get(ignoringPlayer.getUniqueId()).remove(ignoredPlayer.getUniqueId());

        // Check if blacklist is empty now to clean up the map.
        if (ignoredPlayersCache.get(ignoringPlayer.getUniqueId()).isEmpty())
            ignoredPlayersCache.remove(ignoringPlayer.getUniqueId());

        // Send message.
        if (!silent) {
            String message = Message.COMMAND_INFO_TPAIGNORE_UNIGNORE.replace("%target%", ignoredPlayer.getName());
            ignoringPlayer.sendMessage(message);
        }
    }

    // ~~~~~ Ignore all ~~~~~

    /**
     * Check if a player ignores all incoming requests.
     * @param playerId Player id that might ignore all requests.
     * @return True, if player ignores all requests. Else false.
     */
    public boolean ignoresAllRequests(UUID playerId) {
        return ignoreAllRequestsCache.contains(playerId);
    }

    /**
     * Toggles if the player ignores all incoming requests.
     * @param player Player that toggles to ignore all incoming requests.
     */
    public void toggleIgnoreAllRequestsMode(Player player, boolean silent) {
        UUID playerId = player.getUniqueId();
        if (ignoresAllRequests(playerId))
            ignoreAllRequestsCache.remove(playerId);
        else
            ignoreAllRequestsCache.add(playerId);

        // Send message.
        if (!silent) {
            String message = ignoresAllRequests(playerId) ?
                    Message.COMMAND_INFO_TPAIGNOREALL_IGNORE.get() : Message.COMMAND_INFO_TPAIGNOREALL_UNIGNORE.get();
            player.sendMessage(message);
        }
    }



}

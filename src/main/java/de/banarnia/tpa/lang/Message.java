package de.banarnia.tpa.lang;

import de.banarnia.api.lang.ILanguage;

public enum Message implements ILanguage {
    PREFIX("§8[§6BetterTPA§8]§7"),

    // TPA
    TIMER_INFO_TPA_EXPIRED_TARGET("%prefix% The TPA-Request of §e%requester% §7expired."),
    TIMER_INFO_TPA_EXPIRED_REQUESTER("%prefix% §cThe TPA-Request to §e%target% §cexpired."),
    TIMER_INFO_TPA_WARMUP("%prefix% You will be teleported in §e%time% seconds§7."),
    TIMER_ÌNFO_TPA_TELEPORT("%prefix% Teleporting to §e%target%§7..."),
    TIMER_ERROR_TPA_WARMUP_OFFLINE("%prefix% §cFailed to execute the TPA-Request, because the player went offline."),

    COMMAND_INFO_TPA_SENDER("%prefix% You've sent a TPA-Request to §e%target%§7."),
    COMMAND_ERROR_TPA_DENIED_TARGET("%prefix% You denied the TPA-Request of §e%requester%§7."),
    COMMAND_ERROR_TPA_DENIED_REQUESTER("%prefix% §e%target% §cdenied the TPA-Request."),
    COMMAND_ERROR_TPA_EVENT_CANCELLED("%prefix% §cThe event was cancelled."),
    COMMAND_ERROR_TPA_PENDING("%prefix% §cThere is still a pending TPA-Request to that player."),
    COMMAND_ERROR_TPA_COOLDOWN("%prefix% §cYou have to wait before executing a new request to that player."),
    COMMAND_ERROR_TPA_IGNORED("%prefix% §e%target% §cdoes not accept any TPA-Requests right now."),

    COMMAND_INFO_TPAIGNORE_IGNORE("%prefix% You will no longer receive TPA-Requests from §e%target%§7."),
    COMMAND_INFO_TPAIGNORE_UNIGNORE("%prefix% You will now receive TPA-Requests from §e%target% §7again."),
    COMMAND_ERROR_TPAIGNORE_BYPASS("%prefix% §cYou cannot ignore this player."),
    COMMAND_ERROR_TPAIGNORE_SELF("%prefix% §cYou cannot ignore yourself."),

    COMMAND_INFO_TPAIGNOREALL_IGNORE("%prefix% You will no longer receive TPA-Requests."),
    COMMAND_INFO_TPAIGNOREALL_UNIGNORE("%prefix% You will now receive TPA-Requests again."),

    COMMAND_INFO_TPA_ACCEPTED_TARGET("%prefix% You accepted the TPA-Request."),
    COMMAND_INFO_TPA_ACCEPTED_REQUESTER("%prefix% §e%target% §7accepted your TPA-Request."),

    GUI_HOME_PAGE_PREVIOUS("§cBack"),
    GUI_HOME_PAGE_NEXT("§aNext")
    ;

    String defaultMessage, message;

    Message(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getKey() {
        return this.toString().toLowerCase().replace("_", "-");
    }

    @Override
    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public String get() {
        String message = this.message != null ? this.message : defaultMessage;
        if (this == PREFIX)
            return message;

        return message.replace("%prefix%", PREFIX.get());
    }

    @Override
    public void set(String message) {
        this.message = message;
    }
}
package com.hennie.runecraft.swaps.menuentry.application.api;

import lombok.Getter;

@Getter
public enum Option {
    REMOVE("Remove"),
    DEPOSIT_ALL("Deposit-All"),
    FILL("Fill"),
    WEAR("Wear"),
    NARDAH("Nardah"),
    LAST_DESTINATION("Last Destination"),
    TELEPORT("Teleport"),
    CAST("Cast"),
    DARK_MAGE("Dark Mage");

    private final String name;

    Option(String name) {
        this.name = name;
    }
}

package com.hennie.runecraft.swaps.menuentry.application.api;

import lombok.Getter;

@Getter
public enum Target {

    COLOSSAL_POUCH("Colossal pouch"),
    MYTHICAL_CAPE("Mythical cape"),
    DESERT_AMULET_4("Desert amulet 4"),
    NPC_CONTACT("NPC Contact");

    private final String name;

    Target(String name) {
        this.name = name;
    }
}

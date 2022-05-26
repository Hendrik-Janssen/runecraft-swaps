package com.hennie.runecraft.swaps.menuentry.application.api;

import lombok.Getter;

import java.util.List;

import static com.hennie.runecraft.swaps.menuentry.application.api.Option.*;

@Getter
public enum Target {

    COLOSSAL_POUCH("Colossal pouch", List.of(DEPOSIT_ALL), FILL),
    MYTHICAL_CAPE("Mythical cape", List.of(REMOVE, WEAR), TELEPORT),
    DESERT_AMULET_4("Desert amulet 4", List.of(REMOVE, WEAR), NARDAH),
    NPC_CONTACT("NPC Contact", List.of(CAST), DARK_MAGE),
    RING_OF_THE_ELEMENTS("Ring of the elements", List.of(REMOVE, WEAR), LAST_DESTINATION),
    CRAFTING_CAPE_T("Crafting cape(t)", List.of(REMOVE, WEAR), TELEPORT),
    BINDING_NECKLACE("Binding necklace", List.of(DEPOSIT_ALL), WEAR);

    private final String name;
    private final List<Option> optionsToSwapFrom;
    private final Option optionToSwapTo;

    Target(String name, List<Option> optionsToSwapFrom, Option optionToSwapTo) {
        this.name = name;
        this.optionsToSwapFrom = optionsToSwapFrom;
        this.optionToSwapTo = optionToSwapTo;
    }
}

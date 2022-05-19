package com.hennie.runecraft.swaps.menuentry.application.service;

import com.hennie.runecraft.swaps.menuentry.application.api.Target;
import com.hennie.runecraft.swaps.menuentry.application.api.MenuEntryService;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static net.runelite.api.MenuAction.CC_OP;

@Singleton
public class MenuEntryServiceImpl implements MenuEntryService {

    private final Client client;

    @Inject
    public MenuEntryServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public void swapMenuEntries(String firstMenuEntryOption, String secondMenuEntryOption, Target target) {
        MenuEntry[] menuEntries = client.getMenuEntries();
        final Optional<MenuEntry> firstMenuEntry = getMenuEntry(menuEntries, firstMenuEntryOption, target.getName());
        final Optional<MenuEntry> secondMenuEntry = getMenuEntry(menuEntries, secondMenuEntryOption, target.getName(), CC_OP);

        if (firstMenuEntry.isPresent() && secondMenuEntry.isPresent()) {
            final int firstMenuEntryIndex = Arrays.asList(menuEntries).indexOf(firstMenuEntry.get());
            final int secondMenuEntryIndex = Arrays.asList(menuEntries).indexOf(secondMenuEntry.get());
            menuEntries[firstMenuEntryIndex] = secondMenuEntry.get().setType(CC_OP);
            menuEntries[secondMenuEntryIndex] = firstMenuEntry.get().setType(CC_OP);
            client.setMenuEntries(menuEntries);
        }
    }

    private Optional<MenuEntry> getMenuEntry(MenuEntry[] menuEntries, String option, String target) {
        return Stream.of(menuEntries)
                .filter(entry -> entry.getOption().equals(option))
                .filter(entry -> entry.getTarget().contains(target))
                .findFirst();
    }

    private Optional<MenuEntry> getMenuEntry(MenuEntry[] menuEntries, String option, String target, MenuAction type) {
        return Stream.of(menuEntries)
                .filter(entry -> entry.getOption().equals(option))
                .filter(entry -> entry.getTarget().contains(target))
                .filter(entry -> entry.getType().equals(type))
                .findFirst();
    }
}

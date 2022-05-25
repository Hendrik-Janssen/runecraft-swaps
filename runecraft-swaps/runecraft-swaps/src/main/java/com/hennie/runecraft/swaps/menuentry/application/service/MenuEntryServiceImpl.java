package com.hennie.runecraft.swaps.menuentry.application.service;

import com.hennie.runecraft.swaps.menuentry.application.api.Option;
import com.hennie.runecraft.swaps.menuentry.application.api.Target;
import com.hennie.runecraft.swaps.menuentry.application.api.MenuEntryService;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static net.runelite.api.MenuAction.CC_OP;

@Singleton
public class MenuEntryServiceImpl implements MenuEntryService {

    private final Client client;

    @Inject
    public MenuEntryServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public void swapMenuEntries(Target target) {
        target.getOptionsToSwapFrom()
                .forEach(optionToSwapFrom -> swapMenuEntry(optionToSwapFrom, target));
    }

    private void swapMenuEntry(Option optionToSwapFrom, Target target) {
        MenuEntry[] menuEntries = client.getMenuEntries();
        final Optional<MenuEntry> menuEntryToSwapFrom = getMenuEntry(menuEntries, optionToSwapFrom, target.getName());
        final Optional<MenuEntry> menuEntryToSwapTo = getMenuEntry(menuEntries, target.getOptionToSwapTo(), target.getName());

        if (menuEntryToSwapFrom.isPresent() && menuEntryToSwapTo.isPresent()) {
            final int menuEntryToSwapFromIndex = Arrays.asList(menuEntries).indexOf(menuEntryToSwapFrom.get());
            final int menuEntryToSwapToIndex = Arrays.asList(menuEntries).indexOf(menuEntryToSwapTo.get());
            if (!isFirstMenuEntry(menuEntryToSwapToIndex, menuEntries)) {
                menuEntries[menuEntryToSwapFromIndex] = menuEntryToSwapTo.get().setType(CC_OP);
                menuEntries[menuEntryToSwapToIndex] = menuEntryToSwapFrom.get().setType(CC_OP);
                client.setMenuEntries(menuEntries);
            }
        }
    }

    private boolean isFirstMenuEntry(int menuEntryToSwapToIndex, MenuEntry[] menuEntries) {
        return menuEntryToSwapToIndex == menuEntries.length - 1;
    }

    private Optional<MenuEntry> getMenuEntry(MenuEntry[] menuEntries, Option option, String target) {
        List<MenuEntry> filteredMenuEntries = Stream.of(menuEntries)
                .filter(entry -> entry.getOption().equals(option.getName()))
                .filter(entry -> entry.getTarget().contains(target))
                .collect(toList());

        if (filteredMenuEntries.size() > 1) {
            return findHighestPrioMenuEntry(filteredMenuEntries);
        } else {
            return filteredMenuEntries.stream()
                    .findFirst();
        }
    }

    private Optional<MenuEntry> findHighestPrioMenuEntry(List<MenuEntry> filteredMenuEntries) {
        return filteredMenuEntries.stream()
                .filter(menuEntry -> menuEntry.getType().equals(CC_OP))
                .findFirst();
    }
}

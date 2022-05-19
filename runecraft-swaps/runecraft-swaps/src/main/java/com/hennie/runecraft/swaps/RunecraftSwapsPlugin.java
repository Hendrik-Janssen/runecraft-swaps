package com.hennie.runecraft.swaps;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.hennie.runecraft.swaps.menuentry.application.api.MenuEntryModule;
import com.hennie.runecraft.swaps.menuentry.application.api.MenuEntryService;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

import static com.hennie.runecraft.swaps.menuentry.application.api.Target.*;

@Slf4j
@PluginDescriptor(
        name = "Runecraft Swaps"
)
public class RunecraftSwapsPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private RunecraftSwapsConfig config;

    @Inject
    private MenuEntryService menuEntryService;

    @Override
    public void configure(Binder binder) {
        super.configure(binder);
        MenuEntryModule.bind(binder);
    }

    @Override
    protected void startUp() {
        log.info("Runecraft Swaps started!");
    }

    @Override
    protected void shutDown() {
        log.info("Runecraft Swaps stopped!");
    }

    @Provides
    RunecraftSwapsConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RunecraftSwapsConfig.class);
    }

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded menuEntryAdded) {
        if (menuEntryAdded.getOption().equals("Deposit-All")) {
            menuEntryService.swapMenuEntries("Fill", "Deposit-All", COLOSSAL_POUCH);
        } else if (menuEntryAdded.getOption().equals("Remove")) {
            menuEntryService.swapMenuEntries("Teleport", "Remove", MYTHICAL_CAPE);
            menuEntryService.swapMenuEntries("Nardah", "Remove", DESERT_AMULET_4);
        } else if (menuEntryAdded.getOption().equals("Cast")) {
            menuEntryService.swapMenuEntries("Cast", "Dark Mage", NPC_CONTACT);
        }
    }

}

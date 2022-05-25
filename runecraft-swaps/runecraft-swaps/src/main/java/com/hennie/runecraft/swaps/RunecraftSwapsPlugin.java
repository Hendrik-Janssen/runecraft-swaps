package com.hennie.runecraft.swaps;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.hennie.runecraft.swaps.menuentry.application.api.MenuEntryModule;
import com.hennie.runecraft.swaps.menuentry.application.api.MenuEntryService;
import com.hennie.runecraft.swaps.menuentry.application.api.Target;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

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
    public void onClientTick(ClientTick clientTick) {
        getTarget().ifPresent(target -> {
            Stream.of(Target.values())
                    .filter(configuredTarget -> target.contains(configuredTarget.getName()))
                    .forEach(configuredTarget -> menuEntryService.swapMenuEntries(configuredTarget));
        });
    }

    private Optional<String> getTarget() {
        return Arrays.stream(client.getMenuEntries())
                .filter(menuEntry -> StringUtils.isNotBlank(menuEntry.getTarget()))
                .findFirst()
                .map(MenuEntry::getTarget);
    }

    private boolean hasTarget(MenuEntryAdded menuEntryAdded, Target target) {
        return menuEntryAdded.getTarget().contains(target.getName());
    }
}

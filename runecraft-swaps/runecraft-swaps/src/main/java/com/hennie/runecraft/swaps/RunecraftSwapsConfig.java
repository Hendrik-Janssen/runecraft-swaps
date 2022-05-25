package com.hennie.runecraft.swaps;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("runecraftswaps")
public interface RunecraftSwapsConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}

//	@ConfigItem(
//			keyName = "swapEssencePouchesInBank",
//			name = "Swap Essence Pouches In Bank",
//			description = "Shows 'Fill' as first option on the essence pouches while the bank interface is open"
//	)
}

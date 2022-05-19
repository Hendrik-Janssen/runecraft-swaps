package com.hennie.runecraft.swaps;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RunecraftSwapsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RunecraftSwapsPlugin.class);
		RuneLite.main(args);
	}
}
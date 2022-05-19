package com.hennie.runecraft.swaps.menuentry.application.api;

import com.google.inject.Binder;
import com.hennie.runecraft.swaps.menuentry.application.service.MenuEntryServiceImpl;

public class MenuEntryModule {

    public static void bind(Binder binder) {
        binder.bind(MenuEntryService.class).to(MenuEntryServiceImpl.class);
    }

}

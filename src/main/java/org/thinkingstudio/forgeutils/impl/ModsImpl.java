package org.thinkingstudio.forgeutils.impl;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import org.thinkingstudio.forgeutils.api.Mods;

public class ModsImpl implements Mods {
    private final ModContainer container;

    public ModsImpl(String id) {
        this.container = ModList.get().getModContainerById(id).orElseThrow();
    }

    @Override
    public void registerModConfigScreen(ModConfigScreenProvider configScreenProvider) {
        container.registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> configScreenProvider.provide(screen));
    }
}

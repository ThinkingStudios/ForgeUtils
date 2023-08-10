package org.thinkingstudio.forgeutils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.thinkingstudio.forgeutils.api.Mods;
import org.thinkingstudio.forgeutils.impl.ModsImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ForgeUtils {
    private static final ThreadLocal<ForgeUtils> instance = ThreadLocal.withInitial(ForgeUtils::new);
    private static final Map<String, Mods> mods = new ConcurrentHashMap<>();

    public static ForgeUtils getInstance() {
        return instance.get();
    }

    public Mods getMod(String id) {
        return mods.computeIfAbsent(id, ModsImpl::new);
    }

    public void getModExtensionPoint() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    public void runOnClient(Supplier<DistExecutor.SafeRunnable> clientInit) {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, clientInit);
    }
}

package ru.octol1ttle.damagevignette.forge;

import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.octol1ttle.damagevignette.common.DamageVignetteCommon;
import ru.octol1ttle.damagevignette.common.DamageVignetteEvents;

@SuppressWarnings("unused")
@Mod(DamageVignetteCommon.MOD_ID)
public class DamageVignetteForge {
    public static final Identifier VIGNETTE_ID = new Identifier("minecraft", "vignette");

    public DamageVignetteForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onHudRender);

        DamageVignetteCommon.init();
    }

    public void onHudRender(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay().id().equals(VIGNETTE_ID)) {
            DamageVignetteEvents.RENDER_HUD.renderHud(event.getGuiGraphics());
        }
    }
}

package ru.octol1ttle.damagevignette.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.octol1ttle.damagevignette.common.DamageVignetteCommon;
import ru.octol1ttle.damagevignette.common.DamageVignetteEvents;

@SuppressWarnings("unused")
@Mod(DamageVignetteCommon.MOD_ID)
public class DamageVignetteForge {

    public static final Identifier VIGNETTE_ID = new Identifier("minecraft", "vignette");

    public DamageVignetteForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(DamageVignetteCommon.MOD_ID, modEventBus);
        modEventBus.addListener(this::onHudRender);

        DamageVignetteCommon.init();
    }

    @SubscribeEvent
    public void onHudRender(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay().id().equals(VIGNETTE_ID)) {
            DamageVignetteEvents.RENDER_HUD.renderHud(event.getGuiGraphics(), event.getPartialTick());
        }
    }
}

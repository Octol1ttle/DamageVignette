package ru.octol1ttle.damagevignette;

import dev.architectury.event.events.client.ClientGuiEvent;
import ru.octol1ttle.damagevignette.events.RenderHudEvent;

public class DamageVignetteEvents {
    public static void register() {
        ClientGuiEvent.RENDER_HUD.register(new RenderHudEvent());
    }
}

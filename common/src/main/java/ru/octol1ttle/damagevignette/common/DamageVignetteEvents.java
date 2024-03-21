package ru.octol1ttle.damagevignette.common;

import dev.architectury.event.events.client.ClientGuiEvent;
import ru.octol1ttle.damagevignette.common.events.RenderHudEvent;

public class DamageVignetteEvents {
    public static final ClientGuiEvent.RenderHud RENDER_HUD = new RenderHudEvent();
}

package cz.lukynka.betteruikeybinds;

import com.mojang.blaze3d.platform.InputConstants;
import cz.lukynka.betteruikeybinds.client.keybinds.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BetterUIKeybinds implements ModInitializer {

    public static final List<Keybind> keybindList = List.of(
            new SingleplayerJump(), // Main Menu -> Jumps into the singleplayer menu with "S"
            new MultiplayerJump(), // Main Menu -> Jumps into the multiplayer menu with "M"
            new MultiplayerListJump(), // Server List -> Joins server with "1-9" or "M" for 1
            new MultiplayerDisconnectScreenBack(), // Disconnected Screen -> Escapes to Server List with "ESC"
            new MultiplayerAddServer(), // Server List -> Jumps to Add new server screen with "A"
            new SelectWorldScreenEnterWorld(), // World Select -> Joins the selected world with "Enter" even when the selection is on SearchBox
            new GameMenuScreenEscape(), // Pause Menu -> Selects the disconnect button with "E" (as Exit)
            new CreativeInventorySearch() // Creative Inventory Menu -> Selects the search tab with "TAB". If search tab is already selected, clears the search box value
    );

    private final Set<Integer> pressedKeys = new HashSet<>();

    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTick);
    }

    private void onClientTick(Minecraft client) {
        long windowHandle = Minecraft.getInstance().getWindow().getWindow();

        for (Keybind keybind : keybindList) {
            for(Integer key : keybind.getKeybinds()) {
                if (InputConstants.isKeyDown(windowHandle, key)) {
                    if (!pressedKeys.contains(key)) {
                        pressedKeys.add(key);
                        onKeyPress(client, key);
                    }
                } else {
                    pressedKeys.remove(key);
                }
            }
        }
    }

    private void onKeyPress(Minecraft client, int key) {
        if(client.screen == null) return;

        for (Keybind keybind : keybindList) {
            if(keybind.getKeybinds().contains(key)) {
                assert client.screen != null;
                if (keybind.getScreen() == client.screen.getClass()) {
                    keybind.handle(key);
                    assert Minecraft.getInstance().screen != null;
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.NOTE_BLOCK_HAT, 1.3f));
                    return;
                }
            }
        }
    }
}
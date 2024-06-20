package cz.lukynka.betteruikeybinds.client.keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class GameMenuScreenEscape implements Keybind {
    @Override
    public String getActionName() {
        return "Select Leave Button";
    }

    @Override
    public List<Integer> getKeybinds() {
        return List.of(GLFW.GLFW_KEY_E);
    }

    @Override
    public Class<?> getScreen() {
        return PauseScreen.class;
    }

    @Override
    public int getRequiredPresses() {
        return 1;
    }

    @Override
    public void handle(Integer key) {
        var screen = (PauseScreen) Minecraft.getInstance().screen;
        assert screen != null;
        for (GuiEventListener child : screen.children()) {
            if(child.getClass() == Button.class) {
                var button = (Button)child;
                if(button.getMessage().contains(Component.translatable("menu.returnToMenu")) || button.getMessage().contains(Component.translatable("menu.disconnect"))) {
                    screen.setFocused(button);
                }
            }
        }
    }
}

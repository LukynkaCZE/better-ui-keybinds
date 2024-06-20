package cz.lukynka.betteruikeybinds.client.keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class SingleplayerJump implements Keybind {

    @Override
    public String getActionName() {
        return "Jump to Singleplayer Screen";
    }

    @Override
    public List<Integer> getKeybinds() {
        return List.of(GLFW.GLFW_KEY_S);
    }

    @Override
    public Class<?> getScreen() {
        return TitleScreen.class;
    }

    @Override
    public int getRequiredPresses() {
        return 1;
    }

    @Override
    public void handle(Integer key) {
        var screen = (TitleScreen) Minecraft.getInstance().screen;
        assert screen != null;
        for (GuiEventListener child : screen.children()) {
            if(child.getClass() != Button.class) return;
            var button = (Button) child;
            if(button.getMessage().contains(Component.translatable("menu.singleplayer"))) {
                button.onPress();
            }
        }
    }
}

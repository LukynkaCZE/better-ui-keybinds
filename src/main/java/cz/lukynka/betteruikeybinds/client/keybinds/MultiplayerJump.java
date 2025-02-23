package cz.lukynka.betteruikeybinds.client.keybinds;

import cz.lukynka.betteruikeybinds.BetterUIKeybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class MultiplayerJump implements Keybind {

    @Override
    public String getActionName() {
        return "Jump to Multiplayer Screen";
    }

    @Override
    public List<Integer> getKeybinds() {
        return List.of(GLFW.GLFW_KEY_M);
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
//        BetterUIKeybinds.logger.info("Pressed {} {}", key.toString(), Minecraft.getInstance().screen.getClass().getSimpleName());
        var screen = (TitleScreen) Minecraft.getInstance().screen;
        assert screen != null;
        for (GuiEventListener child : screen.children()) {
            if(child.getClass() != Button.class) return;
            var button = (Button) child;
//            BetterUIKeybinds.logger.info(button.getMessage().getString());
//            BetterUIKeybinds.logger.info(button.getMessage().getContents().toString());
            if(button.getMessage().contains(Component.translatable("menu.multiplayer"))) {
                System.out.println("Found");
                button.onPress();
            }
        }
    }
}

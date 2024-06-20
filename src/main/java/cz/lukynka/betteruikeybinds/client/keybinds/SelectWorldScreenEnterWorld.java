package cz.lukynka.betteruikeybinds.client.keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class SelectWorldScreenEnterWorld implements Keybind {
    @Override
    public String getActionName() {
        return "Jump into World";
    }

    @Override
    public List<Integer> getKeybinds() {
        return List.of(GLFW.GLFW_KEY_ENTER);
    }

    @Override
    public Class<?> getScreen() {
        return SelectWorldScreen.class;
    }

    @Override
    public int getRequiredPresses() {
        return 1;
    }

    @Override
    public void handle(Integer key) {
        var screen = (SelectWorldScreen) Minecraft.getInstance().screen;
        assert screen != null;
        for (GuiEventListener child : screen.children()) {
            if(child.getClass() == WorldSelectionList.class) {
                var worldSelectionList = (WorldSelectionList)child;
                if(worldSelectionList.getSelectedOpt().isPresent()) {
                    var selected = worldSelectionList.getSelectedOpt().get();
                    selected.joinWorld();
                }
            }
        }
    }
}

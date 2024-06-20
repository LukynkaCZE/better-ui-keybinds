package cz.lukynka.betteruikeybinds.client.keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Map;

public class MultiplayerListJump implements Keybind {

    private final Map<Integer, Integer> keyMap = Map.ofEntries(
            Map.entry(GLFW.GLFW_KEY_1, 1),
            Map.entry(GLFW.GLFW_KEY_2, 2),
            Map.entry(GLFW.GLFW_KEY_3, 3),
            Map.entry(GLFW.GLFW_KEY_4, 4),
            Map.entry(GLFW.GLFW_KEY_5, 5),
            Map.entry(GLFW.GLFW_KEY_6, 6),
            Map.entry(GLFW.GLFW_KEY_7, 7),
            Map.entry(GLFW.GLFW_KEY_8, 8),
            Map.entry(GLFW.GLFW_KEY_9, 9),
            Map.entry(GLFW.GLFW_KEY_0, 10),
            Map.entry(GLFW.GLFW_KEY_M, 1)
    );

    @Override
    public String getActionName() {
        return "Jump to Server in Server List";
    }

    @Override
    public List<Integer> getKeybinds() {
        return keyMap.keySet().stream().toList();
    }

    @Override
    public Class<?> getScreen() {
        return JoinMultiplayerScreen.class;
    }

    @Override
    public int getRequiredPresses() {
        return 0;
    }

    @Override
    public void handle(Integer key) {
        var screen = (JoinMultiplayerScreen) Minecraft.getInstance().screen;
        assert screen != null;
        try {
            var server = screen.getServers().get(keyMap.get(key) - 1);
            for (GuiEventListener child : screen.children()) {
                if(child.getClass() == ServerSelectionList.class) {
                    var entry = ((ServerSelectionList) child).children().get(keyMap.get(key) - 1);
                    screen.setSelected(entry);
                    screen.joinSelectedServer();
                    return;
                }
            }
            System.out.println(server.ip);
        } catch (Exception ex) {
            //ignore
        }
    }
}

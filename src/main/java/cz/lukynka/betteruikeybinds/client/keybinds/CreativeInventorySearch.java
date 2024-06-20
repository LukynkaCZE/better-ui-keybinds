package cz.lukynka.betteruikeybinds.client.keybinds;

import cz.lukynka.betteruikeybinds.client.mixin.CreativeModeInventoryAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTabs;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class CreativeInventorySearch implements Keybind{

    @Override
    public String getActionName() {
        return "Set selected tab to Search";
    }

    @Override
    public List<Integer> getKeybinds() {
        return List.of(GLFW.GLFW_KEY_TAB);
    }

    @Override
    public Class<?> getScreen() {
        return CreativeModeInventoryScreen.class;
    }

    @Override
    public int getRequiredPresses() {
        return 1;
    }

    @Override
    public void handle(Integer key) {
        var screen = (CreativeModeInventoryScreen) Minecraft.getInstance().screen;
        var accessor = (CreativeModeInventoryAccessor) screen;

        assert accessor != null;
        if(accessor.getSelectedTab() == CreativeModeTabs.searchTab()) {
            accessor.getSearchBox().setValue("");
            accessor.callRefreshSearchResults();
        } else {
            accessor.callSelectTab(CreativeModeTabs.searchTab());
        }
    }
}

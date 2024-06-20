package cz.lukynka.betteruikeybinds.client.mixin;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CreativeModeInventoryScreen.class)
public interface CreativeModeInventoryAccessor {

    @Accessor("selectedTab")
    CreativeModeTab getSelectedTab();

    @Accessor("searchBox")
    EditBox getSearchBox();

    @Invoker
    void callSelectTab(CreativeModeTab creativeModeTab);

    @Invoker
    void callRefreshSearchResults();
}
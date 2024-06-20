package cz.lukynka.betteruikeybinds.client.mixin;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SelectWorldScreen.class)
public class SelectWorldScreenMixin {

    @Shadow protected EditBox searchBox;

    @Shadow private WorldSelectionList list;

    @Inject(at = @At("TAIL"), method = "init")
    private void init(CallbackInfo ci) {
        this.searchBox.setResponder((string) -> {
            this.list.updateFilter(string);
            try {
                var firstChildren = this.list.children().getFirst();
                list.setSelected(firstChildren);
            } catch (Exception ex) {
                //ignore
            }
        });
    }

}

package cz.lukynka.betteruikeybinds.client.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.item.CreativeModeTabs.SEARCH;

@Mixin(CreativeModeTabs.class)
public class CreativeModeTabsMixin {

    @Inject(at = @At("HEAD"), method = "getDefaultTab", cancellable = true)
    private static void getDefaultTab(CallbackInfoReturnable<CreativeModeTab> cir) {
        cir.setReturnValue(BuiltInRegistries.CREATIVE_MODE_TAB.getOrThrow(SEARCH));
    }

}

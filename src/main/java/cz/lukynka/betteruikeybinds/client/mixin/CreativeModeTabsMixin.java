package cz.lukynka.betteruikeybinds.client.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreativeModeTabs.class)
public class CreativeModeTabsMixin {

    @Shadow @Final public static ResourceKey<CreativeModeTab> SEARCH;

    @Inject(at = @At("HEAD"), method = "getDefaultTab", cancellable = true)
    private static void getDefaultTab(CallbackInfoReturnable<CreativeModeTab> cir) {
        cir.setReturnValue(BuiltInRegistries.CREATIVE_MODE_TAB.getValueOrThrow(SEARCH));
    }

}

package cz.lukynka.betteruikeybinds.client.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {

    @Shadow
    @Final
    private static Logger LOGGER;

    @Inject(at = @At("HEAD"), method = "onScroll", cancellable = true)
    public void onScroll(long l, double d, double e, CallbackInfo ci) {
        if (l == Minecraft.getInstance().getWindow().handle()) {
            if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_LEFT_ALT)) {
                ci.cancel();
                var isDirectionUp = e == 1;
                var stepSize = 5;

                var fov = Minecraft.getInstance().options.fov();
                var newFov = 0;
                if(!isDirectionUp) {
                    newFov = fov.get() + stepSize;
                } else {
                    newFov = fov.get() - stepSize;
                }

                fov.set(Math.clamp(newFov, 30, 110));
                Minecraft.getInstance().gui.setOverlayMessage(Component.literal("FOV now: "+ fov.get() + "%").withStyle(ChatFormatting.YELLOW), false);
            }
        }
    }
}

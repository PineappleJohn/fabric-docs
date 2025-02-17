package org.darwin.fabricdocs.client.MixinExample;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    // Damage example one - Cancelling
    @Inject(at = @At("HEAD"), method = "damage")
    public void cancelDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
       cir.cancel(); // This will cancel the invoking, so the player won't take damage.
    }
    // Damage example two - Returning
    @Inject(at = @At("HEAD"), method = "damage")
    public void returnDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel(); // This will cancel the invoking, so the player won't take damage.
    }
}

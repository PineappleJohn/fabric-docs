---
title: Advancing
description: This page covers how to cancel functions and return values
authors:
  - pineapplejohn
---

## Canceling {#canceling}
Canceling is very useful when it comes to mixins and injecting. It may sound small but it can change the functionality of a mod. <br/>
For example, if you're mod has a flying component and you want it to not take damage when landing, you can cancel the damage method. <br/>
Invoking a cancel is simple, here's what it looks like.
```java
@Inject(at = @At("HEAD"), method = "damage")
public void cancelDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
  cir.cancel(); // This will cancel the method, so the player won't take damage.
}
```

## Returning {#returning}
Returning is mainly used for custom events, which will be introduced later, but it can be very useful. Heres what the event would look like,
```java
YourCustomEvent.EVENT.register((world, source, amount) -> {
  if (source.isOf(DamageTypes.FALL) && world.isClient)
  {
    return true;
  }
});
```
Here is the mixin class,
```java
@Inject(at = @At("HEAD"), method = "damage")
public void cancelDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
  boolean cancel = YourCustomEvent.EVENT.invoker().interact(world, source, (boolean) (Object) this);

  if (cancel)
  {
    cir.cancel();
  }
}
```
<sup><sub>Keep in mind, this is example code, it most likely won't work without adjustments</sub></sup>

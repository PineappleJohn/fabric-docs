---
title: Mixins
description: This page covers how to use Mixin classes to inject code.
authors:
  - pineapplejohn
---

## Mixin {#mixin}
Mixin classes are used for a multitude of things. For example, creating custom events, 
changing code, and much more. Mixin classes allow you to select a class and inject code into a method. <br/><br/>
To declare a mixin class, add the annotation ```@Mixin([YourClassHere].class)``` <br/> Then, go to ```src/client/resources/[YourModIdHere].client.mixins.json``` and correct the *package* directory. <br/> Finally, within the mixins json file, add ```"mixins" : [(YourMixinClassNameHere)]``` <br/>
Result: <br/>
```java
@Mixin(PlayerEntity.class)
public abstract class PlayerEventsMixin {
  
}
```
<br/>
Now, by writing any variables or methods into this mixin class, it will injected into the selected class. <br/>
Using the right annotations, mixins also allow you to access private (hidden) or protected variables.

## Injecting {#injecting}
Injecting can seem confusing at first, but in reality it is quite simple. There are a few places you can inject into when injecting. The main places you need to know are: <br/>
- ```HEAD```, Injects before any lines of code. <br/>
- ```CTOR_HEAD```, Injects after any ```super``` methods or initialization. <br/>
- ```TAIL```, Injects after all the code. <br/>
There are a few others, but generally all you need to know are these ones. <br/>
This is what the basic injection looks like:
```java
@Inject(at = @At("HEAD"), method = "damage")
public void onDamaged(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
  // Your code here...
}
```

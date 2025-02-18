---
title: Creating custom events
description: This page will teach you how to create custom events with callbacks.
authors:
  - pineapplejohn
---

::: warning 
You **must** already have a Mixin class prepared! If you don't, see [**Mixins**](../develop/mixins/mixins). 
:::
## Disclaimer {#disclaimer}
Creating custom events uses ```lambda``` (```(args)->{func}```), before writing any custom events, do some research into it. A few good sources to start are <br/>
- [w3schools.com](https://www.w3schools.com/java/java_lambda.asp)
- [geeksforgeeks.org](https://www.geeksforgeeks.org/lambda-expressions-java-8/)
- [docs.oracle.com](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)

## Creating a custom event {#making-the-event}
To create a custom event with callback, you need to use an ```interface```. So, create a file and write, <br/>
```java
public interface MyCustomCallback {
}
```
::: warning 
Make sure that your callback interface is not in the same directory as your mixin class.
::: 
<br/>
Next, create an event variable with your interface. <br/>
```java
Event<MyCustomCallback> EVENT = EventFactory.createArrayBacked(MyCustomCallback.class,
        (listeners) -> (<Any function parameters>) -> {
            for (MyCustomCallback listener : listeners) {
                <Your returned type> result = listener.interact(<Any function parameters>);
 
                if(result != ) {
                    return result;
                }
            }
 
        return <Your returned type>;
    });
 
    <Your returned type> interact(<Any function parameters>);
```
<sup> You don't need to name it EVENT, it's just customary. </sup> <br/>
Your mixin class might have a specific return type, sometimes it's primitive. If it is an ```ActionResult``` or a derivative of one, sometimes you might have return it with ```ActionResult.PASS```, this is recomended as it won't interfere with other mods or the games processing. Also, you may benifit from adding Javadoc comments. 
```
/**
 * Callback for <YourCallback>
 *
 * Upon return:
 * - SUCCESS cancels further processing and continues with normal behavior.
 * - PASS falls back to further processing and defaults to SUCCESS if no other listeners are available
 * - FAIL cancels any further processing.
/**
```

## Modifing your mixin {#modifing-mixin-class}
You may have seen this in [returning](../develop/mixins/canceling), this is where the mixin class invokes the interfaces listeners. In summary, the mixin class invokes the event. Here is what the code would generally look like,
```java
@Inject(at = @At("HEAD"), method = "<MyMethod>")
public void cancelDamage(<MyArgs>, CallbackInfoReturnable cir) {
  boolean cancel = MyCustomCallback.EVENT.invoker().interact(<MyArgs>);
  if (cancel)
  {
    cir.cancel();
  }
}
```

## Registering your event {#registering}
After creating your custom callback, you will need to create a ```listener``` which will run whenever your event is called. Creating listeners is incredibly simple, just write this in your client initialization file:
```java
MyCustomCallback.EVENT.register((<MyArgs>) -> {
  // Event code goes here...
});
```
Now whenever your event happens in-game, your code will run. Check **Usages** for a more in-depth view.

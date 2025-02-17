---
title: Mixins
description: This page covers how to use Mixin classes to inject code.
authors:
  - pineapplejohn
---

## Mixin {#mixin}
Mixin classes are used for a multitude of things. For example, creating custom events, 
changing code, and much more. Mixin classes allow you to select a class and inject code into a method. <br/><br/>
To declare a mixin class, add the annotation ```@Mixin([YourClassHere].class)``` <br/> Then, go to ```src/client/resources/[YourModIdHere].client.mixins.json``` and correct the *package* directory. <br/> Finally, within the mixins json file, add ```"mixins" : [(YourMixinClassNameHere)]```
<br/>
Now, **anything** in this class is injected into the selected one. <br/> <br/>
Using the right annotations, mixins also allow you to access private (hidden) or protected variables.

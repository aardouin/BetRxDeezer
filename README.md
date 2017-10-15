BetRxDeezer
-------

This is a sample app to show how I would build an app with RxJava using MVVM architecture. 


The project was build using AndroidStudio 3.0 beta 7 and gradle 4.1 .
It won't compile with an y lower version of AndroidStudio.

The whole project is written in kotlin.

Technical decisions 
-------

As I'm not experimented with Rx and MVVM architecture, I  gave a try to few tools that I'm not used to.

###Databing :
I took the path to use databinding as much as possible in order to reduce the boilerplate for view manipulation and to take advantage of MVVM as much as possible.
 There are still some incertitudes in my mind about how `ViewModel` should interact with `Context`. 
 
 Here is the reflexion that led me to how I did it. 

 1- ViewModel should be testable easily so avoiding anything that involves Android classes is primordial
 
 2- The solution might be to use interfaces to provide a `context`, but what about **memory leaks** that would occur if a `ViewModel` is retained thus retaining the `Context`.
 
 3- The only case where I needed a `Context` was for String resources. So I opted for a solution where the context is given via the `databinding`. That way, I wasn't retaining any `Context` in the `ViewModel` and it is still **testable** with Roboelectric.
 
 
###ViewInjection
As I am using `databinding ` view injections library such as `ButterKnife` isn't required. 
Therefor I opted for `kotlin-android-extensions` giving me access to views really easily.

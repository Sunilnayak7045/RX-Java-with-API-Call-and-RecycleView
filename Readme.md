
RXJava:

->Synchronous : will run in the same thread.

->Asynchronous: will run in a different thread.


->RxJava is a JVM library that uses observable sequences to perform asynchronous and event-based programming. 
  Its primary building blocks are triple O’s, which stand for Operator, Observer, and Observables. 
  And we use them to complete asynchronous tasks in our project. It greatly simplifies multithreading in our project.

-> mainThread() returns a scheduler, which aids in performing tasks on the main UI thread, which is primarily used in the Android project. 
   So, here we have AndroidSchedulers.
   mainThread() is used to gain access to the application’s main thread so that we can perform actions such as updating the UI. 
   Because updating the UI from the background thread is technically not possible in Android, 
   we can use AndroidSchedulers.mainThread() to update anything on the main thread. 
   Internally, it makes use of the Handler and Looper concepts to perform the action on the main thread.

example1: https://www.geeksforgeeks.org/rxjava-for-android/

example2: Understanding RxJava Create and fromCallable Operator : https://www.geeksforgeeks.org/understanding-rxjava-create-and-fromcallable-operator/

-> An observable object can have one or more observers. 
   An observer may be any object that implements interface Observer. 
   After an observable instance changes, 
   an application calling the Observable's notifyObservers method causes all of its observers to be notified of the change by a call to their update method.

-> When an observable object is newly created, its set of observers is empty.

-> What are Disposables? Disposable means they are short lived or meant to be discarded after use. 
   The same idea is conveyed in RxJava’s Disposables.

->A Disposable is a stream or a link between an Observable and an Observer
  When an Observer subscribes to an Emitter, or Observables, you create a link. 
  This link takes up resources which later become disposable “solid waste”. 
  You need to handle this waste if the  link is going to run for a long time.
  Observable has a method called onComplete() that will do the disposing for you when called. 
  Many at times though, you will find it more beneficial and convenient to have the ability to cancel your subscriptions easily and at any time.
  More complex case, when the stream will run endlessly and you’ll use Disposables to handle them to prevent memory leaks.

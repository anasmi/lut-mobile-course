# Learning diary

## Part1
25.01.2022 & 26.01.2022 &28.01.2022

I started to set-up environment by watching the first introduction video. Video is a bit outdated
as it still uses Java as a default language, while Kotlin is a preffered default option language.
I've decided to use Kotlin in this course, as I would like to learn it and this seems like a good opportunity.

Android version I am using is the oldest available - 32, so it doesn't match the video's version,
but I guess it's fine.
The first impression after creating a new project is that it's nice to see that there is an editor available.
Positioning of an element inside a `ConstraintLayout` relative to its borders or other components is a nice feature, especially for beginner.

It makes sense to give ids to elements also specifying for component is in question. It's later easier to
validate that a correct element is imported in kotlin file.
Compared to a video, a top violet header is not presented in editor anymore, but it's still rendered
when trying out in an emulation device.
To get my app working correctly I added null/empty checks. Otherwise, application
crushed when tried to parse an integer from string.
As this point, I was surprised there no logs directly visible anywhere, but debugging as was shown in the video
could have helped.

Other notes:
- Editor's activity_main.xml file can be found under `app/src/main/layout/` folder. If editor is
 accidentaly closed it can be re-opened from there.

- References string is a nice thing. Can be found under `values\strings.xml`. If any string is 
  used throughout the application it can be easily added there and referenced everywhere.
  No need to go through the whole application to alter value if there is a need later on.
- `AndroidManifest.xml` is located currently under the main folder. This is a file that specifies
how application should be started, including what is the main activity of the app.
  - Kotlin has also auto-import settings.
  - First, an api needs to be installed and licenses accepted. Otherwise, simulator doesn't start up.

## Part 2
  Core elements to android development are :
  - Activity : a rectangular area that displays something, not necessary a view only. Button also extends activity, for example.
  - Intent : an action we want to be performed. Works as an imediator, represents what a user what to do.
  - IntentService: does a task intent wants to be performed. Can be done on background.
  - BroadcastReceivers: received an intent from a `sendBroadcast` method often indicating that some work has been completed.

In this part, following the video we are going to create a simple navigation example, where two buttons are added to main screen. Clicking one button
activates another view in the application and the second button opens Chrome browser, if one is available.
I've created a new project for this application. So this project is not related to the one created in previous part. Alternative would be to create
separate branches for each part, but it's a bit easier to follow and add changes later on, if everything is present in master.

Aligning elements vertically/horizontally in a parent layout, I have to first select an element. Otherwise, if I select all child components and align them,
they are overlapping as they are all moved to the midle.
So, in this example, the second button is positioned relatively to the first one. It's top is attached to the bottom of the first button.
Both are aligned to be in the middle of the view, and the first button is centered vertically in its parent layout.
Making alignments explicit asserts that view is rendered properly in both orientations: portrait and landscape.

Notes:
- `strings.xml` file is also good for internalization of an app.
- Activity has lifecycle of events, thus we creating the code inside `onCreate` method, as it's the first one to be called. 
That is where we can attach listeners.
- To launch another activity, we call `startActivity` method

## Part 3

ListView is located under Legacy components, thus I am using `RecyclerView` instead. https://stackoverflow.com/questions/50079026/why-are-some-views-located-inside-the-legacy-tab-in-android-studio-3-1-and-what
`RecycleView` is designed for the same person, but it's performance optimized. When an item inside the list scrolls from the visibility zone, components created for it are re-used by elements, that come into view.
So, ultimately, if we have hundred items to display, only part of them is actually rendered. This improves performance and responsiveness of a view, where this view is presented.

Neverteless, using another element added a lot of complexity to the exercises, as a custom Adapter must be implemented in order for an item to be shown from the beginning.
Also, each item gets its own `ViewHolder` that defines how each separate has to be displayed. I've implemented this class as an inner class of the Adapter.
Important notices is that unless a layout manager of the RecyclerViewer is specified, no items is rendered at all. Also, a textView of an item must have width and height of the content, not parent element.
Otherwise, only one element got rendered.
Next, we have added textViews for descriptions and prices. Here, I have assumed that amount ot items in each array (items, descriptions, prices) is the same.
Othwerwise, adapter might through ArrayOutOfBoundException. The instructor in the video later on also creates a custom adapter to assign values from resource arrays into the view correctly.
`RecyclerView` doesn't have an itemClickListener anymore, so I've assigned a listener to each individual item inside the `CustomAdapter` class. Thus launching of a details activity happens from it.
Due to this we should pass `MainActivity` object to the `CustomAdapter`, as an activity can only be started from a class extending the `Context. For example, an Activity extends Context, thus an activity can start another one.

I've used different items in the list, as I didn't want to use pictures from the web to avoid any legal issues of using third party photoes. Apart from that application has the same functionality as the one presented in the video.

Pictures used in the application are put under `drawable` folder that is located under `res`. Referencing a file is easy as it happens by name using `R.drawable.apple`, for example.
To prevent application from failure when encountering a big picture, we first scale it down if necessary and only after that assign it to the ImageView.
Useful links were, at least : 
- https://stackoverflow.com/questions/50079026/why-are-some-views-located-inside-the-legacy-tab-in-android-studio-3-1-and-what
- https://developer.android.com/guide/topics/ui/layout/recyclerview
- https://stackoverflow.com/questions/27705928/recycler-view-not-showing
- https://stackoverflow.com/questions/38926071/recyclerview-setadapter-does-not-accept-arrayadapter
#Learning diary
25.01.2022 & 26.01.2022
Started to set-up environment by watching first introduction video. Video is a bit outdated
 as it still uses Java as a default language, while Kotlin is a preffered default.
 I am going to use Kotlin in this course, as this is the default choice and I would like to learn it.
Android version I am using is the oldest available - 15, so it doesn't match the video's version,
but I guess it's fine.
The first impression is that it's nice to see that there is an editor available. Also, positioning of an element
inside a `ConstraintLayout` relative to its borders or other components is a nice feature, especially for beginner.

It makes sense to give ids to elements also specifying for component is in question. It's later easier to
validate that a correct element is importen in kotlin file.
Notes:
- Editor's activity_main.xml file can be found under `app/src/main/layout/` folder. If editor is
 accidentaly closed it can be re-opened from there.

- References string is a nice thing. Can be found under `values\strings.xml`. If any string is 
  used throughout the application it can be easily added there and referenced everywhere.
  No need to go through the whole application to alter value if there is a need later on.
- `AndroidManifest.xml` is located currently under the main folder. This is a file that specifies
how application should be started, including what is the main activity of the app.
  - Kotlin has also auto-import settings.
  - First, an api needs to be installed and licenses accepted. Otherwise, simulator doesn't start up.
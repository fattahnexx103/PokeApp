# PokeApp
Pokedex App using Kotlin and MVVM Architecture

Language for this app used was Kotlin
Architecture used for this program was MVVM
I used Android Navigation to navigate among the fragments 
I used retrofit for the api calls
I used glide to load the images
I used pagination for the recyclerview to load more items upon scrolling to the end of it 
THe viewmodel was used to manage the data coming from the api calls
I used Observables to observe changes in the data and update the views accordingly


Asssumptions:
I made 20 api calls for each of the 20 items in the recylcer view. So n items would equal n calls. 
I did not use the homepage json which only had the pokemon and the url to the pokemon.
I figured I can directly use the url with the pokemon number to get the details for that pokemon. 
So instead of making 21 calls for 1 page and complicating my model and logic, im using 20 calls and simplifying my logic and model. 


Things I would have wanted to added given more time:
  Dependency injection using Dagger
  Unit Tests
  Android Palette library for the UI and the detail fragment
  Android Data Binding

# Console paint

## About

This is a simple console app that draws ascii symbols on canvas.

<img width="448" alt="Screenshot 2022-01-24 at 00 20 33" src="https://user-images.githubusercontent.com/24525928/150698433-ebf6f09b-aef5-44c0-b743-aed94105051f.png">


## How Tos

  ```sh
  # Build and run app
  make

  # Run tests
  make test

  # Just run a built app
  make run
  ```

## Some implementation notes

- **What's interesting is that 2 years ago I was working on another console
  app:** [Music advisor](https://github.com/pochka15/hyperskill.music-advisor). I've taken some code from this repo.
- I didn't dig into the corner cases like what if it's given a meaningless input like x coordinate = -1000 and so on
- Philosophy
    - I try to be pragmatic. If I need to implement some known algorithm, and I know that internet can help me with it
      then I just google it.
    - If there is no sense to extract 5 lines of code into a separate method I just don't extract them and leave a
      comment. If there is no stable abstraction, and I don't see any reason to create an interface for some class I
      just don't create it like in case with [Canvas.kt](src/main/kotlin/drawing/Canvas.kt)
    - I try to follow KISS, SOLID, in each commit I try to read my code and ask myself if it all makes sens.

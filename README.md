# Angry birds clone
Project to try various OOP patterns on a simple game concept. This game does not contain destruction physics, just the various means for the launcher control and movement of birds.


## Running

You will need at least JDK 21 and Gradle 8.3, potentially JavaFX.
Clone the repository and in the cloned repo run `gradle run`.


## Controls
**Up/Down arrows** - Move the launcher up or down\
**Left/Right arrows** - Rotate the launcher\
**W/S** - Increase/Decrease power of the launcher\
**Space** - Shoot

**M** - Toggle between missile types\
**N** - Toggle between shooting modes\
**ENTER** - Activate special attack of a bird (dependent on its way of flying)\
**Q/E** - Increment/Decrement number of birds in Dynamic Shooting Mode\
**ESC** - exit the game

**BACKSPACE** - revert one shot back



## Shooting modes
**Single** - Shoot one bird \
**Double** - Shoot 2 birds in a cone \
**Dynamic** - Shoot set amount of birds in a cone


## Missile types
**Simple** - Birds fly in a straight line\
**Realistic** - Birds fly in a ballistic curve\
**Falling** - After activating, birds just fall in a straight down\
**Split** - After activating, each bird splits into three


## Cheats
After pressing **P** key, you can type one of existing cheats. After the cheat is typed, confirm it by pressing **P** key again.
There is no UI showing, that the cheat is being typed.

`mute` - enable/disable the sounds\
`debug` - enable/disable the debug mode (unlimited amount of birds in the scene and revert back after any action)


## Game
Game is composed of one scene with the game loop. No menu or saving system. Each time the games is ran, positions of pigs are randomly generated.
Each pig has random amount of health. This is the number of birds that need to hit said pig. After the bigs is killed, it can be turned into a wall. If allpigs are killed, any key press will exit the game.
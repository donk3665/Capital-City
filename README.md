# Capital City

## How to play the game
If you want to manually build the code:
In the ```src/main/java/main.Main``` click the play button or run the file and a popup window 
should show. If the program seems unresponsive/slow during the game phase of the program, restart the program with the following vm argument: -Dsun.java2d.d3d=false

If you want the easier way:
Go to the releases panel on the right of the github page, and download the latest release. Run the file using the bat file.

## Playing the game
Playing the Game is straight forward, there is a context of text explaning what is happening
on the current state of the game and option buttons presenting choices of what the player can do.
* IMPORTANT NOTE: This is still an initial creation of a monopoly game, there are likely bugs that need to be fixed/improvments that can be made

# Navigating the project files in the repository
This section outlines the brief overview of the structure of the project.

### Entities
This directory contains all the core business rules and is the lowest level
 
### Logic
The Backbone of this game; everything in this file controls the states as the game progresses
and different outcomes depending on various player inputs

### UseCases
The Logic pertaining to a user interaction with the screen as well different spaces
on the board

## Persistence
The Logic pertaining to storing game data in and getting game data from a file

## GUI
The front end of the program.

### resources
All of the assets and resources used in the program

### Buttons
Classes which control buttons used in the program

### GUIDataTransfer
Interfaces used for transfering data to the GUI

### GUIInteractors
Logic pertaining to transfering info from the back end to the front end

## Network
The functions used for multiplayer connectivity

## Test
Tests are out of date, and not implemented.

## Deprecated
Files that were possibly used in a previous build, but are no longer used.

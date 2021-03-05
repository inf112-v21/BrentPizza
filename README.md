# INF112 Project BrentPizza 

# RoboRally :robot: :pizza:

## How to run the program

The program is built using Maven. To run the game, run  serverStart.java in the master branch. When main file is run, the game will ask for amount of players. 
2 is recommended, however 4 is maximum. To determine the robots action, choose 5 cards from the 9 options that are given and press the ready button. 

Recommended screen resolution: 1920x1080

To play **Multiplayer:** 

To play multiplayer run the **main1.java** after running **main.java**. Robot movement is identical to single player, the game state will be updated after the ready button is pressed. 


# How to do manual testing
To run the automated tests you have to start ServerStart class and input 11 players into the console. This liet's you start the game with 11 players to you can run 11 automated tests. After that you have to run the tests one by one because when you run them all together they crash. This is probably due to how we initiate the game in the tests, so this will be fixed at a later date. 

To test the functionality where you ass cards to the program table you have to do it manually. This is done by starting the server with 1-4 players. It is recommended to move to the center of the board to make sure you don't walk off when running the programmed cards. This is done by turning the robot around with spacebar and moving with arrow up. When in the center you can add a card from the players hand (in the bottom center of the screen) to the program cards (right of the screen) by clicking them in the order you want them to be excecuted. After you have added 1-5 cards and have the desired movement you want press "READY" to excecute the current program. 

## Known bugs
- For the game to work properly the generated window can't be altered. Otherwise when you choose cards you will not get the intended card. This is due to the way we set resoulution in the program, this needs to be a global setting instead of the local solution we are using now
- For the player to be able to watch the game being won, there has been installed a delay. This will not be a part of the final product, however it is here
due to its information value. This enbales however the player enable the win condition several times before the applictions shuts down.

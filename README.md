# INF112 Project BrentPizza 

# RoboRally :robot: :pizza:

## How to run the program

The program is built using Maven. To run the game, run  serverStart.java in the master branch. The server will ask for amount of players in the console. 
2 is recommended, however 11 is maximum. After that run either Main or Main1 ro start the game. To determine the robots action, choose 5 cards from the 9 options that are given and press the ready button. When the games is started, press the **Start**-button on the screen to start the game. If you are playing multi-player, the robots will not move until all players have chosen their cards and pressed ready. Keep eye on console to be properly updated on current game events. Proper GUI messages will be implemtned in the last iteration. 

Recommended screen resolution: 1920x1080

To play **Multiplayer:** 

To play multiplayer run the **serverStart.java**, **main1.java** and **main.java**. Then press ready on both applications. 

# How to do manual testing
To run the tests. Just press run on the test you wish to run. Then you have to press ready in the application. Wait for the game to load. Then you have to close the application. We will move to a headless version of the application on the next iteration. 

To test the functionality where you add cards to the program table you have to do it manually. This is done by starting the server with 1-4 players. It is recommended to move to the center of the board to make sure you don't walk off when running the programmed cards. This is done by turning the robot around with spacebar and moving with arrow up. When in the center you can add a card from the players hand (in the bottom center of the screen) to the program cards (right of the screen) by clicking them in the order you want them to be excecuted. After you have added 1-5 cards and have the desired movement you want press "READY" to excecute the current program.

## Known bugs
- For the game to work properly the generated window can't be altered. Otherwise when you choose cards you will not get the intended card. This is due to the way we set resoulution in the program, this needs to be a global setting instead of the local solution we are using now
- For the player to be able to watch the game being won, there has been installed a delay. This will not be a part of the final product, however it is here
due to its information value. This enbales however the player enable the win condition several times before the applictions shuts down.
- If the robot walks over the win flag in the middle of the programmed sequence he does not win when he should

# My Personal Project: “Cake Rush” Game

## Description of the project
My project is **a game and a memory exercise** where the user plays as a baker who must **recreate a cake from memory**. At the start of each round, the user is shown a randomly generated cake. Once this preview ends, the user has to recreate the image they saw by selecting the correct options from cake elements (number of tiers, color of the cake, glazing color, decorations, etc.). Each element has multiple options to choose from; for example, decorations can include a candle, note, balloon, or flower. If the recreated cake matches the preview cake, the player earns a point and continues to the next round. **The aim is to complete as many rounds as possible.** The game is lost when the player has lost three times. 

## Who will use the game and why it interests me
This game is great for people who enjoy challenges that **train memory and focus**. It can be played by students to improve their memory skills or simply as a good recreational activity. The reason why I chose this idea is because I want to challenge myself and create a game. As well I plan to draw the cake elements myself and include them in the game to make it more visual. Besides, I enjoy baking, which is why I am excited to create a game about cakes.

## User stories
- As a user, I want to be able to save the round of the game to the game session.
- As a user, I want to be able to view the list of played rounds in the game session.
- As a user, I want to be able to view the game’s journal and see the list of all games sessions.
- As a user, I want to be able to preview the cake before the round starts so I would be able to recreate it.
- As a user, I want to be able to select different cake elements to recreate a cake.
- As a user, I want to be able to see the total score at the end of the game. 
- As a user, when I select the quit option from the menu on the main screecn, I want to be able to save all played games to file (if I so choose)
- As a user, when I start the application, I want to be able to load all played games from file (if I so choose)

# Instructions for End User

- You can create a new game by pressing the "Create New Game" button on the main menu.
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by pressing the "Play" button on the game menu screen. That will start a new round that will be added to the game. After the round is finished, a popup is shown prompting you to start a new round by pressing "Continue" or return to the game menu by pressing the "Exit" button.
- You can view the panel that displays the list of rounds that have already been added to the game by pressing the "View Rounds" button on the game menu screen.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the "View Games" button on the main menu screen. Then, by clicking the "Show Active Games" button, you can filter the games by their state.
- You can sort games by total score by pressing one of three toggle buttons: "NONE", "Arrow Up", "Arrow Down", on the Played Games screen.
- You can press the active "Continue" buttons in the game list to open the active game menu and continue playing.
- You can locate my visual component by starting to play a round.
- You can save the state of my application by pressing "Yes" on the popup that is shown after you choose to exit the application.
- You can reload played games that were saved to a file by clicking "Yes" on the popup that is shown when the application starts.

# Phase 4: Task 2

## Not loading existing games
Tue Nov 25 21:13:10 PST 2025
Starting empty game library

Tue Nov 25 21:13:18 PST 2025
Created new game with id 1

Tue Nov 25 21:13:21 PST 2025
Starting new round with random java.util.Random@2f1902b9

Tue Nov 25 21:13:30 PST 2025
Completed round with score 0 correctly guessed 1/5

Tue Nov 25 21:13:30 PST 2025
Added new round to the game with id 1

## Loading and saving games
Tue Nov 25 21:18:27 PST 2025
Starting empty game library

Tue Nov 25 21:18:29 PST 2025
Loading game library with next id 4

Tue Nov 25 21:18:29 PST 2025
Loaded existing game with id 1

Tue Nov 25 21:18:29 PST 2025
Loaded existing game with id 2

Tue Nov 25 21:18:29 PST 2025
Loaded existing game with id 3

Tue Nov 25 21:18:39 PST 2025
Saving game library with next id 4 and 3 games

# Phase 4: Task 3

I think a lot can be improved in the UI class because, in the UI, many classes heavily depend on each other. I could split the RoundScreen class into smaller components. For example, one part could handle just the visual display, and the other could depend on GameRound and interact with the model. I think I would be able to remove, in many places, the dependency on both GameRound and GameSession if I added an ID of the game to the GameRound model. Then I will know what GameRound belongs to which GameSession, without the need to have both of them. This way, UI components that relate only to the round would depend solely on GameRound.

I think that, since all the main screens depend on the MainPanel, which in my application acts as the main window that manages showing different screens, I can just use the singleton pattern to create one instance of the panel and remove all the current dependencies. For the model class, I think a good improvement would be converting the enums from CakeElements into separate classes with different subclasses. That would make it easier to add more elements in the future without needing to adjust a lot of code. This would also help reduce repetition in methods related to the enums, as they all look very similar. Then the Cake class could just store a list of different cake elements and perform certain actions based on the object type.
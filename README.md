# CompassDirect
 What is CompassDirect?

CompassDirect (CD) is a lightweight, easy-to-use, player-based plugin which helps players find, save, and tell locations to each other. Whenever someone uses a CD compass, they get their own set of locations that server admins can choose, as well as ones that the player can choose. For example, your server has a set of shops, a spawn, a wilderness area, and a plots zone. However, they are scattered around your map and isn't very "user friendly" to find them. Well, thanks to CD, you can 'globally' save these locations, so any compass that any player uses, they will always have those locations saved. Now lets say you are a player, and come across a dungeon, and for some reason leave it to come back another time. Well normally, people would just have to remember where that location is. But with CD, a simple command will save that location into their locations list so that they can locate that location whenever they want. Also, lets say Bob here finds a location, and wants to tell Jeremy who's in his faction that location, a simple command will prompt Jeremy if he wants to save the location or not. (Like a teleportation request, but no telepotration).
Commands and Uses
* /cd activate

~Activates the compass in your hand
* /cd add [location name]

~This saves a location into the players list where he is currently standing with the name s/he provided.
* /cd remove [location name]

~This removes a location from the player's list with the specified name
* /cd tell [player name] [location name]

~Tell a specified player one of your locations with the specified name
* /cd accept

~Accept a player's location tell
* /cd decline

~Decline a player's location tell
* /cd list

~Lists all the locations that the player has saved
* /cd addglobal [spawn:safe:unsafe:shop] [name]

~Adds a specified location type to all players and sets it as a global location
* /cd removeglobal [name]

~Removes a specified global location from all players and global location list
* /cd all

~Lists all PENDING location tells
* /cd help

~Pretty self explanatory...
Permissions
* compassdirect.op

~gives access to command /cd addgloball

~gives access to command /cd removeglobal

~gives access to command /cd all
* compassdirect.addremove

~gives access to command /cd add

~gives access to command /cd remove
* compassdirect.activate

~gives access to command /cd activate
* compassdirect.share

~gives access to command /cd tell
Compass Use

~Using the compass in game is very easy. With a compass in hand, right click to cycle up once through your location save list. Left click to cycle down one location. When you cycle, the pointer on the compass points directly toward the locations saved coordinate. Its that simple!
Installation and Management

~Just plop the .jar file into your plugins folder, and restart server.

~When the server is reloaded/restarted, a config file will be produced with default locations. The locations are "Wilderness", "Plots", "Spawn", and "DevicsShop". Notice how there aren't any spaces in the names, nor special characters [!@#$%^&*()-_=+|...etc...]

~These are the global locations that all player will receive when they use a CD compass

~~~"Spawn" locations will appear in-game with a purple color

~~~"Safe" locations will appear in-game with a green color

~~~"Unsafe" locations will appear in-game with a red color

~~~"Shop" locations will appear in-game with a blue color

~~~Any player saved locations will appear in the players section underneath their name and will appear in-game with a yellow color

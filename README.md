kebacoinCasino
It's a project for my university class. 
The general rule was that it needed to be a project using the Spring framework, and either SpringController or RestController.
I used REST in this project, but the requests are not stateless, because I need to check if the player is playing fairly.

I implemented three basic games from casinos: roulette, slot machine and blackjack. These not include any advanced logic, just basic Collection shuffles, and the game logic itself.

The project is not in a fully finished state but it's working.

### I'm not using this software to earn any money in any form and the software uses a made up currency kebacoin.

For running the application

```batch
gradlew bootRun
```

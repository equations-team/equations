# Equations

* [Trello](https://trello.com/b/NrDTWWid/equations)

## Team Members
* [Professor Limber](https://github.com/mlimber)
* [Derrek Woodworth](https://github.com/DerrekWoodworth)
* [Nicanor Vergara](https://gitbub.com/npvergara)
* [Stephen Mingolelli](https://github.com/smingolelli)
* [Nicola Mingolelli](https://github.com/nmingolelli)
* [Oliver Haney](https://github.com/haney-oliver)
* [Mussaed Alahmadi](https://github.com/mussaed)
* [James P Armstrong IV](https://github.com/PlanetaryTennis)
* [Ruby Wang](https://github.com/Rubyw123) 

## Resources
* [Git Guide](git-guide.md)

* [Running Docker](https://docs.docker.com/get-started/part2/)

## Team Docs

### Opponent AI Interatction Time Map
![oai-timemap](/readme-images/EquationsComputerOpponentInteractionTimeMap.png "Time Map")
### OAI-ECS Design 
Using abstract Player class to define bahaviors and references, for both the OAI and human player classes. 

#### Player
Fields: 
 - `GameStateManager gsm`
 - `String name`

Behaviors:
 - `void takeTurn()`

#### OAI
Fields:
 - `GameStateManager gsm (inherited)`
 - `String name (inherited)`
 - `Ecs ecs` 

Behaviors:
 - `void takeTurn() (inherited)`

#### EquationsAnswerSolution (Entrypoint of the ECS) 
 - `Score calculateScore()`

 

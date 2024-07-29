# Forest Fire Simulation

This is a simple forest fire simulation in JAVA.
The simulation is based on the following rules:
- The simulation works by steps
- A forest is composed of trees
- Each tree can be in one of the following states: `HEALTHY`, `BURNING`, `BURNED`
- Each burning tree can ignite its neighbors with a certain probability
- If a tree is in the state `BURNING`, it will be in the state `BURNED` in the next step

## Configuration

To configure the simulation, you can edit the `config.json` file located in the `resources` folder.

- `forestWidth` & `forestHeight` are the number of trees in the forest in width and in height. *(must be an Integer)*
- `treeSize` is the size of a tree in pixels. *(must be an Integer)*
- `fireProbability` is the probality of a burning tree to ignite its neighbors. *(must be a Double between 0 and 1)*
- `burningTrees` is a list of all the tree coordinates that will be burning at the first step. *(must be a list of Coordinates Integers)*

## Features
- You can ignite manually a tree at anytime by clicking on it.
- The simulation works by steps, you can go to the next step by clicking on the `Step` button.
- You can reset the simulation by clicking on the `Reset` button.

## Potentials Improvements
- Add a Play/Pause button.
- Add a slider to control the speed of the simulation.
- Add other types of cells like `WATER` or `ROCK` that can block the fire.

## Dependencies

- Java 17
- json_simple-1.1.jar (included in the project)
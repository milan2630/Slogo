# SIMULATION_API.md

## Milan Bhat (mb554), Robert Chen (rec43), Ryan Weeratunga (rkw14), Jaidha Rosenblatt (jrr59),

[Simulation Team 04](https://www2.cs.duke.edu/courses/compsci308/current/classwork/05_slogo_api/simulation_apis/api_simulation_team04.txt)
*  Cell
    * public Cell(String state, int x, int y) 
        * External so that the configuation can be set
    * public String getNextState() 
        * Internal
    * public void setNextState(String nextState) 
        * Internal
    * public Point getCoordinate() 
        * External for the Visualizer
    * public int getX() 
        * External
    * public int getY() 
        * External
    * public void setState(String state) 
        * Internal
    * public String getState() 
        * Internal
*  Cell Subclasses
    *  ForageCell
        * public ForageCell(String state, int x, int y) 
            * Internal since it is not public to the View
        * public int getFoodPher() 
            * Internal 
        * public int getHomePher() 
            * Internal
        * public void updateFoodPher(int change) 
            * Internal
        * public void updateHomePher(int change) 
            * Internal
        * public void setFull() 
            * Internal
        * public boolean isFull() 
            * Internal
        * public boolean isHungry()
            * Internal
    * PredPreyCell
        * public PredPreyCell(String state, int x, int y) 
            * Internal
        * public void setLives(int lives) 
            * Internal
        * public void updateLives(int change)
            * Internal
        * public int getLives() 
            * Internal

*  Grid class
    * public Grid(int rows, int columns) 
    * public Grid(Map<String, Double> data, Map<String, String> cellTypes, Map<String, String> details)
    * public Map<String, String> getStateMap() 
*  Grid Subclasses
    * SegGrid
        * public SegGrid(Map<String, Double> data, Map<String, String> cellTypes,... )
            * External for setup
        * public void updateGrid() 
            * External
        * protected void updateCell(int x, int y, List <Cell neighbors)
            * Internal
        * public Map<String, String> getDetails()
            * External
        * public int getRows() 
            * Internal
        * public int getColumns() 
            * Internal
        * public Cell current(int x, int y)
            * Internal
        * public Map getStats()
            * Internal
        * public int getNumIterations()
            * Internal
        * protected List createGrid()
            * Internal
        * protected void setInitState(Map layout)
            * Internal
        * protected void checkValidStates(List<String> states, Map<String, String> data)
        * protected int getIntFromData()
            * Internal
        * protected double getDoubleFromData()
            * Internal
        * protected List<List<Cell>> getGrid()
            * Internal
        * protected void updateCell(int x, int y, List<Cell> neighbors)
            * Internal
        * protected Cell getCell(int x, int y) 
            * Internal
        * protected void setCellState(int x, int y, String state)
            * Internal
    * FireGrid
        * public FireGrid(Map<String, Double> data, Map<String, String> cellTypes, ...)
            * External
        * public void updateGrid() 
            * External
        * protected List<List<Cell>> createGrid() 
            * External
        * protected void updateCell(int x, int y, List<Cell> neighbors)
            * Internal
    * LifeGrid
        * public LifeGrid(Map<String, Double> data, Map<String, String> cellTypes, ...)
            * External
        * public void updateGrid() 
            * External
        * protected List<List<Cell>> createGrid() 
            * External
        * protected void updateCell(int x, int y, List<Cell> neighbors) 
            * Internal
            
### API Redesign
### External
#### GridVisualizer
Make a class that gets an immutable collection of cell objects (which hold coordinates and states) from each simulation. This utilizes the GridPane class in Javafx to transform each cell into a gridpane coordinates.

### Internal

#### Simulation Interface
Make an interface for simulations that holds properties and methods that apply to all simulations. This will hold the actual grid (list of lists) as well as a general method for checking neighbors. The simulation will also have instance of a stats class, author, and title of the simulation.

#### Simulation Concrete Subclasses
Each simulation type will inherit the simulation interface. It will contain the specific neighbor methods and the specific cells types. These will have specific methods to that simulation for handling interactions between neighbors. This would also contain the mapping for states to colors.

#### Abstract Cell Class
We will have an abstract class for cell which represents a cell for a grid. It will just c

#### Cells Subclasses
Instead of having each simulation use the same kind of cell, make cell abstract and have each new simulation create a specific cell class. For example, Fire would have a FireCell class that only allowed "burning", "tree", or "empty" as possible states.

## API Tasks
### Internal
As a client of the backend, the frontend in main creates a new instance of specific simulation type by passing in an XML file. It then creates a new instance of the GridView. Every time the client wants to update the grid, an updateGrid() method will be called which updates the GridView and the simulation state.
### External
A new developer joins a team and adds a new simulation by creating a new simulation class that uses the Simulation class. This means that they will have to implement the specific neighbor check method, the mapping from state to colors, and the layout of the grid. The developer must also add a new type of Cell that is specific to that simulation and inherits the abstract Cell class.

 


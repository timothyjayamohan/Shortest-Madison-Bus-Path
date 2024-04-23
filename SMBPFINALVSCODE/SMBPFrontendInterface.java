
public interface SMBPFrontendInterface {
	public void runCommandLoop();
	public char mainMenuPrompt();
	public void loadDataCommand();
	public void newStop();
	public void deleteStop();
	public void newStreet();
	public void deleteStreet();
	public void numStops();
	public void numStreets();
	public void shortestPath();
	public void shortestCost();
	public void shortestPathMultiple();
	public void shortestCostMultiple();
}

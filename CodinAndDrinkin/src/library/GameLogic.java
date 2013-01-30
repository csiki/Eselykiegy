package library;

/*
 * Realized by class Game
 */

public interface GameLogic {
	
	public CrateInterface savePlayer(String name, Sex sex, int height, int weight);
	public int addBev(String name, float vol, int ABV);
	public TaskValidationOutcome loadTask(String path);
	public SolutionOutcome sendSolution(int compilerID, String code);
	public float bevToDrink(int bevID);
	public void bevToPour(int bevID, float vol);
	
}

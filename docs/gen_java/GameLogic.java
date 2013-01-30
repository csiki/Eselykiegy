/***************************************************
 * GameLogic.java
 * --------------
 *
 * Author:  Csiki
 * Company: -
 * Date:    2013.01.30.
 *
 * Copyright (c), 2013, -
 **************************************************/    

//#ACD# M(UDIS::UID_FBBC56F6-599E-46e3-82A8-2D1FD2BC9E3E) ---- additional import statements
//user defined code to be added here ...

//#end ACD#

public interface GameLogic {
  //#ACD# M(UDAT::UID_FBBC56F6-599E-46e3-82A8-2D1FD2BC9E3E) ---- additional static attributes
  //user defined code to be added here ...

  //#end ACD#

  // ------------------------------------------------------------
  // methods
  // ------------------------------------------------------------
  public CrateInterface savePlayer( String,  Sex,  int,  int);
  public int addBev( String,  float,  int);
  public TaskValidationOutcome loadTask( String);
  public SolutionOutcome sendSolution( int,  String);
  public float bevToDrink(int bevID);
  public void bevToPour(int bevID, float vol);
  //#ACD# M(UDOP::UID_FBBC56F6-599E-46e3-82A8-2D1FD2BC9E3E) ---- additional operations
  //user defined code to be added here ...

  //#end ACD#
}

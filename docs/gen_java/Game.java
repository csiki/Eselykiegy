/***************************************************
 * Game.java
 * ---------
 *
 * Author:  Csiki
 * Company: -
 * Date:    2013.01.30.
 *
 * Copyright (c), 2013, -
 **************************************************/    

import java.util.*;
//#ACD# M(UDIS::UID_09BC1CEC-E3E3-421a-AE4A-4A0E85FCE76F) ---- additional import statements
//user defined code to be added here ...

//#end ACD#

public class Game implements GameLogic {

  // ------------------------------------------------------------
  // instance attributes
  // ------------------------------------------------------------
private Task currentTask;
private long currentTaskStartTime;
protected List<Task> tasks;
protected Player player;
protected List<Compiler> compilers;
protected List<Solution> solutions;

  //#ACD# M(UDAT::UID_09BC1CEC-E3E3-421a-AE4A-4A0E85FCE76F) ---- additional attributes
  //user defined code to be added here ...

  //#end ACD#
  //#ACD# M(UDCO::UID_09BC1CEC-E3E3-421a-AE4A-4A0E85FCE76F) ---- additional constructors
  //user defined code to be added here ...

  //#end ACD#

  // ------------------------------------------------------------
  // methods
  // ------------------------------------------------------------
  private float calculateAlcComsumeVol(SolutionOutcome sout) {
    float retVal = 0.0F;
    //#ACD# M(UDOP::UID_1B226AFD-2C01-4b72-A292-587488CB64B4) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  private Task openFile( File) {
    Task retVal = null;
    //#ACD# M(UDOP::UID_C7FDCC96-2262-44a1-B941-8A834BFBA493) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  private TaskValidationOutcome taskValidation( Task) {
    TaskValidationOutcome retVal = null;
    //#ACD# M(UDOP::UID_F36D2511-B785-4d87-9880-7FD93E3B6F3A) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  private float calculateBloodVol( int,  int) {
    float retVal = 0.0F;
    //#ACD# M(UDOP::UID_7E121FAC-A9A4-4165-8C32-7C940467B668) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  private boolean isPassed(int taskID) {
    boolean retVal = false;
    //#ACD# M(UDOP::UID_6E6733E2-4372-499f-8F59-814D22E3CB12) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public CrateInterface savePlayer( String,  Sex,  int,  int) {
    CrateInterface retVal = null;
    //#ACD# M(UDOP::UID_C0FB6B48-0223-41ba-88CE-49E905E664E5-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public int addBev( String,  float,  int) {
    int retVal = 0;
    //#ACD# M(UDOP::UID_5EE039FD-301E-4083-9BBA-3437E65938EF-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public TaskValidationOutcome loadTask( String) {
    TaskValidationOutcome retVal = null;
    //#ACD# M(UDOP::UID_32E01641-2504-4717-82DA-33FC59193BD9-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public SolutionOutcome sendSolution( int,  String) {
    SolutionOutcome retVal = null;
    //#ACD# M(UDOP::UID_6A672316-93C1-493b-BEED-00CE7CCAF36D-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public float bevToDrink(int bevID) {
    float retVal = 0.0F;
    //#ACD# M(UDOP::UID_F3134CC4-03E4-442e-862B-FB41A4AE288A-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public void bevToPour(int bevID, float vol) {
    //#ACD# M(UDOP::UID_B493E1B8-774C-42e2-B562-956D61B0368B-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
  }

  //#ACD# M(UDOP::UID_09BC1CEC-E3E3-421a-AE4A-4A0E85FCE76F) ---- additional operations
  //user defined code to be added here ...

  //#end ACD#
}

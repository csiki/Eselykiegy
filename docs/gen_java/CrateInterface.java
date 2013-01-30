/***************************************************
 * CrateInterface.java
 * -------------------
 *
 * Author:  Csiki
 * Company: -
 * Date:    2013.01.30.
 *
 * Copyright (c), 2013, -
 **************************************************/    

//#ACD# M(UDIS::UID_BBA09E4E-D876-4250-B383-70317551C5D6) ---- additional import statements
//user defined code to be added here ...

//#end ACD#

public interface CrateInterface {
  //#ACD# M(UDAT::UID_BBA09E4E-D876-4250-B383-70317551C5D6) ---- additional static attributes
  //user defined code to be added here ...

  //#end ACD#

  // ------------------------------------------------------------
  // methods
  // ------------------------------------------------------------
  public String getBevName(int bevID);
  public float getBevVol(int bevID);
  public float getBevABV(int bevID);
  //#ACD# M(UDOP::UID_BBA09E4E-D876-4250-B383-70317551C5D6) ---- additional operations
  //user defined code to be added here ...

  //#end ACD#
}

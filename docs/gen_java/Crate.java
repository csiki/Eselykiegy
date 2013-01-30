/***************************************************
 * Crate.java
 * ----------
 *
 * Author:  Csiki
 * Company: -
 * Date:    2013.01.30.
 *
 * Copyright (c), 2013, -
 **************************************************/    

import java.util.*;
//#ACD# M(UDIS::UID_809A52FE-6B60-4307-8841-0001AD78A5AD) ---- additional import statements
//user defined code to be added here ...

//#end ACD#

public class Crate implements CrateInterface {

  // ------------------------------------------------------------
  // instance attributes
  // ------------------------------------------------------------
  protected Vector beverage;

  //#ACD# M(UDAT::UID_809A52FE-6B60-4307-8841-0001AD78A5AD) ---- additional attributes
  //user defined code to be added here ...

  //#end ACD#
  //#ACD# M(UDCO::UID_809A52FE-6B60-4307-8841-0001AD78A5AD) ---- additional constructors
  //user defined code to be added here ...

  //#end ACD#

  // ------------------------------------------------------------
  // methods
  // ------------------------------------------------------------
  public int add( String,  float,  int) {
    int retVal = 0;
    //#ACD# M(UDOP::UID_5DD92C15-8FAE-4021-B1EC-787B2CE5C86F) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public void pour(int bevID, float vol) {
    //#ACD# M(UDOP::UID_484483B1-BDAD-43fc-91C4-A4B4A1873F91) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
  }

  public void consume(int bevID, float vol) {
    //#ACD# M(UDOP::UID_79002E67-8242-426e-BC98-EDF5AAB9DF7E) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
  }

  public String getBevName(int bevID) {
    String retVal = "";
    //#ACD# M(UDOP::UID_BB19AD42-9B3F-4009-8314-440B7D307CDF-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public float getBevVol(int bevID) {
    float retVal = 0.0F;
    //#ACD# M(UDOP::UID_7E4C4242-F3D1-4b72-8A95-64872B08EE55-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  public float getBevABV(int bevID) {
    float retVal = 0.0F;
    //#ACD# M(UDOP::UID_3BFEC5DA-32A3-4141-87C8-04323929C4BA-impl) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  //#ACD# M(UDOP::UID_809A52FE-6B60-4307-8841-0001AD78A5AD) ---- additional operations
  //user defined code to be added here ...

  //#end ACD#
}

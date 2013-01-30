/***************************************************
 * Player.java
 * -----------
 *
 * Author:  Csiki
 * Company: -
 * Date:    2013.01.30.
 *
 * Copyright (c), 2013, -
 **************************************************/    

//#ACD# M(UDIS::UID_63603D53-A905-4a5c-9084-654DC3928D0E) ---- additional import statements
//user defined code to be added here ...

//#end ACD#

public class Player {

  // ------------------------------------------------------------
  // instance attributes
  // ------------------------------------------------------------
private String name;
private Sex sex;
private int weight;
private int height;
private float bloodVol;
private float consumedAlc;
protected Crate crate;

  //#ACD# M(UDAT::UID_63603D53-A905-4a5c-9084-654DC3928D0E) ---- additional attributes
  //user defined code to be added here ...

  //#end ACD#
  //#ACD# M(UDCO::UID_63603D53-A905-4a5c-9084-654DC3928D0E) ---- additional constructors
  //user defined code to be added here ...

  //#end ACD#

  // ------------------------------------------------------------
  // methods
  // ------------------------------------------------------------
  public void consume(int bevID,  float, float alcVol) {
    //#ACD# M(UDOP::UID_E5EEB772-3B97-4f56-9FC6-2F0F8B252B6B) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
  }

  public void pour(int bevID,  float) {
    //#ACD# M(UDOP::UID_B3DD9F22-3538-48e7-A6FC-91F84C4F6E2B) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
  }

  public int addBev( String,  float,  float) {
    int retVal = 0;
    //#ACD# M(UDOP::UID_6AB9F920-B1F9-4ad4-BFEA-C6D8A03FE8D9) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
    return retVal;
  }

  //#ACD# M(UDOP::UID_63603D53-A905-4a5c-9084-654DC3928D0E) ---- additional operations
  //user defined code to be added here ...

  //#end ACD#
}

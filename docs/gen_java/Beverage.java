/***************************************************
 * Beverage.java
 * -------------
 *
 * Author:  Csiki
 * Company: -
 * Date:    2013.01.30.
 *
 * Copyright (c), 2013, -
 **************************************************/    

//#ACD# M(UDIS::UID_22AB7D7D-7A95-441e-93F2-A7B32D8D0590) ---- additional import statements
//user defined code to be added here ...

//#end ACD#

public class Beverage {

  // ------------------------------------------------------------
  // instance attributes
  // ------------------------------------------------------------
private String name;
private float abv;
private float vol;
protected Crate crate;

  //#ACD# M(UDAT::UID_22AB7D7D-7A95-441e-93F2-A7B32D8D0590) ---- additional attributes
  //user defined code to be added here ...

  //#end ACD#
  //#ACD# M(UDCO::UID_22AB7D7D-7A95-441e-93F2-A7B32D8D0590) ---- additional constructors
  //user defined code to be added here ...

  //#end ACD#

  // ------------------------------------------------------------
  // methods
  // ------------------------------------------------------------
  public void pour(float vol) {
    //#ACD# M(UDOP::UID_01E9FC7F-9AA8-4a60-8C1B-8CD096FD0BC9) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
  }

  public void consume(float vol) throws NotEnoughAlcoholException {
    //#ACD# M(UDOP::UID_E1BF55A1-BC0C-45b8-849D-19CD722EBF3E) ---- method body
    //user defined code to be added here ...
      
    //#end ACD#
  }

  //#ACD# M(UDOP::UID_22AB7D7D-7A95-441e-93F2-A7B32D8D0590) ---- additional operations
  //user defined code to be added here ...

  //#end ACD#
}

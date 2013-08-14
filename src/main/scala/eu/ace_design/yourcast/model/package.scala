package eu.ace_design.yourcast


package object model {
  import language.implicitConversions // Allow implicit conversion (here toDate: String -> Date)

  /**
   * Transform a date given as a String into a pure Java Date instance
   * @param str the date, as a String (yyy-MM-dd HH:mm)
   * @return a Date instance
   */
  implicit def toDate(str: String): java.util.Date =
    new java.text.SimpleDateFormat("yyy-MM-dd HH:mm").parse(str)

}

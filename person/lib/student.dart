//class inheritance from Person class
import 'package:person/person.dart';

class Student extends Person {
  late int id;
  late int creditPoints;

  //super contructor for parent class variables
  Student(this.id, String n, int a) : super(n, a);
  //super constructor with quick constructor named
  Student.complete(this.id, this.creditPoints, String n, int a) : super(n, a);

  /* GETTERS */
  int get getId {
    return id;
  }

  int get getCreditPoints {
    return creditPoints;
  }

  /* SETTERS */
  set setId(int i) {
    id = i;
  }

  set setCreditPoints(int cp) {
    creditPoints = cp;
  }
}

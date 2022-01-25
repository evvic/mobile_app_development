import 'package:person/person.dart';
import 'package:person/student.dart';

void main(List<String> arguments) {
  //print('Hello world: ${person.calculate()}!');
  print("We in the console baby");
  Person edawg = Person.complete("Eric", 23, 180, 76);

  //print(edawg.toString());

  Person ewic = Student(123456, "EWIC", 23);
  Person oscar = Student(928475, "Oscar", 24);

  // growable list
  var listy = [edawg, ewic];
  listy.add(oscar);

  for (var student in listy) {
    print(student.toString());
  }
}

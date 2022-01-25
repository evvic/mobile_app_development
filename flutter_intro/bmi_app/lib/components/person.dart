import 'dart:core';
import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Person {
  late String name;
  late int age;
  late double height; //cm
  late double weight; //kg

  // quick constructor for name and age
  Person(this.name, this.age);

  @override
  String toString() {
    return name + ', ' + age.toString();
  }

  int BMI() {
    //calculate BMI and return as int
    return (weight / pow((height / 100), 2)).round();
  }

  String get getName {
    return name;
  }

  int get getAge {
    return age;
  }

  double get getHeight {
    return height;
  }

  double get getWeight {
    return weight;
  }
}

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
  Person.complete(this.name, this.age, this.height, this.weight);

  Person.verySmallPerson(this.name) {
    height = 50;
  }

  @override
  String toString() {
    return name + ', ' + age.toString();
  }

  int BMI() {
    //calculate BMI and return as int
    return (weight / pow((height / 100), 2)).round();
  }

  /* SETTERS */
  set setName(String n) {
    name = n;
  }

  set setAge(int a) {
    age = a;
  }

  set setHeight(double h) {
    height = h;
  }

  set setWeight(double w) {
    weight = w;
  }

  /* GETTERS */
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

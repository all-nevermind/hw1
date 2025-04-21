package ru.sklyarenko.netology;

import ru.sklyarenko.netology.homework.HomeWork;
import ru.sklyarenko.netology.homework.lesson1.Introduction;

public class Main {
    public static void main(String[] args) {
        HomeWork lesson1 = new Introduction();
        System.out.println("Lesson 1. Introduction to Java Programming.\n" + lesson1.launch() + "\n");;
    }


}
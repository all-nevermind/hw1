package ru.sklyarenko.netology.homework.lesson1;

/**
 * Введение в программирование на Java.
 * ДЗ от 17 апреля 2025 г.
 */
public class Introduction {

    public static void printSolution() {
        System.out.println("Lesson 1. Introduction to Java Programming.\n" + task1() + "\n" + task2() + "\n");;
    }
    /**
     * Напишите программу, которая при запуске выводит ваше имя и фамилию на латинице.
     * */
    private static String task1() {
        return "Task 1. Print solution:\nSklyarenko Nataliya";
    }

    /**
     * 2.Напишите программу, в которой заведены две целочисленные переменные:
     * - income для хранения размера доходов;
     * - spending для хранения размера расходов;
     * Программа должна выводить на экран сообщение о том, сколько денег осталось на счету. Не считайте в уме итоговую сумму, расчёт итоговой суммы должна производить сама программа.
     * */
    private static String task2() {
        int income = 10;
        int spending = 2;
        return "Task 2. Print solution:\nIncome: " + income + " usd.\nExpenditure: " + spending + " usd.\nThere is " + (income - spending) + " ruble left on the account.";
    }
}

package ru.sklyarenko.netology;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
    }

    /**
     * Напишите программу, которая при запуске выводит ваше имя и фамилию на латинице.
     * */
    private static void task1() {
        System.out.println("Task 1.");
        System.out.println("Sklyarenko Nataliya");
    }

    /**
     * 2.Напишите программу, в которой заведены две целочисленные переменные:
     * - income для хранения размера доходов;
     * - spending для хранения размера расходов;
     * Программа должна выводить на экран сообщение о том, сколько денег осталось на счету. Не считайте в уме итоговую сумму, расчёт итоговой суммы должна производить сама программа.
     * */
    private static void task2() {
        System.out.println("Task 2.");
        int income = 10;
        int spending = 2;
        System.out.println("Income: " + income + " usd.\nExpenditure: " + spending + " usd.\nThere is " + (income - spending) + " ruble left on the account.");
    }
}
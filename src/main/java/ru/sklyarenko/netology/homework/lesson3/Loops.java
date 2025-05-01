package ru.sklyarenko.netology.homework.lesson3;


import ru.sklyarenko.netology.exceptions.ApplicationException;
import ru.sklyarenko.netology.utils.ReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Условные операторы и циклы.
 * ДЗ от 24 апреля 2025 г.
 */
public class Loops {

    /**
     * Вывод в лог решения к ДЗ.
     */
    public static void printSolution() {
        System.out.println("Lesson 3. Conditional operators and loops.");
        task1();
        task2();
    }

    /**
     * Нужно написать программу, которая будет рассчитывать количество дней в году.
     * Для расчета количества дней в году требуется знать несколько правил:
     * - В високосном году 366 дней, в обычном 365.
     * - Високосный год — это год, номер которого делится без остатка на 400 (например 2000 или 2400),
     * либо делится на 4 но не делится на 100 (например 2008, 2096, но не 2100).
     *
     * Схема работы программы:
     * - Вывод сообщения в консоли Введите год в формате "yyyy".
     * - Ввод года в формате yyyy (например 2004).
     * - Чтение значения из консоли и расчет количества дней.
     * - Результат работы программы: напечатать в консоли количество дней в году Количество дней 365 или Количество дней 366.
     */
    private static void task1() {
        System.out.println("Task 1. Print solution:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int year = ReaderUtil.readInt(reader, "Введите год в формате yyyy: ");
            boolean isLeapYear = isLeap(year);
            System.out.println((isLeapYear ? "366" : "365") + " дней в году.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Нужно написать программу, которая будет играть с пользователем в следующую игру.
     * Каждый ход программа спрашивает у него два числа: год и число дней в этом году.
     * Вы проверяете, правильно ли пользователь указал количество дней в этом году и, если правильно, повторяете ход.
     * В противном случае игра завершается.
     * По окончании игры напишите пользователю сколько раз он угадал, это и будет количество набранных очков в игре.
     */
    private static void task2() {
        System.out.println("Task 2. Print solution:");
        game();
    }

    private static void game() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int result = playing(reader, 0);
            System.out.println("Вы угадали " + result + " раз(а)!");
            if(more(reader))
                game();
            else
                System.out.println("До свидания!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int playing(BufferedReader reader, int score) throws ApplicationException, IOException {
        int inputYear = ReaderUtil.readInt(reader, "Введите год в формате yyyy: ");
        boolean inputIsLeap = inputIsLeap(reader, inputYear);
        boolean isLeap = isLeap(inputYear);
        String message = "%s, " + inputYear + " год " + (isLeap ? "является високосным." : "не является високосным.") + " Ваш текущий счёт: %d";

        if(inputIsLeap == isLeap) {
            System.out.printf((message )+ "%n", "Верно", ++score);
            return playing(reader, score);
        } else {
            System.out.printf((message) + "%n", "Ошибка", score);
            return score;
        }
    }

    private static boolean inputIsLeap(BufferedReader reader, int inputYear) throws IOException {
        String inputIsLeap = ReaderUtil.readWithEnum(reader, "Вы считаете " + inputYear + " год високосным? (y/n): ", IsLeap.getValues());
        return (inputIsLeap.trim().equalsIgnoreCase(IsLeap.Y.getValue()));

    }

    private static boolean more(BufferedReader reader) throws IOException {
        String inputIsLeap = ReaderUtil.readWithEnum(reader, "Сыграем заного? (y/n): ", IsLeap.getValues());
        return (inputIsLeap.trim().equalsIgnoreCase(IsLeap.Y.getValue()));

    }

    private static boolean isLeap(int year) {
        return year % 100 != 0 && (year % 400 == 0 || year % 4 == 0);
    }

    private enum IsLeap {
        Y,
        N;

        public static String[] getValues() {
            return Arrays.stream(values()).map(Enum::name).toArray(String[]::new);
        }

        public String getValue() {
            return this.name();
        }
    }
}

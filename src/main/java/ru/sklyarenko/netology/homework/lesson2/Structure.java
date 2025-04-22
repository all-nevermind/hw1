package ru.sklyarenko.netology.homework.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Структура программы.
 * ДЗ от 21 апреля 2025 г.
 */
public class Structure {

    /**
     * Вывод в лог решения к ДЗ.
     */
    public static void printSolution() {
        System.out.println("Lesson 2. Program structure.");
        task1();
    }

    /**
     * Написать калькулятор для расчёта пошлины для провоза товара. Пошлина будет рассчитывается по следующему принципу:
     * 1 копейка за каждый рубль цены товара
     * 100 рублей за каждый килограмм товара
     * Копейки в итоговой сумме пошлины можно не учитывать, оставляя только рубли.
     * Например, для товара ценой в 546 рублей и весом 3 кг размер пошлины составит 5.46 + 3 * 100 = 305 рублей.
     * Программа должна приветствовать пользователя, спрашивать стоимость в рублях и вес товара в кг (всё целые числа), а в ответ сообщать размер пошлины.
     * Логика рассчёта пошлины по характеристикам товара должна быть вынесена в отдельный статический метод. Он должен:
     * Принимать два параметра - цену и вес товара (целые числа)
     * Возвращать посчитанную сумму пошлины
     * <p>
     * Дополнительно: Для того чтобы пользователь мог вводить данные на той же строке,
     * что и сообщение программы, выводите это сообщение через System.out.print() вместо System.out.println().
     * В таком случае, программа не будет делать перенос строки в конце сообщения.
     */
    private static void task1() {
        System.out.println("Task 1. Print solution:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Здравствуйте!");
            int price = read(reader, "Введите цену товара (в руб.): ");
            int weight = read(reader, "Введите вес товара (в кг.): ");

            System.out.print("Размер пошлины (в руб.) составит: " + Duty.get(price, weight).intValue() + " руб.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static int read(BufferedReader reader, String message) throws IOException {
        System.out.print(message);
        try {
            return parse(reader.readLine());
        } catch (ClassCastException e) {
            return read(reader, message);
        }
    }

    private static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Cannot parse value \"" + input + "\" to Integer.");
            throw new ClassCastException();
        }
    }

    /**
     * Не поняла, что нужно в доп. задании и что такое "ставка пошлины на вес".
     * Но вот отдельный класс, в нем константы. Всё.
     */
    private static class Duty {
        private static final byte WEIGHT_RATE = 100;
        private static final double PRICE_RATE = 0.01;

        private static Double get(int price, int weight) {
            return price * PRICE_RATE + weight * WEIGHT_RATE;
        }
    }
}

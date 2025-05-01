package ru.sklyarenko.netology.homework.lesson4;

import lombok.Getter;
import ru.sklyarenko.netology.exceptions.ApplicationException;
import ru.sklyarenko.netology.utils.ReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AssistantSTS {
    private final BufferedReader reader;
    private final Account account;
    private final List<STS> variants = new ArrayList<>();

    private AssistantSTS(BufferedReader reader) {
        this.reader = reader;
        this.account = new Account(this.reader);
        fillSTS();
    }

    public static void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            new AssistantSTS(reader).process();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void process() throws IOException, ApplicationException {
        Operation operation = pickOperation();
        switch (operation) {
            case addIncome -> {
                this.account.addIncome();
                process();
            }
            case addExpenditure -> {
                this.account.addExpenditure();
                process();
            }
            case selectSTS -> {
                prioritySTS();
                process();
            }
            case accountInfo -> {
                System.out.println("Доходы: " + this.account.getIncome() + "\n" +
                        "Расходы: " + this.account.getExpenditure() + "\n" +
                        "Доходы минус расходы: " + this.account.getBudget() + "\n");
                process();
            }
            case stsInfo -> {
                this.variants.forEach(sts -> {
                    System.out.println("Название: " + sts.name() + "\n" +
                            "Налогооблагаемая сумма: " + sts.getTaxable() + ". Ставка: " + sts.getTax() + "%. Ваш налог составит: " + sts.calc() + " рублей\n");
                });
                process();
            }
            case reset -> {
                this.account.clear();
                process();
            }
            case cancel -> {
                System.out.println("До свидания!");
                this.reader.close();
            }
            default -> process();
        }
    }

    private Operation pickOperation() throws IOException {
        return ReaderUtil.readWithEnum(
                this.reader,
                menuInfo(),
                Operation.values(),
                Operation::getId);
    }

    private static String menuInfo() {
        return "Выберите операцию и введите её номер:\n" + Arrays.stream(Operation.values()).map(o -> o.id + ". " + o.description).collect(Collectors.joining("\n"));
    }


    private void prioritySTS() {
        STS priority = variants.stream().min(Comparator.comparingInt(STS::calc)).get();
        List<STS> copy = new ArrayList<>(variants);
        copy.remove(priority);
        List<Integer> remainingTax = copy.stream().map(STS::calc).toList();
        List<Integer> differenceTax = remainingTax.stream().map(v -> v - priority.calc()).toList();
        System.out.println("Мы советуем вам " + priority.name() + "\n" +
                "Налогооблагаемая сумма: " + priority.getTaxable() + ". Ставка: " + priority.getTax() + "%. Ваш налог составит: " + priority.calc() + " рублей\n" +
                "Налог в других системах: " + remainingTax.stream().map(Object::toString).collect(Collectors.joining(", ")) + " рублей\n" +
                "Экономия минимум: " + differenceTax.stream().min(Integer::compare).get() + " рублей," +
                " максимум: " + differenceTax.stream().max(Integer::compare).get() + " рублей.\n");
    }

    private void fillSTS() {
        this.variants.add(new STS(6) {
            @Override
            public int getTaxable() {
                return account.getIncome();
            }

            @Override
            public String name() {
                return "УСН доходы";
            }
        });
        this.variants.add(new STS(15) {
            @Override
            public int getTaxable() {
                return account.getBudget();
            }

            @Override
            public String name() {
                return "УСН доходы минус расходы";
            }
        });

        this.variants.add(new STS(10) {
            @Override
            public int getTaxable() {
                if (account.getIncome() >= 10000) return account.getBudget();
                else return account.getIncome();
            }

            @Override
            public String name() {
                return "УСН при доходах от 10 000 налог на доходы минус расходы, иначе на доходы.";
            }
        });
    }

    @Getter
    private enum Operation {
        addIncome("Добавить доход"),
        addExpenditure("Добавить расход"),
        selectSTS("Выбрать систему налогообложения"),
        accountInfo("Посмотреть данные аккаунта"),
        stsInfo("Посмотреть варианты УСН"),
        reset("Сброс"),
        cancel("Отмена");

        private String id;
        private String description;

        Operation(String description) {
            this.id = ordinal() + 1 + "";
            this.description = description;
        }

    }
}

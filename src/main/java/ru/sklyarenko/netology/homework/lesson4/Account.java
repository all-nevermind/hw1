package ru.sklyarenko.netology.homework.lesson4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.sklyarenko.netology.exceptions.ApplicationException;
import ru.sklyarenko.netology.exceptions.FaultCode;
import ru.sklyarenko.netology.utils.ReaderUtil;

import java.io.BufferedReader;
import java.util.logging.Logger;


@RequiredArgsConstructor
@ToString
public class Account {
    private static final Logger logger = Logger.getLogger(Account.class.getName());
    private final BufferedReader reader;
    @Getter
    private int income;
    @Getter
    private int expenditure;


    public void addIncome() throws ApplicationException {

        System.out.println("Добавить новый доход.");
        int amount = getAmount();
        if (amount == 0) {
            logger.info("Go back to the menu.");
            return;
        }
        if (amount < 0) {
            logger.warning("Enter a positive number.");
            addIncome();
        } else
            this.income += amount;


    }

    public void addExpenditure() throws ApplicationException {
        System.out.println("Добавить новый расход. Доступно: " + getBudget());
        int amount = Math.abs(getAmount());
        if(amount == 0) {
            logger.info("Go back to the menu.");
            return;
        }
        if (amount > getBudget()) {
            logger.warning("Expenditure cannot exceed income.");
            addExpenditure();
        } else
            this.expenditure += amount;
    }


    private int getAmount() throws ApplicationException {
        try {
            return ReaderUtil.read(this.reader, "Для возврата в меню введите любой символ. Введите сумму: ");
        } catch (ApplicationException e) {
            if (e.getCode().equals(FaultCode.PARSE_ERROR))
                return 0;
            throw e;
        }
    }

    public int getBudget() {
        return this.income - this.expenditure;
    }

    public void clear() {
        this.income = 0;
        this.expenditure = 0;
    }


}

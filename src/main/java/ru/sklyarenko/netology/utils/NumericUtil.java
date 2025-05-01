package ru.sklyarenko.netology.utils;


import ru.sklyarenko.netology.exceptions.ApplicationException;
import ru.sklyarenko.netology.exceptions.FaultCode;
import java.util.logging.Logger;

public class NumericUtil {

    public static int parseInt(String input) throws ApplicationException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            logger.warning("Value '" + input + "' it is not an integer.");
            throw new ApplicationException(FaultCode.PARSE_ERROR);
        }
    }

    private static final Logger logger = Logger.getLogger(NumericUtil.class.getName());
}

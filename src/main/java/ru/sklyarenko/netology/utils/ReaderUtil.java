package ru.sklyarenko.netology.utils;


import ru.sklyarenko.netology.exceptions.ApplicationException;
import ru.sklyarenko.netology.exceptions.FaultCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class ReaderUtil {
    public static int readInt(BufferedReader reader, String message) throws ApplicationException, IOException {
        System.out.println(message);
        try {
            return NumericUtil.parseInt(reader.readLine());
        } catch (ApplicationException e) {
            if (e.getCode().equals(FaultCode.PARSE_ERROR))
                return readInt(reader, message);
            throw e;
        }
    }

    public static String readWithEnum(BufferedReader reader, String message, String[] variants) throws IOException {
        System.out.println(message);
        String value = reader.readLine();
        for (String variant : variants) {
            if (value.trim().equalsIgnoreCase(variant))
                return value;
        }
        logger.warning("The entered value = '" + value + "' does not match the expected values:" + Arrays.toString(variants));
        return readWithEnum(reader, message, variants);
    }
    private static final Logger logger = Logger.getLogger(ReaderUtil.class.getName());
}

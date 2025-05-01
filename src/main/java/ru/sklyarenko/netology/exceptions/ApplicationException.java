package ru.sklyarenko.netology.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

@Getter
public class ApplicationException extends Exception{
    private final FaultCode code;

    public ApplicationException(FaultCode code) {
        this.code = code;
    }
}

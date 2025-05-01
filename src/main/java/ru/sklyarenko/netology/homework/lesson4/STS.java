package ru.sklyarenko.netology.homework.lesson4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class STS {
    public abstract int getTaxable();
    public abstract String name();
    @Getter
    private final int tax;
    public int calc() {
        return getTaxable() * tax / 100;
    }

}

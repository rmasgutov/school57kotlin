package ru.tbank.education.school.lesson8.lection.npe.checks;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class NotNullAnnotationExample {
    public static @NotNull String uppercase(@NotNull String s) {
        return s.toUpperCase();
    }

    public static @Nullable String maybeNull(boolean flag) {
        return flag ? "ok" : null;
    }

    public static void main(String[] args) {
        uppercase(null);
    }
}
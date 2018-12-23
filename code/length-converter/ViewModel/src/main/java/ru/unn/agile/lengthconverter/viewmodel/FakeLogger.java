package ru.unn.agile.lengthconverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final List<String> log = new ArrayList<>();

    @Override
    public void log(final String str) {
        log.add(str);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}

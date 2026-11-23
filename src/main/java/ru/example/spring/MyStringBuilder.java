package ru.example.spring;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MyStringBuilder {
    private char[] value;
    private int count;
    private final Deque<Snapshot> history = new ArrayDeque<>();

    public MyStringBuilder() {
        this("");
    }

    public MyStringBuilder(String str) {
        this.value = new char[str.length() + 16];
        append(str);
    }

    public MyStringBuilder append(String str) {
        if (str == null) str = "null";
        int len = str.length();
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }

    public void save() {
        history.push(new Snapshot(value, count));
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        Snapshot snapshot = history.pop();
        this.value = snapshot.getSavedChars();
        this.count = snapshot.getSavedCount();
    }

    private static final class Snapshot {
        private final char[] savedChars;
        private final int savedCount;

        private Snapshot(char[] source, int count) {
            this.savedChars = Arrays.copyOf(source, count);
            this.savedCount = count;
        }

        private char[] getSavedChars() {
            return savedChars;
        }

        private int getSavedCount() {
            return savedCount;
        }
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}


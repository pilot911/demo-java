package ru.telenok.newspaper.common.exception;

import java.util.ArrayList;
import java.util.List;

public class ListException extends Exception {

    private List<String> list = new ArrayList<>();

    public List<String> getAll() {
        return list;
    }

    public void add(String code) {
        list.add(code);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}

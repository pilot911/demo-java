package ru.telenok.newspaper.common;

public interface ImageConstant {

    enum Proportion {
        STRONG("STRONG"), FILL_FULL("FILL_FULL");

        private String name;

        Proportion(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

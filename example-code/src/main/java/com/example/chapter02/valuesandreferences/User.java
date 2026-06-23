package com.example.chapter02.valuesandreferences;

public class User {
    private String mName;
    private int mLevel;

    public User(String nameOrNull, int level) {
        if (nameOrNull == null || nameOrNull.isBlank()) {
            mName = "unknown";
        } else {
            mName = nameOrNull;
        }

        if (level < 1) {
            mLevel = 1;
        } else {
            mLevel = level;
        }
    }

    public String getName() {
        return mName;
    }

    public int getLevel() {
        return mLevel;
    }

    public void changeName(String nameOrNull) {
        if (nameOrNull == null || nameOrNull.isBlank()) {
            return;
        }

        mName = nameOrNull;
    }

    public void changeLevel(int level) {
        if (level < 1) {
            return;
        }

        mLevel = level;
    }

    @Override
    public String toString() {
        return mName + "(level=" + mLevel + ")";
    }
}

package com.example.chapter05.syntaxandlanguageergonomics;

public class MessageCounter {
    private final String mMessage;
    private int mCount;

    public MessageCounter(String messageOrNull) {
        if (messageOrNull == null || messageOrNull.isBlank()) {
            mMessage = "UNKNOWN";
        } else {
            mMessage = messageOrNull;
        }

        mCount = 0;
    }

    public String getMessage() {
        return mMessage;
    }

    public int getCount() {
        return mCount;
    }

    public void increaseCount() {
        ++mCount;
    }

    @Override
    public String toString() {
        return mMessage + "=" + mCount;
    }
}

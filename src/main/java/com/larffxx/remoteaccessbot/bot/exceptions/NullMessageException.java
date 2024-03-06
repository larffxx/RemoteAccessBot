package com.larffxx.remoteaccessbot.bot.exceptions;


public class NullMessageException extends Exception {
    public NullMessageException(String errMsg) {
        super(errMsg);
        throw new RuntimeException();
    }
}

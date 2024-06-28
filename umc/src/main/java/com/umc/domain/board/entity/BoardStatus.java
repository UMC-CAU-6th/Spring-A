package com.umc.domain.board.entity;

public enum BoardStatus {
    USE, DISABLE;

    public static BoardStatus fromString(String state) {
        try {
            return BoardStatus.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown state: " + state);
        }
    }
}

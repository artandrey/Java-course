package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class SeatRange {
    @Getter
    private int row;
    @Getter
    private int start;
    @Getter
    private int count;
    @Getter
    @Setter
    private int rank;

    public int getEnd() {
        return start + count;
    }
}

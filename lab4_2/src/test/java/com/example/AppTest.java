package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void shouldDecodePhrase() {
        String input = "t2st3ng vetviph boo";
        assertEquals(Decoder.decode(input), "testing testing zoo");
    }

    @Test
    public void shouldDecodeVovels() {
        assertEquals(Decoder.decodeVovel("t2st3ng"), "testing");
    }

    @Test
    public void shouldDecodeConsonants() {
        assertEquals(Decoder.decodeConsonant("vetviph"), "testing");
    }

}

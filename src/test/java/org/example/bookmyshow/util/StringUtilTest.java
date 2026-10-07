package org.example.bookmyshow.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest {

    @Test
    void shouldReturnTrueWhenStringIsBlank(){
        assertTrue(StringUtil.isBlank(""));

    }

    @Test
    void ShouldReturnFalseWhenStringIsNotBlank(){
        assertFalse(StringUtil.isBlank("james"));
    }

    @Test
    void shouldReturnTrueWhenStringContainsOnlySpaces() {
        assertTrue(StringUtil.isBlank("   "));
    }

    @Test
    void testAssertEquals(){
        String result = "Hello" + " World";
        assertEquals("Hello World",result);
    }

    @Test
    void throwException(){
        assertThrows(IllegalArgumentException.class,
                ()-> {
            throw new IllegalArgumentException();
        });
    }
}

package com.bf1.manualsuite;


import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TrieSETTest {

    TrieSET trieset;
    final String STR = "Hello World";
    final String STR2 = "This is Razeen";

    @Before
    public  void init(){
        trieset = new TrieSET();
    }

    @Test
    public void addString() {
        trieset.add(STR);
        assertEquals(true, trieset.contains(STR));
    }

    @Test
    public void addStringNull() {
        assertThrows(IllegalArgumentException.class, () ->
                    { trieset.add(null);
                });
    }


    @Test
    public void containsTrue() {
        trieset.add(STR);
        boolean result = trieset.contains(STR);
        assertEquals(true, result);
    }

    @Test
    public void containsFalse() {
        boolean result = trieset.contains(STR);
        assertEquals(false, result);
    }


    @Test
    public void containsNull() {
        assertThrows(IllegalArgumentException.class, () ->
        { trieset.contains(null);
        });
    }

    @Test
    public void deleteContains() {
        trieset.add(STR);
        trieset.delete(STR);
        assertEquals(false, trieset.contains(STR));
    }

    @Test
    public void deleteNotContains(){
        trieset.delete(STR);
        assertEquals(false, trieset.contains(STR));
    }

    @Test
    public void deleteNull(){
        trieset.add(STR);
        assertThrows(IllegalArgumentException.class, ()-> {
           trieset.delete(null);
        });
    }

    @Test
    public void isEmpty(){
        assertEquals(true, trieset.isEmpty());
    }

    @Test
    public void notEmpty() {
        trieset.add(STR);
        assertEquals(false, trieset.isEmpty());
    }

    @Test
    public void patternMatch() {
        trieset.add("Hello");
        trieset.add("Ron");
        for (String str: trieset.keysThatMatch("H..l.")) {
            assertEquals("Hello", str);
        }

    }

    @Test
    public void keysWithPrefix(){

        trieset.add(STR);
        for (String str: trieset.keysWithPrefix("He")) {
            assertEquals("Hello World", str);
        }
    }

    @Test
    public void LongestPrefix(){
        trieset.add("HelloWo");
        trieset.add("Hello");
        assertEquals("HelloWo", trieset.longestPrefixOf("HelloWorld"));

    }


    @Test
    public void LongestPrefixNull(){
        trieset.add(STR);
        trieset.add("hello");
        assertThrows(IllegalArgumentException.class, () -> {
            trieset.longestPrefixOf(null);
        });
    }

    @Test
    public void size(){
        trieset.add(STR);
        assertEquals(1, trieset.size());
    }


}
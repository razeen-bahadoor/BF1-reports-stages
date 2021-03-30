package com.bf1.grammarsuite;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrieSetTest {

    @Test
    public void testAdd(){
        TrieSET set = new TrieSET();
        assertThrows(IllegalArgumentException.class, ()->{
            set.add(null);
        });

        set.add("");
        assertEquals(true, set.contains(""));
        set.add("hello");
        assertEquals(true, set.contains(""));
        set.add(set.longestPrefixOf("hel"));
        assertEquals(true, set.contains("hello"));
    }

    @Test
    public void testDelete(){
        TrieSET set = new TrieSET();
        assertThrows(IllegalArgumentException.class, ()->{
            set.delete(null);
        });
        set.add("");
        set.add("hello");
        set.delete("hello");
        assertEquals(false, set.contains("hello"));
        set.add("hello");

        set.delete("tom");
        assertEquals(true, set.contains("hello") && set.contains(""));

    }

    @Test
    public void testContains(){
        TrieSET set = new TrieSET();
        assertThrows(IllegalArgumentException.class, ()->{
            set.contains(null);
        });

        set.add("hello");
        assertEquals(true, set.contains("hello"));
        assertEquals(false, set.contains("tom"));
    }

    @Test
    public void testIsEmptyNot(){
        TrieSET set = new TrieSET();
        set.add("hello");
        assertEquals(false, set.isEmpty());
    }
    @Test
    public void testIsEmpty(){
        TrieSET set = new TrieSET();
        assertEquals(true, set.isEmpty());
    }

    @Test
    public void testKeysThatMatch(){
        TrieSET set = new TrieSET();
        assertEquals(false, set.keysThatMatch(null).iterator().hasNext());


        set.add("hello");
        set.add("hexlo");
        assertEquals("hello",set.keysThatMatch("h.llo").iterator().next());
        assertEquals(false, set.keysThatMatch("t.om").iterator().hasNext());
    }

    @Test
    public void testKeysWithPrefix() {
        TrieSET set = new TrieSET();
        assertThrows(NullPointerException.class, ()->{
            set.keysWithPrefix(null);
        });


        set.add("hello");
        set.add("hexlo");
        set.add("tom");

        Iterator<String> it = set.keysWithPrefix("he").iterator();
        assertEquals("hello", it.next());
        assertEquals("hexlo", it.next());
        it = set.keysWithPrefix("lo").iterator();
        assertEquals(false,it.hasNext() );

    }

    @Test
    public void testLongestPrefixOf(){
        TrieSET set = new TrieSET();
        assertThrows(IllegalArgumentException.class, ()->{
            set.longestPrefixOf(null);
        });

        set.add("hello");
        set.add("hel");
        assertEquals("hello", set.longestPrefixOf("hellotom"));
        assertEquals(null, set.longestPrefixOf("to"));
    }

    @Test
   public void testSize(){
        TrieSET set = new TrieSET();
        assertEquals(0, set.size());
        set.add("hello");
        assertEquals(1, set.size());
    }
}


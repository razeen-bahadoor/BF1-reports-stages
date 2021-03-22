package com.bf1.manualsuite;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
public class StringWritTest {
    private StringWrite writer;

    @Before
    public void init() {
        writer = new StringWrite();
        writer = new StringWrite(20);
    }

    @Test
    public void testFlush(){
        writer.flush();
    }

    @Test
    public void testClose(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBuffer(){
        writer.append('c');
        assertEquals("c",writer.getBuffer().toString());
    }
    @Test
    public void testAppendChar() {
        writer.append('c');
        writer.append('1');
        writer.append('*');
        writer.append('\'');
        writer.append(' ');
        assertEquals("c1*' ", writer.toString());

    }

    @Test
    public void testAppendCharSequenceStringLiteral() {
       writer.append("Hello");
    }


    @Test
    public void testAppendCharSequenceNewString(){
        writer.append(new String("Hello"));
        assertEquals("Hello", writer.toString());

    }

    @Test
    public void testAppendCharSequenceNewStringEmpty(){
        writer.append(new String());
        assertEquals("", writer.toString());

    }

    @Test
    public void testAppendCharSequenceStringBuffer(){
        writer.append(new StringBuffer("Hello"));
        assertEquals("Hello", writer.toString());

    }

    @Test
    public void testAppendCharSequenceStringBufferEmpty(){
        writer.append(new StringBuffer());
        assertEquals("", writer.toString());


    }
    @Test
    public void testAppendCharSequenceStringBuilder(){
        writer.append(new StringBuilder("Hello"));
        assertEquals("Hello", writer.toString());

    }

    @Test
    public void testAppendCharSequenceStringBuilderEmpty(){
        writer.append(new StringBuilder());
        assertEquals("", writer.toString());


    }

    @Test
    public void testAppendCharSeqEntireString(){
        writer.append("hello",0,5);
        assertEquals("hello", writer.toString());
    }
    @Test
    public void testAppendCharSeqPartialString(){
        writer.append("hello",1,3);
        assertEquals("el", writer.toString());
    }
    @Test
    public void testAppendCharSeqNull(){
        writer.append(null,1,3);
        assertEquals("ul", writer.toString());
    }

    @Test
    public void testAppendCharSeqSameIndex(){
        writer.append("hello",2,2);
        assertEquals("", writer.toString());

    }
    @Test
    public void testAppendCharSeqEndOutBounds(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            writer.append("hello",0,6);

        });
    }

    @Test
    public void testAppendCharSeqNegativeStart(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            writer.append("hello",-1,3);

        });
    }
    @Test
    public void testAppendCharSeqNegativeEnd(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            writer.append("hello",0,-2);

        });
    }

    @Test
    public void testAppendCharSeqStartGreaterEnd(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            writer.append("hello",2,1);

        });
    }



    @Test
    public void testWriteInt(){
        writer.write(65);
        assertEquals("A", writer.toString());
    }

    @Test
    public void testWritString() {
        writer.write("hello");
        assertEquals("hello", writer.toString());
    }

    @Test
    public void testWriteCharArray() {
        writer.write(new char[]{'a','b','c','d'}, 0,4);
        assertEquals("abcd", writer.toString());

    }
    @Test
    public void testWriteCharArraySingle() {
        writer.write(new char[]{'a','b','c','d'}, 1,1);
        assertEquals("b", writer.toString());

    }

    @Test
    public void testWriteCharArrayOffsetError() {
        assertThrows(IndexOutOfBoundsException.class, ()->{
            writer.write(new char[]{'a','b','c','d'}, 10,2);

        });
    }

    @Test
    public void testWriteCharArrayNegativeOffsetError() {
        assertThrows(IndexOutOfBoundsException.class, ()->{
            writer.write(new char[]{'a','b','c','d'}, -1,3);

        });
    }

    @Test
    public void testWriteCharArrayNegativeLenError() {
        assertThrows(IndexOutOfBoundsException.class, ()->{
            writer.write(new char[]{'a','b','c','d'}, 1,-2);

        });
    }

     @Test
    public void testWriteStringAll(){
        writer.write("hello", 0, 4);
        assertEquals("hell", writer.toString());
     }

    @Test
    public void testWriteStringSingleChar(){
        writer.write("hello", 1, 1);
        assertEquals("e", writer.toString());
    }


    @Test
    public void testWriteStringNo(){
        writer.write("hello", 1, 0);
        assertEquals("", writer.toString());
    }

    @Test
    public void testWriteStringSubString(){
        writer.write("hello", 1, 3);
        assertEquals("ell", writer.toString());
    }

    @Test
    public void testWriteStringOffsetError(){
        assertThrows(IndexOutOfBoundsException.class, ()->{

            writer.write("Hello",10,2);

        });
    }




}

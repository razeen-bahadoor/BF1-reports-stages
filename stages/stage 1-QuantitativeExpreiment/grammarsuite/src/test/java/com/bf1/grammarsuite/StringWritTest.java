package com.bf1.grammarsuite;

import org.junit.Before;
import org.junit.Test;
import com.bf1.grammarsuite.StringWrite;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class StringWritTest {

    @Test
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, ()->{
            new StringWrite((-1));
        });
    }

    @Test
    public void appendChar(){

        assertEquals("a", new StringWrite().append('a').toString());
        assertEquals("a", new StringWrite(0).append('a').toString());
        assertEquals("a", new StringWrite(1).append('a').toString());

    }


    @Test
    public void appendString(){
        assertEquals("hello", new StringWrite().append("hello").toString());
        assertEquals("hello", new StringWrite(0).append("hello").toString());
        assertEquals("hello", new StringWrite(1).append("hello").toString());
    }


    @Test
    public void appendSubStr(){
        assertThrows(StringIndexOutOfBoundsException.class, ()->{
           new StringWrite().append("hello",-1,-2);
        });

        assertThrows(StringIndexOutOfBoundsException.class, ()->{
            new StringWrite().append("hello",-1,2);
        });

        assertThrows(StringIndexOutOfBoundsException.class, ()->{
            new StringWrite().append("hello",1,-2);
        });

        assertThrows(StringIndexOutOfBoundsException.class, ()->{
            new StringWrite().append("hello",2,1);
        });

        assertThrows(StringIndexOutOfBoundsException.class, ()->{
            new StringWrite().append("hello",0,9);
        });

        assertEquals("e", new StringWrite().append("hello", 1,2).toString());
    }

    @Test
    public void testWrietInt(){
      StringWrite writer =   new StringWrite();
      writer.write(65);
        assertEquals("A", writer.toString());

    }

    @Test
    public void testFLushAndClose(){
        try {
            new StringWrite().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new StringWrite().flush();
    }
    @Test
   public void testWriteString(){
        StringWrite writer =   new StringWrite();
        writer.write("hello");
        assertEquals("hello", writer.toString());

        assertThrows(IndexOutOfBoundsException.class,()->{ new StringWrite().write("hello", -1,-2);});
        assertThrows(IndexOutOfBoundsException.class,()->{ new StringWrite().write("hello", -1,2);});
        assertThrows(IndexOutOfBoundsException.class,()->{ new StringWrite().write("hello", 1,-2);});
        assertThrows(IndexOutOfBoundsException.class,()->{ new StringWrite().write("hello", 0,-9);});


        StringWrite writ = new StringWrite();
        writ.write(new StringWrite().append("hello").toString(), 1,2);
        assertEquals("el", writ.toString());


    }

    @Test
   public void testChar(){
        char[] chararray= new char[]{'a','b','c'};
        assertThrows(IndexOutOfBoundsException.class, ()->{new StringWrite().write(chararray, -1,-2);});
        assertThrows(IndexOutOfBoundsException.class, ()->{new StringWrite().write(chararray, -1,2);});
        assertThrows(IndexOutOfBoundsException.class, ()->{new StringWrite().write(chararray, 1,-2);});
        assertThrows(IndexOutOfBoundsException.class, ()->{new StringWrite().write(chararray, 0,10);});

        StringWrite w1 = new StringWrite();
        w1.write(chararray, 2,1);
        assertEquals("c", w1.toString());

    }

    @Test
   public void testBuffer(){
        assertEquals("hello",new StringWrite().append("hello").getBuffer().toString());
    }
}

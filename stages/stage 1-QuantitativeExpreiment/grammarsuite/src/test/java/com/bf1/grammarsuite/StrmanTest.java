package com.bf1.grammarsuite;

import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
// start 9pm end 11pm

public class StrmanTest {
    @Test
    public void testAppend() {
        assertThrows(IllegalArgumentException.class, ()->{
            Strman.append(null, Strman.collapseWhitespace("hello world"));
        });

        assertThrows(IllegalArgumentException.class, ()->{
            Strman.append(Strman.base64Decode("hello"), null);
        });


        assertEquals("helloworldthisisrazeen", Strman.appendArray(Strman.removeSpaces("he  llo"),Strman.split("world this is razeen", " ")));

    }

    @Test
    public void testAppendArray(){
        assertThrows(IllegalArgumentException.class, ()->{
            Strman.appendArray(null, Strman.words("hello world"));
        });


           assertEquals("00680065006c006c006f" ,Strman.appendArray(Strman.hexEncode("hello"), null));

           assertEquals("Helloworld", Strman.appendArray(Strman.upperFirst("hello"), Strman.chars("world")));
    }

    @Test
    public void testCollapseWhiteSpace(){
        assertThrows(IllegalArgumentException.class, ()->{
            Strman.collapseWhitespace(null);
        });

        assertEquals("helloworld", Strman.collapseWhitespace(Strman.prepend("world", Strman.lowerFirst("Hello"))));
        assertEquals("hello world raz", Strman.collapseWhitespace(Strman.prependArray("raz", Strman.between("tomhello world xx", "tom", "xx"))));
    }

    @Test
    public void testEnsureLeft() {
        assertEquals("helloworldworld", Strman.ensureLeft(Strman.repeat("world",2),Strman.rightTrim("hello   ")));
        assertEquals("helloworldworld", Strman.ensureLeft( Strman.leftTrim("   helloworldworld"), "hello") );

        assertEquals("helloworldworld", Strman.ensureLeft(Strman.repeat("world",2),Strman.rightTrim("hello   "), Strman.unequal("hello", "hello") ));
        assertEquals("helloworldworld", Strman.ensureLeft( Strman.leftTrim("   helloworldworld"), "hello", Strman.isUpperCase("HELLO")) );

    }


    @Test
    public void testBase64Decode() {
        assertEquals("zYh", Strman.base64Decode(Strman.last("hello", 4)));
    }

    @Test
    public void testBase64Encode(){
        assertEquals("bGVo", Strman.base64Encode(Strman.reverse("hel")));
    }

    @Test
    public void testBinDecode(){
        assertEquals("hello", Strman.binDecode(Strman.binEncode(Strman.toCamelCase("hello"))));
    }

    @Test
    public void testBinEncode(){
        assertEquals("Hello", Strman.binDecode(Strman.binEncode(Strman.toStudlyCase("hello"))));
    }


    @Test
    public void testDecDecode(){
        assertEquals("A", Strman.decDecode(Strman.leftPad("65", "0",4)));
    }

    @Test
    public void testDecEncode(){
        assertEquals("00065", Strman.decEncode(Strman.rightPad("", "A", 1)));
    }

    @Test
    public void testEnsureRight(){
        assertEquals("hello", Strman.ensureRight(Strman.join(Strman.chars("hello"), ""),Strman.replace("hexxo","x", "l", Strman.contains("dhkjdhj","jjlj"))));
        assertEquals("hello", Strman.ensureRight("he", Strman.last("hello", Strman.indexOf("heclo", "l",0,false)) ));


        assertEquals("Hello", Strman.ensureRight(Strman.join(Strman.chars("Hello"), ""),Strman.replace("hexxo","x", "l", Strman.contains("dhkjdhj","jjlj")), false));
        assertEquals("Hello", Strman.ensureRight("He", Strman.last("Hello", Strman.indexOf("heclo", "l",0,false)) , false));
    }

    @Test
    public void testHexDecode() {
        assertEquals("T",Strman.hexDecode(Strman.leftPad("4", "5", 2)));
    }
    @Test
    public void testHexEncode() {
        assertEquals("0045",Strman.hexEncode(Strman.insert("", "E",0)));
    }

    @Test
    public void testInsert(){
      assertEquals("HelloWorld",Strman.insert("Hello", Strman.surround("r","Wo", "ld"),Strman.lastIndexOf("hellll0", "l")) );
    }

    @Test
    public void testLast(){
        assertEquals("world", Strman.last(Strman.slice("hellohelloworld",5, 15 ), 5));
        assertEquals("hello", Strman.last("hello",20));
    }

    @Test
    public void testLeftPad(){
        assertEquals("hello", Strman.leftPad(Strman.removeNonWords("hell**o"), "*", 1));
        assertEquals("**foo.", Strman.leftPad(Strman.safeTruncate("foo bar", 4, "."), "*", 6));

    }

    @Test
    public void testLeftTrim() {
        assertEquals("h*", Strman.leftTrim(Strman.truncate("  hello",4 , "*")));
        assertEquals("hello", Strman.leftTrim(Strman.removeSpaces(" hello")));
    }

    @Test
    public void testPrepend() {
        assertEquals("hello", Strman.prepend(Strman.slugify("hello")));
        assertEquals("helloworldhello", Strman.prepend("hello", Strman.words("hello world")));
        assertEquals("hello", Strman.prepend("hello", Strman.words("")));
    }


    @Test
    public void testPrependArray(){
        assertEquals("helloworldhello", Strman.prependArray("hello", Strman.words("hello world")));
        assertEquals("hello", Strman.prependArray("hello", Strman.words("")));
    }

    @Test
    public void testRemoveLeft(){
        assertEquals("", Strman.removeLeft("",Strman.removeSpaces(" ")));
        assertEquals("world", Strman.removeLeft(Strman.removeSpaces("hello world"), Strman.removeNonWords("he**llo") ));
        assertEquals("helloworld", Strman.removeLeft(Strman.removeSpaces("hello world"), Strman.removeNonWords("to**m") ));


        assertEquals("world", Strman.removeLeft(Strman.removeSpaces("hello world"), Strman.capitalize("hello") , Strman.isLowerCase("HELLO")));
        assertEquals("helloworld", Strman.removeLeft(Strman.removeSpaces("hello world"), Strman.capitalize("tom"), Strman.endsWith("hello", "x")));

    }

    @Test
    public void testRemoveNonWords() {
        assertEquals("", Strman.removeNonWords("**"));
        assertEquals("", Strman.removeNonWords(Strman.removeSpaces("  ")));
        assertEquals("hello", Strman.removeNonWords(Strman.leftPad("hello", "*", 2)));
        assertEquals("hellohello", Strman.removeNonWords(Strman.repeat("hello", Strman.length("he"))));

    }

    @Test
    public void testRemoveRight(){
        assertEquals("", Strman.removeRight("",Strman.removeSpaces(" ")));
        assertEquals("hello", Strman.removeRight(Strman.removeSpaces("hello world"), Strman.removeNonWords("wo**rld") ));
        assertEquals("helloworld", Strman.removeRight(Strman.removeSpaces("hello world"), Strman.removeNonWords("to**m") ));

        assertEquals("hello", Strman.removeRight(Strman.removeSpaces("hello World"), Strman.capitalize("world") ,Strman.contains("hello", "ell")));
        assertEquals("helloworld", Strman.removeRight(Strman.removeSpaces("hello world"), Strman.capitalize("tom"), Strman.endsWith("hello", "x")));

    }


    @Test
    public void testRemoveSpaces(){
        assertEquals("", Strman.removeSpaces(Strman.removeNonWords("***")));
        assertEquals("helloworld", Strman.removeSpaces(Strman.append("hello", "world")));
        assertEquals("helloworld", Strman.removeSpaces(Strman.removeNonWords("hello** world")));
    }

    @Test
    public void testRepeat(){
        assertEquals("", Strman.repeat(Strman.removeSpaces("  "), 0));
        assertEquals("", Strman.repeat("hello", Strman.lastIndexOf("hello", "h")));
        assertEquals("hellohello", Strman.repeat("hello", Strman.length("he")));
    }

    @Test
    public void testReplace() {
        assertEquals("helloworld", Strman.replace("hellowxxld",Strman.repeat("x", 2), Strman.append("", "or"),true));
        assertEquals("helloworld", Strman.replace("hellowXXld",Strman.repeat("x", 2), Strman.append("", "or"),false));
        assertEquals("", Strman.replace(Strman.removeSpaces(" "),Strman.removeSpaces(" "),Strman.removeSpaces(" "),true ));
        assertEquals("", Strman.replace(Strman.removeSpaces(" "),Strman.removeSpaces(" "),Strman.removeSpaces(" "),false ));

    }


    @Test
    public void testReverse(){
        assertEquals("", Strman.reverse(Strman.removeSpaces(" ")));
        assertEquals("dlrow olleh", Strman.reverse(Strman.append("hello", " ","world")));
    }

    @Test
    public void testRightPad() {
        assertEquals("", Strman.rightPad(Strman.removeNonWords("**"), Strman.removeSpaces(""), 0));
        assertEquals("", Strman.rightPad(Strman.removeNonWords("**"), Strman.removeSpaces(""), 1));
        assertEquals("hello", Strman.rightPad(Strman.append("", "hello"), Strman.removeSpaces(" "), 0));
        assertEquals("hello", Strman.rightPad(Strman.appendArray("", Strman.chars("hello")), "h", 2));
        assertEquals("hellohhhhh", Strman.rightPad(Strman.appendArray("", Strman.chars("hello")), "h", 10));
    }

    @Test
    public void testRightTrim(){
        assertEquals("", Strman.rightTrim(Strman.removeSpaces(" ")));
        assertEquals("hello", Strman.rightTrim(Strman.append("","hello")));
        assertEquals("hello", Strman.rightTrim(Strman.append("","hello     ")));
    }

    @Test
    public void testSafeTruncate(){
        assertEquals("", Strman.safeTruncate(Strman.append("hello", "world"), Strman.length(""), "**"));
        assertEquals("**", Strman.safeTruncate(Strman.append("hello", "world"), Strman.length("1"), "**"));
        assertEquals("hello*", Strman.safeTruncate("hello world", 6, "*"));
        assertEquals("*", Strman.safeTruncate("helloworld", 6, "*"));

        assertEquals("", Strman.safeTruncate("", Strman.length("1"), "."));

    }

    @Test
    public void testTruncate(){
        assertEquals("", Strman.truncate(Strman.append("hello", "world"), Strman.length(""), "**"));
        assertEquals("*", Strman.truncate(Strman.append("hello", "world"), Strman.length("1"), "*"));
        assertEquals("h*", Strman.truncate("hello world", 2, "*"));
        assertEquals("", Strman.truncate("", Strman.length("1"), "."));
    }

    @Test
    public void testHtmlDecode(){
        assertEquals("<",Strman.htmlDecode(Strman.append("&","l","t")));
        assertEquals("null", Strman.htmlDecode(Strman.removeSpaces(" ")));
    }


    @Test
    public void testHtmlEncode(){
        System.out.println(Strman.htmlEncode("&"));
        assertEquals("&AMP;", Strman.htmlEncode(Strman.append("", "&")));
        assertEquals("nullnull", Strman.htmlEncode("à"));
        assertEquals("null", Strman.htmlEncode(" "));

    }

    @Test
    public void testShuffle(){
        assertEquals("RR", Strman.shuffle(Strman.repeat("R", Strman.length("22"))));
    }


    @Test
    public void testSlice(){
        assertEquals("", Strman.slice(Strman.collapseWhitespace(" "),Strman.length(""), Strman.length("")));
        assertEquals("hell", Strman.slice(Strman.collapseWhitespace("hello "),Strman.indexOf("hello", "h",0,false), Strman.length("aaaa")));

    }

    @Test
    public void testSlugify(){
        assertEquals("foo-bar", Strman.slugify(Strman.append("foo", " ", "bar")));
        assertEquals("foo", Strman.slugify(Strman.append("foo")));

        assertEquals("", Strman.slugify(Strman.append("")));


    }
    
    
    @Test
    public void testTransliterate(){
        assertEquals("foo bar", Strman.transliterate("fóõ bár"));
    }

    @Test
    public void testSurround(){
        assertEquals("", Strman.surround(Strman.removeSpaces(""),Strman.removeSpaces(""),Strman.removeSpaces("")));
        assertEquals("hello", Strman.surround(Strman.append("hello"),Strman.removeSpaces(""),Strman.removeSpaces("")));
        assertEquals("worldhelloworld", Strman.surround(Strman.append("hello"),Strman.removeSpaces("world "),Strman.removeSpaces("world ")));

    }

    @Test
    public void testToCamel(){
        assertEquals("", Strman.toCamelCase(Strman.removeSpaces(" ")));
        assertEquals("camelCase", Strman.toCamelCase(Strman.append("Camel", "Case")));
        assertEquals("camelCase", Strman.toCamelCase(Strman.append("camel-", "Case")));


    }

    @Test
    public void testStudyl(){
        assertEquals("", Strman.toStudlyCase(Strman.removeSpaces(" ")));
        assertEquals("HelloWorld", Strman.toStudlyCase(Strman.append("Hello", "World")));
        assertEquals("Helloworld",  Strman.toStudlyCase(Strman.append("hello", "world")));

    }


    @Test
    public void testToDecamelise(){
        assertEquals("hello world",Strman.toDecamelize(Strman.toCamelCase("HelloWorld"),null));
        assertEquals("", Strman.toDecamelize(Strman.append(Strman.removeSpaces(" ")),null));

    }


    @Test
    public void testToKebab(){
        assertEquals("helloworld", Strman.toKebabCase(Strman.append("helloworld")));
        assertEquals("hello-world", Strman.toKebabCase(Strman.append("hello World")));
        assertEquals("", Strman.toKebabCase(Strman.append(Strman.removeSpaces(" "))));


    }

    @Test
    public void testToSnake(){
        assertEquals("helloworld", Strman.toSnakeCase(Strman.append("helloworld")));
        assertEquals("hello_world", Strman.toSnakeCase(Strman.append("hello World")));
        assertEquals("", Strman.toSnakeCase(Strman.append(Strman.removeSpaces(" "))));


    }


    //***********************************

    @Test
    public void testJoin(){
        assertThrows(IllegalArgumentException.class, ()->{
            Strman.join(null,null);
        });

        assertThrows(IllegalArgumentException.class, ()->{
            Strman.join(new String[]{"hello", "World"},null);
        });

        assertThrows(IllegalArgumentException.class, ()->{
            Strman.join(null,Strman.repeat("*",1));
        });


         assertEquals("",   Strman.join(new String[]{},Strman.repeat("*",1)));


        assertEquals("hello**world", Strman.join(Strman.words("hello world"), Strman.repeat("*",2)));
    }


    @Test
    public void testLowerFirst() {
        assertThrows(IllegalArgumentException.class, ()->{
            Strman.lowerFirst(null);
        });

        assertEquals("hello", Strman.lowerFirst(Strman.upperFirst("hello")));
        assertEquals("hello", Strman.lowerFirst(Strman.append("hello")));
        assertEquals("", Strman.lowerFirst(Strman.removeSpaces(" ")));
    }


    @Test
    public void testUpperFirst() {
        assertThrows(IllegalArgumentException.class, ()->{
            Strman.upperFirst(null);
        });

        assertEquals("Hello", Strman.upperFirst(Strman.lowerFirst("hello")));
        assertEquals("Hello", Strman.upperFirst(Strman.append("hello")));

    }


    @Test
    public void testCapitalize(){
        assertThrows(IllegalArgumentException.class, ()->{
            Strman.capitalize(null);
        });

        assertEquals("", Strman.capitalize(Strman.removeSpaces("  ")));
        assertEquals("Hello", Strman.capitalize(Strman.append("heLLO")));
        assertEquals("Hello", Strman.capitalize(Strman.append("HeLLO")));

    }


    @Test
    public void testChop(){
        assertArrayEquals(new String[]{}, Strman.chop(Strman.removeSpaces(" "),Strman.length("hh")));
        assertArrayEquals(new String[]{}, Strman.chop(null,Strman.length("hh")));
        assertArrayEquals(new String[]{"hello"}, Strman.chop(Strman.append("hello"), Strman.length("")));
        assertArrayEquals(new String[]{"whi", "tes", "pac", "e"},Strman.chop(Strman.append("whitespace"), Strman.length("jjj")));
    }
    
    @Test
    public void testSwapCase(){
        assertEquals("hE", Strman.swapCase(Strman.append("He")));
        assertEquals("", Strman.swapCase(Strman.removeSpaces(" ")));
        assertEquals("", Strman.swapCase(null));
    }

    @Test
    public void testHumanize(){
        assertEquals("", Strman.humanize(Strman.removeSpaces(" ")));
        assertEquals("", Strman.humanize(null));
        assertEquals("Hello world", Strman.humanize(Strman.appendArray("", new String[]{"hello", " ","world"})));
        assertEquals("Hello world",Strman.humanize(Strman.underscored("hello world")) );
        assertEquals("Hello world",Strman.humanize(Strman.dasherize("hello world")) );
        assertEquals("Hello world",Strman.humanize(Strman.toCamelCase("hello world")) );


    }

    @Test
    public void testDasherize(){
        assertEquals("", Strman.dasherize(Strman.removeSpaces(" ")));
        assertEquals("", Strman.dasherize(null));
        assertEquals("hello-world", Strman.dasherize(Strman.toKebabCase("hello world")));
        assertEquals("hello", Strman.dasherize(Strman.append("hello")));
        assertEquals("hello-world", Strman.dasherize(Strman.toSnakeCase("hello world")));
        assertEquals("hello-world", Strman.dasherize(Strman.toCamelCase("hello world")));
    }

    @Test
    public void testLines(){
        assertArrayEquals(new String[]{""}, Strman.lines(Strman.removeSpaces(" ")));
        assertArrayEquals(new String[]{}, Strman.lines(null));
        assertArrayEquals(new String[]{"hello", "world"}, Strman.lines("hello\r\nworld"));
    }

    @Test
    public void testZip(){
       String[] zip =  Strman.zip(Strman.removeSpaces(" "), Strman.removeSpaces(" ")).toArray(new String[]{});
        assertArrayEquals(new String[]{}, zip );

        String[] zip2 = Strman.zip(null,null).toArray(new String[]{});
        assertArrayEquals(new String[]{}, zip2 );

        String[] zip3 = Strman.zip(Strman.append("abc"), Strman.append("def")).toArray(new String[]{});
        assertArrayEquals(new String[]{"ad", "be", "cf"}, zip3);


    }


    @Test
    public void testUnderscored(){
            assertEquals("", Strman.underscored(Strman.removeSpaces(" ")));
        assertEquals("", Strman.underscored(null));
        assertEquals("hello", Strman.underscored(Strman.append("hello")));
        assertEquals("hello_world", Strman.underscored(Strman.appendArray("", new String[]{"hello", "_", "world"})));
        assertEquals("hello_world", Strman.underscored(Strman.toCamelCase("hello world")));
        assertEquals("hello_world", Strman.underscored(Strman.dasherize("hello world")));

    }

    @Test
    public void testIsBlank(){
        assertEquals(true, Strman.isBlank(Strman.removeSpaces(" ")));
        assertEquals(true, Strman.isBlank(null));
        assertEquals(false, Strman.isBlank(Strman.removeSpaces(" hello")));


    }
    @Test
    public void testBetween(){
        assertArrayEquals( new String[]{" world "},Strman.between(Strman.appendArray("", new String[]{"hello ", "world " ,"is"}), "hello", "is"));
        assertArrayEquals( new String[]{},Strman.between(Strman.appendArray("", new String[]{"hello ", "world " ,"is"}), "z", "x"));

    }

    @Test
    public void testChars(){
        assertArrayEquals(new String[]{"h","e","l","l","o"}, Strman.chars(Strman.append("hello")));
        assertThrows(IllegalArgumentException.class, ()->{
           Strman.chars(null);
        });
    }

    @Test
    public void testRemoveEmpty() {
        assertArrayEquals(new String[]{"hello", "world"},Strman.removeEmptyStrings(Strman.words("hello world")));
        assertArrayEquals(new String[]{"hello", "world"},Strman.removeEmptyStrings(Strman.split(Strman.append("hello world", "",""), " ")));

    }

    @Test
    public void testSplit(){
        assertArrayEquals(new String[]{"helloworld"}, Strman.split(Strman.append("helloworld"), " "));
        assertArrayEquals(new String[]{"hello" , "orld"}, Strman.split(Strman.append("helloworld"), "w" ));
        assertArrayEquals(new String[]{"he","", "owor", "d"}, Strman.split(Strman.append("helloworld"), "l" ));


    }

    @Test
    public void testWords(){
        assertArrayEquals(new String[]{"*", "h"}, Strman.words(Strman.repeat("* h", 1)) );
        assertArrayEquals(new String[]{"helloworld"}, Strman.words(Strman.append("hello", "world")));
        assertArrayEquals(new String[]{"hello","world"}, Strman.words(Strman.append("hello", " world")));
        assertArrayEquals(new String[]{"hello","orld"}, Strman.words(Strman.append("hello", "world"), "w"));
        assertArrayEquals(new String[]{"helloworld"}, Strman.words(Strman.append("hello", "world"), "x"));



    }
//***************************************************
    @Test
    public void testCharsCount(){
       Map<Character, Long> k =  Strman.charsCount(Strman.removeSpaces(" "));
       assertEquals(true, k.isEmpty());

        Map<Character, Long> m =  Strman.charsCount(null);
        assertEquals(true, m.isEmpty());
        Map<Character, Long> n =  Strman.charsCount(Strman.repeat("h", 3));
       assertEquals(3,n.get('h'));


    }
//*********************************************************************
    @Test
    public void testContains() {
        assertEquals(true,Strman.contains(Strman.append("helLo", ""), Strman.repeat("l", 2)));
        assertEquals(true,Strman.contains(Strman.append("hello", ""), Strman.repeat("l", 2), true));
        assertEquals(true,Strman.contains(Strman.append("heLlo", ""), Strman.repeat("l", 2), false));

        assertEquals(false,Strman.contains(Strman.append("hello", ""), Strman.repeat("c", 2)));
        assertEquals(false,Strman.contains(Strman.append("hello", ""), Strman.repeat("cc", 2), true));
        assertEquals(false,Strman.contains(Strman.append("hello", ""), Strman.repeat("c", 2), false));

        assertEquals(true,Strman.contains("", "", true));


    }

    @Test
    public void testContainsAll(){
        assertEquals(true, Strman.containsAll(Strman.appendArray("",new String[]{"hello", "world", "this", "is"}), new String[]{"world", "this"}));
        assertEquals(true, Strman.containsAll(Strman.appendArray("",new String[]{"hello", "world", "this", "is"}), new String[]{"world", "this"}, true));
        assertEquals(false, Strman.containsAll(Strman.appendArray("",new String[]{"hello", "world", "this", "is"}), new String[]{"WORLD", "tHis"}, true));
        assertEquals(true, Strman.containsAll(Strman.appendArray("",new String[]{"hello", "world", "this", "is"}), new String[]{"WORLD", "tHis"}, false));

    }

    @Test
    public void testContainsAny(){
        assertEquals(true, Strman.containsAny(Strman.appendArray("",new String[]{"hello", "world", "this", "is"}), new String[]{"i","world", "j"}));
        assertEquals(false, Strman.containsAny(Strman.appendArray("",new String[]{"hello", "world", "this", "is"}), new String[]{"x","World", "j"}, true));
        assertEquals(true, Strman.containsAny(Strman.appendArray("",new String[]{"hello", "world", "this", "is"}), new String[]{"i","World", "j"}, false));



    }

    @Test
    public void testEndsWith(){
        assertEquals(true, Strman.endsWith(Strman.append("", "hello world"), Strman.append("","world")));
        assertEquals(false, Strman.endsWith(Strman.append("", "hello world"), Strman.repeat("x",4)));
        assertEquals(false, Strman.endsWith(Strman.append("", "hello worldx"), Strman.repeat("x",4)));


        assertEquals(true, Strman.endsWith(Strman.append("", "hello world"), Strman.append("","world"), true));
        assertEquals(false, Strman.endsWith(Strman.append("", "hello world"), Strman.repeat("x",4), true));
        assertEquals(false, Strman.endsWith(Strman.append("", "hello worldx"), Strman.repeat("x",4),true));
        assertEquals(true, Strman.endsWith(Strman.append("", "hello world"), Strman.append("","world"), false));
        assertEquals(false, Strman.endsWith(Strman.append("", "hello world"), Strman.repeat("x",4), false));
        assertEquals(false, Strman.endsWith(Strman.append("", "hello worldx"), Strman.repeat("x",4),false));

    }


    @Test
    public void testInequal(){
        assertEquals(false, Strman.inequal(Strman.lowerFirst("Hello"), Strman.append("he", "llo")));
        assertEquals(true, Strman.inequal(Strman.lowerFirst("Hello"), Strman.append("e", "llo")));
        assertEquals(false, Strman.inequal(Strman.removeSpaces("  "), Strman.removeNonWords("**")));

    }


    @Test
    public void testEnclosedBetween(){
        assertEquals(true, Strman.isEnclosedBetween("hello", Strman.removeSpaces(" ")));
        assertEquals(true, Strman.isEnclosedBetween(Strman.removeSpaces(""), Strman.removeSpaces(" ")));
        assertEquals(true, Strman.isEnclosedBetween(Strman.removeSpaces("worldhelloworld"), Strman.removeSpaces("w o r l d")));
        assertEquals(false, Strman.isEnclosedBetween(Strman.removeSpaces("helloworld"), Strman.removeSpaces("w o r l d")));

        assertEquals(false, Strman.isEnclosedBetween(Strman.append("hello"),Strman.append("xx")));


        assertEquals(true, Strman.isEnclosedBetween("hello", Strman.removeSpaces(" "), Strman.removeSpaces(" ")));
        assertEquals(true, Strman.isEnclosedBetween(Strman.removeSpaces(""), Strman.removeSpaces(" "), Strman.removeSpaces(" ")));
        assertEquals(true, Strman.isEnclosedBetween(Strman.removeSpaces("worldhelloworld"), Strman.removeSpaces("w o r l d"), Strman.removeSpaces("w o r l d")));

        assertEquals(false, Strman.isEnclosedBetween(Strman.removeSpaces("worldhello"), Strman.removeSpaces("w o r l d"), Strman.removeSpaces("w o r")));
        assertEquals(false, Strman.isEnclosedBetween(Strman.removeSpaces("worldhello"), Strman.removeSpaces("w o r l d"), Strman.removeSpaces("w o r")));


    }

    @Test
    public void testIslowerCase(){
        assertEquals(true, Strman.isLowerCase(Strman.lowerFirst("Hello")));
        assertEquals(false, Strman.isLowerCase(Strman.upperFirst("hello")));
        assertEquals(true, Strman.isLowerCase(Strman.repeat("*", 3)));
        assertEquals(true, Strman.isLowerCase(Strman.removeSpaces(" ")));
    }

    @Test
    public void testisString(){
            assertEquals(false, Strman.isString(Strman.length("hello")));
        assertEquals(false, Strman.isString(Strman.unequal("hello", "tom")));
        assertEquals(true, Strman.isString(Strman.removeSpaces("  ")));
        assertEquals(true, Strman.isString(Strman.removeSpaces(" hello ")));



    }

    @Test
    public void testIsUpperCase(){
        assertEquals(true, Strman.isUpperCase(Strman.repeat("*", 1)));
        assertEquals(true, Strman.isUpperCase(Strman.removeSpaces(" ")));
        assertEquals(false, Strman.isUpperCase(Strman.removeSpaces("hello ")));
        assertEquals(true, Strman.isUpperCase(Strman.removeSpaces("100 ")));

        assertEquals(true, Strman.isUpperCase(Strman.removeSpaces("HELLO ")));




    }

    @Test
    public void testUnequal(){
        assertEquals(false, Strman.unequal(Strman.lowerFirst("Hello"), Strman.append("he", "llo")));
        assertEquals(true, Strman.unequal(Strman.lowerFirst("Hello"), Strman.append("e", "llo")));
        assertEquals(false, Strman.unequal(Strman.removeSpaces("  "), Strman.removeNonWords("**")));

    }


    @Test
    public void testCountSubstr(){
        assertEquals(0, Strman.countSubstr(Strman.append("hello"), "xx"));
        assertEquals(2, Strman.countSubstr(Strman.append("xxhelloxxhelloxxx"), "hello"));
        assertEquals(0, Strman.countSubstr(Strman.removeSpaces(" "), "hello"));


        assertEquals(2,Strman.countSubstr("hellolol",Strman.append("lol"), true, true));
        assertEquals(1,Strman.countSubstr("hellolol",Strman.append("lol"), true, false));


        assertEquals(2,Strman.countSubstr("hellolol",Strman.append("lol"), false, true));
        assertEquals(1,Strman.countSubstr("hellolol",Strman.append("lol"), false, false));


    }

    @Test
    public void testIndexOf(){
        assertEquals(0, Strman.indexOf(Strman.removeSpaces(" "), Strman.removeSpaces(" "), 0, true));
        assertEquals(-1, Strman.indexOf(Strman.removeSpaces("hello "), Strman.removeSpaces("x "), 0, true));

        assertEquals(2, Strman.indexOf(Strman.append("hello"), Strman.removeSpaces("l "), 0, true));
        assertEquals(2, Strman.indexOf(Strman.append("heLlo"), Strman.removeSpaces("l "), 0, false));

    }



    @Test
    public void testLasIndexOf(){
        assertEquals(0, Strman.lastIndexOf(Strman.removeSpaces(" "), Strman.removeSpaces(" "), 0, true));
        assertEquals(-1, Strman.lastIndexOf(Strman.removeSpaces("hello "), Strman.removeSpaces("x "), 0, true));

        assertEquals(-1, Strman.lastIndexOf(Strman.append("hello"), Strman.removeSpaces("l "), 0, true));
        assertEquals(-1, Strman.lastIndexOf(Strman.append("heLlo"), Strman.removeSpaces("l "), 0, false));

        assertEquals(3, Strman.lastIndexOf(Strman.append("hello"), Strman.removeSpaces("l ")  ));
        assertEquals(3, Strman.lastIndexOf(Strman.append("heLlo"), Strman.removeSpaces("l ")));

        assertEquals(3, Strman.lastIndexOf(Strman.append("hello"), Strman.removeSpaces("l ")  , true));
        assertEquals(3, Strman.lastIndexOf(Strman.append("heLlo"), Strman.removeSpaces("l "), false));

    }


    @Test
    public void testAt(){
        assertEquals("l", Strman.at(Strman.append("hello"), 2).get());
        assertEquals("o", Strman.at(Strman.append("hello"), -1).get());

        assertEquals(Optional.empty().empty(), Strman.at(Strman.append("hello"), 10));
        assertEquals(Optional.empty(), Strman.at(Strman.append("hello"), -10));

    }


    @Test
    public void testFirst(){
        assertEquals("hell", Strman.first(Strman.append("hello"), 4).get());
        assertEquals(Optional.empty(), Strman.first(Strman.append(""), 0));
        assertEquals("", Strman.first(Strman.append("hello"), 0).get());




    }

    @Test
    public void testHead(){
        assertEquals("h", Strman.head(Strman.append("hello")).get());
        assertEquals(Optional.empty(), Strman.head(Strman.append("")));

    }

    @Test
    public void testTail(){
        assertEquals("ello", Strman.tail(Strman.append("hello")).get());
        assertEquals(Optional.empty(), Strman.tail(Strman.append("")));

    }

    @Test
    public void testTrimStart(){
        assertEquals("hello", Strman.trimStart(Strman.append("  ", "hello")).get());
        assertEquals("hello", Strman.trimStart(Strman.append("", "hello")).get());

        assertEquals("hello", Strman.trimStart(Strman.append("world", "hello"), "world").get());
        assertEquals("worldhello", Strman.trimStart(Strman.append("world", "hello"), "xxx").get());

    }


    @Test
    public void testTrimEnd(){
        assertEquals("hello", Strman.trimEnd(Strman.append("hello", "  ")).get());
        assertEquals("hello", Strman.trimEnd(Strman.append("", "hello")).get());

        assertEquals("world", Strman.trimEnd(Strman.append("world", "hello"), "hello").get());
        assertEquals("worldhello", Strman.trimEnd(Strman.append("world", "hello"), "xxx").get());

    }


}

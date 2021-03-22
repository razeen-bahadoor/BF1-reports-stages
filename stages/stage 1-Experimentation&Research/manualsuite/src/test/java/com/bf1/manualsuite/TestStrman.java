package com.bf1.manualsuite;


import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestStrman {

    final String STR = "Hello World";

    @Test
    public void testAppendNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Strman.append(null, "hello");
        });
    }

    @Test
    public void testAppend() {
        assertEquals("Hello World", Strman.append("Hello ", "World"));
    }


    @Test
    public void testAppendArray() {
        assertEquals("Hello World from Razeen",
                Strman.appendArray(STR, new String[]{
                        " from", " Razeen"
                })
        );
    }

    @Test
    public void testAppendEmptyArray() {
        assertEquals("Hello World",
                Strman.appendArray(STR, new String[]{
                })
        );
    }

    @Test
    public void testAtOutOfRange() {
        assertEquals(Optional.empty(),
                Strman.at("Hello World", -100)
        );
    }

    @Test
    public void testAtNegativeIndexInRange() {
        assertEquals("o", Strman.at("Hello World", -4).get());
    }


    @Test
    public void testAtPositiveIndexInRange() {
        assertEquals("o", Strman.at("Hello World", 4).get());
    }

    @Test
    public void testBetween() {

        assertArrayEquals(new String[]{"abc", "def"},
                Strman.between("[abc][def]",
                        "[", "]"));
    }

    @Test
    public void testChars() {
        String[] expected = new String[]{"H", "e", "l", "l", "o"};
        assertArrayEquals(expected, Strman.chars("Hello"));
    }

    @Test
    public void testCollapseWhitespace() {
        assertEquals("Hello World", Strman.collapseWhitespace("Hello  World"));
    }


    @Test
    public void testContainsCaseSensitive() {
        assertTrue(Strman.contains("Hello World", "World"));
    }

    @Test
    public void testContainsCaseInsensitive() {
        assertTrue(Strman.contains("Hello World", "world", false));

    }


    @Test
    public void testContainsAllCaseSensitive() {
        assertTrue(Strman.containsAll("Hello World from Razeen", new String[]{"World", "from"}));
    }

    @Test
    public void testContainsAllCaseInsensitive() {
        assertTrue(Strman.containsAll("Hello World from Razeen", new String[]{"World", "from"}, false));

    }

    @Test
    public void testContainsAnyCaseSensitive() {
        assertTrue(Strman.containsAny("Hello World from Razeen", new String[]{"from", "david"}));
    }

    @Test
    public void testContainsAnyCaseInSensitive() {
        assertTrue(Strman.containsAny("Hello World from Razeen", new String[]{"From", "david"}, false));
    }

    @Test
    public void testCountSubstrNoOverlapping(){
        assertEquals(1, Strman.countSubstr(STR, "ll"));
    }

    @Test
    public void testCountSubstrOverlapping(){
        assertEquals(2, Strman.countSubstr("Helllo", "ll", false, true));
    }


    @Test
    public void testEndsWithCaseSens(){
        assertTrue(Strman.endsWith(STR,"ld"));
    }

    @Test
    public void testEndsWithCaseInSens(){
        assertTrue(Strman.endsWith(STR,"LD", false));
    }


    @Test
    public void testEndsWithCaseInSensPosition(){
        assertTrue(Strman.endsWith(STR,"LO", 4,false));
    }


    @Test
    public void testEnsureLeftCaseInsensitive(){
        assertEquals("Hello", Strman.ensureLeft("Hello", "Hel"));
    }


    @Test
    public void testEnsureLeftPrependCaseInsensitive(){
        assertEquals("Hello", Strman.ensureLeft("lo", "Hel"));
    }

    @Test
    public void testBase64Decode(){
        assertEquals("strman", Strman.base64Decode("c3RybWFu"));
    }

    @Test
    public void testBase64Encode(){
        assertEquals("c3RybWFu", Strman.base64Encode("strman"));


    }


    @Test
    public void testBinDecode(){
        assertEquals("A", Strman.binDecode("0000000001000001"));
    }

    @Test
    public void testBinEncode(){
        assertEquals("0000000001000001", Strman.binEncode("A"));


    }


    @Test
    public void testdecDecode(){
        assertEquals("A", Strman.decDecode("00065"));
    }

    @Test
    public void testdecEncode(){
        assertEquals("00065", Strman.decEncode("A"));


    }

    @Test
    public void testEnsureRight(){
        assertEquals("foobar", Strman.ensureRight("foo", "bar"));

        assertEquals("foobar", Strman.ensureRight("foobar", "bar"));
    }
/*********************************************************/

@Test
    public void testFirst(){
        assertEquals("Hell", Strman.first("Hello", 4).get());
    }

    @Test
    public void testHead(){
        assertEquals("H", Strman.head("Hello").get());
    }

    @Test
    public void  testHexDecode(){
        assertEquals("A", Strman.hexDecode("0041"));
    }

    @Test
    public void testHexEncode(){
        assertEquals("0041", Strman.hexEncode("A"));
    }

@Test
    public void testIndexOf(){

    assertEquals(1, Strman.indexOf("Hello", "e",0, false));

    assertEquals(-1, Strman.indexOf("Hello", "x",0, false));
}

@Test
    public void testUnequal(){
    assertEquals(false, Strman.unequal("Hello", "Hello"));
    assertEquals(true, Strman.unequal("Hello", "World"));
}


@Test
public void testInsert(){
    assertEquals("hello", Strman.insert("hel", "lo", 3));
}

@Test
    public void testUppercase(){
    assertTrue(Strman.isUpperCase("HELLO"));
    assertFalse(Strman.isUpperCase("hello"));
}


    @Test
    public void testLowerCase(){
        assertTrue(Strman.isLowerCase("hello"));
        assertFalse(Strman.isLowerCase("HELLO"));
    }

    @Test
    public void testLast() {
        assertEquals("llo", Strman.last("Hello", 3));
    }

    @Test
    public void testLeftPad(){
        assertEquals("00001", Strman.leftPad("1", "0", 5));
    }

    @Test
    public void testIsString(){
        assertTrue(Strman.isString("hello"));
        assertFalse(Strman.isString(1));
    }

    @Test
    public void testLastIndexOf(){
        assertEquals(1, Strman.lastIndexOf("Hello", "e"));
    }

    @Test
    public void testLastIndexOfCaseSensitive(){
        assertEquals(1, Strman.lastIndexOf("Hello", "E", false));
    }


    @Test
    public void testLastIndexOfCaseSensitiveOffset(){
        assertEquals(-1, Strman.lastIndexOf("Hello", "l", 1,false));
    }

    @Test
    public void testLeftTrim() {
        assertEquals("Hello", Strman.leftTrim("   Hello"));
    }


    @Test
    public void testLength(){
        assertEquals(4, Strman.length("Hell"));
    }

    @Test
    public void testPrepend() {
        assertEquals("hello", Strman.prepend("llo", "he"));
    }

    @Test
    public void testPrependArray(){
        assertEquals("barfoofoo", Strman.prependArray("foo", new String[]{"bar", "foo"}));
    }


    @Test
    public void testRemoveEmptyStrings(){
        assertArrayEquals(new String[]{"Hello", "World"}, Strman.removeEmptyStrings(new String[]{"Hello", "", "World"}));
    }

    @Test
    public void testRemoveLeft() {
        assertEquals("llo", Strman.removeLeft("Hello", "He"));
    }

    @Test
    public void testRemoveLeftCaseSebnsitive() {
        assertEquals("llo", Strman.removeLeft("Hello", "HE", false));
    }

    @Test
    public void testRemoveNonWord(){
        assertEquals("Hello", Strman.removeNonWords("He**llo"));


    }



    @Test
    public void testRemoveRigh() {
        assertEquals("He", Strman.removeRight("Hello", "llo"));
    }

    @Test
    public void testRemoveRightCaseSebnsitive() {
        assertEquals("He", Strman.removeRight("Hello", "LLO", false));
    }

    @Test
    public void testRemoveSpaces(){
        assertEquals("HelloWorld", Strman.removeSpaces("Hello World"));
    }


    @Test
    public void testRepeated(){
        assertEquals("HelloHello", Strman.repeat("Hello", 2));
    }

    @Test
    public void testReplace(){
        assertEquals("Hello Earth", Strman.replace("Hello World", "World", "Earth", true));
    }


    @Test
    public void testRightPad(){
        assertEquals("Hello**", Strman.rightPad("Hello", "*", 7));
    }

    @Test
    public void testRightTrim(){
        assertEquals("Hello", Strman.rightTrim("Hello  "));
    }
    @Test
    public void testSelfTruncate(){
        assertEquals("foo.", Strman.safeTruncate("foo bar", 4, "."));



}

@Test
    public void testSplit(){
        String[] expected = new String[]{"Hello", "World"};
        assertArrayEquals(expected, Strman.split("Hello World", " "));
}


    @Test
    public void testWirds(){
        assertArrayEquals(new String[]{"Hello", "World"}, Strman.words("Hello World"));
        assertArrayEquals(new String[]{"Hello", "World"}, Strman.words("Hello,World", ","));

    }

    @Test
    public void testChop(){
        assertArrayEquals(new String[]{}, Strman.chop(null,1));
        assertArrayEquals(new String[]{}, Strman.chop("",1));
        assertArrayEquals(new String[]{"whi", "tes", "pac", "e"}, Strman.chop("whitespace", 3));


    }

    @Test
    public void testSwapCase(){
        assertEquals("", Strman.swapCase(""));
        assertEquals("RE", Strman.swapCase("re"));

    }

    @Test
    public void testShuffle(){
        assertEquals("", Strman.shuffle(""));
        assertEquals("RR", Strman.shuffle("RR"));
    }


    @Test
    public void testTransliterate(){
        assertEquals("foo bar", Strman.transliterate("fóõ bár"));
        assertEquals("", Strman.transliterate(""));
        assertEquals("hello", Strman.transliterate("hello"));
    }
    @Test
    public void testUpperFirst(){
        assertEquals("Hello", Strman.upperFirst("hello"));
        assertEquals("Hello", Strman.upperFirst("hello"));

    }
    
    @Test
    public void testIsenclosedBetween(){
        assertEquals(true,Strman.isEnclosedBetween("hello", "",""));
        assertEquals(true,Strman.isEnclosedBetween("", "",""));
        assertEquals(false,Strman.isEnclosedBetween("worldhello", "","world"));
        assertEquals(true,Strman.isEnclosedBetween("worldhellotom", "world","tom"));

        assertEquals(true, Strman.isEnclosedBetween("", ""));
        assertEquals(true, Strman.isEnclosedBetween("hello", ""));
        assertEquals(true, Strman.isEnclosedBetween("ollo", "o"));

    }


    @Test
    public void testZip(){
    assertArrayEquals(new String[]{}, Strman.zip(null,null).toArray(new String[]{}));
        assertArrayEquals(new String[]{"ad", "be", "cf"}, Strman.zip("abc", "def").toArray(new String[]{}));
    }
    @Test
    public void testTruncate(){
        assertEquals("hel*",Strman.truncate("helloworld",4,"*"));
    }

    @Test
    public void trimStart() {
            assertEquals("Hello", Strman.trimStart("Hello").get());
        assertEquals("ello", Strman.trimStart("Hello","H").get());

    }

    @Test
    public void testCapitalize(){
        assertEquals("", Strman.capitalize(""));
        assertEquals("Hello", Strman.capitalize("hEllo"));
        assertEquals("Hello", Strman.capitalize("Hello"));

    }

    @Test
    public void testLowerFirst(){
        assertEquals("", Strman.lowerFirst(""));
        assertEquals("hello", Strman.lowerFirst("hello"));
        assertEquals("hello", Strman.lowerFirst("Hello"));

    }

    @Test
    public void testHtmlDecode(){
        assertEquals("<", Strman.htmlDecode("&lt"));
        assertEquals("null", Strman.htmlDecode("%20"));
    }


    @Test
    public void testHtmlEncode() {
            assertEquals("&LT;", Strman.htmlEncode("<"));
        assertEquals("nullnull", Strman.htmlEncode("20"));

    }

    @Test
    public void testUnderscored(){
        assertEquals("", Strman.underscored(""));
        assertEquals("", Strman.underscored(null));
        assertEquals("hello_world", Strman.underscored("hello_world"));
        assertEquals("hello_world", Strman.underscored("helloWorld"));
        assertEquals("hello_world", Strman.underscored("hello-world"));

    }
    @Test
    public void testCharsCount(){
    Map<Character, Long> map = Strman.charsCount("hhh");
        assertEquals(3,map.get('h') );
    }

    @Test
    public void testHumanize(){
        assertEquals("", Strman.humanize(""));
        assertEquals("", Strman.humanize(null));
        assertEquals("Hello world", Strman.humanize("Hello world"));
        assertEquals("Hello world", Strman.humanize("Hello_world"));
        assertEquals("Hello world", Strman.humanize("helloWorld"));
        assertEquals("Hello world", Strman.humanize("hello-World"));



    }

    @Test
    public void testReverse(){
        assertEquals("", Strman.reverse(""));
        assertEquals("ER", Strman.reverse("RE"));

    }

    @Test
    public void testSlice(){
        assertEquals("hell", Strman.slice("hello",0, 4));
    }


    @Test
    public void testIsBlank(){
        assertEquals(true, Strman.isBlank(""));
        assertEquals(true, Strman.isBlank(null));
        assertEquals(false, Strman.isBlank("null"));


    }

    @Test
    public void testInequal(){

        assertEquals(true, Strman.inequal("hello", "yom"));
        assertEquals(false, Strman.inequal("hello", "hello"));

    }

    @Test
    public void testDasherize(){

        assertEquals("", Strman.dasherize(""));
        assertEquals("helloworld", Strman.dasherize("helloworld"));
        assertEquals("hello-world", Strman.dasherize("helloWorld"));
        assertEquals("hello-world", Strman.dasherize("hello_world"));
    }

    @Test
    public void testLines(){
        assertArrayEquals(new String[]{"Hello", "World"}, Strman.lines("Hello\r\nWorld"));
    }
    @Test
    public void testSlugify(){
        assertEquals("foo-bar", Strman.slugify("foo bar"));
        assertEquals("", Strman.slugify(""));
        assertEquals("foobar", Strman.slugify("foobar"));
    }
    @Test
    public void trimSEnd() {
        assertEquals("Hello", Strman.trimEnd("Hello").get());
        assertEquals("Hell", Strman.trimEnd("Hello","o").get());

    }

    @Test
    public void testToStudylCase(){
        assertEquals("Hotmail", Strman.toStudlyCase("hotmail"));
    }

    @Test
    public void testSnakeCase(){
        assertEquals("hello_world", Strman.toSnakeCase("hello world"));
    }

    @Test
    public void testKebabCase(){
        assertEquals("hello-world", Strman.toKebabCase("hello world"));
    }

    @Test
    public void testDeCamel(){
        assertEquals("hello*world", Strman.toDecamelize("HelloWorld","*"));

    }


    @Test
    public void testTail(){
        assertEquals("ello world", Strman.tail("hello world").get());
    }

    @Test
    public void testSurround(){
        assertEquals("*Hello%", Strman.surround("Hello","*","%" ));
    }


}



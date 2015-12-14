package com.github.lwz0316.httpagent.impl;

import junit.framework.TestCase;

/**
 * Created by liuwenzhu on 2015/12/14.
 */
public class JsonParserTest extends TestCase {

    public void testParse() throws Exception {
        final String jsonData = "{\"name\": \"Tom\", \"age\": 20}";
        People people = new JsonParser<People>().parse(jsonData.getBytes(), People.class);
        assertNotNull(people);
        assertEquals(people.name, "Tom");
        assertEquals(people.age, 20);
    }
}
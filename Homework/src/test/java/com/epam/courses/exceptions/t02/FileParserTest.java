package com.epam.courses.exceptions.t02;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Date: 03.02.2017
 *
 * @author Karapetyan N.K
 */
public class FileParserTest {

    @Test
    public void getValueTest(){
        FileParser fp = new FileParser(
                "src/main/resources/db.properties");
        assertThat(fp.getValue("driver"), is("org.h2.Driver"));
    }
}
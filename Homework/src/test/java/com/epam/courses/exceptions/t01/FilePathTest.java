package com.epam.courses.exceptions.t01;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Date: 31.01.2017
 *
 * @author Karapetyan N.K
 */
public class FilePathTest {
    private FilePath fp = new FilePath();
    private String simplefile = "simplefile.txt";
    private String defaultFile = "default.txt";
    private String oldFile;
    private String newFile;

    @Test
    public void simpleWriteTest() throws IOException {
        fp.writeToFile(simplefile, "hello");
        oldFile = read(simplefile);
        fp.writeToFile(simplefile, "world");
        newFile = read(simplefile);
        assertThat(newFile, is(oldFile+"world\n"));
    }

    @Test
    public void defaultWriteTest() throws IOException {
        fp.writeToFile("", "hello");
        oldFile = read(defaultFile);
        fp.writeToFile("", "world");
        newFile = read(defaultFile);
        assertThat(newFile, is(oldFile+"world\n"));
    }

    @Test
    public void dirsAndFilesTest(){
        String df =
                fp.getDirsAndFiles("src/test/java/com/epam/courses/exceptions/t01").toString();
        String dftest = "Files:\n" +
                "src\\test\\java\\com\\epam\\courses\\exceptions\\t01\\dirtest1\\filetest1.data\n" +
                "src\\test\\java\\com\\epam\\courses\\exceptions\\t01\\dirtest2\\filetest2.data\n" +
                "src\\test\\java\\com\\epam\\courses\\exceptions\\t01\\FilePathTest.java\n\n" +
                "Directories:\n" +
                "src\\test\\java\\com\\epam\\courses\\exceptions\\t01\\dirtest1\n" +
                "src\\test\\java\\com\\epam\\courses\\exceptions\\t01\\dirtest2\n";
        assertThat(df, is(dftest));
    }

    private String read(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String str;
        while((str = br.readLine())!=null)
            sb.append(str+"\n");
        br.close();
        return sb.toString();
    }
}

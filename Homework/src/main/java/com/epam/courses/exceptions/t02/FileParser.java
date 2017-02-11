package com.epam.courses.exceptions.t02;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Date: 03.02.2017
 *
 * @author Karapetyan N.K
 */
@SuppressWarnings("WeakerAccess")
public class FileParser {
    private Properties properties;
    private static Logger logger = Logger.getLogger("src/main/resources/log4j");

    public FileParser(File file){
         loading(file);
    }
    // Перегруженный конструктор
    public FileParser(String file){
        this(new File(file));
    }

    public String getValue(@SuppressWarnings("SameParameterValue") String key){
        if(properties.containsKey(key))
            return properties.getProperty(key);
        KeyNotExistException e = new KeyNotExistException();
        logger.warn("Key not exist in properties", e);
        throw e;
    }

    /*
    * При создании объекта данного класса, происходит загрузка данных
    * из файла в объект Properties.
    */
    private Properties loading(File file){
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            logger.error("Can't find such file",e );
        } catch (IOException e) {
            logger.error("IO exception", e);
        }
        return properties;
    }
}

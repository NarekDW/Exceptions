package com.epam.courses.exceptions.t02;

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

    public FileParser(File file){
         loading(file);
    }
    // Перегруженный конструктор
    public FileParser(String file){
        this(new File(file));
    }

    public String getValue(@SuppressWarnings("SameParameterValue") String key){
        String value;
        if((value = properties.getProperty(key))== null)
                throw new KeyNotExistException(
                        "\nКлюча - "+key+" не существует в данном фале!");
        return value;
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
            System.err.println("Файл: "+file.getAbsolutePath()+" не найден! \n");
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода:\n");
        }
        return properties;
    }
}

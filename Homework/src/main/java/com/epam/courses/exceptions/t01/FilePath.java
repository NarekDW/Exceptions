package com.epam.courses.exceptions.t01;

import java.io.*;
import java.util.Arrays;

/**
 * Date: 31.01.2017
 *
 * @author Karapetyan N.K
 */
public class FilePath {
    private final String REGEX = "(.*[\\.]gradle)|(.*[\\.]idea)|(build)"; // Отсекает системные папки
    private  FileSet files = new FileSet();
    private  FileSet dirs = new FileSet();

    /**
     * <p>
     * Метод рекурсивно проходит по всему дереву директорий
     * начиная с заданной директории dirStart, и записывает директории
     * в отдельный массив File[], а файлы в другой массив File[].
     * </p>
     * */
    public FilePath getDirsAndFiles(File dirStart){
        FilePath fp = new FilePath();
        for(File item : dirStart.listFiles()){
            if(item.isDirectory()){
                if(!item.getName().matches(REGEX)){
                    fp.dirs.add(item);
                    fp.addAll(getDirsAndFiles(item));
                }
            } else if(item.isFile())
                fp.files.add(item);
        }
        return fp;
    }
    // Перегруженный метод
    public FilePath getDirsAndFiles(String dirStart){
        return getDirsAndFiles(new File(dirStart));
    }

    /**
     * <p>
     * Метод дозаписывающий информацию в файл
     * в случае неправильного задания имени файла(например не задать имя файла),
     * создается default файл, куда записывается информация.
     * </p>
     * @parametr encodeing задаёт кодировку файла
     * */
    private String encoding = "UTF-8";
    public void writeToFile(File file, String message){
        try(PrintWriter out = new PrintWriter( new OutputStreamWriter(
                new FileOutputStream(file, true), encoding
        ))) {
            out.println(message);
        } catch (FileNotFoundException e) {
            File defaultFile = null;
            try {
                file.createNewFile();
                writeToFile(file, message);
            } catch (IOException e1) {
                defaultFile = new File("default.txt");
                writeToFile(defaultFile, message);
            }
        } catch (UnsupportedEncodingException e) {
            encoding = "cp1251";
            writeToFile(file, message);
        }
    }
    // Перегруженный метод
    public void writeToFile(String file, String message){
        writeToFile(new File(file), message);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Files:\n");
        for(File f:files.contain)
            sb.append(f+"\n");
        sb.append("\nDirectories:\n");
        for(File d:dirs.contain)
            sb.append(d+"\n");
        return sb.toString();
    }

    private void addAll(FilePath other){
        files.add(other.files.contain);
        dirs.add(other.dirs.contain);
    }

    /**
     * Приватный внутренний класс содержащий массив File[]
     * позволяющий добавлять в массив новые файлы.
     * @see java.util.ArrayList
     * @see java.io.File
     * */
    private class FileSet{
        int size = 0;
        File[] contain = new File[size];
        void add(File ... files){
            contain = Arrays.copyOf(contain, (size+files.length));
            System.arraycopy(files, 0, contain, size, files.length);
            size+=files.length;
        }
    }
}

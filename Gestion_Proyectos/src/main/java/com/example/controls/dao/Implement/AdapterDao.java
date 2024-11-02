package com.example.controls.dao.Implement;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.example.controls.tda.list.LinkedList;
import com.google.gson.Gson;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class<T> clazz;
    private Gson g;
    public static String URL = "media/";

    public AdapterDao(Class<T> clazz) {
        this.clazz = clazz;
        this.g = new Gson();
    }

    @Override
    public LinkedList<T> listAll() {
        LinkedList<T> list = new LinkedList<>();
        try {
            String data = readFile();
            T[] array = (T[]) g.fromJson(data, java.lang.reflect.Array.newInstance(clazz, 0).getClass());
            list.toList(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void persist(T object) throws Exception {
        LinkedList<T> list = listAll();
        list.add(object);
        String jsonData = g.toJson(list.toArray());
        saveFile(jsonData);
    }

    @Override
    public void merge(T object, Integer index) throws Exception {
        LinkedList<T> list = listAll();
        if (index >= 0 && index < list.getSize()) {
            list.set(index, object);
            String jsonData = g.toJson(list.toArray());
            saveFile(jsonData);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de los límites de la lista");
        }
    }

    @Override
    public T get(Integer id) throws Exception {
        return null;
    }

    
    private String readFile() throws Exception {
        Scanner in = new Scanner(new FileReader(URL + clazz.getSimpleName() + ".json"));
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }

    private void saveFile(String data) throws Exception {
        FileWriter fileWriter = new FileWriter(URL + clazz.getSimpleName() + ".json");
        fileWriter.write(data);
        fileWriter.flush();
        fileWriter.close();
    }
}

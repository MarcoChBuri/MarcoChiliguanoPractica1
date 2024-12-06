package com.example.rest;

import com.example.controls.tda.list.*;
import java.util.Random;
import com.example.controls.tda.exception.*;

public class PruebaVelocidad {

    static LinkedList<Integer> generarListaAleatoria(int tamano) {
        Integer[] array = new Integer[tamano];
        LinkedList<Integer> lista = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < tamano; i++) {
            array[i] = random.nextInt(1_000_000);
        }
        lista.toList(array);
        return lista;
    }

    static double medirTiempoOrdenamiento(LinkedList<Integer> listaOriginal, String metodoOrdenamiento, int tipoOrden, int repeticiones) throws Exception {
        long tiempoTotal = 0;

        for (int i = 0; i < repeticiones; i++) {
            LinkedList<Integer> lista = generarListaAleatoria(listaOriginal.getSize());
            long inicio = System.nanoTime();

            switch (metodoOrdenamiento) {
                case "shell":
                    lista.tipoNUM("shellsort", tipoOrden);
                    break;
                case "merge":
                    lista.tipoNUM("mergesort", tipoOrden);
                    break;
                case "quick":
                    lista.tipoNUM("quicksort", tipoOrden);
                    break;
                default:
                    throw new IllegalArgumentException("Método de ordenamiento no soportado: " + metodoOrdenamiento);
            }

            long fin = System.nanoTime();
            tiempoTotal += (fin - inicio);
        }

        return tiempoTotal / repeticiones / 1_000_000.0;
    }

    static double medirTiempoBusqueda(LinkedList<Integer> lista, String metodoBusqueda, Integer valor) throws Exception {
        System.out.println("Valor a buscar: " + valor); // Imprimir el valor buscado
    
        long inicio = System.nanoTime();
    
        switch (metodoBusqueda) {
            case "lineal":
                lista.NUMLineal(valor);
                break;
            case "binaria":
                lista.NUMBinary(valor);
                break;
            default:
                throw new IllegalArgumentException("Método de búsqueda no soportado: " + metodoBusqueda);
        }
    
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000.0;
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> lista = generarListaAleatoria(25_000);
        int numTests = 5;
    
        try {
            System.out.println("Tiempo de ejecución (Shell): " + medirTiempoOrdenamiento(lista, "shell", 0, numTests) + " ms");
            System.out.println("Tiempo de ejecución (Merge): " + medirTiempoOrdenamiento(lista, "merge", 0, numTests) + " ms");
            System.out.println("Tiempo de ejecución (Quick): " + medirTiempoOrdenamiento(lista, "quick", 0, numTests) + " ms");
    
            System.out.println("Tiempo de búsqueda (Lineal, valor fijo): " + medirTiempoBusqueda(lista, "lineal", 1000) + " ms");
            System.out.println("Tiempo de búsqueda (Binaria, valor fijo): " + medirTiempoBusqueda(lista, "binaria", 1000) + " ms");
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
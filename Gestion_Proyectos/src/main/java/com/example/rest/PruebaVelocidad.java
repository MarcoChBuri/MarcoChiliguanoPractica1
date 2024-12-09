package com.example.rest;

import com.example.controls.tda.list.*;
import java.util.Random;
import com.example.controls.tda.exception.*;

public class PruebaVelocidad {

    public static void main(String[] args) {
        try {
            imprimirResultado();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        System.out.println("Valor a buscar: " + valor); 
    
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

    static void imprimirResultado() throws Exception {
        int[] tamanos = {10_000, 20_000, 25_000};
        int numTests = 5; 

        System.out.println("Ejecutando pruebas de ordenamiento...");

        for (int tamano : tamanos) {
            LinkedList<Integer> lista = generarListaAleatoria(tamano);
            System.out.println("Para tamaño de lista: " + tamano + " elementos");
            
            double tiempoShell = medirTiempoOrdenamiento(lista, "shell", 0, numTests);
            System.out.println("ShellSort: " + tiempoShell + " ms");

            double tiempoMerge = medirTiempoOrdenamiento(lista, "merge", 0, numTests);
            System.out.println("MergeSort: " + tiempoMerge + " ms");

            double tiempoQuick = medirTiempoOrdenamiento(lista, "quick", 0, numTests);
            System.out.println("QuickSort: " + tiempoQuick + " ms");
            
            System.out.println("------");
        }

        System.out.println("Ejecutando pruebas de búsqueda...");

        for (int tamano : tamanos) {
            LinkedList<Integer> lista = generarListaAleatoria(tamano);
            int valorABuscar = 1000; 
            System.out.println("Para tamaño de lista: " + tamano + " elementos");
            
            double tiempoLineal = medirTiempoBusqueda(lista, "lineal", valorABuscar);
            System.out.println("Búsqueda Lineal: " + tiempoLineal + " ms");
            
            double tiempoBinario = medirTiempoBusqueda(lista, "binaria", valorABuscar);
            System.out.println("Búsqueda Binaria: " + tiempoBinario + " ms");
            
            System.out.println("------");
        }
    }

}

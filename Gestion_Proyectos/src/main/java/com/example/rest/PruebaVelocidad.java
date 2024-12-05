
package com.example.rest;
import com.example.controls.tda.list.*;
import java.util.Random;
public class PruebaVelocidad {
    static LinkedList<Integer> crearLista(Integer size) {
        Integer[] arr = new Integer[size];
        LinkedList<Integer> lista = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000000);
        }
        lista.toList(arr);
        return lista;
    }
    
    static Long testOrder(LinkedList<Integer> lista, String metodo, Integer tipo, int numTests) throws Exception {
        long totalTime = 0;
        for (int i = 0; i < numTests; i++) {
            long start = System.nanoTime();
            
            switch (metodo) {
                case "shell":
                    lista.tipoNUM("shellsort", tipo);
                    break;
                case "merge":
                    lista.tipoNUM("mergesort", tipo);
                    break;
                case "quick":
                    lista.tipoNUM("quicksort", tipo);
                    break;
                default:
                    throw new IllegalArgumentException("Método no soportado: " + metodo);
            }
            long end = System.nanoTime();
            totalTime += (end - start);
            
            // Reset list to unsorted state for next test
            lista = crearLista(lista.getSize()); // Restablecer la lista a su estado original
        }
        return totalTime / numTests; // Promedio de los tiempos
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> lista = crearLista(10000);
        int numTests = 5; // Número de repeticiones para obtener un promedio
        System.out.println(lista.toString());
        
        try {
            System.out.println("Shell: " + testOrder(lista, "shell", 0, numTests) + " ns");
            System.out.println("Merge: " + testOrder(lista, "merge", 0, numTests) + " ns");
            System.out.println("Quick: " + testOrder(lista, "quick", 0, numTests) + " ns");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
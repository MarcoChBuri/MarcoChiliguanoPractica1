package com.example.controls.tda.list;
import java.lang.reflect.Method;

import com.example.controls.tda.exception.ListEmptyException;

public class LinkedList<E> {
    private Node<E> header;
    private Node<E> last;
    private Integer size;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    public Boolean isEmpty() {
        return (this.header == null || this.size == 0);
    }

    public Integer getSize() {
        return this.size;
    }

    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    //METODOS PARA AGREGAR ELEMENTOS
    private void addHeader(E dato) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(dato);
            header = help;
            last = help;
        } else {
            Node<E> healpHeader = this.header;
            help = new Node<>(dato, healpHeader);
            this.header = help;
        }
        this.size++;
    }

    private void addLast(E info) {
        Node<E> help;
        if (isEmpty()) {
            addHeader(info);
        } else {
            help = new Node<>(info, null);
            last.setNext(help);
            last = help;
            this.size++;
        }
    }

    public void add(E info) {
        addLast(info);
    }

    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty() || index.intValue() == 0) {
            addHeader(info);
        } else if (index.intValue() == this.size.intValue()) {
            addLast(info);
        } else {
            Node<E> search_preview = getNode(index - 1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info, search);
            search_preview.setNext(help);
            this.size++;
        }
    }

    //OBTENER NODO
    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header;
        } else if (index.intValue() == (this.size - 1)) {
            return last;
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    //OBTENER ELEMENTOS
    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacía");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header.getInfo();
        } else if (index.intValue() == (this.size - 1)) {
            return last.getInfo();
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search.getInfo();
        }
    }    

    //ELIMINAR ELEMENTOS
    public E deleteFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");
        } else {
            E element = header.getInfo();
            Node<E> help = header.getNext();
            header = help;
            if (size.intValue() == 1) {
                last = null;
            }
            size--;
            return element;
        }
    }

    public E deleteLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");
        } else {
            E element = last.getInfo();
            Node<E> help = getNode(size - 2);
            if (help == null) {
                last = null;
                if (size == 2) {
                    last = header;
                } else {
                    header = null;
                }
            } else {
                last = null;
                last = help;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }

    public E delete(Integer index) throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacía");
        } else if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index == 0) {
            return deleteFirst();
        } else if (index == this.size - 1) {
            return deleteLast();
        } else {
            Node<E> before = getNode(index - 1);
            Node<E> actual = before.getNext();
            before.setNext(actual.getNext());
            E element = actual.getInfo();
            actual = null;
            size--;
            return element;
        }
    }
    
    //MODIFICAR ELEMENTOS
    public void update(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            header.setInfo(info);
        } else if (index.intValue() == (this.size - 1)) {
            last.setInfo(info);
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            search.setInfo(info);
        }
    }
    // toarray

    //OTROS METODOS
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("List Data \n");
        try {
            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append(" -> ");
                help = help.getNext();
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }

        return sb.toString();
    }

    public E[] toArray(){
        E[] matrix = null;
        if (!isEmpty()) {
            Class clazz = header.getInfo().getClass();
            matrix = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            Node<E> aux = header;
            for(int i=0; i<size && aux != null; i++){
                matrix[i] = aux.getInfo();
                aux = aux.getNext();
            }
        }
        return matrix;
    }
    //CONVERTIRLOA A LISTA
    public LinkedList<E> toList(E[] matrix){
        reset();
        for(int i = 0; i < matrix.length; i++){
            this.add(matrix[i]);
        }
        return this;
    }

    // compare class

    private Boolean compare(Object a, Object b, Integer type) {
        switch (type) {
            case 0:
                if (a instanceof Number) {
                    Number a1 = (Number) a;
                    Number b1 = (Number) b;
                    return a1.doubleValue() > b1.doubleValue();
                } else {
                    return (a.toString()).compareTo(b.toString()) > 0;
                }
                // break;

            default:
                // mayor a menor
                if (a instanceof Number) {
                    Number a1 = (Number) a;
                    Number b1 = (Number) b;
                    return a1.doubleValue() < b1.doubleValue();
                } else {
                    return (a.toString()).compareTo(b.toString()) < 0;
                }
                // break;
        }

    }

    private Boolean attribute_compare(String attribute, E a, E b, Integer type) throws Exception {
        return compare(exist_attribute(a, attribute), exist_attribute(b, attribute), type);
    }

    private Object exist_attribute(E a, String attribute) throws Exception {
        Method method = null;
        attribute = attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
        attribute = "get" + attribute;
        for (Method aux : a.getClass().getMethods()) {           
            if (aux.getName().contains(attribute)) {
                method = aux;
                break;
            }
        }
        if (method == null) {
            for (Method aux : a.getClass().getSuperclass().getMethods()) {              
                if (aux.getName().contains(attribute)) {
                    method = aux;
                    break;
                }
            }
        }
        if (method != null) {            
            return method.invoke(a);
        }
        
        return null;
    }

    //MERGESHORT
    private void merge(E[] list, int left, int mid, int right, String attribute, Integer type) throws Exception {
        int n1 = mid - left + 1;
        int n2 = right - mid;
    
        E[] leftList = (E[]) new Object[n1];
        E[] rightList = (E[]) new Object[n2];
    
        for (int i = 0; i < n1; ++i)
            leftList[i] = list[left + i];
        for (int j = 0; j < n2; ++j)
            rightList[j] = list[mid + 1 + j];
    
        int i = 0, j = 0;
        int k = left;
    
        while (i < n1 && j < n2) {
            if (attribute_compare(attribute, leftList[i], rightList[j], type)) {
                list[k] = leftList[i];
                i++;
            } else {
                list[k] = rightList[j];
                j++;
            }
            k++;
        }
    
        while (i < n1) {
            list[k] = leftList[i];
            i++;
            k++;
        }
    
        while (j < n2) {
            list[k] = rightList[j];
            j++;
            k++;
        }
    }
    
    private void mergesort(E[] list, int left, int right, String attribute, Integer type) throws Exception {
        if (left < right) {
            int mid = left + (right - left) / 2;
    
            mergesort(list, left, mid, attribute, type);  
            mergesort(list, mid + 1, right, attribute, type); 
    
            merge(list, left, mid, right, attribute, type); 
        }
    }
    //numeros:
    private void merge(E[] list, int left, int mid, int right, Integer type) throws Exception {
        int n1 = mid - left + 1;
        int n2 = right - mid;
    
        E[] leftList = (E[]) new Object[n1];
        E[] rightList = (E[]) new Object[n2];
    
        for (int i = 0; i < n1; ++i)
            leftList[i] = list[left + i];
        for (int j = 0; j < n2; ++j)
            rightList[j] = list[mid + 1 + j];
    
        int i = 0, j = 0;
        int k = left;
    
        while (i < n1 && j < n2) {
            if (compare(leftList[i], rightList[j], type)) {
                list[k] = leftList[i];
                i++;
            } else {
                list[k] = rightList[j];
                j++;
            }
            k++;
        }
    
        while (i < n1) {
            list[k] = leftList[i];
            i++;
            k++;
        }
    
        while (j < n2) {
            list[k] = rightList[j];
            j++;
            k++;
        }
    }
    
    private void mergesort(E[] list, int left, int right, Integer type) throws Exception {
        if (left < right) {
            int mid = left + (right - left) / 2;
    
            mergesort(list, left, mid, type);  
    
            merge(list, left, mid, right, type);  
        }
    }
    
    

   
    //QUICKSORT
    private int partition(E[] list, int low, int high, String attribute, Integer type) throws Exception {
        E pivot = list[high];
        int i = low - 1;
    
        for (int j = low; j < high; j++) {
            if (attribute_compare(attribute, list[j], pivot, type)) {
                i++;
                E temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }
    
        E temp = list[i + 1];
        list[i + 1] = list[high];
        list[high] = temp;
    
        return i + 1;
    }
    
    private void quicksort(E[] list, int low, int high, String attribute, Integer type) throws Exception {
        if (low < high) {
            int pi = partition(list, low, high, attribute, type);
    
            quicksort(list, low, pi - 1, attribute, type);
            quicksort(list, pi + 1, high, attribute, type);
        }
    }
    //numeros
    private int partition(E[] list, int low, int high, Integer type) throws Exception {
        E pivot = list[high];
        int i = low - 1;
    
        for (int j = low; j < high; j++) {
            if (compare(list[j], pivot, type)) {
                i++;
                E temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }
    
        E temp = list[i + 1];
        list[i + 1] = list[high];
        list[high] = temp;
    
        return i + 1;
    }
    
    private void quicksort(E[] list, int low, int high, Integer type) throws Exception {
        if (low < high) {
            int pi = partition(list, low, high, type);
    
            quicksort(list, low, pi - 1, type);
            quicksort(list, pi + 1, high, type);
        }
    }
    

    
    // //SHELLSORT

    private void shellsort(E[] list, String attribute, Integer type) throws Exception {
        int n = list.length;
    
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                E temp = list[i];
                int j;
                for (j = i; j >= gap && attribute_compare(attribute, temp, list[j - gap], type); j -= gap) {
                    list[j] = list[j - gap];
                }
                list[j] = temp;
            }
        }
    }
    //numeros 
    private void shellsort(E[] list, Integer type) throws Exception {
        int n = list.length;
    
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                E temp = list[i]; 
                int j;
    
                for (j = i; j >= gap && compare(temp, list[j - gap], type); j -= gap) {
                    list[j] = list[j - gap];
                }
    
                list[j] = temp;
            }
        }
    }
    

   
    //escoger metodoOrdenacion nstring
    public LinkedList tipo(String algorithm, String attribute, Integer type) throws Exception {
        E[] array = this.toArray(); 
    
        switch (algorithm.toLowerCase()) {
            case "quicksort":
                quicksort(array, 0, array.length - 1, attribute, type);
                break;
            case "mergesort":
                mergesort(array, 0, array.length - 1, attribute, type);
                break;
            case "shellsort":
                shellsort(array, attribute, type);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo no soportado: " + algorithm);
        }
    
        return this.toList(array);
    }
// escoger metodo ordenacion Int:
public LinkedList<E> tipoNUM(String algorithm, Integer type) throws Exception {
    E[] array = this.toArray();


    switch (algorithm.toLowerCase()) {
        case "quicksort":
            quicksort(array, 0, 0, type);
            break;
        case "mergesort":
            mergesort(array, 0, 0, type);
            break;
        case "shellsort":
            shellsort(array, type);
            break;
        default:
            throw new IllegalArgumentException("Algoritmo no soportado: " + algorithm);
    }
    
    return this.toList(array);
}




//-------METDOS DE BUSQUEDA--------
    public LinkedList<E> linearSearch(String attribute, String value) throws Exception {
        LinkedList<E> lista = new LinkedList<>();
        E[] array = this.toArray(); 
    
        for (int i = 0; i < array.length; i++) {
            E element = array[i];
            Object attributeValue = exist_attribute(element, attribute); 
    
            if (attributeValue != null) {
                String attributeValueStr = attributeValue.toString().toLowerCase(); 
                String valueStr = value.toLowerCase(); 
    
                if (attributeValueStr.startsWith(valueStr)) {
                    lista.add(element); 
                }
            }
        }
    
        return lista;
    
    }
    
    //numeros:
    public LinkedList<E> NUMLineal(Object value) throws Exception {
        LinkedList<E> lista = new LinkedList<>();
        if (!this.isEmpty()) {
            E[] aux = this.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].equals(value)) {
                    lista.add(aux[i]);
                    break;
                }
            }
        }
        return lista;
    }

    
    ///binaria
    public LinkedList<E> binarySearch(String attribute, String value) throws Exception {
        LinkedList<E> resultList = new LinkedList<>();
        E[] array = this.toArray();
        int low = 0;
        int high = array.length - 1;
    
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            E midElement = array[mid];
            Object attributeValue = exist_attribute(midElement, attribute);
            
            if (attributeValue != null) {
                String attributeValueStr = attributeValue.toString().toLowerCase();
                String valueStr = value.toLowerCase();
    
                if (attributeValueStr.startsWith(valueStr)) {
                    
                    collectResults(array, mid, attribute, value, resultList);
                    break;
                } else if (attributeValueStr.compareTo(valueStr) < 0) {
                    low = mid + 1; 
                } else {
                    high = mid - 1; 
                }
            }
        }
    
        return resultList;
    }
    
    private void collectResults(E[] array, int mid, String attribute, String value, LinkedList<E> resultList) throws Exception {
    
        E midElement = array[mid];
        Object attributeValue = exist_attribute(midElement, attribute);
        String attributeValueStr = attributeValue.toString().toLowerCase();
        String valueStr = value.toLowerCase();
        
    
        if (attributeValueStr.startsWith(valueStr)) {
            resultList.add(midElement);
        }
    
    
        int left = mid - 1;
        while (left >= 0) {
            E leftElement = array[left];
            Object leftAttributeValue = exist_attribute(leftElement, attribute);
            String leftAttributeValueStr = leftAttributeValue.toString().toLowerCase();
    
            if (leftAttributeValueStr.startsWith(valueStr)) {
                resultList.add(leftElement);
                left--;
            } else {
                break;
            }
        }
    
        int right = mid + 1;
        while (right < array.length) {
            E rightElement = array[right];
            Object rightAttributeValue = exist_attribute(rightElement, attribute);
            String rightAttributeValueStr = rightAttributeValue.toString().toLowerCase();
    
            if (rightAttributeValueStr.startsWith(valueStr)) {
                resultList.add(rightElement);
                right++;
            } else {
                break;
            }
        }
    }

//numeros
public LinkedList<E> NUMBinary(Number value) throws Exception {

    LinkedList<E> resultList = new LinkedList<>();
    E[] array = this.toArray();
    mergesort(array, 0, 0, size);  
    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        E midElement = array[mid];
        Comparable<Number> midValue = (Comparable<Number>) midElement;

        int comparison = midValue.compareTo(value);

        if (comparison == 0) {  
            resultList.add(midElement);
            break;
        } else if (comparison < 0) {  
            low = mid + 1;
        } else {  
            high = mid - 1;
        }
    }

    int mid = low + (high - low) / 2;  
    E midElement = array[mid];
    resultList.add(midElement);

    // Búsqueda hacia la izquierda
    int left = mid - 1;
    while (left >= 0) {
        E leftElement = array[left];
        Comparable<Number> leftValue = (Comparable<Number>) leftElement;

        if (leftValue.compareTo(value) == 0) {
            resultList.add(leftElement);
            left--;
        } else {
            break;
        }
    }

    // Búsqueda hacia la derecha
    int right = mid + 1;
    while (right < array.length) {
        E rightElement = array[right];
        Comparable<Number> rightValue = (Comparable<Number>) rightElement;

        if (rightValue.compareTo(value) == 0) {
            resultList.add(rightElement);
            right++;
        } else {
            break;
        }
    }

    return resultList;
}




}


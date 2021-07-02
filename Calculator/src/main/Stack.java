package main;

public class Stack <T>{

    private int size = 0;
    private T [] arr;

    //добавление элемента в голову стека
    public void push(T new_elem){
        if (arr == null){
            size = 1;
            arr = (T[]) new Object[size];
            arr[size-1] = new_elem;
        }
        else {
            int new_size = size+1;
            T[] new_arr = (T[]) new Object[new_size];
            System.arraycopy(arr,0,new_arr,0, size);
            arr = new_arr;
            arr[new_size-1] = new_elem;
            size = new_size;
        }

    }
    //удаление и возвращение элемента, находящегося в голове стека
    public T pop(){
        if (size != 0){
            T buf;
            buf = arr[size-1];
            int new_size = size-1;
            T[] new_arr = (T[]) new Object[new_size];
            System.arraycopy(arr,0,new_arr,0, new_size);
            arr = new_arr;
            size = new_size;
            return buf;
        }
        else {
            System.out.println("В стэке отсутствуют элементы");
            System.exit(1);
            return null;
        }

    }
    // возращение из головы без удаления элемента
    public T peek(){
        if (size != 0){
            T buf;
            buf = arr[size-1];
            return buf;
        }
        else {
            System.out.println("В стэке отсутствуют элементы");
            System.exit(1);
            return null;
        }

    }

    public boolean isEmpty(){
        if (size != 0){
            return false;
        }
        else {
            return true;
        }
    }

}

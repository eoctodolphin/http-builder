package io.eoctodolphin.utils;

public interface Utils<T> {

    static Integer fristNonNull(Integer first, Integer second){
        return first !=null ? first : second;
    }

    static String fristNonNull(String first, String second){
        return first !=null ? first : second;
    }
    
}

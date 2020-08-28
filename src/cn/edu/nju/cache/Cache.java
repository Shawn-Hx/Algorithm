package cn.edu.nju.cache;

public interface Cache {

    int get(int key);

    void put(int key, int value);

}

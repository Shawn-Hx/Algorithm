package cn.edu.nju.design.cache;

public interface Cache {

    int get(int key);

    void put(int key, int value);

}

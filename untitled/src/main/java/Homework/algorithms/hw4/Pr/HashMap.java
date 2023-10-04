package Homework.algorithms.hw4.Pr;

import java.util.Iterator;

/**
 * Структура хэш-таблицы
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class HashMap <K, V> implements Iterable<HashMap.Entity> {
    @Override
    public Iterator<HashMap.Entity> iterator() {
        return new HashMapIterator();
    }
    class HashMapIterator implements Iterator<HashMap.Entity>{
        int bucketIndex = 0;
        int nodeIndex = 0;
        Entity entity;

        @Override
        public boolean hasNext() {
            for (int i = bucketIndex; i < buckets.length; i ++) {

                Bucket<K, V> bucket = buckets[i];

                if (bucket != null) {
                    Bucket.Node node = bucket.head;

                    int j = 0;

                    while (node != null) {

                        if (j < nodeIndex) {
                            j ++;
                            node = node.next;
                            continue;
                        }

                        entity = new Entity();
                        entity.key = (K)node.value.key;
                        entity.value = (V)node.value.value;
                        nodeIndex ++;
                        return true;
                    }

                    nodeIndex = 0;
                }

                bucketIndex ++;
            }

            return false;
        }

        @Override
        public Entity next() {
            return entity;
        }
    }

    //region Публичне методы

    public V put(K key, V value) {
        if (buckets.length * LOAD_FACTOR <= size)
            recalculate();

        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket();
            buckets[index] = bucket;
        }

        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;

        V result = (V) bucket.add(entity); //buf
        if (result== null) { //buf
            size++;
        }
        return result; //buf

    }
    public V get(K key){
        int index =calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if(bucket == null){
            return null;
        } return (V)bucket.get(key);
    }
    public V delete(K key){
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if(bucket ==null)
            return null;
        V result = (V)bucket.delete(key);
        if(result != null){
            size--;
        }
        return result;
    }

    //endregion

    //region Методы

    private void recalculate() {
        size = 0;
        Bucket<K, V>[] old = buckets;
        buckets = new Bucket[old.length * 2];
        for (int i = 0; i < old.length; i++) {
            Bucket<K, V> bucket = old[i];
            if (bucket != null) {
                Bucket.Node node = bucket.head;
                while (node != null) {
                    put((K) node.value.key, (V) node.value.value);
                    node = node.next;
                }
            }
        }

    }

    private int calculateBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    //endregion

    //region Константы
    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5;

    //endregion

    //region Вспомогательные структуры

    /**
     * Элемент хэш-таблицы
     */
    class Entity {

        /**
         * Ключ
         */
        K key;

        /**
         * Значение
         */
        V value;

    }


    /**
     * Элемент массива(связаный список) из которого состоит хэш-таблицв
     */
    class Bucket<K, V> {
        /**
         * Указатель на первый элемент связаного списка
         */
        Node head;

        /**
         * Узел связанного списка
         */
        class Node {
            /**
             * Ссылка на следующий узел (если имеется)
             */
            public Node next;
            /**
             * Значение узла
             */
            Entity value;

        }


        public V add(Entity entity) {
            Node node = new Node();
            node.value = entity;

            if (head == null) {
                head = node;
                return null;
            }

            Node currentNode = head;
            while (true) {
                if (currentNode.value.key.equals(entity.key)) {
                    V buf = (V) currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return buf;
                }
                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                } else {
                    currentNode.next = node;
                    return null;
                }
            }
        }
        public V delete(K key){
            if (head == null)
                return null;
            if (head.value.key.equals(key)){
                V buf = (V)head.value.value;
                head = head.next;
                return buf;
            }
            else {
                Node node = head;
                while (node.next != null){
                    if (node.next.value.key.equals(key)){
                        V buf = (V)node.next.value.value;
                        node.next = node.next.next;
                        return buf;
                    }
                    node = node.next;
                }
                return null;
            }
        }

        public V get(K key){
            Node node = head;
            while (node != null){
                if (node.value.key.equals(key))
                    return (V)node.value.value;
                node = node.next;
            }
            return null;
        }



    }

    //endregion

    //region Поля
    /**
     * Массив бакетов (связаных списков)
     */
    private Bucket[] buckets;
    private int size;

    //endregion

    //region Конструкторы
    public HashMap() {
        buckets = new Bucket[INIT_BUCKET_COUNT]; //16

    }

    public HashMap(int initCount) {
        buckets = new Bucket[initCount];

    }
    //endregion


}



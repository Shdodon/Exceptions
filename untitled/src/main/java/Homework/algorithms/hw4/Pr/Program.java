package Homework.algorithms.hw4.Pr;

public class Program {
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>( 4);
        String oldValue = hashMap.put("+79001112233", "AAAAAAAA");
        oldValue = hashMap.put("+79001112231", "BBBBBBB");
        oldValue = hashMap.put("+79001112232", "CCCCCCC");
        oldValue = hashMap.put("+79001112232", "FFFFFFF");
        oldValue = hashMap.put("+79001112234", "EEEEEEE");
        oldValue = hashMap.put("+79001112235", "QQQQQQQ");
        oldValue = hashMap.put("+79001112236", "ZZZZZZZ");
        oldValue = hashMap.put("+79001112237", "LLLLLLL");
        oldValue = hashMap.put("+79001112238", "sdfsdf");
        oldValue = hashMap.put("+79001112239", "ASDASDA");
        oldValue = hashMap.put("+790011122310", "SFEFR");
        oldValue = hashMap.put("+790011122311", "EWTRYHTH");



           System.out.println(hashMap);
    }
}

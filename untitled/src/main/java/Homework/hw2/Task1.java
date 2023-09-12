package Homework.hw2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
 * и возвращает введенное значение.
 * Ввод текста вместо числа не должно приводить к падению приложения, вместо этого,
 * необходимо повторно запросить у пользователя ввод данных.
 */
public class Task1 {
    public static void main(String[] args) {
        task();
    }
    public static void task(){
     try {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        float result = getUserFloatNumber(bufferedReader);
        bufferedReader.close();
        System.out.println(result);
    }catch (IOException ex){
        System.out.println("Возникла ошибка при работе с файлом");
        return;
    }
}
    public static float getUserFloatNumber(BufferedReader bufferedReader){
        System.out.print("Введите число: ");
        float result;
        try {
            result = Float.parseFloat(bufferedReader.readLine());
        }catch (NumberFormatException e){
            System.out.println("Произошла ошибка при преобразовании введенного значения в число с плавающей точкой");
            return getUserFloatNumber(bufferedReader);
        } catch (Exception e){
            System.out.println("Произошла ошибка при получении числа");
            return getUserFloatNumber(bufferedReader);
        }
        return result;
    }
}


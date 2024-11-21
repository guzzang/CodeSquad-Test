import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Math_puzzle {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> numbersList = Arrays.asList(numbers);
        int phase = 1;

        while(true){
            System.out.println("간단 숫자 퍼즐");
            System.out.println("Turn " + phase);
            shuffle(numbersList);
            System.out.println(numbersList);
            System.out.println("교환할 두 숫자를 입력>");
            phase += 1;
            if(phase == 2) break;
        }

    }

    // 숫자를 섞는 함수
    static void shuffle(List<Integer> numbersList){
        Collections.shuffle(numbersList);
    }


}

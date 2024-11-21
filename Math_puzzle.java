import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Math_puzzle {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> numbersList = Arrays.asList(numbers);
        int phase = 1;
        System.out.println("간단 숫자 퍼즐");

        while(true){
            System.out.println("Turn " + phase);
            shuffle(numbersList);
            System.out.println(numbersList);
            // if(phase >= 2) System.out.println("\n");

            System.out.println("교환할 두 숫자를 입력>");
            String inputNumbers = br.readLine();
            // System.out.println("\n");
            checkInputIsCorrectFormat(inputNumbers);
            phase += 1;
            if(phase == 2) break;
        }
    }

    // 숫자를 섞는 함수
    static void shuffle(List<Integer> numbersList){
        Collections.shuffle(numbersList);
    }

    // Input이 주어진 조건에 맞는 형식인지 확인하는 함수
    static void checkInputIsCorrectFormat(String inputNumbers){
        // 시작 공백, 한글 입력 check
        if(checkInputIsNotStartEmptyOrOnlyNumber(inputNumbers)){
            printErrorMessage();
        }
        else{
            String[] Numbers = inputNumbers.split(",");
            // 입력 하나인 경우 check
            if(checkInputSizeIsOne(Numbers)){
                printErrorMessage();
            }
            else{
                String inputNumber1 = Numbers[0].trim();
                String inputNumber2 = Numbers[1].trim();
                Integer changeNumber1 = Integer.parseInt(inputNumber1);
                Integer changeNumber2 = Integer.parseInt(inputNumber2);
                // 범위 초과 체크
                if(checkInputIsInRange(changeNumber1, changeNumber2)){
                    printErrorMessage();
                }

            }
        }
    }


    static boolean checkInputIsNotStartEmptyOrOnlyNumber(String inputNumbers){
        return inputNumbers.startsWith(" ") || inputNumbers.matches(".*[^0-9,\\s].*");

    }

    static boolean checkInputSizeIsOne(String[] Numbers){
        return Numbers.length != 2;
    }

    static boolean checkInputIsInRange(Integer changeNumber1, Integer changeNumber2){
        return (changeNumber1 < 1 || changeNumber1 > 8) || (changeNumber2 < 1 || changeNumber2 > 8);
    }


    static void printErrorMessage(){
        System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
        System.out.println("\n");
    }


}

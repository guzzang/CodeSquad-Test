import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MathPuzzle {

    static int turn;

    static String inputNumbers;
    static String inputNumber1;
    static String inputNumber2;
    static Integer changeNumber1;
    static Integer changeNumber2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> numbersList = Arrays.asList(numbers);
        turn = 1;
        System.out.println("간단 숫자 퍼즐");
        shuffle(numbersList);

        while(true){
            int flag = 0;
            showTurnAndNumbersList(numbersList);
            inputSwapNumbers(br);
            if(checkInputIsCorrectFormat(inputNumbers)){
                swap(numbersList);
                if(checkNumbersListIsSorted(numbersList, flag)) break;
                turn++;
            }
            else{
                turn++;
            }
        }
    }

    // Turn과 해당 Turn의 NumbersList를 보여주는 함수
    static void showTurnAndNumbersList(List<Integer> numbersList){
        System.out.println("Turn " + turn);
        System.out.println(numbersList);

        if(turn >= 2) System.out.println("\n");
    }

    // swap할 두개의 숫자를 입력받는 함수
    private static void inputSwapNumbers(BufferedReader br) throws IOException {
        System.out.println("교환할 두 숫자를 입력>");
        inputNumbers = br.readLine();
        System.out.println("\n");
    }
    //

    // 숫자를 섞는 함수
    static void shuffle(List<Integer> numbersList){
        Collections.shuffle(numbersList);
    }

    // Input이 주어진 조건에 맞는 형식인지 확인하는 함수
    static boolean checkInputIsCorrectFormat(String inputNumbers){
        // 시작 공백, 한글 입력 check
        if(checkInputIsNotStartEmptyOrOnlyNumber(inputNumbers)){
            printErrorMessage();
            return false;
        }
        else{
            String[] Numbers = inputNumbers.split(",");
            // 입력 하나인 경우 check
            if(checkInputSizeIsOne(Numbers)){
                printErrorMessage();
                return false;
            }
            else{
                inputNumber1 = Numbers[0].trim();
                inputNumber2 = Numbers[1].trim();
                changeNumber1 = Integer.parseInt(inputNumber1);
                changeNumber2 = Integer.parseInt(inputNumber2);
                // 범위 초과 체크
                if(checkInputIsInRange(changeNumber1, changeNumber2)){
                    printErrorMessage();
                    return false;
                }
            }
        }
        return true;
    }

    // Input이 공백으로 시작하는지 숫자로만 되어있는지 확인하는 함수
    static boolean checkInputIsNotStartEmptyOrOnlyNumber(String inputNumbers){
        return inputNumbers.startsWith(" ") || inputNumbers.matches(".*[^0-9,\\s].*");

    }

    // Input의 개수가 하나인지 확인하는 함수
    static boolean checkInputSizeIsOne(String[] Numbers){
        return Numbers.length != 2;
    }

    // Input의 값이 범위 안에 있는지 확인하는 함수
    static boolean checkInputIsInRange(Integer changeNumber1, Integer changeNumber2){
        return (changeNumber1 < 1 || changeNumber1 > 8) || (changeNumber2 < 1 || changeNumber2 > 8);
    }

    // 숫자 swap 함수
    static void swap(List<Integer> numbersList){
        // 입력 index끼리 swap하기
        int index1 = numbersList.indexOf(changeNumber1);
        int index2 = numbersList.indexOf(changeNumber2);
        Collections.swap(numbersList, index1, index2);
    }

    // 오름차순 확인 함수
    static boolean checkNumbersListIsSorted(List<Integer> numbersList, int flag) {
        for(int i = 0; i < numbersList.size() - 1; i++){
            if(numbersList.get(i) > numbersList.get(i+1)) flag = 1;
        }
        if(flag != 1){
            System.out.println("축하합니다! " + turn + "턴만에 퍼즐을 완성하셨습니다!");
            return true;
        }
        return false;
    }

    // 에러 메시지 출력 함수
    static void printErrorMessage(){
        System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
        System.out.println("\n");
    }


}

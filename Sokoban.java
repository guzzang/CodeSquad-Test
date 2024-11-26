import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Sokoban {

    static class SokobanMap{
        String stageNumber;
        int numEmptySpace;
        int mapWidth;
        int mapHeight;
        int numHall;
        int numBall;
        int[] playerPosition = new int[2];
        String originalMap;
    }


    static SokobanMap map1 = new SokobanMap();
    static SokobanMap map2 = new SokobanMap();
    static String[] mapArray;
    static String[] commandArray;
    static String[][] mapMatrix;

    public static void main(String[] args) throws IOException {

        // Step 1
        String map =
                """
                Stage 1
                #####
                #OoP#
                #####
                =====
                Stage 2
                  #######                    
                ###  O  ###
                #    o    #
                # Oo P oO #
                ###  o  ###
                 #   O  #  
                 ########  
                """;
        splitMapStages(map);
        calculateMapInformation(map1);
        calculateMapInformation(map2);

        printMapInformation(map1);
        System.out.println("\n");
        printMapInformation(map2);

        // Step 2
        // player 이동을 구현하기 위해서 map을 2차원 배열에 저장
        printMap(map2);
        saveMapMatrix(map2.originalMap);

        System.out.print("SOKOBAN> ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        splitCommand(command);
        executeCommand(commandArray);

    }

    static void saveMapMatrix(String originalMap) {
        String[] mapRowInformation = originalMap.split("\n");

        // mapMatrix 초기화
        mapMatrix = new String[mapRowInformation.length][];
        for (int i = 0; i < mapRowInformation.length; i++) {
            mapMatrix[i] = new String[mapRowInformation[i].length()]; // 가변 행렬 초기화
        }

        for(int i = 0 ; i < mapRowInformation.length; i++){
            for(int j = 0; j < mapRowInformation[i].length(); j++){
                mapMatrix[i][j] = String.valueOf(mapRowInformation[i].charAt(j));
            }
        }
    }

    static void splitCommand(String command) {
        commandArray = command.split("");
    }

    static void executeCommand(String[] commandArray) {
        List<String> rightCommands = List.of("w", "W", "a", "A", "s", "S", "d", "D", "q", "Q");

        for(String command : commandArray){
            if(!rightCommands.contains(command)){
                System.out.println(command.toUpperCase() + ": (경고) 지원하지 않는 명령입니다!");
            }
            switch (command){
                case "w" : case "W":
                    // p 좌표를 위로 1칸 이동 // 벽이나 공에 충돌시 경고
                    commandW();
                    break;
                case "a" : case "A":
                    // p 좌표를 왼쪽으로 1칸 이동 // 벽이나 공에 충돌시 경고
                    commandA();
                    break;
                case "s" : case "S":
                    // p 좌표를 아래로 1칸 이동 // 벽이나 공에 충돌시 경고
                    commandS();
                    break;
                case "d" : case "D":
                    // p 좌표를 오른쪽으로 1칸 이동 // 벽이나 공에 충돌시 경고
                    commandD();
                    break;
                case "q" : case "Q":
                    System.out.println("Bye~");
                    return;
            }
        }
    }

    static void commandW() {
        map2.playerPosition[0] += 1;
        if(mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]].equals("o")|| mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]].equals("O")|| mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]].equals("#")){
            System.out.println("W: (경고!) 해당 명령을 수행할 수 없습니다!");
            map2.playerPosition[0] -= 1;
        }
        else System.out.println("W: 위로 이동합니다.");
    }

    static void commandA() {
        map2.playerPosition[1] -= 1;
        if(mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]] == "o" || mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]] == "O" || mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]] == "#"){
            System.out.println("A: (경고!) 해당 명령을 수행할 수 없습니다!");
            map2.playerPosition[1] += 1;
        }
        else System.out.println("A: 왼쪽으로 이동합니다.");
    }

    static void commandS() {
        map2.playerPosition[0] -= 1;
        if(mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]] == "o" || mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]] == "O" || mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]] == "#"){
            System.out.println("S: (경고!) 해당 명령을 수행할 수 없습니다!");
            map2.playerPosition[0] += 1;
        }
        else System.out.println("S: 아래로 이동합니다.");
    }

    static void commandD() {
        map2.playerPosition[1] += 1;
        if(mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]].equals("o") || mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]].equals("O") || mapMatrix[map2.playerPosition[0]][map2.playerPosition[1]].equals("#")){
            System.out.println("D: (경고!) 해당 명령을 수행할 수 없습니다!");
            map2.playerPosition[1] -= 1;
        }
        else System.out.println("D: 오른쪽으로 이동합니다.");
    }


    // "=====" 를 기준으로 Stage 1 Stage 2를 나누는 함수
    static void splitMapStages(String mapInformation){
        mapArray = mapInformation.split("=====");
        saveStageDetails(map1, mapArray[0].trim());
        saveStageDetails(map2, mapArray[1].trim());
    }

    // map 정보가 주어지면 제목과 내용을 분리하는 함수
    static void saveStageDetails(SokobanMap map, String mapInformation) {
        String[] splitMapInformation = mapInformation.split("\n", 2);
        map.stageNumber = splitMapInformation[0];
        map.originalMap = splitMapInformation[1];

    }

    static void calculateMapInformation(SokobanMap map) {
        String[] mapRowInformation = map.originalMap.split("\n");
        calculateMapWidthAndHeight(map, mapRowInformation);
        countMapInformation(map, mapRowInformation);
    }

    // map 정보가 주어지면 map의 가로와 세로 길이를 구하는 함수
    static void calculateMapWidthAndHeight(SokobanMap map, String[] mapRowInformation) {
        map.mapHeight = mapRowInformation.length;
        for (String mapRow : mapRowInformation) {
            map.mapWidth = Math.max(map.mapWidth, mapRow.length());
        }
    }

    // map의 각 구성요소에 대해 대응되는 저장값으로 변환하는 함수
    static Integer saveSokobanMapInformationToNumber(char mapInformation) {

        if(mapInformation == ' ') return 0;
        else if(mapInformation == 'O') return 1;
        else if(mapInformation == 'o') return 2;
        else if(mapInformation == 'P') return 3;
        else if(mapInformation == '#') return 4;

        return null;
    }

    // map을 돌며 대응되는 저장값이 있을때마다 해당 저장값에 대응하는 변수의 값을 증가시키는 함수
    static void countMapInformation(SokobanMap map, String[] mapRowInformation) {
        for (int i = 0; i < mapRowInformation.length; i++) {
            for (int j = 0; j < mapRowInformation[i].length(); j++) {
                if (saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j)) != null) {
                    switch (saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j))) {
                        case 0 -> map.numEmptySpace++;
                        case 1 -> map.numHall++;
                        case 2 -> map.numBall++;
                        case 3 -> {
                            map.playerPosition[0] = i + 1;
                            map.playerPosition[1] = j + 1;
                        }
                    }
                }
            }
        }
    }

    static void printMapInformation(SokobanMap map) {
        System.out.println(map.stageNumber);
        System.out.println("\n");
        System.out.println(map.originalMap);
        System.out.println("\n");
        System.out.println("가로크기: " + map.mapWidth);
        System.out.println("세로크기: " + map.mapHeight);
        System.out.println("구멍의 수: " + map.numHall);
        System.out.println("공의 수: " + map.numBall);
        System.out.println("플레이어 위치: " + map.playerPosition[0] + "행 " + map.playerPosition[1] + "열");
    }

    static void printMap(SokobanMap map) {
        System.out.println("\n");
        System.out.println(map.stageNumber);
        System.out.println("\n");
        System.out.println(map.originalMap);
        System.out.println("\n");
    }




    // map을 순회하며 구성요소의 해당 저장값에 대응하는 SokobanMap class의 변수값 증가
//    static void saveNumberToSokobanMapInformation(SokobanMap map, String mapInformation){
//        if(map.stageNumber.equals("Stage 1")){
//            String[] mapRowInformation = mapInformation.split("\n");
//            for(int i = 0; i < mapRowInformation.length; i++) {
//                map1.mapWidth = Math.max(map1.mapWidth, mapRowInformation[i].length());
//                for(int j = 0; j <mapRowInformation[i].length(); j++) {
//                    if(saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j)) != null){
//                        switch (saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j))){
//                            case 0 -> map1.numEmptySpace++;
//                            case 1 -> map1.numHall++;
//                            case 2 -> map1.numBall++;
//                            case 3 -> {
//                                map1.playerPosition[0] = i + 1;
//                                map1.playerPosition[1] = j + 1;}}}}}
//            map1.mapHeight = mapRowInformation.length;
//        }
//        else{
//            String[] mapRowInformation = mapInformation.split("\n");
//            for(int i = 0; i < mapRowInformation.length; i++) {
//                map2.mapWidth = Math.max(map2.mapWidth, mapRowInformation[i].length());
//                for(int j = 0; j < mapRowInformation[i].length(); j++) {
//                    if(saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j)) != null){
//                        switch (saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j))){
//                            case 0 -> map2.numEmptySpace++;
//                            case 1 -> map2.numHall++;
//                            case 2 -> map2.numBall++;
//                            case 3 -> {
//                                map2.playerPosition[0] = i + 1;
//                                map2.playerPosition[1] = j + 1;
//                            }
//                        }
//                    }
//                }
//            }
//            map2.mapHeight = mapRowInformation.length;
//        }
//    }

}

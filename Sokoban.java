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

    public static void main(String[] args) {
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

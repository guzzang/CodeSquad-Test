public class Sokoban {

    static class SokobanMap{
        String stageNumber;
        int numEmptySpace;
        int mapWidth;
        int mapHeight;
        int numHall;
        int numBall;
        int[] playerPosition = new int[2];
        String originalMapStage;
    }


    static SokobanMap map1 = new SokobanMap();
    static SokobanMap map2 = new SokobanMap();
    static String[] mapArray;

    public static void main(String[] args) {
        String mapInformation =
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
        splitMapStages(mapInformation);
        calculateMapInformation(map1);
        calculateMapInformation(map2);

        printMap(map1);
        printMap(map2);

    }

    static void splitMapStages(String mapInformation){
        mapArray = mapInformation.split("=====");
        saveStageDetails(map1, mapArray[0].trim());
        saveStageDetails(map2, mapArray[1].trim());
    }

    static void saveStageDetails(SokobanMap map, String mapInformation) {
        String[] splitMapInformation = mapInformation.split("\n", 2);
        map.stageNumber = splitMapInformation[0];
        map.originalMapStage = splitMapInformation[1];

    }

    static void calculateMapInformation(SokobanMap map) {
        String[] mapRowInformation = map.originalMapStage.split("\n");
        calculateMapWidthAndHeight(map, mapRowInformation);
        countMapInformation(map, mapRowInformation);
    }

    static void calculateMapWidthAndHeight(SokobanMap map, String[] mapRowInformation) {
        map.mapHeight = mapRowInformation.length;
        for (String mapRow : mapRowInformation) {
            map.mapWidth = Math.max(map.mapWidth, mapRow.length());
        }
    }

    static Integer saveSokobanMapInformationToNumber(char mapInformation) {

        if(mapInformation == ' ') return 0;
        else if(mapInformation == 'O') return 1;
        else if(mapInformation == 'o') return 2;
        else if(mapInformation == 'P') return 3;
        else if(mapInformation == '#') return 4;

        return null;
    }

    // map의 각 구성요소에 대해 대응되는 저장값으로 변환
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

    static void printMap(SokobanMap map) {
        System.out.println(map.stageNumber);
        System.out.println("\n");
        System.out.println(map.originalMapStage);
        System.out.println("\n");
        System.out.println("가로크기: " + map.mapWidth);
        System.out.println("세로크기: " + map.mapHeight);
        System.out.println("구멍의 수: " + map.numHall);
        System.out.println("공의 수: " + map.numBall);
        System.out.println("플레이어 위치: " + map.playerPosition[0] + "행 " + map.playerPosition[1] + "열");
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

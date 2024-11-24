public class Sokoban {

    static class SokobanMap{
        int numEmptySpace;
        int numHall;
        int numBall;
        int numPlayer;
        int numWall;
        int stageSeparator;
        String originalMap;
    }

    static class SokobanMapStage1{
        int mapWidth;
        int mapHeight;
        int numHall;
        int numBall;
        int[] playerPosition = new int[2];
        String originalMapStage1;
    }

    static class SokobanMapStage2{
        int mapWidth;
        int mapHeight;
        int numHall;
        int numBall;
        int[] playerPosition = new int[2];
        String originalMapStage2;
    }

    static SokobanMap map = new SokobanMap();
    static SokobanMapStage1 map1 = new SokobanMapStage1();
    static SokobanMapStage2 map2 = new SokobanMapStage2();

    static String[] mapArray;

    public static void main(String[] args) {
        String mapInformation = """
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
        saveMapStageInformation(mapInformation);
        // inputSokobanMapInformation(mapInformation);
        saveNumberToSokobanMapInformation(map1.originalMapStage1);
        saveNumberToSokobanMapInformation(map2.originalMapStage2);

        System.out.println("map1.originalMapStage1 = " + map1.originalMapStage1);
        System.out.println("map1.mapWidth = " + map1.mapWidth);
        System.out.println("map1.mapHeight = " + map1.mapHeight);
        System.out.println("Map1 Halls: " + map1.numHall);
        System.out.println("Map1 Balls: " + map1.numBall);
        System.out.println("map1.playerPosition[0] = " + map1.playerPosition[0]);
        System.out.println("map1.playerPosition[1] = " + map1.playerPosition[1]);

        System.out.println("map2.originalMapStage2 = " + map2.originalMapStage2);
        System.out.println("map2.mapWidth = " + map2.mapWidth);
        System.out.println("map2.mapHeight = " + map2.mapHeight);
        System.out.println("Map2 Halls: " + map2.numHall);
        System.out.println("Map2 Balls: " + map2.numBall);
        System.out.println("map2.playerPosition[0] = " + map2.playerPosition[0]);
        System.out.println("map2.playerPosition[1] = " + map2.playerPosition[1]);
    }

    static void saveMapStageInformation(String mapInformation) {
        mapArray = mapInformation.split("=====");
        map1.originalMapStage1 = mapArray[0];
        map2.originalMapStage2 = mapArray[1];
    }

    // map을 문자열로 받고 SokobanMap class에 map 정보를 저장
    static void inputSokobanMapInformation(String mapInformation) {
        map.originalMap = mapInformation;
    }

    // map의 각 구성요소에 대해 대응되는 저장값으로 변환
    static Integer saveSokobanMapInformationToNumber(char mapInformation) {

        if(mapInformation == ' ') return 0;
        else if(mapInformation == 'O') return 1;
        else if(mapInformation == 'o') return 2;
        else if(mapInformation == 'P') return 3;
        else if(mapInformation == '#') return 4;

        return null;
    }

    // map을 순회하며 구성요소의 해당 저장값에 대응하는 SokobanMap class의 변수값 증가
    static void saveNumberToSokobanMapInformation(String mapInformation){
        int max = Integer.MIN_VALUE;
        if(mapInformation.startsWith("Stage 1")){
            String[] mapRowInformation = mapInformation.split("\n");
            for(int i = 0; i < mapRowInformation.length; i++) {
                map1.mapWidth = Math.max(max, mapRowInformation[i].length());
                map1.mapHeight++;
                for(int j = 0; j <mapRowInformation[i].length(); j++) {
                    if(saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j)) != null){
                        switch (saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j))){
                            case 1 -> map1.numHall++;
                            case 2 -> map1.numBall++;
                            case 3 -> {
                                map1.playerPosition[0] = i;
                                map1.playerPosition[1] = j + 1;
                            }
                        }
                    }
                }
            }
            map1.mapHeight -= 1;
        }
        else{
            String[] mapRowInformation = mapInformation.split("\n");
            for(int i = 0; i < mapRowInformation.length; i++) {
                map2.mapWidth = Math.max(max, mapRowInformation[i].length());
                map2.mapHeight++;
                for(int j = 0; j <mapRowInformation[i].length(); j++) {
                    if(saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j)) != null){
                        switch (saveSokobanMapInformationToNumber(mapRowInformation[i].charAt(j))){
                            case 1 -> map2.numHall++;
                            case 2 -> map2.numBall++;
                            case 3 -> {
                                map2.playerPosition[0] = i - 1;
                                map2.playerPosition[1] = j + 1;
                            }
                        }
                    }
                }
            }
            map2.mapHeight -= 2;
        }
    }

}

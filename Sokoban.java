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

    static SokobanMap map = new SokobanMap();

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
                 #   O  #\s
                 ########
                """;
        inputSokobanMapInformation(mapInformation);
        saveNumberToSokobanMapInformation(mapInformation);

        System.out.println("Halls: " + map.numHall);
        System.out.println("Balls: " + map.numBall);
        System.out.println("Players: " + map.numPlayer);
        System.out.println("Walls: " + map.numWall);
        System.out.println("Empty Spaces: " + map.numEmptySpace);
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
        String[] mapRowInformation = mapInformation.split("\n");
        for(String mapRow :mapRowInformation) {
            for(int i = 0; i < mapRow.length(); i++) {
                if(saveSokobanMapInformationToNumber(mapRow.charAt(i)) != null){
                    switch (saveSokobanMapInformationToNumber(mapRow.charAt(i))) {
                        case 1 -> map.numHall++;
                        case 2 -> map.numBall++;
                        case 3 -> map.numPlayer++;
                        case 4 -> map.numWall++;
                        case 0 -> map.numEmptySpace++;
                    }
                }
            }
        }
    }
}

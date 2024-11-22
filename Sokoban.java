public class Sokoban {

    public static void main(String[] args) {
        String sokobanMap = """
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
        inputSokobanMap(sokobanMap);

    }

    // 해당 내용을 문자열로 받아서 출력
    static void inputSokobanMap(String sokobanMap) {
        System.out.println(sokobanMap);
    }


}

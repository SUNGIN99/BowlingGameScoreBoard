public class Game {
    private final int userNo; // user번호
    private final Frame[] frames; // 10 프레임
    private int finalScore; // 최종 점수

    public Game(int userNo, int finalScore) {
        this.userNo = userNo;
        this.frames = new Frame[10];
        this.finalScore = finalScore;
    }
}

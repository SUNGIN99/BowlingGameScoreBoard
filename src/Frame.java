public class Frame {
    private final int frameNo; // 프레임 번호
    private final int[] pitches; // 1/2/3 번째 투구
    private final int frameScore; // 프레임 점수

    public Frame() {
        this.frameNo = 0;
        this.pitches = new int[]{0, 0, 0};
        this.frameScore = 0;
    }
}

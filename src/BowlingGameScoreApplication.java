
import java.util.ArrayList;
import java.util.Scanner;

public class BowlingGameScoreApplication {

    private static ArrayList<String> queue = new ArrayList<>();
    private static ArrayList<Integer> queueCount = new ArrayList<>();

    // 점수 저장할 배열
    private static int [][] frameScoresPerPitch = new int[10][3];
    private static int [] frameScores = new int[10];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int updateScoreIndex = 0;

        int sum = 0;
        int temp = 0, temp1;
        int result = 0;
        int n1, n2 = 0;


        for(int i=0;i<10;i++){ //1경기 10프레임
            int [] pitchScore = {-1, -1, -1};

            temp1=temp; //temp1 에 전전 프레임 결과 저장
            temp=result; //temp에 지난 프레임 결과 저장
            result=0;

            n1=input.nextInt();

            // 점수 저장
            pitchScore[0] = n1;
            frameScoresPerPitch[i] = pitchScore;
            updateScoreIndex = countUp();
            System.out.println("updateScoreIndex: " + updateScoreIndex);

            if(updateScoreIndex != -1){
                updateFrameScore(updateScoreIndex, queueCount.get(0));
            }

            n2=0;

            if(n1==10){ //스트라이크
                result=2;
                sum+=n1;

                queue.add(i+"S");
                queueCount.add(0);
            }

            else{
                n2=input.nextInt();

                pitchScore[1] = n2;
                frameScoresPerPitch[i] = pitchScore;
                updateScoreIndex = countUp();
                System.out.println("updateScoreIndex: " + updateScoreIndex);

                if(updateScoreIndex != -1){
                    updateFrameScore(updateScoreIndex, queueCount.get(0));
                }

                sum+=n1+n2;

                if(n1+n2==10) { //스페어
                    result = 1;

                    queue.add(i+"P");
                    queueCount.add(0);
                }
                else {
                    result=0;
                }
            }

            switch(temp){
                case 2 :
                    if (temp1==2)
                        sum+=n1+n1+n2;
                    else
                        sum += n1 + n2;
                    break; //스트라이크
                case 1 :
                    sum+=n1;
                    break; //스페어
                default :
                    break;
            }

            if (result == 0){
                frameScores[i] = sum;
            }

            System.out.println(i + ", " +sum);
            System.out.println("queue: " + queue);
            System.out.println("queueCount: " + queueCount);
            System.out.println("updateIndex: " + updateScoreIndex);

            printFrameScore(frameScores);
            printFrameScorePerPitch(frameScoresPerPitch);
        }
        for(int i=0; i<result; i++){
            sum += input.nextInt(); // 10프레임 나머지 처리
            frameScores[9] = sum;
            System.out.println(sum);
        }
        printFrameScore(frameScores);
        printFrameScorePerPitch(frameScoresPerPitch);
    }

    private static int countUp() {
        int updateIndex = -1;
        queueCount.replaceAll(count -> ++count);
        int afterPitches;
        if (queueCount.size() != 0) {
            String prevTenPointIndex = queue.get(0);
            afterPitches = queueCount.get(0);
            if ((prevTenPointIndex.charAt(1) == 'S' && afterPitches == 2) ||
                    (prevTenPointIndex.charAt(1) == 'P' && afterPitches == 1)) {
                updateIndex = Integer.valueOf(prevTenPointIndex.charAt(0)) - 48;
            }
        }
        return updateIndex;
    }

    private static void updateFrameScore(int updateScoreIndex, int afterPitches){
        int i = updateScoreIndex + 1;
        int pitchCountAfterTenPoint = 0;
        int frameResult = updateScoreIndex <= 0 ? 0 : frameScores[updateScoreIndex-1];

        if (afterPitches == 2){ // 스트라이크일 경우
            frameResult += frameScoresPerPitch[updateScoreIndex][0];
        }
        else if (afterPitches == 1){ // 스페어일 경우
            frameResult += frameScoresPerPitch[updateScoreIndex][0] + frameScoresPerPitch[updateScoreIndex][1];
        }

        while(pitchCountAfterTenPoint < afterPitches){
            for(int j = 0; j < 3; j++){
                if (pitchCountAfterTenPoint >= afterPitches) break;
                if (frameScoresPerPitch[i][j] != -1){
                    frameResult += frameScoresPerPitch[i][j];
                    pitchCountAfterTenPoint++;
                }
            }
             i++;
        }

        frameScores[updateScoreIndex] = frameResult;
        queueCount.remove(0);
        queue.remove(0);
    }

    private static void printFrameScore(int[] frameScores){
        for(int i = 0; i<10; i++){
            System.out.print(frameScores[i] + " ");
        }
        System.out.println();
    }

    private static void printFrameScorePerPitch(int[][] frameScoresPerPitch){
        for(int i = 0; i<10; i++){
            System.out.print("[");
            for (int j = 0; j<3; j++){
                System.out.print(frameScoresPerPitch[i][j] + " ");
            }
            System.out.print("], ");
        }
        System.out.println();
    }
}

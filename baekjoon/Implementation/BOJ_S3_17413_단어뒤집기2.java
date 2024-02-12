package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_17413_단어뒤집기2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine() + " "; //마지막 단어 또는 태그가 result에 저장되게 하기 위해 가장 뒤에 공백 추가
        char[] input = str.toCharArray();
        StringBuilder result = new StringBuilder(); //최종 결과
        StringBuilder word = new StringBuilder(); //단어
        StringBuilder tag = new StringBuilder(); //태그
        boolean inTag = false;

        for (int i = 0; i < input.length; i++) {
            if (input[i] == '<') { //태그가 시작되는 경우
                //태그가 시작되기 직전까지의 내용은 뒤집어서 result에 저장
                word = reverseWord(word.toString());
                result.append(word);
                //word 초기화
                word = new StringBuilder();
                //태그가 시작됨 표시
                inTag = true;
            }
            //태그 내부의 내용인 경우 tag에 저장
            if (inTag) {
                tag.append(input[i]);
            }
            if (input[i] == '>') { //태그가 끝나는 경우
                //result에 그대로 저장
                result.append(tag);
                //tag 초기화
                tag = new StringBuilder();
                //태그가 종료됨 표시
                inTag = false;
            }
            if (!inTag && input[i] == ' ') { //태그 밖에서 공백을 만난 경우
                //하나의 단어가 끝났으르모 뒤집어서 result에 저장
                word = reverseWord(word.toString());
                result.append(word);
                //word 초기화
                word = new StringBuilder();
                //빈칸 결과에 추가
                result.append(input[i]);
            }
            if (!inTag && input[i] != ' ' && input[i] != '<' && input[i] != '>') { //단어에 포함된 문자인 경우
                //word에 저장
                word.append(input[i]);
            }
        }
        //마지막 부분에 word 또는 tag가 있으므로 저장
        result.append(word);
        result.append(tag);

        //처음에 추가한 마지막 공백 제거
        String rresult = result.substring(0, result.length() - 1);
        System.out.println(rresult);
    }

    //단어를 뒤집는 함수
    private static StringBuilder reverseWord(String word) {
        StringBuilder reverseWord = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            reverseWord.append(word.charAt(i));
        }
        return reverseWord;
    }
}

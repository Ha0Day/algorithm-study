package Ch8_DynamicProgramming;

//8.10 Paint Fill
// 이미지 편집 프로그램에서 흔히 쓰이는 '영역 칠하기' 함수를 구현하라.
// 영역 칠하기 함수는 화면(색이 칠해진 이차원 배열)과 그 화면상의 한 지점, 그리고 새로운 색상이 주어졌을 때,
// 주어진 지점과 색이 같은 주변 영역을 새로운 색상으로 다시 색칠한다.
public class CR_Ch8_Q10_PaintFill {
    public static void main(String[] args) {
        Color[][] screen = {
                {Color.White, Color.White, Color.White, Color.White, Color.White},
                {Color.White, Color.Red,   Color.Red,   Color.Red,   Color.White},
                {Color.White, Color.Red,   Color.White, Color.Red,   Color.White},
                {Color.White, Color.Red,   Color.Red,   Color.Red,   Color.White},
                {Color.White, Color.White, Color.White, Color.White, Color.White}
        };

        changeColor(screen, 1, 1, Color.Green);


        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++) {
                System.out.print(screen[i][j] + " ");
            }
            System.out.println();
        }

    }

    static void changeColor(Color[][] arr, int r, int c, Color color) {
        changeColor(arr, r, c, arr[r][c], color);
    }

    static void changeColor(Color[][] arr, int r, int c, Color originalColor, Color newColor) {
        if (r < 0 || r >= arr.length || c < 0 || c >= arr[0].length) {
            return;
        }

        if (arr[r][c].equals(originalColor)) {
            arr[r][c] = newColor;
            changeColor(arr, r - 1, c, originalColor, newColor);
            changeColor(arr, r + 1, c, originalColor, newColor);
            changeColor(arr, r, c - 1, originalColor, newColor);
            changeColor(arr, r, c + 1, originalColor, newColor);
        }
    }
}

enum Color {
    White, Red, Green
}


package Ch10_Sort;

//10.8 Find Duplicates
// 1부터 N(<=32,000)까지의 숫자로 이루어진 배열이 있다.
// 배열엔 중복된 숫자가 나타날 수 있고, N이 무엇인지는 알 수 없다.
// 사용 가능한 메모리가 4KB일 때, 중복된 원소를 모두 출력하려면 어떻게 해야 할까?
public class CR_Ch10_Q8_FindDuplicates {
    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 9, 10, 154, 5324, 3, 4689, 12346, 1354, 1, 154, 3427};

        findDuplicates(arr);
    }

    //Solution
    // 4KB = 4*1024*8bit = 32 * 2^10 bit의 주소 공간 사용 가능
    // 32 * 2^10 비트는 32,000보다 크다.
    // 따라서 32,000 비트로 구성된 비트 벡터를 생성하고 그 각 비트가 하나의 정수 나타내도록 함
    static void findDuplicates(int[] arr) {
        BitSet bs = new BitSet(32000);

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int num0 = num - 1; //bitset은 0에서 시작하고, 숫자는 1에서 시작
            if (bs.check(num0)) {
                System.out.println(num);
            } else {
                bs.set(num0);
            }
        }
    }
}

class BitSet {
    int[] bitset;

    public BitSet(int size) {
        bitset = new int[size / 32 + 1]; //int가 32bit이므로 32로 나눈다.
    }

    public void set(int num0) {
        int wordNumber = (num0 / 32); //배열 index 구하기
        int bitNumber = (num0 % 32);
        bitset[wordNumber] |= 1 << bitNumber;   //bit 값을 1로 바꿈
    }

    public boolean check(int num0) {
        int wordNumber = (num0 / 32);
        int bitNumber = (num0 % 32);
        return (bitset[wordNumber] & (1 << bitNumber)) != 0;    //bit 값이 1인지 확인
    }
}

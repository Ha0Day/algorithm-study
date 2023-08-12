package Ch3_Stacks_Queues.ThreeInOne;

import java.util.EmptyStackException;

//3.1 Three in One
//배열 한 개로 스택 세 개를 어떻게 구현할지 설명하라
//Solution
class Sol_ThreeInOne {
    public static void main(String[] args) throws Exception {

        //solution1
        //FixedMultiStack stack = new FixedMultiStack(5); //각 스택에게 5만큼의 공간 부여

        //solution2
        MultiStack stack = new MultiStack(3, 5); //각 스택에게 5만큼의 공간 부여

        stack.push(0, 1);
        stack.push(0, 2);
        stack.push(0, 3);
        stack.push(0, 4);
        stack.push(0, 5);
        stack.push(0, 6);
        stack.push(0, 7);
        stack.push(1, 8);
        stack.push(2, 9);
        stack.push(2, 10);
        stack.push(2, 11);
        stack.push(2, 12);
        stack.push(2, 13);
        stack.push(2, 14);
        stack.push(2, 15);

        stack.printAllStacks();

        stack.pop(0);
        stack.pop(0);
        stack.pop(0);
        stack.pop(1);
        stack.pop(2);
        stack.pop(2);

        stack.printAllStacks();

        System.out.println(stack.peek(2));
    }

}


//Solution1 - 고정 크기 할당
//배열을 같은 크기의 세 부분으로 나누어 각각의 스택이 그 크기 내에서만 사용됨
//배열을 2개 사용하긴 함..?
class FixedMultiStack {
    private static int numberOfStacks = 3;
    private static int stackCapacity;
    private static int[] values;
    private static int[] sizes;


    public static void printAllStacks() {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public static void push(int stackNum, int value) throws Exception {
        if (isFull(stackNum)) {
            throw new Exception();
        }

        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public static int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }

        int topIndex = indexOfTop(stackNum);
        int value = values[topIndex];
        values[topIndex] = 0;
        sizes[stackNum]--;
        return value;
    }

    public static int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[indexOfTop(stackNum)];
    }

    public static boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    public static boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    private static int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
    }
}


//Solution2 - 유연한 공간 분할
//환형(circular)으로 배열 설계. 마지막 스택이 배열 맨 끝에서 시작해서 처음으로 연결될 수 있음
//스택 3개의 총 크기는 고정, 각 스택의 크키가 변할 수 있는 것
//매우 복잡해서 면접 문제 난이도 범위를 넘어섬
class MultiStack {
    //StackInfo는 각 스택에 대한 자료를 들고 있는 간단한 클래스이다.
    //스택의 실제 아이템을 들고 있지는 않는다.
    //개별적인 변수 다발을 사용해서 실제 아이템을 들고 있게 할 수도 있지만,
    //이 방법은 굉장히 지저분하고 실제로 이렇게 해서 얻을 수 있는 게 많지 않다.
    private class StackInfo {
        public int start, size, capacity;

        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        //주어진 배열 내의 인덱스가 스택의 구역 내에 있는지 확인
        //스택은 순환해서 배열의 시작 지점으로 옮겨갈 수도 있다.
        public boolean isWithinStackCapacity(int index) {
            //배열의 범위를 벗어나면 false를 반환
            if (index < 0 || index >= values.length) {
                return false;
            }

            //인덱스가 배열을 순환해야 한다면 그 값을 알맞게 고쳐준다.
            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start <= contiguousIndex && contiguousIndex < end;
        }

        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
        }

        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    private StackInfo[] info;
    private static int[] values;

    public MultiStack(int numberOfStacks, int defaultSize) {
        //모든 스택에 대한 메타데이터를 만든다.
        info = new StackInfo[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            info[i] = new StackInfo(defaultSize * i, defaultSize);
        }
        values = new int[numberOfStacks * defaultSize];
    }

    public static void printAllStacks() {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }

    //stackNum 위치에 값을 넣어주고, 필요에 따라 스택을 움직이거나 늘려준다.
    //모든 스택이 꽉 찬 경우를 대비해서 예외 처리를 해준다.
    public void push(int stackNum, int value) throws Exception {
        if (allStackAreFull()) {
            throw new Exception();
        }

        //스택이 꽉 찼다면 크기를 늘려준다.
        StackInfo stack = info[stackNum];
        if (stack.isFull()) {
            expand(stackNum);
        }

        //크기를 하나 증가시킨 스택의 마지막 원소의 인덱스를 찾은 뒤 스택 포인터를 늘린다.
        stack.size++;
        values[stack.lastElementIndex()] = value;
    }

    //스택에서 값 하나를 제거한다.
    public int pop(int stackNum) throws EmptyStackException {
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        //마지막 원소를 제거한다.
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0; //마지막 원소를 삭제한다.
        stack.size--; //크기를 줄여준다.
        return value;
    }

    //스택의 꼭대기 값을 구한다.
    public int peek(int stackNum) {
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()];
    }

    //스택의 원소들을 하나 옮긴다.
    //만약 스택에 여유 공간이 있다면 스택의 크기를 원소를 하나 줄여준다.
    //여유 공간이 없다면 그 다음에 위치한 스택 또한 옮겨야 한다.
    private void shift(int stackNum) {
        System.out.println("///Shifting " + stackNum);
        StackInfo stack = info[stackNum];

        //스택이 꽉 차 있다면, 그 다음에 위치한 스택을 원소 하나 크기만큼 옮겨야 한다. 그래야 현재 스택에 여유 공간이 생긴다.
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.capacity++; //다음 스택에서 얻어온 가용 공간
        }

        //스택의 모든 원소를 하나씩 뒤로 옮긴다.
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }
        //스택의 데이터를 변경한다.
        values[stack.start] = 0; //원소를 삭제한다.
        stack.start = nextIndex(stack.start); //시작 지점을 옮긴다.
        stack.capacity--; //용량을 줄인다.
    }

    //다음 스택으로 뒤로 옮김으로써 스택의 크기를 늘려준다.
    private void expand(int stackNum) {
        shift((stackNum + 1) % info.length); // 다음 스택의 값을 하나씩 뒤로 미룸
        info[stackNum].capacity++;
    }

    //현재 스택에 존재하는 원소의 개수를 반환한다.
    public int numberOfElements() {
        int size = 0;
        for (StackInfo sd : info) {
            size += sd.size;
        }
        return size;
    }

    //모든 스택이 꽉 차있다면 true를 반환한다.
    public boolean allStackAreFull() {
        return numberOfElements() == values.length;
    }

    //인덱스를 0에서 length-1 범위 안에 있도록 조절해 준다.
    private int adjustIndex(int index) {
        //자바의 mod 연산자는 음수값을 반환할 수도 있다.
        //예를 들어, (-11 % 5)는 4가 아닌 -1을 반환하다.
        //하지만 우리가 실제로 원하는 값은 4일 것이다. (인덱스가 배열 범위 안에서 순환하기 때문에)
        int max = values.length;
        return ((index % max) + max) % max;
    }

    //(배열의 순환을 적용한) 현재 위치의 다음 인덱스를 반환
    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    //(배열의 순환을 적용한) 현재 위치의 이전 인텍스를 반환
    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }
}

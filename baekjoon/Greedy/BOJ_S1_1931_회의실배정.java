package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_1931_회의실배정 {

    static class Room implements Comparable<Room> {

        int s;
        int e;

        public Room(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Room o) {

            if (this.e - o.e < 0) {
                return -1;
            }
            if (this.e - o.e > 0) {
                return 1;
            } else {
                if (this.s - o.s < 0) {
                    return -1;
                }
                if (this.s - o.s > 0) {
                    return 1;
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Room [s=" + s + ", e=" + e + "]";
        }

    }

    static int N;
    static List<Room> rooms;
    static int count;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        rooms = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            rooms.add(new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(rooms);

        int a = 0;
        count = 1;
        for (int i = 1; i < rooms.size(); i++) {
            if (rooms.get(a).e <= rooms.get(i).s) {
                a = i;
                count++;
            }
        }

        System.out.println(count);

    }

}


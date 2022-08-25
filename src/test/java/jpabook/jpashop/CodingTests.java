package jpabook.jpashop;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class CodingTests {

    Solution solution = new Solution();
    @Test
    public void 부족한금액계산() throws Exception{
        //given
        int price = 3, money = 20, count = 4, result = 10;

        //when
        int expect = solution.solution12(price, money, count);
        //then
        Assertions.assertThat(result).isEqualTo(expect);
        
    }
    @Test
    public void 큐스택() throws Exception{
        //given
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        //when
        int[] result = solution.solution1(progresses, speeds);

        //then
        int[] test = {2,1};
        Assertions.assertThat(result).isEqualTo(test);
     }

     @Test
     public void 큐스택2() throws Exception{
         //given
         int[] priorities = {1, 1, 9, 1, 1, 1};
         int location = 0;
         //when
         int result = solution.solution2(priorities, location);
         //then
        Assertions.assertThat(5).isEqualTo(result);
    }
    
    @Test
    public void 스코필지수() throws Exception{
        //given
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        //when
        int result = solution.solution3(scoville, K);
        //then
        Assertions.assertThat(2).isEqualTo(result);
     }


    @Test
    public void 길찾기() throws Exception{
        //given
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int result = 11;
        //when
        int r = solution.solution4(maps);
        //then
        Assertions.assertThat(result).isEqualTo(r);
    }
    @Test
    public void 전구() throws Exception{
        //given
        int[] A = {2,1,3,5,4};
        //when
        int result = solution.solution5(A);
        //then
        Assertions.assertThat(3).isEqualTo(result);
     }
     
    @Test
    public void 소수찾기() throws Exception{
        //given
        String numbers = "17";
        String numbers2 = "011";
        //when
        int result = solution.solution7(numbers);
        int result2 = solution.solution7(numbers2);
        //then
        Assertions.assertThat(result).isEqualTo(3);
        Assertions.assertThat(result2).isEqualTo(2);
     }
    @Test
    public void 소수찾기1레벨() throws Exception{
        //given
        int n = 10;
        //when
        int result = solution.solution8(n);
        //then
        Assertions.assertThat(result).isEqualTo(4);
    }
    @Test
    public void 최소직사각형() throws Exception{
        //given
        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        //when
        int result = solution.solution9(sizes);
        //then
        Assertions.assertThat(result).isEqualTo(4000);
    }
    
    @Test
    public void 정수제곱근() throws Exception{
        //given
        long a = 121;
        long n = 3;
        //when
        long result = solution.solution10(a);
        long result2= solution.solution10(n);
        //then
        Assertions.assertThat(result).isEqualTo(144);
        Assertions.assertThat(result2).isEqualTo(-1);
    }
    
    @Test
    public void 로또번호() throws Exception{
        //given
        int[] lottos = {45, 4, 35, 20, 3, 9};
        int[] win_nums = {20, 9, 3, 45, 4, 35};
        //when
        int[] result = solution.solution11(lottos, win_nums);
        int[] expect = {1, 1};
        //then
        Assertions.assertThat(result).isEqualTo(expect);
    }
    @Test
    public void 전화번호() throws Exception{
        //given
        
        //when
        
        //then
        
    }

}

class Solution {
    public String solution13(int[] numbers, String hand) {
        String answer = "";
        ArrayList<Integer> left= new ArrayList<>();
        left.add(1); left.add(4); left.add(7);
        ArrayList<Integer> right= new ArrayList<>();
        right.add(3); right.add(6); right.add(9);
        int leftIndex = 10;
        int rightIndex = 12;

        for(int number : numbers){
            if(left.contains(number)){
                answer += "L";
                leftIndex = number;
            } else if (right.contains(number)) {
                answer +="R";
                rightIndex = number;
            }else{
                if(number == 0){
                    number = 11;
                }
                int leftLength = (Math.abs(number-leftIndex))/3 + (Math.abs(number-leftIndex))%3;
                // 2
                // 5 ,9 -> 1 + 1
                // 8
                int rightLength = (Math.abs(number-rightIndex))/3 + (Math.abs(number-rightIndex))%3;

                if(leftLength == rightLength){
                    if(hand.equals("right")){
                        answer += "R";
                        rightIndex = number;
                    }else{
                        answer += "L";
                        leftIndex = number;
                    }
                } else if (leftLength > rightLength) {
                    answer += "R";
                    rightIndex = number;
                }else{
                    answer += "L";
                    leftIndex = number;
                }
            }
        }
        return answer;
    }
    private void calculate(){

    }
    public int solution12(int price, int money, int count) {
        int total = 0;

        for(int i = 1; i <= count; i++){
            total += i * price;
        }

        return total - money;
    }
    public int[] solution11(int[] lottos, int[] win_nums) {
        int[] arr = {6,5,4,3,2};
        int[] answer = new int[2];
        int countMax = 0;
        int countMin = 0;

        for(int i = 0; i < 6; i++){
            int temp = lottos[i];
            if(Arrays.stream(win_nums).anyMatch(x -> x == temp)){
                countMax++;
                countMin++;
            }
            if(lottos[i] == 0){
                countMax++;
            }
        }

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == countMax){
                answer[0] = i + 1;
            }
            if(countMax <= 1){
                answer[0] = 6;
            }
        }

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == countMin){
                answer[1] = i + 1;
            }
            if(countMin <= 1){
                answer[1] = 6;
            }
        }
        return answer;
    }

    public long solution10(long n) {
        double answer = (double) Math.sqrt(n);
        System.out.println(answer);
        if(Math.floor(answer) == answer){
            return (long)((answer + 1) * (answer + 1));
        }
        return -1;
        
        /* 다른사라 ㅁ답
        *       if (Math.pow((int)Math.sqrt(n), 2) == n) {
            return (long) Math.pow(Math.sqrt(n) + 1, 2);
        }
        * */
    }


    public int solution9(int[][] sizes) {
        int[] arr = {0,0};
        for(int i = 0; i < sizes.length; i++){
            int d = Math.max(sizes[i][0], sizes[i][1]);
            int h = Math.min(sizes[i][0], sizes[i][1]);
            arr[0] = Math.max(arr[0], d);
            arr[1] = Math.max(arr[1], h);
        }
        return arr[0] * arr[1];
    }



    public int solution8(int n) {
        int answer = 0;
        for(int i = 2; i <= n; i++){
            if(isPrime(i)){
                answer++;
            }
        }

        return answer;
    }
    public boolean isPrime(int n) {
        for (int i = 2; i<=(int)Math.sqrt(n); i++) {
            if (n % i == 0) {
                System.out.println(n);
                return false;
            }
        }
        return true;
    }


    List<Integer> list1 = new ArrayList<>();

    public int solution7(String numbers) {
        boolean[] visited = new boolean[numbers.length()];
        int answer = 0;

        for(int i = 0; i < numbers.length(); i++){
            dfs(visited, numbers, "", i+1);
        }

        for(int i = 0; i < list1.size(); i++){
            if(prime(list1.get(i))){
                answer++;
            }
        }
        return answer;
    }

    public void dfs(boolean[] visited, String numbers, String temp, int n){
        if(temp.length() == n){
            int number = Integer.valueOf(temp);
            if(!list1.contains(number)){
                list1.add(number);
            }
        }
        for(int i = 0; i < numbers.length(); i++){
            if(!visited[i]){
                visited[i] = true;
                temp += numbers.charAt(i);
                dfs(visited, numbers, temp, n);
                visited[i] = false;
                temp = temp.substring(0, temp.length() - 1);
            }
        }
    }

    public boolean prime(int n){
        if(n < 2){
            return false;
        }
        for(int i = 2; i<=(int)Math.sqrt(n); i++){
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int[] solution6(int[] answers){
        int[] A = {1,2,3,4,5};
        int[] B = {2,1,2,3,2,4,2,5};
        int[] C = {3,3,1,1,2,2,4,4,5,5};
        int[] answerABC = {0,0,0};

        for(int i = 0; i < answers.length; i++){
            if(A[i % A.length] == answers[i]){
                answerABC[0]++;
            }
            if(B[i % A.length] == answers[i]){
                answerABC[1]++;
            }
            if(B[i % A.length] == answers[i]){
                answerABC[2]++;
            }
        }

        int max = Arrays.stream(answerABC).max().getAsInt();
        List<Integer> list = new ArrayList<Integer>();
        if(max == answerABC[0]){list.add(1);}
        if(max == answerABC[1]){list.add(2);}
        if(max == answerABC[2]){list.add(3);}

        return list.stream().mapToInt(i -> i.intValue()).toArray();



    }
    public int solution5(int[] A){
        int answer = 0;  // 1 1
        int[] arr = new int[A.length];
        arr[0] = A[0];
        // 2,1,3,5,4
        // [2, 3, 4, 1, 5]
        if(A[0] == 1){
            answer++;
        }
        for(int i = 1; i < A.length; i++){
            arr[i] = A[i]; // 1,2,3, 5, 4
            Arrays.sort(arr); // 1,2,3,45,
            System.out.println(arr[i] - arr[i-1]);
            if(arr[i] - arr[i-1] == 1){
                answer++;
            }
        }
        return answer;
    }

    class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return this.x;
        }
        public int getY(){
            return this.y;
        }
    }
    public int solution4(int[][] maps){
        int xMove[] = {0, 1, 0, -1};
        int yMove[] = {1, 0, -1, 0};
        int result = 0;
        Queue<Node> queue = new LinkedList<>();
        int x = 0;
        int y = 0;
        queue.add(new Node(x, y));

        while(!queue.isEmpty()){
            Node node = queue.poll();
            x = node.getX();
            y = node.getY();

            for(int i = 0; i < 4; i++){
                int xNext = x + xMove[i];
                int yNext = y + yMove[i];
                if(xNext >= 0 && xNext < maps.length &&
                    yNext >= 0 && yNext < maps[0].length && maps[xNext][yNext] == 1){
                    maps[xNext][yNext] = maps[x][y] + 1;
                    queue.offer(new Node(xNext, yNext));
                }
            }
        }
        int answer = maps[maps.length - 1][maps[0].length - 1];

        return answer == 1 ? -1 : answer;
    }


    public int solution3(int[] scoville, int K){
        int answer = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++){
            heap.add(scoville[i]);
        }

        while(heap.peek() < K){
            if(heap.size() < 2) return -1;
            int f1 = heap.poll();
            int f2 = heap.poll();

            int newFood = f1 + (f2 * 2);
            heap.add(newFood);
            answer++;
        }

        return answer;
    }

    class Pair{
        int index;
        int value;
        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public int solution2(int[] priorities, int location) {
        Queue<Pair> queue = new LinkedList<>();
        int answer = 0;

        for(int i = 0; i < priorities.length; i++){
            queue.add(new Pair(i, priorities[i]));
        }

        int now = -1;
        while(!queue.isEmpty()){
            Pair current = queue.poll();
            boolean frag = false;

            for(Pair pair : queue){
                if(pair.value > current.value){
                    frag = true;
                }
            }

            if(frag){
                queue.add(current);
            }else{
                now++;
                if(current.index == location){
                    now++;
                    answer = now;
                    break;
                }
            }
        }
        return answer;
    }


    public int[] solution1(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++){
            queue.add((int) Math.ceil((100.0 - progresses[i]) / speeds[i]));
        }

        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            int count = 1;
            int day = queue.poll();

            while(!queue.isEmpty() && day >= queue.peek()){
                queue.poll();
                count++;
            }
            list.add(count);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();

    }
}



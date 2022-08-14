package jpabook.jpashop;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

public class CodingTests {

    Solution solution = new Solution();

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

}

class Solution {

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



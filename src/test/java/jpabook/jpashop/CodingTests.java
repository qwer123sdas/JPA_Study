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

}

class Solution {

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



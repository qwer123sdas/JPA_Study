package jpabook.jpashop;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CodingTests {

    Solution solution = new Solution();

    @Test
    public void 큐스택() throws Exception{
        //given
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        //when
        int[] result = solution.solution(progresses, speeds);

        //then
        int[] test = {2,1};
        Assertions.assertThat(result).isEqualTo(test);
     }
}

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
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

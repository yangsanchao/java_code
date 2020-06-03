package com.yangsc.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * <p>Description: ForkJoin</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/6/2 18:20
 **/
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new MyForkJoinTask(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " 时间：" + (end - start));
    }


}

class MyForkJoinTask extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    // 临界值
    private Long temp = 10000L;

    public MyForkJoinTask(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else { // forkjoin 递归
            long middle = (start + end) / 2;
            MyForkJoinTask task1 = new MyForkJoinTask(start, middle);
            task1.fork();
            MyForkJoinTask task2 = new MyForkJoinTask(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}

package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class KillDemo {
    LinkedBlockingQueue<RequestPromise> linkedBlockingQueue = new  LinkedBlockingQueue(8);
    long stock = 6L;
    public static void main(String[] args) throws InterruptedException {
        KillDemo killDemo = new KillDemo();
        killDemo.mergeJob();
        Thread.sleep(100);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Result>> userFutures = new ArrayList<>();
        for(int i=0;i<10;i++){
            UserRequest userRequest = new UserRequest(100+i,1,i);
            Future<Result> userFuture = executorService.submit(() -> {
                return killDemo.operate(userRequest);
            });
            userFutures.add(userFuture);
        }
        userFutures.stream().forEach((afeature)->{
            try {
                Result result = afeature.get(300, TimeUnit.MILLISECONDS);
                System.out.println( Thread.currentThread().getName()+"，客户端请求响应 = "+result.getMessage()+"，"+result.isSuccess());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Result operate(UserRequest userRequest) throws InterruptedException {

        RequestPromise requestPromise = new RequestPromise(userRequest);

        boolean success = linkedBlockingQueue.offer(requestPromise, 200, TimeUnit.MILLISECONDS);
        if(!success){
            requestPromise.setResult(new Result(false,"系统繁忙！"));
        }

        synchronized (requestPromise){
            try {
                requestPromise.wait(200);
            } catch (InterruptedException e) {
                requestPromise.setResult(new Result(false,"被打扰失败，等待超时？"));
            }
        }
        return requestPromise.getResult();
    }

    private void mergeJob(){
        new Thread(()->{
            while (true){
                List<RequestPromise> requestPromisesList = new ArrayList<>();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(linkedBlockingQueue.isEmpty()){
                    continue;
                }
                while (linkedBlockingQueue.peek()!=null){
                    requestPromisesList.add(linkedBlockingQueue.poll());
                }
                long sum = requestPromisesList.stream().mapToLong((request) -> request.getUserRequest().getDecrement()).sum();
                if(sum<=stock){
                    stock -= sum;
                    requestPromisesList.forEach((requestPromise)->{
                        requestPromise.setResult(new Result(true,"在批量扣减成功"));
                        synchronized (requestPromise){
                            requestPromise.notify();
                        }
                    });
                    continue;
                }
                requestPromisesList.forEach((requestPromise)->{
                    if(stock>=requestPromise.getUserRequest().getDecrement()) {
                        stock = stock - requestPromise.getUserRequest().getDecrement();
                        requestPromise.setResult(new Result(true,"循环扣减成功"));
                    }else{
                        requestPromise.setResult(new Result(false,"库存不足，扣减失败了"));
                    }
                    synchronized (requestPromise){
                        requestPromise.notify();
                    }
                });
                requestPromisesList.clear();
            }
        }).start();
    }
}

class RequestPromise{
    private Result result;
    private UserRequest userRequest;

    public RequestPromise(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}

class Result{
    boolean isSuccess;
    String message;

    public Result(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}

class UserRequest{
    int orderId;
    int decrement;
    int userId;

    public  UserRequest(int orderId, int decrement, int userId) {
        this.orderId = orderId;
        this.decrement = decrement;
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDecrement() {
        return decrement;
    }

    public void setDecrement(int decrement) {
        this.decrement = decrement;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserReauest{" +
                "orderId=" + orderId +
                ", decrement=" + decrement +
                ", userId=" + userId +
                '}';
    }
}
package com.bam;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据库中,每个人请假按请假当天日期记录一条数据，比如：请假1天，有1行数据，请假3天，就有3行数据，每行记录请假当天的日期 比如： 2021-12-12，2021-12-13，2021-12-14
 * 要求：列表显示每个人请假天数，请假天数连续的要合在一起,比如表示成 姓名：张三 请假时间：（2021-12-12 到 2021-12-14）
 */
public class TestContinueDate {
    public static void main(String[] args) {
        List<LeaveInfo> leaves = new ArrayList();
        leaves.add(new LeaveInfo("wang",LocalDateTime.of(2019,12,13,12,12),"sick"));
        leaves.add(new LeaveInfo("wang",LocalDateTime.of(2019,11,11,12,12),"sick"));
        leaves.add(new LeaveInfo("zhang", LocalDateTime.now(),"anual"));
        leaves.add(new LeaveInfo("wang",LocalDateTime.of(2019,12,12,12,12),"sick"));
        leaves.add(new LeaveInfo("huang",LocalDateTime.of(2021,10,12,13,23),"sick"));
        leaves.add(new LeaveInfo("wang",LocalDateTime.of(2019,12,14,12,12),"sick"));
        leaves.add(new LeaveInfo("huang",LocalDateTime.of(2021,10,13,5,45),"sick"));
        leaves.add(new LeaveInfo("wang",LocalDateTime.of(2019,11,12,12,12),"sick"));
        System.out.println("leaves = " + leaves);
        //按请假时间排序
        leaves.sort(Comparator.comparing(LeaveInfo::getLeaveDate));
        System.out.println("leaves = " + leaves);
        //按人名分组
        Map<String,List<LeaveInfo>> groupNames= leaves.stream().collect(Collectors.groupingBy(LeaveInfo::getName));
       groupNames.forEach((name,nameLeaves)->{
           Map<Integer, int[]> aggregate = aggregate(nameLeaves);
           aggregate.forEach((index,leaveAgg)->{
               System.out.println("index = " + index);
               int start = leaveAgg[0];
               int end = leaveAgg[1];
               System.out.println("连续天开始 = " + nameLeaves.get(start));
               System.out.println("连续天结束 = " + nameLeaves.get(end));
           });
       });
    }

    //根据连续时间聚合
    static Map<Integer, int[]> aggregate(List<LeaveInfo> leaveInfos){
        int totalSize = leaveInfos.size();
        int cursor = totalSize-1;
        int findGroupIndex = 0;
        Map<Integer, int[]> mapAggregation = new HashMap<>();
        for(int i=0;i<totalSize;i++){
            if(isEqual(i,cursor,leaveInfos)){
                findGroupIndex++;
                mapAggregation.put(findGroupIndex,new int[]{i,cursor});
                i = cursor;
                cursor = totalSize-1;
            }else{
                cursor=cursor-1;
                i = i-1;
            }
        }
        return mapAggregation;

    }

    static boolean isEqual(int index,int cursor,List<LeaveInfo> leaveInfos){
        if (leaveInfos.get(index).getLeaveDate().plusDays(cursor-index).getDayOfYear() == leaveInfos.get(cursor).getLeaveDate().getDayOfYear())
            return true;
        return false;
    }
}

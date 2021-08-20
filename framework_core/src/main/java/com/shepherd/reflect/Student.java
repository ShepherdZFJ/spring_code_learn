package com.shepherd.reflect;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/8/20 11:43
 */
@Data
public class Student extends Person {
    @Deprecated
    public Long sn =20211314l;
    private String schoolName="浙工大";
    private Double score;

    public Student(){}

    public Student(Long sn, String schoolName, Double score) {
        this.sn = sn;
        this.schoolName = schoolName;
        this.score = score;
    }

    private void doHomework(String subject, Integer hour) {
        System.out.println("每天学习科目：" + subject + "   时长："+ hour);
//        Student student = null;
//        student.setScore(99.5);
    }

    public void sleep(Date startTime, String addr, Integer hour) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
        String format = sdf.format(startTime);
        System.out.println("睡眠开始时间：" + format);
        System.out.println("每天睡眠：" + addr + "   时长："+ hour);
    }

    public Integer play() {
        return 1;
    }






}

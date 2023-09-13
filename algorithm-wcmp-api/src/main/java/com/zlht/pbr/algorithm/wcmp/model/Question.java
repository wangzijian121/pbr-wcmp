package com.zlht.pbr.algorithm.wcmp.model;

import lombok.Data;

@Data
public class Question {
    private String question;
    private String A;
    private String B;
    private String C;
    private String D;
    private String answer;

    public Question(String question, String a, String b, String c, String d, String answer) {
        this.question = question;
        A = a;
        B = b;
        C = c;
        D = d;
        this.answer = answer;
    }

    // Getters and setters (or use Lombok for automatic generation)
}
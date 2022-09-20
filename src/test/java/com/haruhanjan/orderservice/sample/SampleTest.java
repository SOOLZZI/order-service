package com.haruhanjan.orderservice.sample;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

@Import({SampleGenerator.class})
public class SampleTest {


    private SampleGenerator sg = new SampleGenerator();

    @Test
    public void generate(){
        System.out.println(sg);
        Object dto = sg.createItemRequestDto();
        System.out.println(dto);
    }
}

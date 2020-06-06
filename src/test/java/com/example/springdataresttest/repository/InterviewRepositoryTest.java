package com.example.springdataresttest.repository;

import org.h2.server.web.WebApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterviewRepositoryTest {

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Autowired
    public void before() throws Exception {
        interviewRepository.deleteAllInBatch();
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void countByDayAndTime() throws Exception {
        List<Interview> interviews = new ArrayList<>();
        //lets setup some data
        for (int i=0;i<5;i++) {
            Interview interview = new Interview();
            interview.setActive(true);
            interview.setInterviewerId(Integer.toString(i));
            interview.setDay("Monday");
            interview.setSlot(i);
            interviews.add(interview);
        }//end for
        //save
        interviewerSlotRepository.saveAll(interviews);
        //now test
        List list = interviewerSlotRepository.countByDayAndTime();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void restTest() throws Exception {
        List<Interview> interviews = new ArrayList<>();
        //lets setup some data
        for (int i=0;i<5;i++) {
            Interview interview = new Interview();
            interview.setActive(true);
            interview.setInterviewerId(Integer.toString(i));
            interview.setDay("Monday");
            interview.setSlot(i);
            interviews.add(interview);
        }//end for
        //save
        interviewRepository.saveAll(interviews);
        //now get
        String result = mockMvc.perform(get("/interviewerSlot/search/countByDayAndTime").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }
}
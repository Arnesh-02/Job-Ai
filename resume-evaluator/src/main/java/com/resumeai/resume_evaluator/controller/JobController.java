package com.resumeai.resume_evaluator.controller;

import com.resumeai.resume_evaluator.model.JobDescription;
import com.resumeai.resume_evaluator.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job/")
public class JobController {

    @Autowired
    JobService obj;

    @PostMapping("/add")
    public JobDescription addJob(@RequestBody JobDescription job) {
        return obj.saveJob(job);
    }


//    @PostMapping("/resume")
//    public String getResume(@RequestBody MultipartFile resume) throws TikaException, IOException {
//        ;
//    }

    @GetMapping("/all")
    public List<JobDescription> getAllJd(){
        return obj.getAllJobs();
    }
}



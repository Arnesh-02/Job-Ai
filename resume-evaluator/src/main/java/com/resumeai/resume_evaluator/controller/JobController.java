package com.resumeai.resume_evaluator.controller;

import com.resumeai.resume_evaluator.model.JobDescription;
import com.resumeai.resume_evaluator.services.JobService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @PostMapping("/match")
    public List<JobDescription> getBestMatches(@RequestParam MultipartFile resume) throws IOException, TikaException {
        String parsedText = obj.resumeParsing(resume);
        return obj.findBestMatches(parsedText);
    }


    @GetMapping("/all")
    public List<JobDescription> getAllJd(){
        return obj.getAllJobs();
    }
}



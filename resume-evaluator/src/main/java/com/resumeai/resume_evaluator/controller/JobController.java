package com.resumeai.resume_evaluator.controller;

import com.resumeai.resume_evaluator.model.JobDescription;
import com.resumeai.resume_evaluator.services.JobService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/job/")
public class JobController {

    @Autowired
    JobService obj;

    @GetMapping("/")
    public String home() {
        return "index";
    }



    @PostMapping("/add")
    public JobDescription addJob(@RequestBody JobDescription job) {
        return obj.saveJob(job);
    }


    @PostMapping("/match")
    public String getBestMatches(@RequestParam MultipartFile resume, org.springframework.ui.Model model) throws IOException, TikaException {
        String parsedText = obj.resumeParsing(resume);
        List<JobDescription> matches = obj.findBestMatches(parsedText);
        model.addAttribute("matches", matches);
        return "index";  // render the same page with results
    }


    @GetMapping("/all")
    public List<JobDescription> getAllJd(){
        return obj.getAllJobs();
    }
}



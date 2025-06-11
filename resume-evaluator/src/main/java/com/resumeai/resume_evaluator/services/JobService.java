package com.resumeai.resume_evaluator.services;

import com.resumeai.resume_evaluator.model.JobDescription;
import com.resumeai.resume_evaluator.repository.JobDsRepo;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class JobService {

    Tika ob=new Tika();
    @Autowired
    JobDsRepo repo;

    public String resumeParsing(MultipartFile resume) throws IOException, TikaException {
        return  ob.parseToString(resume.getInputStream());
    }

    public List<JobDescription> getAllJobs() {
        return repo.findAll();
    }

    public JobDescription saveJob(JobDescription job) {
        return repo.save(job);
    }




}

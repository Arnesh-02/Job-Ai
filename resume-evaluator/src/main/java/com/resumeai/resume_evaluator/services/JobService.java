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

    @Autowired
    private CohereService cohereService;

    public String resumeParsing(MultipartFile resume) throws IOException, TikaException {
        return  ob.parseToString(resume.getInputStream());
    }

    public List<JobDescription> getAllJobs() {
        return repo.findAll();
    }

    public JobDescription saveJob(JobDescription job) {
        return repo.save(job);
    }


    public List<JobDescription> findBestMatches(String resumeText) {
        List<JobDescription> jobs = repo.findAll();

        return jobs.stream()
                .map(jd -> {
                    try {
                        String jdText = jd.getJobTitle() + ". "
                                + jd.getJobSummary() + " "
                                + jd.getKeyResponsibility() + " "
                                + jd.getRequirements();

                        double score = cohereService.getSimilarity(resumeText, jdText);
                        jd.setScore(score);
                        System.out.println("Matched " + jd.getJobTitle() + " → Score: " + score);
                    } catch (Exception e) {
                        jd.setScore(0.0);
                        System.out.println("Error for " + jd.getJobTitle() + ": " + e.getMessage());
                    }
                    return jd;
                })
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))
                .limit(3)
                .toList();
    }



}

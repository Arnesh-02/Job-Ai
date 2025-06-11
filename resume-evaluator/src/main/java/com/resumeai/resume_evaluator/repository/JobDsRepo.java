    package com.resumeai.resume_evaluator.repository;

    import com.resumeai.resume_evaluator.model.JobDescription;
    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface JobDsRepo extends MongoRepository<JobDescription,String> {
        JobDescription findByJobTitle(String title);
    }

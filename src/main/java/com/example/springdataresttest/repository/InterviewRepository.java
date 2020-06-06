package com.example.springdataresttest.repository;

import com.example.springdataresttest.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(path = "interview", collectionResourceRel = "interview")
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    String PREFERRED_INTERVIEWERS_QUERY = "" +
            "SELECT id FROM interview WHERE " +
            "day = :weekday " +
            "AND slot = :slot " +
            "AND is_active = true " +
            "AND type IN (:type,'EITHER') " +
            "AND interview NOT IN " +
            "(SELECT interviewer_id FROM interview WHERE " +
            "date IN (:dates)) ";

    @Query(nativeQuery = true, value = PREFERRED_INTERVIEWERS_QUERY)
    List<String> getAvailableInterviewersWithPreferences(
            @Param("weekday") String weekday,
            @Param("slot") int slot,
            @Param("type") String type,
            @Param("dates") Date[] dates);
}

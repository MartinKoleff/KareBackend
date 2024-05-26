package com.koleff.kare.auth.controller;

import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/test")
@CrossOrigin("*")
public class AdminController {

    @GetMapping("/adminaccess")
    public String testAdminAccess() {
        return "Admin level access";
    }

    @GetMapping("/exercisedetailsdto")
    public ExerciseDetailsDto testExerciseDetailsDto() {
        return new ExerciseDetailsDto(
                0L,
                1L,
                "Pull up",
                "Back exercise",
                1,
                2,
                "snapshot.jpg",
                "youtube.com/videourl"
        );
    }
}

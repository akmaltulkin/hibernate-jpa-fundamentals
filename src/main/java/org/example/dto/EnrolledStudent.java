package org.example.dto;

import org.example.entities.joinsExample.Enrollment;
import org.example.entities.joinsExample.Student;

public record EnrolledStudent(

        Student student,
        Enrollment enrollment
) {
}

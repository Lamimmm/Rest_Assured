
package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "studentId",
    "firstName",
    "lastName",
    "batch",
    "joinDate",
    "birthDate",
    "password",
    "subject",
    "gender",
    "admissionNo",
    "major",
    "section",
    "contact",
    "company"
})
public class Student {

    @JsonProperty("studentId")
    private Integer studentId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("batch")
    private Integer batch;
    @JsonProperty("joinDate")
    private String joinDate;
    @JsonProperty("birthDate")
    private String birthDate;
    @JsonProperty("password")
    private String password;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("admissionNo")
    private String admissionNo;
    @JsonProperty("major")
    private String major;
    @JsonProperty("section")
    private String section;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("company")
    private Company company;
}

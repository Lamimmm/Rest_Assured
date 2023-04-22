
package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "companyId",
    "companyName",
    "title",
    "startDate",
    "address"
})
public class Company {

    @JsonProperty("companyId")
    private Integer companyId;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("title")
    private String title;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("address")
    private Address address;
}

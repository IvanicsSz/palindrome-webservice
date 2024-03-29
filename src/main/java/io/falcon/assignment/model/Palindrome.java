package io.falcon.assignment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@Entity
@Table(name = "palindrome")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class Palindrome {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @NotEmpty(message = "Please provide a content")
    @JsonProperty("content")
    private String userContent;

    @NotNull(message = "Please provide a timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("timestamp")
    private Date timeStamp;

    @JsonProperty("longest_palindrome_size")
    private Integer palindromeSize;

    public Palindrome(){};

    public Palindrome(String userContent, Date timeStamp){
        this.userContent = userContent;
        this.timeStamp = timeStamp;
    }

}
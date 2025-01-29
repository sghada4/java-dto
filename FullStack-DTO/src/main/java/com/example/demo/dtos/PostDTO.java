package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    private Long id;

    private String title;

    private String description;

    private UserDTO postedBy;

    private List<UserDTO> likes;

    private Date createdAt;

    private Date updatedAt;
}
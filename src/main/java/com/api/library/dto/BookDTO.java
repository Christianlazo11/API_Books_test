package com.api.library.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String gender;
    private Date yearOfPublication;
}

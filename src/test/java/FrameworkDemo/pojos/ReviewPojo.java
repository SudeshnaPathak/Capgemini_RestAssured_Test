package FrameworkDemo.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPojo {
    private Integer reviewId;
    private Integer shopperId;
    private String shopperName;
    private String heading;
    private String description;
    private Double rating;
    private LocalDateTime dateTime;

}

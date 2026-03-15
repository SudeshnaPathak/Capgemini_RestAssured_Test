package FrameworkDemo.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPojo {
    private Integer productId;
    private String name;
    private String title;
    private String description;
    private Double rating;
    private Double price;
    private Double offer;
    private String type;
    private String brand;
    private String category;
    private Integer merchantId;
    private Integer quantity;
    private String status;
    private String thumbnailURL;
    private List<String> productImageURLs;
    private List<String> searchTags;
    private List<ReviewPojo> reviews;
    private LocalDateTime createdDateTime;
    private String zoneId;
}

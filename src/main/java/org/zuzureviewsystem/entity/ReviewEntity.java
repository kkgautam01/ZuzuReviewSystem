package org.zuzureviewsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "hotel_id")
//    private HotelEntity hotelId;
//
//    @OneToOne
//    @JoinColumn(name = "provider_id")
//    private ProviderEntity providers;
//
//    @OneToOne
//    @JoinColumn(name = "reviewer_id")
//    private ReviewerEntity reviewers;

    private Long hotelId;
    private Long providerId;
    private Long reviewerId;
    private Long reviewId;

    private double rating;
    private String reviewComment;
    private String reviewTitle;
    private String reviewDate;
    private int translatedFrom;
    private int translatedTo;
    private String checkinMonthYear;


}

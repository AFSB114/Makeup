package com.makeupp.makeupp.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private product product;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(length = 300, nullable = false)
    private String comment;

    @Temporal(TemporalType.DATE)
    @Column(name = "review_date", nullable = false)
    private Date reviewDate;

    public review() {}

    public review(user user, product product, int rating, String comment, Date reviewDate) {
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public int getReviewId() { 
        return reviewId; }
        
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }

    public user getUser() { return user; }
    public void setUser(user user) { this.user = user; }

    public product getProduct() { return product; }
    public void setProduct(product product) { this.product = product; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Date getReviewDate() { return reviewDate; }
    public void setReviewDate(Date reviewDate) { this.reviewDate = reviewDate; }
}

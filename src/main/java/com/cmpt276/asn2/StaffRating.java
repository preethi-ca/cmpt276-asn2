package com.cmpt276.asn2;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity 
@Table(name = "staff_ratings")
public class StaffRating {
    // id (auto)
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // name (required, length constraints)
    @NotBlank(message = "Name required")
    @Size(max=100, message="Name cannot exceed 100 characters. Try again.")
    private String name;

    // email (required, must be valid email format; must be unique OR explain your rule)
    @NotBlank(message = "Email required")
    @Email(message = "Valid email address required. Try again.")
    private String email;

    // roleType (enum: TA, PROF, INSTRUCTOR, STAFF, etc.)
    @NotNull(message = "Role type required. Try again.")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // clarity (integer 1–10)
    @NotNull(message = "Clarity required")
    @Min(value=1, message="Clarity must be atleast 1. Try again.")
    @Max(value=10, message="Clarity cannot exceed 10. Try again.")
    private Integer clarity;

    // niceness (integer 1–10)
    @NotNull(message = "Niceness required")
    @Min(value=1, message="Niceness must be atleast 1. Try again.")
    @Max(value=10, message="Niceness cannot exceed 10. Try again.")    
    private Integer niceness;

    // knowledgeableScore (integer 1–10)
    @NotNull(message = "Knowledgeable score required")
    @Min(value=1, message="Knowledgeable score must be atleast 1. Try again.")
    @Max(value=10, message="Knowledgeable score cannot exceed 10. Try again.")    
    private Integer knowledgeableScore;

    // comment (optional, length limit)
    @Size(max=100, message="Comment cannot exceed 100 characters. Try again.")
    private String comment;

    public StaffRating(String name, String email, RoleType roleType, int clarity, int niceness, int knowledgeableScore, String comment) {
        this.name = name;
        this.email = email;
        this.roleType = roleType;
        this.clarity = clarity;
        this.niceness = niceness;
        this.knowledgeableScore = knowledgeableScore;
        this.comment = comment;
    }

    // getters and setters
    public StaffRating() {
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public int getClarity() {
        return clarity;
    }

    public int getNiceness() {
        return niceness;
    }

    public int getKnowledgeableScore() {
        return knowledgeableScore;
    }
    
    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public void setNiceness(int niceness) {
        this.niceness = niceness;
    }

    public void setKnowledgeableScore(int knowledgeableScore) {
        this.knowledgeableScore = knowledgeableScore;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // overall score (aggregate or average of clarity, niceness, and knowledgeableScore) 
    public double getOverallScore() {
        double overall_score = (clarity + niceness + knowledgeableScore) / 3.0;
        return overall_score;
    }

    // Get the display title based on the staff member's role
    public String getDisplayTitle() {
        StaffMemberProfile profile;
    
        switch (roleType) {
            case TA:
                profile = new TAProfile();
                break;
            case PROF:
                profile = new PROFProfile();
                break;
            case INSTRUCTOR:
                profile = new INSTRUCTORProfile();
                break;
            case STAFF:
                profile = new STAFFProfile();
                break;
            default:
                return "Role is unknown.";
        }
        // polymorphism
        return profile.displayTitle();
    }
}
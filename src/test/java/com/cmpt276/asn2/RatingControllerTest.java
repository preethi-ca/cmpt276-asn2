package com.cmpt276.asn2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StaffRatingRepository ratingRepo;

    // test #1: home page contains the ratings
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("ratings"));
    }

    // test #2: valid rating gets created
    @Test
    public void testCreateRating() throws Exception {
        mockMvc.perform(post("/ratings")
                .param("name", "Preethi Chidambaram")
                .param("email", "pca115@sfu.ca")
                .param("roleType", "TA")
                .param("clarity", "10")
                .param("niceness", "9")
                .param("knowledgeableScore", "8")
                .param("comment", "The best TA I've ever had!"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    // test #3: empty name gets rejected
    @Test
    public void testInvalidCreateRating() throws Exception {
        mockMvc.perform(post("/ratings")
                .param("name", "")
                .param("email", "pca115@sfu.ca")
                .param("roleType", "TA")
                .param("clarity", "10")
                .param("niceness", "9")
                .param("knowledgeableScore", "8")
                .param("comment", "The best TA I've ever had!"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeHasFieldErrors("rating", "name"));
    }

    // test #4: rating gets saved to/retrieved from the database
    @Test
    @Transactional
    public void testSaveAndRetrieveRating() {
        StaffRating rating = new StaffRating(
                "Preethi Chidambaram",
                "pca115@sfu.ca",
                RoleType.TA,
                10,
                9,
                8,
                "The best TA I've ever had!");

        StaffRating savedRating = ratingRepo.save(rating);

        StaffRating retrievedRating = ratingRepo.findById(savedRating.getId()).orElse(null);

        assertNotNull(retrievedRating);
        assertEquals("Preethi Chidambaram", retrievedRating.getName());
    }

    // test #5: rating gets updated through the controller
    @Test
    @Transactional
    public void testUpdateRatingController() throws Exception {
        StaffRating rating = new StaffRating(
                "Preethi Chidambaram",
                "pca115@sfu.ca",
                RoleType.TA,
                10,
                9,
                8,
                "The best TA I've ever had!");

        StaffRating savedRating = ratingRepo.save(rating);

        mockMvc.perform(post("/ratings/update")
                .param("id", String.valueOf(savedRating.getId()))
                .param("name", "Preethi Chidambaram")
                .param("email", "pca115@sfu.ca")
                .param("roleType", "TA")
                .param("clarity", "9")
                .param("niceness", "9")
                .param("knowledgeableScore", "8")
                .param("comment", "The best TA I've ever had!"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ratings/detail?id=" + savedRating.getId()));

        StaffRating updatedRating = ratingRepo.findById(savedRating.getId()).orElse(null);

        assertNotNull(updatedRating);
        assertEquals(9, updatedRating.getClarity());
    }

    // test #6: rating gets deleted through the controller
    @Test
    @Transactional
    public void testDeleteRatingController() throws Exception {
        StaffRating rating = new StaffRating(
                "Preethi Chidambaram",
                "pca115@sfu.ca",
                RoleType.TA,
                10,
                9,
                8,
                "The best TA I've ever had!");

        StaffRating savedRating = ratingRepo.save(rating);
        int id = savedRating.getId();

        mockMvc.perform(post("/ratings/delete")
                .param("id", String.valueOf(id)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertFalse(ratingRepo.findById(id).isPresent());
    }
}
package com.cmpt276.asn2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

@Controller
public class RatingController {
    @Autowired
    private StaffRatingRepository ratingRepo;

    // Index page: list all staff ratings (summary view)
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("ratings", ratingRepo.findAll());
        return "index";
    }

    // Detail page: shows full attributes
    @GetMapping("/ratings/detail")
    public String showRatingDetails(@RequestParam Map<String, String> params, Model model) {
        int id = Integer.parseInt(params.get("id"));
        // get all ratings from database
        List<StaffRating> ratings = ratingRepo.findAll();
        // end of database call
        for (StaffRating rating : ratings) {
            if (rating.getId() == id) {
                model.addAttribute("rating", rating);
                return "detail";
            }
        }
        return "redirect:/";
    }

    // Edit page: form to edit an existing staff rating
    @GetMapping("/ratings/edit")
    public String showEditForm(@RequestParam Map<String, String> params, Model model) {
        int id = Integer.parseInt(params.get("id"));
        // get all ratings from database
        List<StaffRating> ratings = ratingRepo.findAll();
        // end of database call
        for (StaffRating rating : ratings) {
            if (rating.getId() == id) {
                model.addAttribute("rating", rating);
                return "edit";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/ratings/new")
    public String showSubmitRatingForm(Model model) {
        model.addAttribute("rating", new StaffRating());
        return "create";
    }

    // add rating
    @PostMapping("/ratings")
    public String addRating(@Valid @ModelAttribute("rating") StaffRating rating, BindingResult results) {
        if (results.hasErrors()) {
            return "create";
        }
        ratingRepo.save(rating);
        return "redirect:/";
    }

    // update rating
    @PostMapping("/ratings/update")
    public String updateRating(@Valid @ModelAttribute("rating") StaffRating rating, BindingResult results) {
        if (results.hasErrors()) {
            return "edit";
        }

        // get all ratings from database
        List<StaffRating> ratings = ratingRepo.findAll();
        // end of database call
        for (StaffRating existingRating : ratings) {
            if (existingRating.getId() == rating.getId()) {
                ratingRepo.save(rating);
                return "redirect:/ratings/detail?id=" + rating.getId();
            }
        }
        return "redirect:/";
    }

    // delete rating
    @PostMapping("/ratings/delete")
    public String deleteRating(@RequestParam Map<String, String> params) {
        int id = Integer.parseInt(params.get("id"));
        // get all ratings from database
        List<StaffRating> ratings = ratingRepo.findAll();
        // end of database call
        for (StaffRating rating : ratings) {
            if (rating.getId() == id) {
                ratingRepo.delete(rating);
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
}
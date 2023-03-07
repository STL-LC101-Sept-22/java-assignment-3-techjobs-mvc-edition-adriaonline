package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
//@RequestParamString columns, @RequestParam(name = "searchTerm", required = false) String Search
            ArrayList<Job> jobs;

        // If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method from JobData.
            if (searchTerm.equals("all") || searchTerm.equals("")){
                jobs = JobData.findAll();
//                model.addAttribute("title", "All Jobs");

        // Otherwise, send the search information to findByColumnAndValue.
            } else {
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            }

            model.addAttribute("columns", columnChoices);

            // Pass ListController.columnChoices into the view, as the existing search handler does.
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);

        // In either case, store the results in a jobs ArrayList.
            model.addAttribute("jobs", jobs);

            return "search";

        }
}

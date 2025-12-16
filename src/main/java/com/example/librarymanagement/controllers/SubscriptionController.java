package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.SubscriptionDto;
import com.example.librarymanagement.model.Subscription;
import com.example.librarymanagement.service.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SubscriptionController {

    private final SubscriptionService service;
    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping("subscription")
    public String getSubscription(Model model){
        model.addAttribute("dto", new SubscriptionDto("", 0.0, 0));

        List<Subscription> subscriptionList = service.getALL();
        model.addAttribute("subscriptions", subscriptionList);

        return "subscription";
    }

    @PostMapping("subscription")
    public String addSubscription(@ModelAttribute SubscriptionDto dto){
        System.out.println("Test: " + dto);

        Subscription subscription = service.save(dto.toSubscription());
        if(subscription == null){
            return "redirect:subscription?subscription=failed";
        } else {
            System.out.println("Added new subscription");
            return "redirect:subscription";
        }
    }

    @PostMapping("subscription/update")
    public String updateSubscription(@ModelAttribute SubscriptionDto dto){
        System.out.println("Update: " + dto);

        service.update(dto.toSubscription());
        return "redirect:/subscription";
    }

    @PostMapping("subscription/{title}")
    public String deleteSubscription(@PathVariable String title){
        if(service.hasNoChild(title)){
            service.delete(title);
            return "redirect:/subscription";
        }
        return "redirect:/subscription?child=true";
    }

}
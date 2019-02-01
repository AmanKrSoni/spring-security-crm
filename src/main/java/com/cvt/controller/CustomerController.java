package com.cvt.controller;

import com.cvt.model.Customer;
import com.cvt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public String showCustomersList(Model model){

        //get customers from service
        List<Customer> customerList=customerService.getCustomers();

        //add customers to model
        model.addAttribute("customers",customerList);
        return "customer-list";
    }

    @GetMapping("/showFormForAdd")
    @PreAuthorize("hasPermission(#customer ,'READ_ONLY') or hasAnyRole('ADMIN') ")
    public String showFormForAdd(Model model,Customer customer){
        model.addAttribute("customer",new Customer());
        return "customer-add";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("showFormForUpdate")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String showFormForUpdate(@RequestParam("customerId") int id,Model model){

        //get customer from service
        Customer customer=customerService.getCustomer(id);

        //set customer as model attribute
        model.addAttribute("customer",customer);
        //send over to form
        return "customer-add";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String deleteCustomer(@RequestParam("customerId") int id){
        customerService.deleteCustomer(id);
        return "redirect:/customers/list";
    }




}

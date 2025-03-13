package app.web;

import app.transaction.model.Transaction;
import app.transaction.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @GetMapping
    public ModelAndView getAllTransactions(){

        List<Transaction> transactions = transactionService.getAllByOwnerId(UUID.fromString("fc815143-d8a2-41d2-b1d3-8fff90dede74"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transactions");
        modelAndView.addObject("transactions", transactions);

        return modelAndView;

    }
}

package app.web;

import app.transaction.model.Transaction;
import app.transaction.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView getAllTransactions() {

        List<Transaction> transactions = transactionService.getAllByOwnerId(UUID.fromString("882f7b7d-52c6-4f42-858c-9ac34bcf23ea"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transactions");
        modelAndView.addObject("transactions", transactions);

        return modelAndView;

    }

    @GetMapping("/{id}")
    public ModelAndView getTransactionById(){

        return null;
    }
}

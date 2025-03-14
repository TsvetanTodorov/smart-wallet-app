package app.web;

import app.transaction.model.Transaction;
import app.user.model.User;
import app.user.service.UserService;
import app.wallet.service.WalletService;
import app.web.dto.TransferRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/transfers")
public class TransferController {

    private final UserService userService;
    private final WalletService walletService;

    @Autowired
    public TransferController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }


    @GetMapping
    public ModelAndView getTransferPage(){

        User user = userService.getById(UUID.fromString("c9073876-e882-4405-a0cc-d2e342444a8f"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transfer");
        modelAndView.addObject("user", user);
        modelAndView.addObject("transferRequest", TransferRequest.builder().build());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView initiateTransfer(@Valid TransferRequest transferRequest, BindingResult bindingResult){

        User user = userService.getById(UUID.fromString("c9073876-e882-4405-a0cc-d2e342444a8f"));

        if(bindingResult.hasErrors()){

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("transfer");
            modelAndView.addObject("user", user);
            modelAndView.addObject("transferRequest", transferRequest);

            return modelAndView;
        }

        Transaction transaction = walletService.transferFund();


        return new ModelAndView("redirect:/transactions");

    }
}

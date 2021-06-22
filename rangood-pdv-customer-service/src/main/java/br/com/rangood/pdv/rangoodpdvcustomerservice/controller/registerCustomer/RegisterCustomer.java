package br.com.rangood.pdv.rangoodpdvcustomerservice.controller.registerCustomer;

import br.com.rangood.pdv.rangoodpdvcustomerservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvcustomerservice.controller.ValidationErrorHandler;
import br.com.rangood.pdv.rangoodpdvcustomerservice.feignclient.userfeignservice.AddUserResquestModel;
import br.com.rangood.pdv.rangoodpdvcustomerservice.model.Customer;
import br.com.rangood.pdv.rangoodpdvcustomerservice.service.CustomerService;
import br.com.rangood.pdv.rangoodpdvcustomerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class RegisterCustomer {

    private final CustomerService customerService;
    private final UserService userService;

    public RegisterCustomer(@Autowired CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity execute(@RequestBody @Valid AddCustomerRequestModel requestClient, final BindingResult bindingResult, Errors errors){
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorHandler.fromBindingErrors(bindingResult));
        }

        Customer customer = customerService.getCustomerByEmail(requestClient.getEmail());
        if (customer != null) {
            return RestErrorHandler.ResponseEntityErrorBuilder
                    .conflited(new String[]{"email", "Email already exist with another register"});
        }
        AddUserResquestModel addUserResquestModel = new AddUserResquestModel();
        addUserResquestModel.setName(requestClient.getName());
        addUserResquestModel.setUsername(requestClient.getEmail());
        addUserResquestModel.setPassword(requestClient.getPassword());
        final boolean isRegistered = userService.registerUserAccount(addUserResquestModel);

        if (!isRegistered) {
            return ResponseEntity.internalServerError().build();
        }

        customer = new Customer(
                requestClient.getName(),
                requestClient.getBirthDate(),
                requestClient.getPhoneNumber(),
                requestClient.getEmail()
        );
        customerService.addCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

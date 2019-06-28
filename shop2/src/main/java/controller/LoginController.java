package controller;

import logic.ShopService;
import logic.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import util.LoginValidator;

import javax.servlet.http.HttpSession;

public class LoginController {
    private ShopService shopService;
    private LoginValidator loginValidator;

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    public void setLoginValidator(LoginValidator loginValidator) {
        this.loginValidator = loginValidator;
    }

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String loginForm(Model model) {
        model.addAttribute(new User());
        return "login"; // View 리턴
    }

//    @ModelAttribute
//    public User getUser() {
//        return new User();
//    }

    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ModelAndView login(User user, BindingResult bindingResult, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        loginValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            mav.getModel().putAll(bindingResult.getModel());
            return mav;
        }

        try {
            User dbuser = shopService.getUser(user.getUserId());

            if (user.getPassword().equals(dbuser.getPassword())) {
                session.setAttribute("loginUser", dbuser);
            } else {
                bindingResult.reject("error.login.password");
                mav.getModel().putAll(bindingResult.getModel());
                return mav;
            }
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("error.login.id");
            mav.getModel().putAll(bindingResult.getModel());

            return mav;
        }

        mav.setViewName("loginSuccess");
        return mav;
    }
}

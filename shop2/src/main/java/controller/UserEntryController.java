package controller;

import logic.ShopService;
import logic.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import util.UserValidator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserEntryController {
    private ShopService shopService;
    private UserValidator userValidator;

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String userEntryForm() {
        return "userEntry"; // View 리턴 : /WEB-INF/view/userEntry.jsp가 뷰
    }

    @ModelAttribute
    public User getUser() {
        return new User();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView userEntry(User user, BindingResult bindingResult) {
        // user : user 객체의 프로퍼티와, 파라미터 정보를 비교하여 자동으로 값이 저장됨.
        ModelAndView mav = new ModelAndView(); // view 설정이 안되는 경우 userEntry.shop 이므로 userEntry 값이 기본값임.
        // 유효성 검증
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            mav.getModel().putAll(bindingResult.getModel());
            return mav;
        }

        try {
            shopService.insertUser(user);
            mav.addObject("user", user);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("error.duplicate.user");
            mav.getModel().putAll(bindingResult.getModel());

            return mav;
        }

        mav.setViewName("userEntrySuccess");
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // allowEmpty : true 이면 입력 안해도 됨.
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
    }
}

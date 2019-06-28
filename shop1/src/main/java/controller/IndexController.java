package controller;

import logic.Item;
import logic.ShopService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class IndexController {
    private ShopService shopService;
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

   @RequestMapping
    public ModelAndView itemList() {
        List<Item> itemList = shopService.getItemList();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("itemList", itemList);
        return mav;
    }
}

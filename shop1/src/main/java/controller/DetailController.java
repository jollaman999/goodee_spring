package controller;

import logic.Item;
import logic.ShopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class DetailController {
    private ShopService shopService;

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping
    public ModelAndView detailItem(Integer id) {
        Item item = shopService.getItemById(id);
        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("item", item);
        return mav;
    }
}

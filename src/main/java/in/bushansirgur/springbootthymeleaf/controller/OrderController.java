package in.bushansirgur.springbootthymeleaf.controller;


import in.bushansirgur.springbootthymeleaf.dao.OrderRepository;
import in.bushansirgur.springbootthymeleaf.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository eRepo;

//
//    @GetMapping({"/user/cart"})
//    public ModelAndView getAllOrder(Long user_id) {
//        ModelAndView mav = new ModelAndView("cart");
//        mav.addObject("orders", eRepo.findAllById(user_id));// how to get the user_id and then list all the orders to cart?
//        return mav;
//    }
//

//    @GetMapping("/user/addorder")
//    public ModelAndView addOrder() {
//        ModelAndView mav = new ModelAndView("add-order");
//        Order newOrder = new Order();
//        mav.addObject("order", newOrder);
//        return mav;
//    }


    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute Order order) {
        eRepo.save(order);
        return "redirect:/index";
    }








}

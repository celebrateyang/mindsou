package com.mindsou.controller;

import com.mindsou.vo.Items;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 竹庆 on 2019/5/21.
 */
@Controller
@RequestMapping("item")
public class ItemController {

    @RequestMapping("/goitems")
    public String goItem(){
        return "item/items";
    }
    @RequestMapping(value = "/goitems",method = RequestMethod.POST)
    public String doItem(@Validated Items items, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();

            for (ObjectError allError : allErrors) {
                System.out.println("allError=>"+allError);
                System.out.println("default message=>"+allError.getDefaultMessage());
            }
            model.addAttribute("allErrors",allErrors);
        }
        System.out.println("name=>"+items.getName());
        return "item/items";
    }

}

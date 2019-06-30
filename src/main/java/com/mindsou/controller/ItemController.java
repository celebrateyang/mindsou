package com.mindsou.controller;

import com.mindsou.vo.Items;
import com.mindsou.vo.Qiyue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String doItem(@Validated Items items, BindingResult bindingResult, Model model,Integer[] haha){
        for (Integer ha : haha) {
            System.out.println("ha=>"+ha);
        }

        System.out.println("=========================");
        System.out.println(haha);
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


    @RequestMapping(value = "getObjectData",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getObjectData(){
        Qiyue qiyue = new Qiyue();
        qiyue.setId(12);
        qiyue.setName("zhonggong");
        qiyue.setOffice("office");
        qiyue.setPosition("rongzi");
        qiyue.setSalary(1234);
        qiyue.setExtn(245);
        Qiyue qiyue1 = new Qiyue();
        qiyue.setId(13);
        qiyue.setName("puli");
        qiyue.setOffice("feewfe");
        qiyue.setPosition("fwef");
        qiyue.setSalary(8888);
        qiyue.setExtn(7777);
        List<Qiyue> qiyues = new ArrayList<>();
        qiyues.add(qiyue);
        qiyues.add(qiyue1);
        Map mapdata = new HashMap();
        mapdata.put("data",qiyues);

        return mapdata;
    }

}

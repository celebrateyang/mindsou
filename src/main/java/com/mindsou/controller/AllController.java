package com.mindsou.controller;

import com.mindsou.vo.All;
import com.mindsou.vo.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.spi.LocaleServiceProvider;

/**
 * Created by 竹庆 on 2019/5/30.
 */
@Controller
@RequestMapping("all")
public class AllController {
    @ModelAttribute("all")
    public All initValue(){
        All all = new All();
        all.setAllName("bamboo");
        Items items1 = new Items(1,"shoujie",45.5,"pic",new Date()) ;
        Items items2 = new Items(2,"diannao",78.9,"pic",new Date()) ;
        List<Items> itemses = new ArrayList<>();
        itemses.add(items1);
        itemses.add(items2);
        all.setItemses(itemses);
        return all;
    }
    @RequestMapping("/index")
    public String getAll(){
        return "item/all";
    }

    @RequestMapping(value = "/jieshou",method = RequestMethod.POST)
    public String jieshou(All all){
        for (String s : all.getBox()) {
            System.out.println("s=====>"+s);
        }

        return "item/all";
    }
}

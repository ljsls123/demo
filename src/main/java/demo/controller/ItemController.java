package demo.controller;

import java.util.ArrayList;
import java.util.List;

import demo.model.Item;
import demo.model.UserComment;
import demo.service.ItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping(value = "/userDetail")
    public String getDetail(@Param("id") int id, Model model) {
        Item item = itemService.detail(id).getResult();
        model.addAttribute("item", item);
        List<UserComment> list = new ArrayList<>();
        if (itemService.comment(item.getId()) != null) {
            list = itemService.comment(item.getId());
        }
        model.addAttribute("list", list);
        return "userItemDetail";
    }

    @GetMapping(value = "/workerDetail")
    public String getWorkerDetail(@Param("id") int id, Model model) {
        Item item = itemService.detail(id).getResult();
        model.addAttribute("item", item);
        return "workerItemDetail";
    }
}

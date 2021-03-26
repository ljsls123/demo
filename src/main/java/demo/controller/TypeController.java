package demo.controller;

import java.util.List;

import demo.model.Type;
import demo.model.response.ResponseResult;
import demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/create")
    public String toCreateType() {
        return "workerCreateType";
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseResult<Void> createType(String name) {
        return typeService.createType(name);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseResult<Void> deleteType(int id) {
        return typeService.deleteType(id);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseResult<Void> updateType(@RequestParam("id") int id, @RequestParam("name") String name) {
        return typeService.updateType(id, name);
    }

    @GetMapping("/get")
    public String getTypes(@RequestParam("page") int page, Model model) {
        List<Type> typeList = typeService.getTypes(page).getResult();
        model.addAttribute("typeList", typeList);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", typeService.getTotal().getResult());
        return "workerGetTypes";
    }

    @GetMapping("/getUpdate")
    public String getUpdate(int id, Model model) {
        model.addAttribute("id", id);
        return "workerUpdateType";
    }
}

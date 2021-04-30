package demo.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import demo.dto.LoginDTO;
import demo.dto.RegisterDTO;
import demo.dto.UpdatePasswordDTO;
import demo.model.Menu;
import demo.model.response.ResponseResult;
import demo.service.UserAuthService;
import demo.util.Utils;
import demo.vo.GetOrdersVO;
import demo.vo.LoginVO;
import demo.vo.RegisterVO;
import demo.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;

    @GetMapping(value = "/register")
    public String toRegister() {
        return "register";
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseResult<RegisterVO> register(RegisterDTO registerDTO) {
        return userAuthService.register(registerDTO);
    }

    @GetMapping(value = "/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseResult<LoginVO> login(LoginDTO loginDTO, HttpSession session) {
        LoginVO loginVO = userAuthService.login(loginDTO).getResult();
        List<Menu> menuList = userAuthService.getMenu(loginVO.getUserId()).getResult();
        session.setAttribute("menuList", new Gson().toJson(Utils.getMenuJson(menuList)));
        session.setAttribute("user", loginVO);
        return ResponseResult.success(loginVO);
    }

    @GetMapping(value = "/updatePassword")
    public String toUpdatePassword() {
        return "updatePassword";
    }

    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public ResponseResult<Void> updatePassword(UpdatePasswordDTO updatePasswordDTO, HttpSession session) {
        LoginVO user = (LoginVO) session.getAttribute("user");
        return userAuthService.updatePassword(updatePasswordDTO, user.getEmail());
    }

    @GetMapping(value = "/main")
    public String toMain() {
        return "userMain";
    }

    @GetMapping(value = "/searchItems")
    public String searchItems(@RequestParam(value = "page") int page, Model model, HttpSession session) {
        SearchItemsVO searchItemsVO = userAuthService.searchItems(page, 5).getResult();
        model.addAttribute("searchItemVO", searchItemsVO);
        model.addAttribute("page", page);
        return "userSearchItems";
    }

    @GetMapping(value = "/searchItems/price")
    public String searchItemsByPrice(@RequestParam(value = "page") int page, @RequestParam(value = "price", defaultValue = "1111111111") String price, Model model, HttpSession session) {
        SearchItemsVO searchItemsVO = userAuthService.searchItemsByPrice(page, 5, price).getResult();
        model.addAttribute("searchItemVO", searchItemsVO);
        model.addAttribute("page", page);
        model.addAttribute("price", price);
        return "userSearchItemsByPrice";
    }

    @GetMapping(value = "/searchItems/type")
    public String searchItemsByType(@RequestParam(value = "page") int page, @RequestParam(value = "type", defaultValue = "") String type, Model model, HttpSession session) {
        SearchItemsVO searchItemsVO = userAuthService.searchItemsByType(page, 5, type).getResult();
        model.addAttribute("searchItemVO", searchItemsVO);
        model.addAttribute("page", page);
        model.addAttribute("type", type);
        return "userSearchItemsByType";
    }

    @GetMapping(value = "/buy")
    @ResponseBody
    public ResponseResult<Void> buy(@RequestParam(value = "itemId") int itemId, HttpSession session) {
        LoginVO user = (LoginVO) session.getAttribute("user");
        userAuthService.buy(user.getUserId(), itemId);
        return ResponseResult.success();
    }

    @GetMapping(value = "/getOrders")
    public String getOrders(Model model, HttpSession session) {
        LoginVO user = (LoginVO) session.getAttribute("user");
        GetOrdersVO getOrdersVO = userAuthService.getOrders(user.getUserId()).getResult();
        model.addAttribute("getOrdersVO", getOrdersVO);
        return "userGetOrders";
    }

    @GetMapping(value = "/comment")
    public String comment(@Param("itemId") int itemId, @Param("detail") String detail, Model model, HttpSession session) {
        LoginVO user = (LoginVO) session.getAttribute("user");
        userAuthService.comment(user.getUserId(), itemId, detail);
        String s = "redirect:/item/userDetail?id=" + itemId;
        return s;
    }
}

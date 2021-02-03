package demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import demo.dto.CreateItemDTO;
import demo.model.response.ResponseResult;
import demo.service.UserInfoService;
import demo.service.WorkerService;
import demo.util.UploadUtil;
import demo.vo.GetItemVO;
import demo.vo.LoginVO;
import demo.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/worker")
public class WorkerController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private WorkerService workerService;

    @GetMapping(value = "/getUserInfo")
    public String getUserInfo(HttpSession session, Model model) {
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        String email = loginVO.getEmail();
        UserInfoVO userInfoVO = userInfoService.getUserInfo(email).getResult();
        model.addAttribute("userInfoVo", userInfoVO);
        return "workerGetUserInfo";
    }

    @GetMapping(value = "/main")
    public String toMain() {
        return "workerMain";
    }

    @GetMapping(value = "/updatePassword")
    public String toUpdatePassword() {
        return "workerUpdatePassword";
    }

    @GetMapping(value = "/updateUserInfo")
    public String toUpdateUserInfo() {
        return "workerUpdateUserInfo";
    }

    @GetMapping(value = "/createItem")
    public String toCreateItem() {
        return "workerCreateItem";
    }

    @PostMapping(value = "/createItem")
    public String createItem(CreateItemDTO createItemDTO, MultipartFile img, HttpSession session, HttpServletRequest request) {
        String path = "D:\\IdeaProject\\demo\\src\\main\\resources\\img";
        String imageName = UploadUtil.upload(img, path);
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        workerService.createItem(createItemDTO, loginVO.getUserId(), imageName);
        return "redirect:/worker/main";
    }

    @GetMapping(value = "/getItems")
    public String getItems(@RequestParam("page") int page, Model model, HttpSession session) {
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        GetItemVO getItemVO = workerService.getItems(page, 5, loginVO.getUserId()).getResult();
        model.addAttribute("getItemVO", getItemVO);
        model.addAttribute("page", page);
        return "workerGetItems";
    }

    @PostMapping(value = "/item/online")
    @ResponseBody
    public ResponseResult<Void> itemOnline(int id) {
        return workerService.itemOnline(id);
    }

    @PostMapping(value = "/item/offline")
    @ResponseBody
    public ResponseResult<Void> itemOffline(int id) {
        return workerService.itemOffline(id);
    }
}

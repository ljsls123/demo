package demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import demo.dto.CreateItemDTO;
import demo.model.response.ResponseResult;
import demo.service.UserInfoService;
import demo.service.WorkerService;
import demo.vo.GetItemVO;
import demo.vo.GetOrdersVO;
import demo.vo.LoginVO;
import demo.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
    public String createItem(CreateItemDTO createItemDTO, MultipartFile img, HttpSession session, HttpServletRequest request) throws IOException {
        //把图片保存在某个路径
        String uploadFolder = request.getServletContext().getRealPath("/upload");
        File uploadFolderFile = new File(uploadFolder);
        if (!uploadFolderFile.exists()) {
            uploadFolderFile.mkdirs();
        }
        //文件
        String suffix = img.getOriginalFilename().split("\\.")[1];
        String fileName = UUID.randomUUID().toString() + "." + suffix;
        String totalPath = uploadFolder + "\\" + fileName;
        FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(totalPath));
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        String imgURL = "http://localhost:8080/upload/" + fileName;
        workerService.createItem(createItemDTO, loginVO.getUserId(), imgURL);
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

    @GetMapping(value = "/getOrders")
    public String getOrders(Model model, HttpSession session) {
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        GetOrdersVO getOrdersVO = workerService.getOrders(loginVO.getUserId()).getResult();
        model.addAttribute("getOrdersVO", getOrdersVO);
        return "workerGetOrders";
    }

    @GetMapping(value = "/setOrders")
    public String setOrders(@RequestParam(value = "status") String status, @RequestParam(value = "orderId") int orderId, Model model, HttpSession session) {
        workerService.setOrders(orderId, status);
        return "redirect:/worker/getOrders";
    }
}

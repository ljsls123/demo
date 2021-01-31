package demo.controller;

import javax.servlet.http.HttpSession;

import demo.dto.UpdateUserInfoDTO;
import demo.model.response.ResponseResult;
import demo.service.UserInfoService;
import demo.vo.LoginVO;
import demo.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/getUserInfo")
    public String getUserInfo(HttpSession session, Model model) {
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        String email = loginVO.getEmail();
        UserInfoVO userInfoVO = userInfoService.getUserInfo(email).getResult();
        model.addAttribute("userInfoVo", userInfoVO);
        return "getUserInfo";
    }

    @GetMapping(value = "/updateUserInfo")
    public String toUpdateUserInfo() {
        return "updateUserInfo";
    }

    @PostMapping(value = "/updateUserInfo")
    @ResponseBody
    public ResponseResult<Void> updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO, HttpSession session) {
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        String email = loginVO.getEmail();
        return userInfoService.updateUserInfo(updateUserInfoDTO, email);
    }
}

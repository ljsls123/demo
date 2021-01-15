package demo.controller;

import javax.servlet.http.HttpSession;

import demo.dto.UpdateUserInfoDTO;
import demo.model.response.ResponseResult;
import demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vo.LoginVO;
import vo.UserInfoVO;

@Controller
@RequestMapping(value = "/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/getUserInfo")
    public ResponseResult<UserInfoVO> getUserInfo(HttpSession session) {
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        String email = loginVO.getEmail();
        return userInfoService.getUserInfo(email);
    }

    @PostMapping(value = "/updateUserInfo")
    public ResponseResult<Void> updateUserInfo(@RequestBody UpdateUserInfoDTO updateUserInfoDTO, HttpSession session) {
        LoginVO loginVO = (LoginVO) session.getAttribute("user");
        String email = loginVO.getEmail();
        return userInfoService.updateUserInfo(updateUserInfoDTO, email);
    }
}

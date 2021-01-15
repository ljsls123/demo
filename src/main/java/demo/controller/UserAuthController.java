package demo.controller;

import javax.servlet.http.HttpSession;

import demo.dto.LoginDTO;
import demo.dto.RegisterDTO;
import demo.dto.UpdatePasswordDTO;
import demo.model.response.ResponseResult;
import demo.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vo.LoginVO;
import vo.RegisterVO;

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
    public ResponseResult<LoginVO> login(LoginDTO loginDTO, HttpSession session) {
        LoginVO loginVO = userAuthService.login(loginDTO).getResult();
        session.setAttribute("user", loginVO);
        return ResponseResult.success(loginVO);
    }

    @GetMapping(value = "/updatePassword")
    public String toUpdatePassword() {
        return "updatePassword";
    }

    @PostMapping(value = "/updatePassword")
    public ResponseResult<Void> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        return userAuthService.updatePassword(updatePasswordDTO);
    }
}

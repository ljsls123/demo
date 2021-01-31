package demo.controller;

import javax.servlet.http.HttpSession;

import demo.dto.LoginDTO;
import demo.dto.RegisterDTO;
import demo.dto.UpdatePasswordDTO;
import demo.model.response.ResponseResult;
import demo.service.UserAuthService;
import demo.vo.LoginVO;
import demo.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        System.out.println("123123123");
        LoginVO loginVO = userAuthService.login(loginDTO).getResult();
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
}

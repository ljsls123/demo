package demo.service;

import demo.dto.LoginDTO;
import demo.dto.RegisterDTO;
import demo.dto.UpdatePasswordDTO;
import demo.model.response.ResponseResult;
import demo.vo.LoginVO;
import demo.vo.RegisterVO;

public interface UserAuthService {
    ResponseResult<RegisterVO> register(RegisterDTO registerDTO);

    ResponseResult<LoginVO> login(LoginDTO loginDTO);

    ResponseResult<Void> updatePassword(UpdatePasswordDTO updatePasswordDTO, String email);
}

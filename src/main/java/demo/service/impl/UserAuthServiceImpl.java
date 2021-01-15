package demo.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import demo.constant.ErrorCode;
import demo.dto.LoginDTO;
import demo.dto.RegisterDTO;
import demo.dto.UpdatePasswordDTO;
import demo.exception.BizException;
import demo.mapper.UserMapper;
import demo.model.User;
import demo.model.response.ResponseResult;
import demo.service.UserAuthService;
import demo.util.EncryptUtil;
import demo.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import vo.LoginVO;
import vo.RegisterVO;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult<RegisterVO> register(RegisterDTO registerDTO) {
        if (ValidateUtil.emailFormatCheck(registerDTO.getEmail())) {
            throw new BizException(ErrorCode.EMAIL_FORMAT_ERROR,
                    String.format("input email: %s format is wrong when Register.", registerDTO.getEmail()));
        }
        if (ValidateUtil.passwordFormatCheck(registerDTO.getPassword())) {
            throw new BizException(ErrorCode.PASSWORD_FORMAT_ERROR,
                    String.format("input password: %s format is wrong when Register.", registerDTO.getPassword()));
        }
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        String salt = EncryptUtil.generateSalt();
        user.setPassword(EncryptUtil.encryptPassword(registerDTO.getPassword(), salt));
        user.setSalt(salt);
        user.setAddress(registerDTO.getAddress());
        user.setUserType(registerDTO.getType());
        user.setNickname(registerDTO.getNickname());
        LocalDateTime date = LocalDateTime.now(ZoneOffset.UTC);
        user.setCreateAt(date);
        user.setUpdateAt(date);
        try {
            userMapper.insertUser(user);
        } catch (DuplicateKeyException e) {
            throw new BizException(ErrorCode.EMAIL_EXISTED_ERROR,
                    String.format("email: %s have registered already when Register.", registerDTO.getEmail()));
        }
        RegisterVO registerVO = new RegisterVO(user.getId(), date);
        return ResponseResult.success(registerVO);
    }

    @Override
    public ResponseResult<LoginVO> login(LoginDTO loginDTO) {
        if (ValidateUtil.emailFormatCheck(loginDTO.getEmail())) {
            throw new BizException(ErrorCode.EMAIL_FORMAT_ERROR,
                    String.format("email: %s format is wrong when login.", loginDTO.getEmail()));
        }
        if (ValidateUtil.passwordFormatCheck(loginDTO.getPassword())) {
            throw new BizException(ErrorCode.PASSWORD_FORMAT_ERROR,
                    String.format("password: %s format is wrong when login.", loginDTO.getPassword()));
        }
        User user = userMapper.selectUserByEmail(loginDTO.getEmail());
        if (user == null) {
            throw new BizException(ErrorCode.EMAIL_NOT_EXISTED_ERROR,
                    "the user does not existed.");
        }
        if (!EncryptUtil.encryptPassword(loginDTO.getPassword(), user.getSalt())
                .equals(user.getPassword())) {
            throw new BizException(ErrorCode.PASSWORD_WRONG_ERROR,
                    String.format("password: %s is wrong when login.", loginDTO.getPassword()));
        }
        if (!user.getUserType().equals(loginDTO.getType())) {
            throw new BizException(ErrorCode.PASSWORD_WRONG_ERROR,
                    "the user does not existed.");
        }
        return ResponseResult.success(new LoginVO(user));
    }

    @Override
    public ResponseResult<Void> updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        if (ValidateUtil.passwordFormatCheck(updatePasswordDTO.getOldPassword())) {
            throw new BizException(ErrorCode.OLD_PASSWORD_FORMAT_ERROR, String
                    .format("oldPassword: %s format wrong when updatePassword", updatePasswordDTO.getOldPassword()));
        }
        if (ValidateUtil.passwordFormatCheck(updatePasswordDTO.getNewPassword())) {
            throw new BizException(ErrorCode.NEW_PASSWORD_FORMAT_ERROR, String
                    .format("newPassword: %s format wrong when updatePassword", updatePasswordDTO.getNewPassword()));
        }
        User user = userMapper.selectUserByEmail(updatePasswordDTO.getEmail());
        if (!user.getPassword().equals(EncryptUtil
                .encryptPassword(updatePasswordDTO.getOldPassword(), user.getSalt()))) {
            throw new BizException(ErrorCode.OLD_PASSWORD_WRONG_ERROR, String
                    .format("oldPassword: %s is wrong when updatePassword.", updatePasswordDTO.getNewPassword()));
        }
        user.setPassword(EncryptUtil.encryptPassword(updatePasswordDTO.getNewPassword(), user.getSalt()));
        userMapper.updateUserByUserId(user);
        return ResponseResult.success();
    }
}

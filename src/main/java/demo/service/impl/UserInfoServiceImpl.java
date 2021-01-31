package demo.service.impl;

import demo.constant.ErrorCode;
import demo.dto.UpdateUserInfoDTO;
import demo.exception.BizException;
import demo.mapper.UserMapper;
import demo.model.User;
import demo.model.response.ResponseResult;
import demo.service.UserInfoService;
import demo.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author roger
 * @since 2021/1/15 上午11:25
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult<UserInfoVO> getUserInfo(String email) {
        User user = userMapper.selectUserByEmail(email);
        if (null == user) {
            throw new BizException(ErrorCode.USER_NOT_EXISTED,
                    "传入userId不存在");
        }
        UserInfoVO userInfoVO = new UserInfoVO(user);
        return ResponseResult.success(userInfoVO);
    }

    @Override
    public ResponseResult<Void> updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO, String email) {
        if (updateUserInfoDTO.getNickName() == null && updateUserInfoDTO.getAddress() == null && updateUserInfoDTO.getTelephone() == null) {
            throw new BizException(ErrorCode.NICKNAME_AND_ADDRESS_BOTH_NULL);
        }
        User user = new User();
        user.setEmail(email);
        if (updateUserInfoDTO.getAddress() != null && updateUserInfoDTO.getAddress().length() < 255) {
            user.setAddress(updateUserInfoDTO.getAddress());
        }
        if (updateUserInfoDTO.getNickName() != null && updateUserInfoDTO.getNickName().length() < 32) {
            user.setNickname(updateUserInfoDTO.getNickName());
        }
        if (updateUserInfoDTO.getTelephone() != null) {
            user.setTelephone(updateUserInfoDTO.getTelephone());
        }
        userMapper.updateUserByEmail(user);
        return ResponseResult.success();
    }
}

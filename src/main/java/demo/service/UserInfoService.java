package demo.service;


import demo.dto.UpdateUserInfoDTO;
import demo.model.response.ResponseResult;
import demo.vo.UserInfoVO;

public interface UserInfoService {
    ResponseResult<UserInfoVO> getUserInfo(String email);

    ResponseResult<Void> updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO, String email);
}
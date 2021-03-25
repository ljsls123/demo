package demo.service;

import demo.dto.LoginDTO;
import demo.dto.RegisterDTO;
import demo.dto.UpdatePasswordDTO;
import demo.model.response.ResponseResult;
import demo.vo.GetOrdersVO;
import demo.vo.LoginVO;
import demo.vo.RegisterVO;
import demo.vo.SearchItemsVO;

public interface UserAuthService {
    ResponseResult<RegisterVO> register(RegisterDTO registerDTO);

    ResponseResult<LoginVO> login(LoginDTO loginDTO);

    ResponseResult<Void> updatePassword(UpdatePasswordDTO updatePasswordDTO, String email);

    ResponseResult<SearchItemsVO> searchItems(int page, int limit);

    ResponseResult<SearchItemsVO> searchItemsByPrice(int page, int limit, String price);

    ResponseResult<SearchItemsVO> searchItemsByType(int page, int limit, String type);

    ResponseResult<Void> buy(int userId, int itemId);

    ResponseResult<GetOrdersVO> getOrders(int id);

    ResponseResult<Void> comment(int userId, int itemId, String detail);
}

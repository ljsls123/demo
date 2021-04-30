package demo.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.constant.ErrorCode;
import demo.dto.LoginDTO;
import demo.dto.RegisterDTO;
import demo.dto.UpdatePasswordDTO;
import demo.exception.BizException;
import demo.mapper.ItemMapper;
import demo.mapper.MenuMapper;
import demo.mapper.OrderedMapper;
import demo.mapper.RoleMenuMapper;
import demo.mapper.UserCommentMapper;
import demo.mapper.UserMapper;
import demo.mapper.UserRoleMapper;
import demo.model.Item;
import demo.model.Menu;
import demo.model.Ordered;
import demo.model.RoleMenu;
import demo.model.User;
import demo.model.UserComment;
import demo.model.UserRole;
import demo.model.response.ResponseResult;
import demo.service.UserAuthService;
import demo.util.EncryptUtil;
import demo.util.ValidateUtil;
import demo.vo.GetOrdersVO;
import demo.vo.LoginVO;
import demo.vo.RegisterVO;
import demo.vo.SearchItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrderedMapper orderedMapper;

    @Autowired
    private UserCommentMapper userCommentMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

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
        user.setUserName(registerDTO.getName());
        user.setGender(registerDTO.getGender());
        user.setTelephone(registerDTO.getTelephone());
        LocalDateTime date = LocalDateTime.now(ZoneOffset.UTC);
        user.setCreateAt(date);
        user.setUpdateAt(date);
        int userId = -1;
        try {
            userId = userMapper.insertUser(user);
        } catch (DuplicateKeyException e) {
            throw new BizException(ErrorCode.EMAIL_EXISTED_ERROR,
                    String.format("email: %s have registered already when Register.", registerDTO.getEmail()));
        }
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(Integer.valueOf(registerDTO.getType()));
        userRoleMapper.insert(userRole);
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
    public ResponseResult<Void> updatePassword(UpdatePasswordDTO updatePasswordDTO, String email) {
        if (ValidateUtil.passwordFormatCheck(updatePasswordDTO.getOldPassword())) {
            throw new BizException(ErrorCode.OLD_PASSWORD_FORMAT_ERROR, String
                    .format("oldPassword: %s format wrong when updatePassword", updatePasswordDTO.getOldPassword()));
        }
        if (ValidateUtil.passwordFormatCheck(updatePasswordDTO.getNewPassword())) {
            throw new BizException(ErrorCode.NEW_PASSWORD_FORMAT_ERROR, String
                    .format("newPassword: %s format wrong when updatePassword", updatePasswordDTO.getNewPassword()));
        }
        User user = userMapper.selectUserByEmail(email);
        if (!user.getPassword().equals(EncryptUtil
                .encryptPassword(updatePasswordDTO.getOldPassword(), user.getSalt()))) {
            throw new BizException(ErrorCode.OLD_PASSWORD_WRONG_ERROR, String
                    .format("oldPassword: %s is wrong when updatePassword.", updatePasswordDTO.getNewPassword()));
        }
        user.setPassword(EncryptUtil.encryptPassword(updatePasswordDTO.getNewPassword(), user.getSalt()));
        userMapper.updateUserByUserId(user);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<SearchItemsVO> searchItems(int page, int limit) {
        int totalPage = itemMapper.searchTotalPage() / 5 + 1;
        int start = (page - 1) * limit;
        List<Item> list = itemMapper.searchByPage(start, limit);
        List<SearchItemsVO.SearchItems> list1 = new ArrayList<>();
        for (Item item : list) {
            String nickName = userMapper.selectUserByUserId(item.getUserId()).getNickname();
            SearchItemsVO.SearchItems searchItems = new SearchItemsVO.SearchItems();
            searchItems.setItem(item);
            searchItems.setNickName(nickName);
            list1.add(searchItems);
        }
        SearchItemsVO searchItemsVO = new SearchItemsVO();
        searchItemsVO.setList(list1);
        searchItemsVO.setTotalPage(totalPage);
        return ResponseResult.success(searchItemsVO);
    }

    @Override
    public ResponseResult<SearchItemsVO> searchItemsByPrice(int page, int limit, String price) {
        int totalPage = itemMapper.searchTotalPageByPrice(price) / 5 + 1;
        int start = (page - 1) * limit;
        List<Item> list = itemMapper.searchByPageByPrice(start, limit, price);
        List<SearchItemsVO.SearchItems> list1 = new ArrayList<>();
        for (Item item : list) {
            String nickName = userMapper.selectUserByUserId(item.getUserId()).getNickname();
            SearchItemsVO.SearchItems searchItems = new SearchItemsVO.SearchItems();
            searchItems.setItem(item);
            searchItems.setNickName(nickName);
            list1.add(searchItems);
        }
        SearchItemsVO searchItemsVO = new SearchItemsVO();
        searchItemsVO.setList(list1);
        searchItemsVO.setTotalPage(totalPage);
        return ResponseResult.success(searchItemsVO);
    }

    @Override
    public ResponseResult<SearchItemsVO> searchItemsByType(int page, int limit, String type) {
        int totalPage = itemMapper.searchTotalPageByType(type) / 5 + 1;
        int start = (page - 1) * limit;
        List<Item> list = itemMapper.searchByPageByType(start, limit, type);
        List<SearchItemsVO.SearchItems> list1 = new ArrayList<>();
        for (Item item : list) {
            String nickName = userMapper.selectUserByUserId(item.getUserId()).getNickname();
            SearchItemsVO.SearchItems searchItems = new SearchItemsVO.SearchItems();
            searchItems.setItem(item);
            searchItems.setNickName(nickName);
            list1.add(searchItems);
        }
        SearchItemsVO searchItemsVO = new SearchItemsVO();
        searchItemsVO.setList(list1);
        searchItemsVO.setTotalPage(totalPage);
        return ResponseResult.success(searchItemsVO);
    }

    @Override
    public ResponseResult<Void> buy(int userId, int itemId) {
        Ordered ordered = new Ordered();
        ordered.setItemId(itemId);
        ordered.setUserId(userId);
        ordered.setOrderStatus("0");
        orderedMapper.insert(ordered);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<GetOrdersVO> getOrders(int id) {
        List<Ordered> list = orderedMapper.getUserOrders(id);
        GetOrdersVO getOrdersVO = new GetOrdersVO();
        for (Ordered ordered : list) {
            User user = userMapper.selectUserByUserId(ordered.getUserId());
            Item item = itemMapper.getById(ordered.getItemId());
            GetOrdersVO.OrderVO orderVO = new GetOrdersVO.OrderVO();
            orderVO.setOrdered(ordered);
            orderVO.setUser(user);
            orderVO.setItem(item);
            getOrdersVO.getList().add(orderVO);
        }
        return ResponseResult.success(getOrdersVO);
    }

    @Override
    public ResponseResult<Void> comment(int userId, int itemId, String detail) {
        UserComment userComment = new UserComment();
        userComment.setUserId(userId);
        userComment.setItemId(itemId);
        userComment.setDetail(detail);
        userCommentMapper.insert(userComment);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<List<Menu>> getMenu(int userId) {
        UserRole userRole = userRoleMapper.selectByUserId(userId);
        List<RoleMenu> parentMenu = roleMenuMapper.selectParentByRoleId(userRole.getRoleId());
        List<Menu> menuList = new ArrayList<>();
        Map<Integer, Menu> map = new HashMap<>();
        List<Menu> menus = new ArrayList<>();
        for (RoleMenu roleMenu : parentMenu) {
            Menu menu = menuMapper.selectByid(roleMenu.getMenuId());
            menuList.add(menu);
        }
        for (Menu menu : menuList) {
            if (menu.getPid() == 0) {
                map.put(menu.getId(), menu);
            }
        }
        for (Menu child : menuList) {
            if (child.getPid() != 0) {
                map.get(child.getPid()).getChild().add(child);
            }
        }
        menus = new ArrayList<>(map.values());
        return ResponseResult.success(menus);
    }
}

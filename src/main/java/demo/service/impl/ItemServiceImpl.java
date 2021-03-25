package demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import demo.mapper.ItemMapper;
import demo.mapper.UserCommentMapper;
import demo.mapper.UserMapper;
import demo.model.Item;
import demo.model.UserComment;
import demo.model.response.ResponseResult;
import demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private UserCommentMapper userCommentMapper;

    @Override
    public ResponseResult<Item> detail(int id) {
        Item item = itemMapper.getById(id);
        return ResponseResult.success(item);
    }

    @Override
    public List<UserComment> comment(int id) {
        List<UserComment> list = new ArrayList<>();
        if (userCommentMapper.getByItemId(id) != null) {
            list = userCommentMapper.getByItemId(id);
            for (UserComment userComment : list) {
                userComment.setNickName(userMapper.selectUserByUserId(userComment.getUserId()).getNickname());
            }
        }
        return list;
    }
}

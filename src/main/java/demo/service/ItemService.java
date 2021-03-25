package demo.service;

import java.util.List;

import demo.model.Item;
import demo.model.UserComment;
import demo.model.response.ResponseResult;

public interface ItemService {

    ResponseResult<Item> detail(int id);

    List<UserComment> comment(int id);
}

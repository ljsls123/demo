package demo.service;

import java.util.List;

import demo.dto.CreateItemDTO;
import demo.model.Item;
import demo.model.response.ResponseResult;

public interface WorkerService {

    ResponseResult<Void> createItem(CreateItemDTO createItemDTO, Integer userId, String img);

    ResponseResult<List<Item>> getItems(int page, int limit, int id);
}

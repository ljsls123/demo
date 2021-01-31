package demo.service.impl;

import demo.dto.CreateItemDTO;
import demo.mapper.ItemMapper;
import demo.model.Item;
import demo.model.response.ResponseResult;
import demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ResponseResult<Void> createItem(CreateItemDTO createItemDTO, Integer userId, String img) {
        Item item = new Item(createItemDTO, userId, img);
        itemMapper.insert(item);
        return ResponseResult.success();
    }
}

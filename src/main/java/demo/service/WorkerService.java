package demo.service;

import demo.dto.CreateItemDTO;
import demo.model.response.ResponseResult;

public interface WorkerService {

    ResponseResult<Void> createItem(CreateItemDTO createItemDTO, Integer userId, String img);
}

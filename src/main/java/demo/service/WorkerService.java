package demo.service;

import demo.dto.CreateItemDTO;
import demo.model.response.ResponseResult;
import demo.vo.GetItemVO;

public interface WorkerService {

    ResponseResult<Void> createItem(CreateItemDTO createItemDTO, Integer userId, String img);

    ResponseResult<GetItemVO> getItems(int page, int limit, int id);

    ResponseResult<Void> itemOnline(int id);

    ResponseResult<Void> itemOffline(int id);
}

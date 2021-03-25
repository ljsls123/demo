package demo.service.impl;

import java.util.List;

import demo.dto.CreateItemDTO;
import demo.mapper.ItemMapper;
import demo.mapper.OrderedMapper;
import demo.mapper.UserMapper;
import demo.model.Item;
import demo.model.Ordered;
import demo.model.User;
import demo.model.response.ResponseResult;
import demo.service.WorkerService;
import demo.vo.GetItemVO;
import demo.vo.GetOrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrderedMapper orderedMapper;

    @Override
    public ResponseResult<Void> createItem(CreateItemDTO createItemDTO, Integer userId, String img) {
        Item item = new Item();
        item.setUserId(userId);
        item.setTitle(createItemDTO.getTitle());
        item.setDescription(createItemDTO.getDescription());
        item.setType(createItemDTO.getType());
        item.setPrice(createItemDTO.getPrice());
        item.setImg(img);
        item.setOnline("1");
        itemMapper.insert(item);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<GetItemVO> getItems(int page, int limit, int id) {
        int totalPage = itemMapper.getTotalPage(id) / 5 + 1;
        int start = (page - 1) * limit;
        List<Item> list = itemMapper.selectByPage(start, limit, id);
        GetItemVO getItemVO = new GetItemVO();
        getItemVO.setList(list);
        getItemVO.setTotalPage(totalPage);
        return ResponseResult.success(getItemVO);
    }

    @Override
    public ResponseResult<Void> itemOnline(int id) {
        itemMapper.itemOnline(id);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Void> itemOffline(int id) {
        itemMapper.itemOffline(id);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<GetOrdersVO> getOrders(int id) {
        List<Integer> list = itemMapper.getIds(id);
        List<Ordered> list1 = orderedMapper.getOrders(list);
        GetOrdersVO getOrdersVO = new GetOrdersVO();
        for (Ordered ordered : list1) {
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
    public ResponseResult<Void> setOrders(int id, String status) {
        orderedMapper.setOrder(id, status);
        return null;
    }
}

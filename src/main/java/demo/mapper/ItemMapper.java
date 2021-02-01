package demo.mapper;

import java.util.List;

import demo.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ItemMapper {

    void insert(Item item);

    List<Item> selectByPage(int page, int num, int userId);
}

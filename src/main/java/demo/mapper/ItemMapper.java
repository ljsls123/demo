package demo.mapper;

import demo.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ItemMapper {

    void insert(Item item);
}

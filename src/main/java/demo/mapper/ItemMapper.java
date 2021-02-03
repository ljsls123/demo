package demo.mapper;

import java.util.List;

import demo.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ItemMapper {

    void insert(Item item);

    List<Item> selectByPage(@Param("page") int page, @Param("num") int num, @Param("userId") int userId);

    int getTotalPage(int userId);

    void itemOnline(int id);

    void itemOffline(int id);
}

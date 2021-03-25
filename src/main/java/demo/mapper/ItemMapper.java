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

    List<Item> searchByPage(@Param("page") int page, @Param("num") int num);

    List<Item> searchByPageByPrice(@Param("page") int page, @Param("num") int num, @Param("price") String price);

    List<Item> searchByPageByType(@Param("page") int page, @Param("num") int num, @Param("type") String type);

    int getTotalPage(int userId);

    int searchTotalPage();

    int searchTotalPageByPrice(String price);

    int searchTotalPageByType(String type);

    void itemOnline(int id);

    void itemOffline(int id);

    List<Integer> getIds(int id);

    Item getById(int id);
}

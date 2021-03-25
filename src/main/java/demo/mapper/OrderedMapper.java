package demo.mapper;

import java.util.List;

import demo.model.Ordered;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderedMapper {

    void insert(Ordered ordered);

    List<Ordered> getOrders(List<Integer> list);

    void setOrder(@Param("id") int id, @Param("status") String status);

    List<Ordered> getUserOrders(int id);

}

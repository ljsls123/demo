package demo.mapper;

import java.util.List;

import demo.model.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TypeMapper {

    void insert(Type type);

    void update(Type type);

    void delete(int id);

    List<Type> getByPage(int page);

    int getTotal();
}

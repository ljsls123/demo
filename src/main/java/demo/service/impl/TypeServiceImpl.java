package demo.service.impl;

import java.util.List;

import demo.mapper.TypeMapper;
import demo.model.Type;
import demo.model.response.ResponseResult;
import demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public ResponseResult<Void> createType(String name) {
        Type type = new Type();
        type.setName(name);
        typeMapper.insert(type);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Void> deleteType(int id) {
        typeMapper.delete(id);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Void> updateType(int id, String name) {
        Type type = new Type(id, name);
        typeMapper.update(type);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<List<Type>> getTypes(int page) {
        int num = (page - 1) * 5;
        List<Type> list = typeMapper.getByPage(num);
        return ResponseResult.success(list);
    }

    @Override
    public ResponseResult<Integer> getTotal() {
        return ResponseResult.success(typeMapper.getTotal());
    }

}

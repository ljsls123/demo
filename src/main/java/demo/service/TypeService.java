package demo.service;

import java.util.List;

import demo.model.Type;
import demo.model.response.ResponseResult;

public interface TypeService {

    ResponseResult<Void> createType(String name);

    ResponseResult<Void> deleteType(int id);

    ResponseResult<Void> updateType(int id, String name);

    ResponseResult<List<Type>> getTypes(int page);

    ResponseResult<Integer> getTotal();
}

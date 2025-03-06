package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    void updateStatus(Long id, Integer status);

    void deleteByMealId(Long setmealId);

    void insertBatch(List<SetmealDish> setmealDishes);

    List<SetmealDish> getBySetmealId(Long id);
}

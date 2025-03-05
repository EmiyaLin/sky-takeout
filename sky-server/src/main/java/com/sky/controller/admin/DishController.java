package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @ApiOperation("新增菜品")
    @PostMapping("")
    public Result addDish(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @ApiOperation("菜品分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO pageQueryDTO) {
        log.info("菜品分页查询：{}", pageQueryDTO);
        PageResult pageResult = dishService.pageQuery(pageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping("")
    @ApiOperation("批量删除菜品")
    public Result delete(@RequestParam List<Long> ids) {
        dishService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getDishById(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVo = dishService.getByIdWithFlavor(id);
        return Result.success(dishVo);
    }

    @PutMapping("")
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        dishService.update(dishDTO);
        return Result.success();
    }
}

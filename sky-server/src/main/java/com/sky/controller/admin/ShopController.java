package com.sky.controller.admin;

import com.sky.constant.StatusConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    public static final String key = "SHOP_STATUS";

    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(key);
        if (status != null) {
            log.info("获取到店铺的营业状态为: {}", status.equals(1) ? "营业中" : "打烊中");
        } else {
            throw new RuntimeException("获取状态失败");
        }
        return Result.success(status);
    }

    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店铺的营业状态为: {}", status.equals(1) ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set(key, status);
        return Result.success();
    }
}

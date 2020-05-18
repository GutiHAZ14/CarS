package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 卖车接口
     * @param carSeries
     * @return
     */
    @GetMapping("buyCar/{carSeries}")
    public JSONResult buyCar(@PathVariable String carSeries){
        List<Car> cars = carService.findBySeries(carSeries);
        if(cars == null ||cars.size() == 0){
            return JSONResult.errorMsg( carSeries + " has already sold out.");
        }
        Car c = cars.get(0);
        return this.deleteById(c.getId());
    }

    /**
     * 模糊查询某品牌
     * 返回最后5条数据
     * @param keyword
     * @return
     */
    @GetMapping("search/{keyword}")
    public JSONResult search(@PathVariable String keyword){
        List<Car> cars = carService.search(keyword);
        List<Car> res =new ArrayList<>();
        //
        for (int i = cars.size() - 1; i >= 0 && i >= cars.size() - 5 ; i--) {
            res.add(cars.get(i));
        }
        return JSONResult.ok(res);
    }


    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }


}

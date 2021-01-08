package com.mayko.ewhaplate.api;

import com.mayko.ewhaplate.dto.request.FoodRandomRequestDto;
import com.mayko.ewhaplate.dto.request.FoodRequestDto;
import com.mayko.ewhaplate.dto.response.ExceptionDto;
import com.mayko.ewhaplate.dto.response.SuccessDto;
import com.mayko.ewhaplate.entity.Food;
import com.mayko.ewhaplate.repository.FoodRepository;
import com.mayko.ewhaplate.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;

    // 추천 맛집 등록
    @PostMapping("/register")
    public SuccessDto register(@RequestBody FoodRequestDto requestDto){
        foodService.register(new Food(requestDto));
        return new SuccessDto(true);
    }

    // 카테고리 포함 x, 이화 장소가 ewhaType인 맛집 중 랜덤으로 뽑기
    @PostMapping("/random")
    public Food getRandomFood(@RequestBody FoodRandomRequestDto requestDto) {
        return foodService.getRandomFood(requestDto);
    }

    // 카테고리 포함 x, 이화 장소가 ewhaType인 맛집 리스트
    @PostMapping("/list")
    public List<Food> getList(@RequestBody FoodRandomRequestDto requestDto){
        List<Food> list = foodService.getFoodList(requestDto);
        if(list.size() == 0) throw new IllegalArgumentException("해당 맛집이 없습니다");
        else return list;
    }

    // 이화 주변 맛집 전체 리스트 GET
    @GetMapping("/list/all")
    public List<Food> getAllList(){
        return foodRepository.findAll();
    }

}

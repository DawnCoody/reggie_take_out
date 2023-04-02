package com.dawncoody.reggie.dto;

import com.dawncoody.reggie.entity.Setmeal;
import com.dawncoody.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}

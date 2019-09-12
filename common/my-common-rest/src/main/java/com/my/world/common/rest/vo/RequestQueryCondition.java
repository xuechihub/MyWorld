package com.my.world.common.rest.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: MyWorld
 * @description: 查询条件
 * @author: xue chi
 * @create: 2019-08-19 13:50
 **/
@NoArgsConstructor
@Data
public class RequestQueryCondition implements Result {

    private static final long serialVersionUID = -8122547131251822172L;

    private Integer pageSize;

    private Integer currentPage;

    private List<SearchCondition> searchConditions;

    private List<SortCondition> sortConditions;

}

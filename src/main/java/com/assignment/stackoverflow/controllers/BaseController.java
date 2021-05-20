package com.assignment.stackoverflow.controllers;

import com.assignment.stackoverflow.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    //
    public Pageable getPaginationAndSortingParams(HttpServletRequest request) {
        String pageParam = request.getParameter("page");
        String sizeParam = request.getParameter("size");
        String sortParam = request.getParameter("sort");

        int page = !StringUtils.isBlank(pageParam) ? (Integer.parseInt(pageParam.trim())-1) : 0;
        int size = !StringUtils.isBlank(sizeParam) ? Integer.parseInt(sizeParam.trim()) : 10;

        Sort sort = fetchOrSetDefaultSort(sortParam);

        return PageRequest.of(page, size, sort);
    }

    public Map<String, Object> getParams(Map<String, Object> params){
        params.remove("page");
        params.remove("size");
        params.remove("sort");
        return params;
    }


    public Map<String, Object> getPaginatedContent(Page pagedContent){
        Map<String, Object> mapper = new HashMap<>();
        Object content = null;
        if(pagedContent.hasContent()) {
            content =pagedContent.getContent();
        } else {
            content =new ArrayList<Object>();
        }

        mapper.put("content",content);
        mapper.put("currentPage", pagedContent.getNumber()+1);
        mapper.put("totalItems", pagedContent.getTotalElements());
        mapper.put("totalPages", pagedContent.getTotalPages());

        return mapper;
    }

    private Sort fetchOrSetDefaultSort(String sortParam) {
        String[] sortParams = StringUtils.isBlank(sortParam) ? " ".split(",") : sortParam.split(",");
        String sortByParam = sortParams.length>0 ? sortParams[0].trim() : "id";
        String orderParam = sortParams.length>1 ? sortParams[1].trim() : "DESC";

        String sortBy = !StringUtils.isBlank(sortByParam) ? sortByParam : "id";
        String order = !StringUtils.isBlank(orderParam) ? orderParam : "DESC";

        return (order.toLowerCase().equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
    }
}

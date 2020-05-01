package ru.fedorov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PTConntroller {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    private List<Map<String, Object>> aggregate(
            @RequestParam("col") String col,
            @RequestParam("row") String row) {
        ArrayList<String> allowedParams = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "y"));
        if (!allowedParams.contains(row) || !allowedParams.contains(col)) return null;
        String sql = "select "+row+" as 'row', "+col+" as 'col', sum(v) as 'val' from source_data group by "+row+", "+col+";";
        return jdbcTemplate.queryForList(sql);
    }
}

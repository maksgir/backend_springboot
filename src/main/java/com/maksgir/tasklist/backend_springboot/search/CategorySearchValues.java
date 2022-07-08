package com.maksgir.tasklist.backend_springboot.search;


// возможные значения, по которым можно искать категории

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CategorySearchValues {
    private String text;
}

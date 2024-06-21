package org.qa.utils.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResourceData {

    public Integer id;
    public String name;
    public Integer year;
    public String color;
    public String pantone_value;
}

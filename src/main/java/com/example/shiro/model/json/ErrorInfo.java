package com.example.shiro.model.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求错误信息
 * @author fengqian
 * @since <pre>2018/06/27</pre>
 */
@AllArgsConstructor
@Getter
public class ErrorInfo {
    private int state;
    private String info;
    private String code;
}

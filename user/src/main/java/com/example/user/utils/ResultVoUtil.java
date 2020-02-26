package com.example.user.utils;

import com.example.user.enums.ResultEnum;
import com.example.user.vo.ResultVo;

public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success(){
        ResultVo r = new ResultVo();
        r.setCode(0);
        r.setMsg("成功");
        return r;
    }

    public static ResultVo error(ResultEnum resultEnum){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(resultEnum.getCode());
        resultVo.setMsg(resultEnum.getMessage());
        return resultVo;
    }
}

package com.umc.common.exception.handler;

import com.umc.common.exception.GeneralException;
import com.umc.common.response.BaseErrorCode;

public class PostHandler extends GeneralException {
    public PostHandler(BaseErrorCode code) {
        super(code);
    }
}

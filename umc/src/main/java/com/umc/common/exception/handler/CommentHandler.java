package com.umc.common.exception.handler;

import com.umc.common.exception.GeneralException;
import com.umc.common.response.BaseErrorCode;

public class CommentHandler extends GeneralException {
    public CommentHandler(BaseErrorCode code) {
        super(code);
    }
}

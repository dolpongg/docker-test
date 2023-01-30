package com.ssafy.api.service;

import com.ssafy.api.request.BookmarkRegisterReq;

/**
 *	북마크 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface BookmarkService {
    void create(BookmarkRegisterReq requestInfo);
    void delete(String email, Long lessonId);
}

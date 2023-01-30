package com.ssafy.api.service;

import com.ssafy.api.request.NoticeRegisterPostReq;
import com.ssafy.api.request.NoticeUpdatePutReq;
import com.ssafy.db.entity.board.Notice;
import com.ssafy.db.entity.user.User;
import com.ssafy.db.entity.user.UserRole;
import com.ssafy.db.repository.NoticeRepositorySupport;
import com.ssafy.db.repository.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("noticeService")
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepositorySupport noticeRepositorySupport;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Override
    public void createNotice(NoticeRegisterPostReq noticeRegisterPostReq) throws Exception {

        User user = userRepositorySupport
                .findUserByAuth(noticeRegisterPostReq.getEmail())
                .get();

        if(user.getRole().equals(UserRole.ROLE_ADMIN)) {
            Notice notice = Notice.builder()
                    .title(noticeRegisterPostReq.getTitle())
                    .content(noticeRegisterPostReq.getContent())
                    .img(noticeRegisterPostReq.getImg())
                    .regtime(LocalDateTime.now().toString())
                    .user(user)
                    .build();

            noticeRepositorySupport.save(notice);
            return;
        } else {
            throw new Exception("공지사항을 설정할 권한이 없습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Notice readNotice(Long id) {
        Notice notice = noticeRepositorySupport.findOne(id);
        return notice;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Notice> readNoticeList(int offset, int limit) {
        List<Notice> noticeList = noticeRepositorySupport.findList(offset, limit);
        return noticeList;
    }

    @Override
    @Transactional(readOnly = true)
    public Long noticeCount() {
        return noticeRepositorySupport.noticeCount();
    }

    @Override
    public void updateNotice(NoticeUpdatePutReq noticeUpdatePutReq) throws Exception {

        User user = userRepositorySupport
                .findUserByAuth(noticeUpdatePutReq.getEmail())
                        .get();
        if(user.getRole().equals(UserRole.ROLE_ADMIN)){
            noticeRepositorySupport.updateNotice(noticeUpdatePutReq);
        } else {
            throw new Exception("공지사항을 수정할 권한이 없습니다.");
        }
    }

    @Override
    public void deleteNotice(String email, Long id) throws Exception {
        Notice notice = noticeRepositorySupport.findOne(id);

        User user = userRepositorySupport
                .findUserByAuth(email)
                .get();

        if(user.getRole().equals(UserRole.ROLE_ADMIN)) {

            noticeRepositorySupport.delete(notice);
            return;
        } else {
            throw new Exception("공지사항을 삭제할 권한이 없습니다.");
        }

    }
}

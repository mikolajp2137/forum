package pl.mikolajp.forum.reply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface IReplyRepository extends JpaRepository<ReplyDao, Integer> {
    List<ReplyDao> findByThreadId(Integer id);
    void deleteAllByThreadId(Integer id);
}
